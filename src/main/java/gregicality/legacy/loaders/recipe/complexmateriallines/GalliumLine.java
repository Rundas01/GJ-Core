package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumHydroxideSolution;
import static gregicality.legacy.api.unification.material.materiallines.GalliumLineMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Bauxite;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class GalliumLine {
    private GalliumLine() {}

    public static void init() {
        //Bauxite + NaOH + H2O -> Bauxite Slurry + NaH
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Bauxite)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(BauxiteSlurry.getFluid(3000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        //Bauxite Slurry + 3 NaHg -> Amalgamated Bauxite Slurry
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumMercurate,3)
                .fluidInputs(BauxiteSlurry.getFluid(1000))
                .fluidOutputs(AmalgamatedBauxiteSlurry.getFluid(1000))
                .EUt(30)
                .duration(240)
                .buildAndRegister();

        //Centrifuging Amalgamated Bauxite Slurry
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AmalgamatedBauxiteSlurry.getFluid(2000))
                .fluidOutputs(CleanAmalgamatedBauxiteSlurry.getFluid(1000),SodiumHydroxideSolution.getFluid(1000))
                .EUt(10)
                .duration(600)
                .buildAndRegister();

        //Electrolyzing Clean Amalgamated Bauxite Slurry
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(CleanAmalgamatedBauxiteSlurry.getFluid(1000))
                .output(dust,AluminiumMercurate,2)
                .output(dust,GalliumMercurate)
                .EUt(24)
                .duration(320)
                .buildAndRegister();
    }
}
