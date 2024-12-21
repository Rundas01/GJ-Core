package gregsjourney.common.metatileentities.singleblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregsjourney.api.metatileentity.part.FilteredItemStackHandler;
import gregsjourney.api.metatileentity.part.IItemCondition;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.items.itemhandlers.GTItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import static gregsjourney.common.metatileentities.part.OreDictItemBus.allowedSymbols;
import static gregtech.api.capability.GregtechDataCodes.UPDATE_AUTO_OUTPUT_ITEMS;
import static gregtech.common.metatileentities.storage.MetaTileEntityCreativeEnergy.getTextFieldValidator;

public class MetaTileEntityOredictFilteredStacksizeBuffer extends MetaTileEntity {
    private int mode, size;
    private String oredictString;
    private boolean autoOutput;
    private EnumFacing outputFacing;
    private final IItemCondition condition;

    public MetaTileEntityOredictFilteredStacksizeBuffer(ResourceLocation metaTileEntityId, IItemCondition condition) {
        super(metaTileEntityId);
        this.condition = condition;
        initializeInventory();
    }

    public MetaTileEntityOredictFilteredStacksizeBuffer(ResourceLocation metaTileEntityId) {
        this(metaTileEntityId, is -> false);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityOredictFilteredStacksizeBuffer(metaTileEntityId, this.condition);
    }

    @Override
    protected void initializeInventory() {
        importItems = new FilteredItemStackHandler(this, 9, this.condition);
        exportItems = new GTItemStackHandler(this, 9);
        itemInventory = new ItemHandlerProxy(importItems, exportItems);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        CycleButtonWidget cbWidget = new CycleButtonWidget(116, 89, 50, 18, new String[]{"Exact", "At Least", "At Most"}, () -> mode, value -> mode = value);
        ImageWidget imWidget = new ImageWidget(7, 14, 158, 16, GuiTextures.DISPLAY);
        ImageWidget imWidget2 = new ImageWidget(7, 30, 158, 16, GuiTextures.DISPLAY);
        TextFieldWidget2 tfWidget = new TextFieldWidget2(9, 19, 176, 16, () -> String.valueOf(size), value -> size = Integer.parseInt(value)).setAllowedChars(TextFieldWidget2.WHOLE_NUMS).setValidator(getTextFieldValidator());
        TextFieldWidget2 tfWidget2 = new TextFieldWidget2(9, 35, 176, 16, () -> oredictString, value -> oredictString = value).setAllowedChars(allowedSymbols);
        ToggleButtonWidget tbWidget = new ToggleButtonWidget(7, 90, 18, 18, GuiTextures.BUTTON_ITEM_OUTPUT, this::isAutoOutput, this::setAutoOutput).setTooltipText("gregtech.gui.item_auto_output.tooltip").shouldUseBaseBackground();
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 194).label(10, 5, getMetaFullName());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.widget(new SlotWidget(importItems, i * 3 + j, 7 + j * 18, 18 * (1 + i) + 16, true, true).setBackgroundTexture(GuiTextures.SLOT));
                builder.widget(new SlotWidget(exportItems, i * 3 + j, 111 + j * 18, 18 * (1 + i) + 16, true, false).setBackgroundTexture(GuiTextures.SLOT));
            }
        }
        builder.widget(imWidget);
        builder.widget(tfWidget);
        builder.widget(imWidget2);
        builder.widget(tfWidget2);
        builder.widget(cbWidget);
        builder.widget(tbWidget);
        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 112).build(getHolder(), entityPlayer);
    }

    public boolean isAutoOutput() {
        return autoOutput;
    }

    public void setAutoOutput(boolean autoOutputItems) {
        this.autoOutput = autoOutputItems;
        if (!getWorld().isRemote) {
            writeCustomData(UPDATE_AUTO_OUTPUT_ITEMS, buf -> buf.writeBoolean(autoOutputItems));
            markDirty();
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.VOLTAGE_CASINGS[1].render(renderState, translation, ArrayUtils.add(pipeline,
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

    public EnumFacing getOutputFacing() {
        return outputFacing == null ? EnumFacing.SOUTH : outputFacing;
    }

    @Override
    public boolean hasFrontFacing() {
        return true;
    }

    @Override
    public void update() {
        super.update();
        if ((getOffsetTimer() % 20 == 0) && !getWorld().isRemote) {
            checkMoveItems();
            if (isAutoOutput()) {
                pushItemsIntoNearbyHandlers(getOutputFacing());
            }
        }
    }

    private void checkMoveItems() {
        for (int i = 0; i < 9; i++) {
            if (areItemsMovable(importItems, exportItems, i)) {
                moveItems(importItems, exportItems, i);
            }
        }
    }

    //TODO: oredict einbinden

    private boolean areItemsMovable(IItemHandlerModifiable input, IItemHandlerModifiable output, int i) {
        if (input.getStackInSlot(i).isEmpty()) {
            return false; // No items in input slot
        }
        if (!output.getStackInSlot(i).isEmpty()
                && input.getStackInSlot(i).getItem() != output.getStackInSlot(i).getItem()) {
            return false; // Items in slots don't match
        }

        return switch (mode) {
            case 0 -> {
                int transferable = Math.min(size, input.getStackInSlot(i).getCount());
                yield transferable >= size && getFreeSpace(output, i) >= transferable;
            }
            case 1 -> {
                int transferable = Math.max(size, Math.min(input.getStackInSlot(i).getCount(), getFreeSpace(output, i)));
                yield transferable >= size && getFreeSpace(output, i) >= transferable;
            }
            case 2 -> {
                int transferable = Math.min(size, Math.min(input.getStackInSlot(i).getCount(), getFreeSpace(output, i)));
                yield transferable > 0 && getFreeSpace(output, i) >= transferable;
            }
            default -> false;
        };
    }

    private void moveItems(IItemHandlerModifiable input, IItemHandlerModifiable output, int i) {
        switch (mode) {
            case 0 -> extractAndInsert(input, output, i, size);
            case 1 -> extractAndInsert(input, output, i, Math.max(size, getFreeSpace(output, i)));
            case 2 -> extractAndInsert(input, output, i, Math.min(size, getFreeSpace(output, i)));
        }
    }

    private void extractAndInsert(IItemHandlerModifiable input, IItemHandlerModifiable output, int i, int amount) {
        int transferableAmount = Math.min(amount, input.getStackInSlot(i).getCount()); // Ensure we only transfer available items
        transferableAmount = Math.min(transferableAmount, getFreeSpace(output, i)); // Also account for available space in the output

        output.insertItem(i, new ItemStack(input.getStackInSlot(i).getItem(), transferableAmount), false);
        input.extractItem(i, transferableAmount, false);
    }

    private int getFreeSpace(IItemHandlerModifiable handler, int i) {
        return handler.getSlotLimit(i) - handler.getStackInSlot(i).getCount();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setTag("InputInventory", ((FilteredItemStackHandler)importItems).serializeNBT());
        data.setTag("OutputInventory", ((GTItemStackHandler)exportItems).serializeNBT());
        data.setInteger("OutputFacing", getOutputFacing().getIndex());
        data.setBoolean("AutoOutput", autoOutput);
        data.setInteger("mode", mode);
        data.setString("oredictstring", oredictString);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        oredictString = data.getString("oredictstring");
        mode = data.getInteger("mode");
        this.autoOutput = data.getBoolean("AutoOutput");
        this.outputFacing = EnumFacing.VALUES[data.getInteger("OutputFacing")];
        ((GTItemStackHandler)this.exportItems).deserializeNBT(data.getCompoundTag("OutputInventory"));
        ((FilteredItemStackHandler)this.importItems).deserializeNBT(data.getCompoundTag("InputInventory"));
    }

    @Override
    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeString(oredictString);
        buf.writeInt(oredictString.length());
        buf.writeInt(mode);
    }

    @Override
    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        mode = buf.readInt();
        oredictString = buf.readString(buf.readInt());
    }
}
