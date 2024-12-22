package gregsjourney.common.metatileentities.singleblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidHandlerProxy;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.capability.GregtechDataCodes.UPDATE_AUTO_OUTPUT_FLUIDS;
import static gregtech.common.metatileentities.storage.MetaTileEntityCreativeEnergy.getTextFieldValidator;

public class MetaTileEntityStacksizeFluidBuffer extends TieredMetaTileEntity {
    private int mode;
    private int size;
    private final int tier;
    private boolean autoOutput;
    private EnumFacing outputFacing;
    private FluidTankList inputs, outputs;

    public MetaTileEntityStacksizeFluidBuffer(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.tier = tier;
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityStacksizeFluidBuffer(metaTileEntityId, tier);
    }

    @Override
    protected void initializeInventory() {
        FluidTank[] inputFluidHandlers = new FluidTank[9], outputFluidHandlers = new FluidTank[9];
        for (int i = 0; i < 9; i++) {
            inputFluidHandlers[i] = new FluidTank(2000);
            outputFluidHandlers[i] = new FluidTank(2000);
        }
        inputs = new FluidTankList(false, inputFluidHandlers);
        outputs = new FluidTankList(false, outputFluidHandlers);
        fluidInventory = new FluidHandlerProxy(inputs, outputs);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        CycleButtonWidget cbWidget = new CycleButtonWidget(116, 89, 50, 18, new String[]{"Exact", "At Least", "At Most"}, () -> mode, value -> mode = value);
        ImageWidget imWidget = new ImageWidget(7, 14, 158, 16, GuiTextures.DISPLAY);
        TextFieldWidget2 tfWidget = new TextFieldWidget2(9, 19, 176, 16, () -> String.valueOf(size), value -> size = Integer.parseInt(value)).setAllowedChars(TextFieldWidget2.WHOLE_NUMS).setValidator(getTextFieldValidator());
        ToggleButtonWidget tbWidget = new ToggleButtonWidget(7, 90, 18, 18, GuiTextures.BUTTON_FLUID_OUTPUT, this::isAutoOutput, this::setAutoOutput).setTooltipText("gregtech.gui.fluid_auto_output.tooltip").shouldUseBaseBackground();
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 194).label(10, 5, getMetaFullName());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.widget(new TankWidget(inputs.getTankAt(i * 3 + j), 7 + j * 18, 18 * (1 + i) + 16, 18, 18).setBackgroundTexture(GuiTextures.FLUID_SLOT).setAlwaysShowFull(true).setContainerClicking(true, true));
                builder.widget(new TankWidget(outputs.getTankAt(i * 3 + j), 111 + j * 18, 18 * (1 + i) + 16, 18,18).setBackgroundTexture(GuiTextures.FLUID_SLOT).setAlwaysShowFull(true).setContainerClicking(true, false));
            }
        }
        builder.widget(imWidget);
        builder.widget(tfWidget);
        builder.widget(cbWidget);
        builder.widget(tbWidget);
        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 112).build(getHolder(), entityPlayer);
    }

    public boolean isAutoOutput() {
        return autoOutput;
    }

    public void setAutoOutput(boolean autoOutputFluids) {
        this.autoOutput = autoOutputFluids;
        if (!getWorld().isRemote) {
            writeCustomData(UPDATE_AUTO_OUTPUT_FLUIDS, buf -> buf.writeBoolean(autoOutputFluids));
            markDirty();
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.VOLTAGE_CASINGS[tier].render(renderState, translation, ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()))));
        for (EnumFacing facing : EnumFacing.VALUES) {
            if (facing == getFrontFacing()) {
                SimpleOverlayRenderer renderer = Textures.PIPE_OUT_OVERLAY;
                renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
            } else {
                Textures.BUFFER_OVERLAY.renderSided(facing, renderState, translation, pipeline);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(Textures.VOLTAGE_CASINGS[tier].getParticleSprite(), this.getPaintingColorForRendering());
    }

    public EnumFacing getOutputFacing() { return outputFacing == null ? EnumFacing.SOUTH : outputFacing; }

    @Override
    public boolean hasFrontFacing() { return true; }

    @Override
    public void update() {
        super.update();
        if ((getOffsetTimer() % 20 == 0) && size > 0 && !getWorld().isRemote) {
            checkMoveFluids();
            if (isAutoOutput()) {
                pushFluidsIntoNearbyHandlers(getOutputFacing());
            }
        }
    }

    private void checkMoveFluids() {
        for (int i = 0; i < 9; i++) {
            if (areFluidsMovable(inputs, outputs, i)) {
                moveFluids(inputs, outputs, i);
            }
        }
    }

    private boolean areFluidsMovable(FluidTankList input, FluidTankList output, int i) {
        if (input.getTankAt(i).getFluidAmount() == 0) {
            return false; // No fluid in input slot
        }
        if (input.getTankAt(i).getFluidAmount() > 0 && output.getTankAt(i).getFluidAmount() > 0 && input.getTankAt(i).getFluid().getFluid() != output.getTankAt(i).getFluid().getFluid()) {
            return false; // Fluids in slots don't match
        }

        return switch (mode) {
            case 0 -> {
                int transferable = Math.min(size, input.getTankAt(i).getFluidAmount());
                yield transferable >= size && getFreeSpace(output, i) >= transferable;
            }
            case 1 -> {
                int transferable = Math.max(size, Math.min(input.getTankAt(i).getFluidAmount(), getFreeSpace(output, i)));
                yield transferable >= size && getFreeSpace(output, i) >= transferable;
            }
            case 2 -> {
                int transferable = Math.min(size, Math.min(input.getTankAt(i).getFluidAmount(), getFreeSpace(output, i)));
                yield transferable > 0 && getFreeSpace(output, i) >= transferable;
            }
            default -> false;
        };
    }

    private void moveFluids(FluidTankList input, FluidTankList output, int i) {
        switch (mode) {
            case 0 -> extractAndInsert(input, output, i, size);
            case 1 -> extractAndInsert(input, output, i, Math.max(size, getFreeSpace(output, i)));
            case 2 -> extractAndInsert(input, output, i, Math.min(size, getFreeSpace(output, i)));
        }
    }

    private void extractAndInsert(FluidTankList input, FluidTankList output, int i, int amount) {
        int transferableAmount = Math.min(amount, input.getTankAt(i).getFluidAmount()); // Ensure we only transfer available fluids
        transferableAmount = Math.min(transferableAmount, getFreeSpace(output, i)); // Also account for available space in the output

        FluidStack drainingStack = new FluidStack(input.getTankAt(i).getFluid().getFluid(), transferableAmount);
        output.fill(drainingStack, true);
        input.drain(drainingStack, true);
    }

    private int getFreeSpace(FluidTankList handler, int i) {
        return handler.getTankAt(i).getCapacity() - handler.getTankAt(i).getFluidAmount();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setTag("InputInventory", inputs.serializeNBT());
        data.setTag("OutputInventory", outputs.serializeNBT());
        data.setInteger("OutputFacing", getOutputFacing().getIndex());
        data.setBoolean("AutoOutput", autoOutput);
        data.setInteger("size", size);
        data.setInteger("mode", mode);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        mode = data.getInteger("mode");
        size = data.getInteger("size");
        autoOutput = data.getBoolean("AutoOutput");
        outputFacing = EnumFacing.VALUES[data.getInteger("OutputFacing")];
        outputs.deserializeNBT(data.getCompoundTag("OutputInventory"));
        inputs.deserializeNBT(data.getCompoundTag("InputInventory"));
    }

    @Override
    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(size);
        buf.writeInt(mode);
    }

    @Override
    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        mode = buf.readInt();
        size = buf.readInt();
    }
}
