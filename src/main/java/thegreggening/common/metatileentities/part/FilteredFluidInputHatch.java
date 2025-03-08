package thegreggening.common.metatileentities.part;

import static gregtech.api.GTValues.UHV;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import thegreggening.api.metatileentity.part.FilteredFluidTank;
import thegreggening.api.metatileentity.part.IFluidCondition;

public class FilteredFluidInputHatch extends MetaTileEntityMultiblockNotifiablePart
                                     implements IMultiblockAbilityPart<IFluidTank> {

    private final FluidTankList fluidTankList;
    private final IFluidCondition condition;
    private static final int BASE_TANK_SIZE = 8000;
    private final int numSlots;

    public FilteredFluidInputHatch(ResourceLocation metaTileEntityId, int tier, int numSlots,
                                   IFluidCondition condition) {
        super(metaTileEntityId, tier, false);
        this.numSlots = numSlots;
        int tankSize = (BASE_TANK_SIZE * (1 << Math.min(UHV, tier))) / (numSlots == 4 ? 4 : 8);
        this.condition = condition;
        FilteredFluidTank[] fluidsHandlers = new FilteredFluidTank[numSlots];
        for (int i = 0; i < fluidsHandlers.length; i++) {
            fluidsHandlers[i] = new FilteredFluidTank(tankSize, this, false, condition);
        }
        this.fluidTankList = new FluidTankList(false, fluidsHandlers);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new FilteredFluidInputHatch(metaTileEntityId, getTier(), numSlots, this.condition);
    }

    @Override
    protected void initializeInventory() {
        if (this.fluidTankList == null) return;
        super.initializeInventory();
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return fluidTankList;
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            pullFluidsFromNearbyHandlers(getFrontFacing());
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        int rowSize = (int) Math.sqrt(numSlots);
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176,
                18 + 18 * rowSize + 94)
                .label(10, 5, getMetaFullName());

        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int index = y * rowSize + x;
                builder.widget(
                        new TankWidget(fluidTankList.getTankAt(index), 89 - rowSize * 9 + x * 18, 18 + y * 18, 18, 18)
                                .setBackgroundTexture(GuiTextures.FLUID_SLOT)
                                .setContainerClicking(true, true)
                                .setAlwaysShowFull(true));
            }
        }
        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 18 + 18 * rowSize + 12);
        return builder.build(getHolder(), entityPlayer);
    }

    @Override
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.addAll(fluidTankList.getFluidTanks());
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return MultiblockAbility.IMPORT_FLUIDS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer = Textures.PIPE_IN_OVERLAY;
            renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
            SimpleOverlayRenderer overlay = Textures.FLUID_HATCH_INPUT_OVERLAY;
            overlay.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }
}
