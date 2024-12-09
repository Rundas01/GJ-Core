package gregsjourney.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.metatileentity.part.FilteredFluidInputHatch;
import gregsjourney.api.metatileentity.part.FilteredFluidTank;
import gregsjourney.common.metatileentities.multiblock.primitive.MetaTileEntityCrucible;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static gregtech.api.unification.material.Materials.Lava;

public class MetaTileEntityHeatHatch extends FilteredFluidInputHatch {

    public MetaTileEntityCrucible controller;
    private final HeatHatchFluidTank fluidTank;

    public MetaTileEntityHeatHatch(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1, 1, MetaTileEntityHeatHatch::isHotEnough);
        fluidTank = new HeatHatchFluidTank(1000, this);
        initializeInventory();
    }

    private static boolean isHotEnough(FluidStack fs) {
        return fs.getFluid().getTemperature() >= Lava.getFluid().getTemperature();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHeatHatch(metaTileEntityId);
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("gj.machine.heat_hatch.info", Integer.toString(1000)));
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

    @Override
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.add(fluidTank);
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return GJMultiblockAbility.HEAT_HATCH;
    }

    public void setController(MetaTileEntityCrucible controller) {
        this.controller = controller;
    }

    private class HeatHatchFluidTank extends FilteredFluidTank {

        public HeatHatchFluidTank(int capacity, MetaTileEntity tileEntity) {
            super(capacity, tileEntity, false, fs -> fs.getFluid().getTemperature() >= Lava.getFluid().getTemperature());
        }

        @Override
        public void onContentsChanged() {
            super.onContentsChanged();
            if (MetaTileEntityHeatHatch.this.controller == null) {
                return;
            }
            MetaTileEntityHeatHatch.this.controller.setSmeltingModifier(MetaTileEntityHeatHatch.this.controller);
        }
    }
}
