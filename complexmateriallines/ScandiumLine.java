package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Thortveitite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumBisulfateSolution;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumSulfate;
import static gregicality.legacy.api.unification.material.materiallines.ScandiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.SiliconDioxide;
import static gregtech.api.unification.material.Materials.Steam;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ScandiumLine {
    private ScandiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Thortveitite Acidic Cracking
        //Sc2Si2O7 + 6 NaHSO4 = Sc2(SO4)3 + 2 SiO2 + 3 Na2SO4 + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Thortveitite,11)
                .fluidInputs(SodiumBisulfateSolution.getFluid(6000))
                .output(dust,ScandiumSulfate,17)
                .output(dust,SodiumSulfate,21)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(ImpureScandiumSlag.getFluid(1000),Steam.getFluid(9000))
                .EUt(VA[MV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureScandiumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ScandiumSlag.getFluid(1000))
                .fluidOutputs(ScandiumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ScandiumSlag.getFluid(8000))
                .output(dustImpure,Thortveitite)
                .fluidOutputs(ScandiumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ScandiumWaste.getFluid(18000))
                .output(dustImpure,Thortveitite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
