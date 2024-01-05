package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Proustite;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumHydroxideSolution;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ProustiteAlkalineLeachSolution;
import static gregicality.legacy.api.unification.material.materiallines.SilverLineMaterials.SilverChloride;
import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class SilverLine {
    private SilverLine(){}

    public static void init(){
        //PROUSTITE ALKALINE LEACHING
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Proustite,7)
                .input(dust,Salt,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(5000))
                .output(dust,SilverChloride,6)
                .fluidOutputs(ProustiteAlkalineLeachSolution.getFluid(1000))
                .duration(240)
                .EUt(VA[LV])
                .buildAndRegister();
    }
}
