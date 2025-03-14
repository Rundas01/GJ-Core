package thegreggening.common.metatileentities.multiblock.primitive;

import static thegreggening.common.recipe.TGRecipeMaps.HEAT_EXCHANGER_RECIPES;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import thegreggening.api.recipe.NoEnergyMultiblockRecipeLogic;
import thegreggening.api.render.TGTextures;

public class MetaTileEntityHeatExchanger extends RecipeMapMultiblockController {

    public MetaTileEntityHeatExchanger(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, HEAT_EXCHANGER_RECIPES);
        this.recipeMapWorkable = new NoEnergyMultiblockRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityHeatExchanger(metaTileEntityId);
    }

    @Override
    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC", "BCB", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "CDC", "ACA")
                .aisle("CCC", "BSB", "ACA")
                .where('S', selfPredicate())
                .where('A', states(MetaBlocks.FRAMES.get(Materials.Steel).getBlock(Materials.Steel)))
                .where('B',
                        abilities(MultiblockAbility.IMPORT_FLUIDS).setExactLimit(2)
                                .or(abilities(MultiblockAbility.EXPORT_FLUIDS).setExactLimit(2)))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .or(autoAbilities(false, true, false, false, false, false, false))
                        .or(autoAbilities(false, false, true, false, false, false, false).setMaxGlobalLimited(1)))
                .where('D', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return TGTextures.HEAT_EXCHANGER_OVERLAY;
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gj.multiblock.heat_exchanger.description") };
    }
}
