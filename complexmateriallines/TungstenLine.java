package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Goethite;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Wolframite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumCarbonate;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class TungstenLine {
    private TungstenLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Scheelite Cracking
        //CaWO4 + 2 NaOH = Ca(OH)2 + Na2WO4
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Scheelite,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumTungstate,7)
                .output(dust,CalciumHydroxide,5)
                .fluidOutputs(ScheeliteSlag.getFluid(600),Steam.getFluid(2000))
                .EUt(VA[IV])
                .duration(400)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Wolframite cracking
        //3 FeMnWO4 + 5 HF + 4 HNO3 = 3 HFeO2 + 3 MnO2 + 3 H2WO4 + 2 N2 + 5 F
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Wolframite,21)
                .fluidInputs(HydrofluoricAcid.getFluid(5000),NitricAcid.getFluid(4000))
                .output(dust,TungsticAcid,21)
                .output(dust,Goethite,12)
                .output(ManganeseDioxide,9)
                .fluidOutputs(WolframiteSlag.getFluid(2100),Nitrogen.getFluid(4000),Fluorine.getFluid(5000),Steam.getFluid(5000))
                .EUt(VA[IV])
                .duration(400)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ScheeliteSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TungstenSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(WolframiteSlag.getFluid(700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TungstenSlag.getFluid(100),IronSlag.getFluid(100),ManganeseSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(TungstenSlag.getFluid(8000))
                .output(dustImpure,Scheelite)
                .fluidOutputs(TungstenWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(TungstenWaste.getFluid(18000))
                .output(dustImpure,Scheelite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Tungstate -> Oxyhydroxide -> Oxide
        //Na2WO4 + 2 H2O + CO2 = Na2CO3 + WO(OH)4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumTungstate,7)
                .fluidInputs(DistilledWater.getFluid(2000),CarbonDioxide.getFluid(1000))
                .output(dust,TungstenOxyHydroxide,10)
                .output(dust,SodaAsh,6)
                .EUt(VA[EV])
                .duration(300)
                .buildAndRegister();

        //Li2WO4 + 2 H2O + CO2 = Li2CO3 + WO(OH)4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Tungstate,7)
                .fluidInputs(DistilledWater.getFluid(2000),CarbonDioxide.getFluid(1000))
                .output(dust,TungstenOxyHydroxide,10)
                .output(dust,LithiumCarbonate,6)
                .EUt(VA[EV])
                .duration(300)
                .buildAndRegister();

        //WO(OH)4 = WO3 + 2 H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,TungstenOxyHydroxide,10)
                .output(dust,TungstenOxide,4)
                .fluidOutputs(Steam.getFluid(2000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Tungsten Oxide Reduction
        //WO3 + 3 H2 = W + 3 H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,TungstenOxide,4)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust,Tungsten)
                .fluidOutputs(Steam.getFluid(3000))
                .EUt(VA[EV])
                .duration(600)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //PURIFICATION PROCESS
        //Ammoniumparatungstate Production
        //2 HCl + 10 NH4Cl + 12 Na2WO4 = (NH4)10(H2W12O42) + 12 NaCl + 6 Na2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumTungstate,84)
                .input(dust,AmmoniumChloride,60)
                .fluidInputs(HydrogenChloride.getFluid(2000))
                .output(dust,AmmoniumParaTungstate,106)
                .output(dust,Salt,24)
                .output(dust,SodiumOxide,18)
                .buildAndRegister();

        //Ammoniumparatungstate Blasting
        //(NH4)10(H2W12O42) = 6 NH3 + 4 H2O + (NH4)4(W12O38)

        //Reduction with Hydrogen
        //(NH4)4(W12O38) + 22 H2 = 12 W + 30 H2O + 4 NO2
    }
}
