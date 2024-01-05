package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ROASTING_RECIPES;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class AntimonyLine {

    private AntimonyLine(){}

    public static void init(){
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AntimonyDross.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(4000))
                .output(dust,Antimony,2)
                .output(dust,MagnesiumChloride,3)
                .output(dust,CalciumChloride,3)
                .EUt(VA[HV])
                .duration(200)
                .buildAndRegister();

        ROASTING_RECIPES.recipeBuilder()
                .input(dust,AntimonySulfide,5)
                .fluidInputs(CarbonDioxide.getFluid(6000))
                .output(dust,Antimony,2)
                .fluidOutputs(CarbonMonoxide.getFluid(6000))
                .fluidOutputs(SulfurDioxide.getFluid(3000))
                .EUt(VA[HV])
                .duration(200)
                .buildAndRegister();
    }
}
