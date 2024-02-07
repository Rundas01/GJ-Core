package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Bertrandite;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Phenakite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.BerylliumLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BerylliumLine {
    private BerylliumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Emerald Cracking
        //Al2Be3Si6O18 + 12 HCl + 6 HNO3 = 2 AlCl3 + 3 BeCl2 + 6 SiO2 + 6 NO + 9 H2O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Emerald,29)
                .fluidInputs(AquaRegia.getFluid(6000))
                .output(dust,BerylliumDichloride,9)
                .output(dust,AluminiumTrichloride,8)
                .output(dust,SiliconDioxide,18)
                .fluidOutputs(EmeraldSlag.getFluid(2900),NitrousOxide.getFluid(6000),HydrogenPeroxide.getFluid(9000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Al2Be3Si6O18 + 12 HClO4 = 2 AlCl3 + 3 BeCl2 + 6 SiO2 + 6 H2O + 24 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Emerald,29)
                .fluidInputs(PerchloricAcid.getFluid(12000))
                .output(dust,BerylliumDichloride,9)
                .output(dust,AluminiumTrichloride,8)
                .output(dust,SiliconDioxide,18)
                .fluidOutputs(EmeraldSlag.getFluid(2900),Oxygen.getFluid(48000),Steam.getFluid(6000))
                .EUt(VA[LuV])
                .duration(200)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Bertrandite Cracking
        //2 Be4Si2H2O9 + 16 HCl + 10 HNO3 = 8 BeCl2 + 4 SiO2 + 10 NO + 15 H2O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bertrandite,34)
                .fluidInputs(AquaRegia.getFluid(8000),NitricAcid.getFluid(2000))
                .output(dust,BerylliumDichloride,24)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(BertranditeSlag.getFluid(3400),NitrousOxide.getFluid(10000),HydrogenPeroxide.getFluid(15000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Be4Si2H2O9 + 8 HClO4 = 4 BeCl2 + 2 SiO2 + 5 H2O + 16 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bertrandite,17)
                .fluidInputs(PerchloricAcid.getFluid(8000))
                .output(dust,BerylliumDichloride,12)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(BertranditeSlag.getFluid(1700),Oxygen.getFluid(32000),Steam.getFluid(5000))
                .EUt(VA[LuV])
                .duration(200)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Phenakite Cracking
        //Be2SiO4 + 4 HCl + 2 HNO3 = 2 BeCl2 + SiO2 + 2 NO + 3 H2O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Phenakite,7)
                .fluidInputs(AquaRegia.getFluid(2000))
                .output(dust,BerylliumDichloride,6)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(PhenakiteSlag.getFluid(700),NitrousOxide.getFluid(2000),HydrogenPeroxide.getFluid(3000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Be2SiO4 + 4 HClO4 = 2 BeCl2 + SiO2 + 2 H2O + 8 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Phenakite,7)
                .fluidInputs(PerchloricAcid.getFluid(4000))
                .output(dust,BerylliumDichloride,6)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(PhenakiteSlag.getFluid(700),Oxygen.getFluid(16000),Steam.getFluid(2000))
                .EUt(VA[LuV])
                .duration(200)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(EmeraldSlag.getFluid(2900))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BerylliumSlag.getFluid(300),AluminiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BertranditeSlag.getFluid(1700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BerylliumSlag.getFluid(400))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PhenakiteSlag.getFluid(700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BerylliumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BerylliumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Beryllium)
                .chancedOutput(dustImpure,Beryllium,1,8750,800)
                .chancedOutput(dustImpure,Beryllium,1,7500,700)
                .chancedOutput(dustImpure,Beryllium,1,6250,600)
                .chancedOutput(dustImpure,Beryllium,1,5000,500)
                .chancedOutput(dustImpure,Beryllium,1,3750,400)
                .fluidOutputs(BerylliumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(BerylliumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Beryllium,1,7500,750)
                .chancedOutput(dustImpure,Beryllium,1,5000,500)
                .chancedOutput(dustImpure,Beryllium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Chloride to Nitrate Conversion
        //BeCl2 + 2 HNO3 = Be(NO3)2 + 2 HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BerylliumDichloride,3)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust,BerylliumDinitrate,9)
                .fluidOutputs(HydrogenChloride.getFluid(2000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Nitrate to Sulfate Conversion
        //Be(NO3)2 + H2SO4 = BeSO4 + 2 NO2 + O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BerylliumDinitrate,9)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,BerylliumSulfate,6)
                .fluidOutputs(NitrogenDioxide.getFluid(2000),Oxygen.getFluid(1000),Water.getFluid(1000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Desulfurizing of Beryllium Sulfide
        //BeS + H2CO3 = BeCO3 + H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BerylliumSulfide,2)
                .fluidInputs(CarbonicAcid.getFluid(1000))
                .output(dust,BerylliumCarbonate,5)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[LV])
                .duration(380)
                .buildAndRegister();

        //(Redox) Reactions in the EBF
        //Sulfate Decomposition
        //BeSO4 = BeS + 2 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BerylliumSulfate,6)
                .output(dust,BerylliumSulfide,2)
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Carbonate Decomposition
        //BeCO3 = BeO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BerylliumCarbonate,5)
                .output(dust,BerylliumOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Oxide Reduction
        //BeO + 2 Li = Be + Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BerylliumOxide,2)
                .input(dust,Lithium,2)
                .output(dust,Beryllium)
                .output(dust,LithiumOxide,3)
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //BeO + Mg = Be + MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BerylliumOxide,2)
                .input(dust,Magnesium)
                .output(dust,Beryllium)
                .output(dust,Magnesia)
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //REFINEMENT PROCESS
        //Chloride Production
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Beryllium)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust,BerylliumDichloride,3)
                .EUt(VA[EV])
                .duration(600)
                .buildAndRegister();

        //Beryllate Production with Ammonium Bifluoride
        //BeCl2 + 2 NH4HF2 = (NH4)2BeF4 + 2 HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BerylliumDichloride,3)
                .input(dust,AmmoniumBifluoride,16)
                .output(dust,AmmoniumTetrafluoroBeryllate,15)
                .fluidOutputs(HydrogenChloride.getFluid(2000))
                .EUt(VA[EV])
                .duration(80)
                .buildAndRegister();

        //Beryllate Blasting
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AmmoniumTetrafluoroBeryllate,15)
                .output(dust,BerylliumDifluoride,3)
                .output(dust,AmmoniumFluoride,12)
                .EUt(VA[EV])
                .duration(120)
                .blastFurnaceTemp(500)
                .buildAndRegister();

        //Solution Mixing
        MIXER_RECIPES.recipeBuilder()
                .input(dust,BerylliumDifluoride)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BerylliumFluorideSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(60)
                .buildAndRegister();

        //Electrolyzing into HP Beryllium
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Beryllium)
                .notConsumable(stick,Graphite)
                .fluidInputs(BerylliumFluorideSolution.getFluid(1000))
                .output(dust,HighPurityBeryllium)
                .fluidOutputs(HydrogenFluoride.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
