package gregicality.legacy.loaders.recipe;

import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public final class GCYLRMiscRecipes {

    private GCYLRMiscRecipes() {}

    public static void init() {
        MiscChemicalRecipes();
    }

    private static void MiscChemicalRecipes() {
        //Sodium Hydroxide Solution
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumHydroxide)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .EUt(24)
                .duration(40)
                .buildAndRegister();

        //Chloric Acid from Chlorine Monoxide
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ChlorineMonoxide.getFluid(2000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(ChloricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
