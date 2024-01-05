package gregicality.legacy.common.metatileentities.multiblock.electric;

import gregicality.legacy.common.block.GCYLRMetaBlocks;
import gregicality.legacy.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FLOTATION_RECIPES;
import static gregicality.legacy.api.render.GCYLRTextures.FLOTATION_OVERLAY;

public class MetaTileEntityFlotationCell extends RecipeMapMultiblockController {
    public MetaTileEntityFlotationCell(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, FLOTATION_RECIPES);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this, true);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityFlotationCell(this.metaTileEntityId);
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("   B   ", "   B   ", "   B   ", "       ", "       ")
                .aisle("  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ")
                .aisle(" AAAAA ", " ABBBA ", " ABBBA ", " ADDDA ", " A   A ")
                .aisle("BAAAAAB", "BABBBAB", "BABBBAB", " ADBDA ", " A E A ")
                .aisle(" AAAAA ", " ABBBA ", " ABBBA ", " ADDDA ", " A   A ")
                .aisle("  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ")
                .aisle("   B   ", "   B   ", "   S   ", "       ", "       ")
                .where('S', selfPredicate())
                .where('A', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)).setMinGlobalLimited(51)
                        .or(autoAbilities(true, true, true, true, true, true, true)))
                .where('B', states(MetaBlocks.BOILER_CASING.getState((BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))))
                .where('D', states(GCYLRMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLOTATION_CASING)))
                .where('E', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where(' ', any())
                .build();
    }
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return FLOTATION_OVERLAY;
    }
}
