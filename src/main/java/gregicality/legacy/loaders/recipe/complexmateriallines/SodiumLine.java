package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumBisulfateSolution;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumSulfate;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class SodiumLine {
    private SodiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Trona Acidic Cracking
        //Na3C2H(H2O)2O6 + 3 NaHSO4 = 3 Na2SO4 + 2 CO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Trona,18)
                .fluidInputs(SodiumBisulfateSolution.getFluid(12000))
                .output(dust,SodiumSulfate,21)
                .fluidOutputs(TronaSlag.getFluid(1800),CarbonDioxide.getFluid(2000),Steam.getFluid(16000))
                .duration(240)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TronaSlag.getFluid(1800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(SodiumSlag.getFluid(300))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(SodiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Sodium)
                .chancedOutput(dustImpure,Sodium,1,8750,800)
                .chancedOutput(dustImpure,Sodium,1,7500,700)
                .chancedOutput(dustImpure,Sodium,1,6250,600)
                .chancedOutput(dustImpure,Sodium,1,5000,500)
                .chancedOutput(dustImpure,Sodium,1,3750,400)
                .chancedOutput(dustImpure,Sodium,1,2500,300)
                .chancedOutput(dustImpure,Sodium,1,1250,200)
                .chancedOutput(dustImpure,Sodium,1,0,100)
                .fluidOutputs(SodiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SodiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Sodium,1,7500,750)
                .chancedOutput(dustImpure,Sodium,1,5000,500)
                .chancedOutput(dustImpure,Sodium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
