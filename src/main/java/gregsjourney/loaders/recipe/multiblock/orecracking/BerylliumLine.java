package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.*;
import static gregsjourney.api.unification.material.GJOreMaterials.Bertrandite;
import static gregsjourney.api.unification.material.GJOreMaterials.Phenakite;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.SodiumAluminate;
import static gregsjourney.api.unification.material.materiallines.BerylliumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.HighPurityLithium;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.MagnesiumLineMaterials.HighPurityMagnesium;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BerylliumLine {
    private BerylliumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Emerald Cracking
        //Al2Be3Si6O18 + 8 NaOH = 2 NaAlO2 + 3 Na2BeO2 + 6 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Emerald,29)
                .fluidInputs(SodiumHydroxideSolution.getFluid(8000))
                .output(dust,SodiumBeryllate,15)
                .output(dust,SodiumAluminate,8)
                .output(dust,SiliconDioxide,18)
                .fluidOutputs(EmeraldSlag.getFluid(2900),Steam.getFluid(12000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        //Bertrandite Cracking
        //Be4Si2H2O9 + 4 H2SO4 = 4 BeSO4 + 2 SiO2 + 5 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bertrandite,17)
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .output(dust,BerylliumSulfate,24)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(BertranditeSlag.getFluid(1700),Steam.getFluid(5000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        //Phenakite Cracking
        //Be2SiO4 + 2 H2SO4 = 2 BeSO4 + SiO2 + 2 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Phenakite,7)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust,BerylliumSulfate,12)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(PhenakiteSlag.getFluid(700),Steam.getFluid(2000))
                .EUt(VA[HV])
                .duration(400)
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

        //Beryllate Hydrolysis
        //Na2BeO2 + 2 H2O = Be(OH)2 + 2 NaOH
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumBeryllate,5)
                .fluidInputs(DistilledWater.getFluid(2000))
                .output(dust,BerylliumHydroxide,5)
                .output(dust,SodiumHydroxide,6)
                .EUt(VA[MV])
                .duration(220)
                .buildAndRegister();

        //Hydroxide -> Sulfate
        //Be(OH)2 + H2SO4 = BeSO4 + 2 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BerylliumHydroxide,5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,BerylliumSulfate,6)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Sulfate Reduction
        //2 BeSO4 + 3 C = Be2S + SO2 + 3 CO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BerylliumSulfate,12)
                .input(dust,Carbon,3)
                .output(dust,BerylliumISulfide,3)
                .fluidOutputs(SulfurDioxide.getFluid(1000),CarbonDioxide.getFluid(3000))
                .EUt(VA[MV])
                .duration(300)
                .buildAndRegister();

        //Sulfide Oxidation
        //Be2S + 2 O2 = 2 BeO + SO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BerylliumISulfide,3)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust,BerylliumOxide,4)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[HV])
                .duration(300)
                .buildAndRegister();

        //Oxide Reduction
        //BeO + 2 Li = Be + Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BerylliumOxide,2)
                .input(dust,HighPurityLithium,2)
                .output(dust,Beryllium)
                .output(dust,LithiumOxide,3)
                .EUt(VA[HV])
                .duration(200)
                .buildAndRegister();

        //BeO + Mg = Be + MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BerylliumOxide,2)
                .input(dust,HighPurityMagnesium)
                .output(dust,Beryllium)
                .output(dust,Magnesia)
                .EUt(VA[EV])
                .duration(400)
                .buildAndRegister();

        //REFINEMENT PROCESS
        //Chloride Production
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
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
        //(NH4)2BeF4 = BeF2 + 2 NH4F
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
        //BeF2 + H2O = Be + 2 HF + O
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
