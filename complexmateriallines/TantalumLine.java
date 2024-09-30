package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Thortveitite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.ScandiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class TantalumLine {
    private TantalumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Tantalite Oxidation Smelting
        //MnTa2O6 + Na2CO3 + KNO3 = KNO2 + MnO2 + 2 NaTaO3 + CO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Tantalite,9)
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000),PotassiumNitrateSolution.getFluid(1000))
                .output(dust,SodiumTantalate,10)
                .output(dust,ManganeseDioxide,3)
                .output(dust,PotassiumNitrite,4)
                .fluidOutputs(ImpureTantalumSlag.getFluid(1000),CarbonDioxide.getFluid(1000),Steam.getFluid(2000))
                .EUt(VA[MV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureTantalumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TantalumSlag.getFluid(1000))
                .fluidOutputs(TantalumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(TantalumSlag.getFluid(8000))
                .output(dustImpure,Tantalite)
                .fluidOutputs(TantalumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(TantalumWaste.getFluid(18000))
                .output(dustImpure,Tantalite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
