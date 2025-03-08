package thegreggening.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidHandlerProxy;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.metatileentity.part.ReactorCoolerFluidTank;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import java.util.Arrays;
import java.util.List;

public class MetaTileEntityReactorCooler extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IFluidTank> {

    private ReactorCoolerFluidTank inputTank;
    private NotifiableFluidTank outputTank;
    private MetaTileEntityNuclearReactor controller;
    private FluidTankList tanks;

    public MetaTileEntityReactorCooler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityReactorCooler(metaTileEntityId);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        inputTank = new ReactorCoolerFluidTank(this, this.controller);
        outputTank = new NotifiableFluidTank(1000, this, true);
        fluidInventory = new FluidHandlerProxy(inputTank, outputTank);
        tanks = new FluidTankList(false, inputTank, outputTank);
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return true;
    }

    @Override
    public FluidTankList getImportFluids() {
        return new FluidTankList(false, inputTank);
    }

    @Override
    public FluidTankList getExportFluids() {
        return new FluidTankList(false, outputTank);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 133);
        builder.widget(new LabelWidget(6, 6, getMetaFullName()));
        builder.widget(new TankWidget(inputTank, 61, 20, 18, 18).setContainerClicking(true, true).setAlwaysShowFull(true).setBackgroundTexture(GuiTextures.FLUID_SLOT));
        builder.widget(new TankWidget(outputTank, 97, 20, 18, 18).setContainerClicking(true, false).setAlwaysShowFull(true).setBackgroundTexture(GuiTextures.FLUID_SLOT));
        return builder.bindPlayerInventory(entityPlayer.inventory, 50).build(getHolder(), entityPlayer);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.VOLTAGE_CASINGS[1].render(renderState, translation, ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()))));
        for (EnumFacing facing : EnumFacing.VALUES) {
            Textures.BUFFER_OVERLAY.renderSided(facing, renderState, translation, pipeline);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(Textures.VOLTAGE_CASINGS[1].getParticleSprite(), this.getPaintingColorForRendering());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setTag("Fluid Inventory", tanks.serializeNBT());
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tanks.deserializeNBT(tag.getCompoundTag("Fluid Inventory"));
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return TGMultiblockAbility.COOLANT_CELL;
    }

    @Override
    public void registerAbilities(List<IFluidTank> list) {
        list.addAll(Arrays.asList(inputTank, outputTank));
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
        this.inputTank.setController(controller);
    }
}
