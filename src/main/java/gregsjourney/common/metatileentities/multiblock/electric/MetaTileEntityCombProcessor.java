package gregsjourney.common.metatileentities.multiblock.electric;

import static forestry.apiculture.ModuleApiculture.getBlocks;
import static gregsjourney.common.recipe.GJRecipeMaps.COMB_RECIPES;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;

import forestry.apiculture.blocks.BlockAlvearyType;
import forestry.apiculture.blocks.BlockRegistryApiculture;
import gregsjourney.api.render.GJTextures;

public class MetaTileEntityCombProcessor extends GCYMRecipeMapMultiblockController {

    BlockRegistryApiculture blocks = getBlocks();

    public MetaTileEntityCombProcessor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, COMB_RECIPES);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this, true);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCombProcessor(this.metaTileEntityId);
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("    CC   CC    ", "    CC   CC    ", "    CC   CC    ")
                .aisle("   CCCXXXCCC   ", "   CHHXXXHHC   ", "   CCCXXXCCC   ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle(" CCCCCCCCCCCCC ", " CHHHHAAAHHHHC ", " CCCCCGGGCCCCC ")
                .aisle("XCCCCCCCCCCCCCX", "XAAAAAAAAAAAAAX", "XGGGGGGGGGGGGGX")
                .aisle("XCCCCCCCCCCCCCX", "XAAAAAAAAAAAAAX", "XGGGGGGGGGGGGGX")
                .aisle("XCCCCCCCCCCCCCX", "XAAAAAAAAAAAAAX", "XGGGGGGGGGGGGGX")
                .aisle(" CCCCCCCCCCCCC ", " CHHHHAAAHHHHC ", " CCCCCGGGCCCCC ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle("  CCCCCCCCCCC  ", "  CHHHAAAHHHC  ", "  CCCCGGGCCCC  ")
                .aisle("   CCCXXXCCC   ", "   CHHXSXHHC   ", "   CCCXXXCCC   ")
                .aisle("    CC   CC    ", "    CC   CC    ", "    CC   CC    ")
                .where('S', selfPredicate())
                .where('X',
                        states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))
                                .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS)))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('A', blocks(blocks.getAlvearyBlock(BlockAlvearyType.PLAIN)))
                .where('H', blocks(blocks.beeCombs[0]))
                .where(' ', any())
                .build();
    }

    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.BRONZE_PLATED_BRICKS;
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return GJTextures.COMB_OVERLAY;
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gj.multiblock.comb_processor.description") };
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public boolean canVoidRecipeItemOutputs() {
        return true;
    }

    @Override
    public boolean canVoidRecipeFluidOutputs() {
        return true;
    }
}
