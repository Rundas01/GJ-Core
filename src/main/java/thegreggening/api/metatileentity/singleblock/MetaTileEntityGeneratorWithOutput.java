package thegreggening.api.metatileentity.singleblock;

import java.util.function.Function;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;

public class MetaTileEntityGeneratorWithOutput extends SimpleGeneratorMetaTileEntity {

    private static final int FONT_HEIGHT = 9;

    public MetaTileEntityGeneratorWithOutput(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                             ICubeRenderer renderer, int tier,
                                             Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, tankScalingFunction, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityGeneratorWithOutput(metaTileEntityId, recipeMap, renderer, getTier(),
                getTankScalingFunction());
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing) {
        return true;
    }

    @Override
    protected void renderOverlays(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        if (getFrontFacing().getAxis() == EnumFacing.Axis.Y) {
            this.renderer.renderOrientedState(renderState, translation, pipeline, EnumFacing.NORTH, workable.isActive(),
                    workable.isWorkingEnabled());
            this.renderer.renderOrientedState(renderState, translation, pipeline, EnumFacing.WEST, workable.isActive(),
                    workable.isWorkingEnabled());
        } else {
            super.renderOverlays(renderState, translation, pipeline);
        }
    }
}
