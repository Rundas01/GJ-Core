package thegreggening.common.metatileentities.multiblock.electric;

import static thegreggening.common.recipe.TGRecipeMaps.REACTION_FURNACE_RECIPES;
import static gregtech.client.renderer.texture.Textures.ARC_FURNACE_OVERLAY;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

public class MetaTileEntityReactionFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityReactionFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, REACTION_FURNACE_RECIPES);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this, true);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityReactionFurnace(this.metaTileEntityId);
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("F F", "XXX", "XXX", "XXX", "XXX")
                .aisle("   ", "XPX", "XPX", "XPX", "XPX")
                .aisle("F F", "XSX", "XXX", "XXX", "XXX")
                .where('S', this.selfPredicate())
                .where('F', states(this.getFrameState()))
                .where('P', states(this.getPipeCasingState()))
                .where('X',
                        states(this.getCasingState()).or(this.autoAbilities(true, true, true, true, true, true, false)))
                .build();
    }

    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Materials.Steel).getBlock(Materials.Steel);
    }

    protected IBlockState getPipeCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return ARC_FURNACE_OVERLAY;
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("tg.multiblock.reaction_furnace.description") };
    }
}
