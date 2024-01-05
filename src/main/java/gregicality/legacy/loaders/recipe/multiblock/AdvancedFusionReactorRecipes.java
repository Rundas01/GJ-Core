package gregicality.legacy.loaders.recipe.multiblock;

import gregicality.legacy.api.recipe.builders.AdvancedFusionRecipeBuilder;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.material.Material;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

import static gregicality.legacy.api.fluids.GCYLRFluidStorageKeys.SUPERCRITICAL;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ADVANCED_FUSION_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLRFusionModeratorMaterials.*;
import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.material.Materials.*;

public final class AdvancedFusionReactorRecipes {

    private AdvancedFusionReactorRecipes() {}

    public static void init() {
        addAutogennedRecipes();
        addManualRecipes();
    }

    private static void addManualRecipes() {
    }

    private static void addAutogennedRecipes() {
        List<Triple<Material,Material,Triple<Integer,Integer,Integer>>> recipes = new ArrayList<>();
        recipes.add(Triple.of(Carbon,Oxygen,Triple.of(120,IV,128000000)));
        recipes.add(Triple.of(Oxygen,Neon,Triple.of(140,IV,128000000)));
        recipes.add(Triple.of(Neon,Magnesium,Triple.of(160,IV,128000000)));
        recipes.add(Triple.of(Magnesium,Silicon,Triple.of(180,IV,128000000)));
        //recipes.add(Triple.of(Silicon,Sulfur,Triple.of(200,IV,128000000)));
        //recipes.add(Triple.of(Sulfur,Argon,Triple.of(220,IV,160000000)));
        //recipes.add(Triple.of(Argon,Calcium,Triple.of(240,IV,180000000)));
        //recipes.add(Triple.of(Calcium,Titanium,Triple.of(260,IV,200000000)));
        //recipes.add(Triple.of(Titanium,Chrome,Triple.of(280,IV,210000000)));
        recipes.add(Triple.of(Chrome,Iron,Triple.of(300,IV,230000000)));
        recipes.add(Triple.of(Iron,Nickel,Triple.of(320,IV,250000000)));
        long neutronMultiplier;
        for(Triple<Material,Material,Triple<Integer,Integer,Integer>> recipe : recipes){
            neutronMultiplier = Math.max(1,recipe.getLeft().getNeutrons() - recipe.getMiddle().getNeutrons() - 2);
            addLBEModeratedFusionRecipe(recipe.getLeft(), recipe.getMiddle(), recipe.getRight().getLeft(), recipe.getRight().getMiddle(),recipe.getRight().getRight(), neutronMultiplier,2);
            addFLiBeModeratedFusionRecipe(recipe.getLeft(), recipe.getMiddle(), recipe.getRight().getLeft(), recipe.getRight().getMiddle(),recipe.getRight().getRight(), neutronMultiplier,2);
            addNaKModeratedFusionRecipe(recipe.getLeft(), recipe.getMiddle(), recipe.getRight().getLeft(), recipe.getRight().getMiddle(),recipe.getRight().getRight(), neutronMultiplier,3);
            addFLiNaKModeratedFusionRecipe(recipe.getLeft(), recipe.getMiddle(), recipe.getRight().getLeft(), recipe.getRight().getMiddle(),recipe.getRight().getRight(), neutronMultiplier,3);
            addUnmoderatedFusionRecipe(recipe.getLeft(), recipe.getMiddle(), recipe.getRight().getLeft(), recipe.getRight().getMiddle(),recipe.getRight().getRight(), neutronMultiplier,1);
        }
    }



    private static void addLBEModeratedFusionRecipe(Material material, Material output, int duration, int tier, int eu, long neutronMultiplier, int ct){
        RecipeBuilder<AdvancedFusionRecipeBuilder> builder = ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000),Helium.getFluid(1000),LeadBismuthEutectic.getFluid(3456))
                .fluidOutputs(output.getPlasma(1000),LeadBismuthEutectic.getFluid(SUPERCRITICAL,3456))
                .coilTier(ct)
                .EUToStart(eu).duration(duration);
        neutronMultiplier = Math.max(neutronMultiplier, 1);
        builder.EUt((int) (VA[tier] * neutronMultiplier));
        builder.buildAndRegister();
    }

    private static void addNaKModeratedFusionRecipe(Material material, Material output, int duration, int tier, int eu, long neutronMultiplier, int ct){
        RecipeBuilder<AdvancedFusionRecipeBuilder> builder = ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000),Helium.getFluid(1000),SodiumPotassium.getFluid(6912))
                .fluidOutputs(output.getPlasma(1000),SodiumPotassium.getFluid(SUPERCRITICAL,6912))
                .coilTier(ct)
                .EUToStart(eu).duration(duration);
        neutronMultiplier = Math.max(neutronMultiplier, 1);
        builder.EUt((int) (VA[tier] * neutronMultiplier));
        builder.buildAndRegister();
    }

    private static void addFLiBeModeratedFusionRecipe(Material material, Material output, int duration, int tier, int eu, long neutronMultiplier, int ct){
        RecipeBuilder<AdvancedFusionRecipeBuilder> builder = ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000),Helium.getFluid(1000),FLiBe.getFluid(3168))
                .fluidOutputs(output.getPlasma(1000),FLiBe.getFluid(SUPERCRITICAL,3168))
                .coilTier(ct)
                .EUToStart(eu).duration(duration);
        neutronMultiplier = Math.max(neutronMultiplier, 1);
        builder.EUt((int) (VA[tier] * neutronMultiplier));
        builder.buildAndRegister();
    }

    private static void addFLiNaKModeratedFusionRecipe(Material material, Material output, int duration, int tier, int eu, long neutronMultiplier, int ct){
        RecipeBuilder<AdvancedFusionRecipeBuilder> builder = ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000),Helium.getFluid(1000),FLiNaK.getFluid(2880))
                .fluidOutputs(output.getPlasma(1000),FLiNaK.getFluid(SUPERCRITICAL,2880))
                .coilTier(ct)
                .EUToStart(eu).duration(duration);
        neutronMultiplier = Math.max(neutronMultiplier, 1);
        builder.EUt((int) (VA[tier] * neutronMultiplier));
        builder.buildAndRegister();
    }

    private static void addUnmoderatedFusionRecipe(Material material, Material output, int duration, int tier, int eu, long neutronMultiplier, int ct){
        RecipeBuilder<AdvancedFusionRecipeBuilder> builder = ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000),Deuterium.getFluid(1000),Deuterium.getFluid(1000))
                .fluidOutputs(output.getPlasma(1000))
                .coilTier(ct)
                .EUToStart(eu).duration(duration);
        neutronMultiplier = Math.max(neutronMultiplier, 1);
        builder.EUt((int) (2 * VA[tier] * neutronMultiplier)).buildAndRegister();
    }
}
