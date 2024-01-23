package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Allanite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CeriumLine {
    private CeriumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Bastnasite Cracking
        //CeCFO3 + 4 HCl + 2 HNO3 = CeCl4 + CO2 + F + 2 NO2 + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bastnasite,6)
                .fluidInputs(AquaRegia.getFluid(2000))
                .output(dust,CeriumTetrachloride,5)
                .fluidOutputs(BastnasiteSlag.getFluid(600),Fluorine.getFluid(1000),NitrousOxide.getFluid(2000),CarbonDioxide.getFluid(2000),Steam.getFluid(3000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Allahnite Cracking
        //CeCaYLaAlFe(SiO4)3(OH) + 21 HNO3 = Ce(NO3)3 + Ca(NO3)2 + Y(NO3)3 + Fe(NO3)3 + La(NO3)3 + Al(NO3)3 + 3 SiO2 + 4 NO2 + 11 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Allanite,23)
                .fluidInputs(NitricAcid.getFluid(21000))
                .output(dust,CeriumTrinitrate,13)
                .output(dust,CalciumDinitrate,9)
                .output(dust,YttriumTrinitrate,13)
                .output(dust,IronTrinitrate,13)
                .output(dust,LanthanumTrinitrate,13)
                .output(dust,AluminiumTrinitrate,13)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(AllaniteSlag.getFluid(2300),NitrogenDioxide.getFluid(4000),Steam.getFluid(11000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BastnasiteSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CeriumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CeriumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Cerium)
                .chancedOutput(dustImpure,Cerium,1,8750,800)
                .chancedOutput(dustImpure,Cerium,1,7500,700)
                .chancedOutput(dustImpure,Cerium,1,6250,600)
                .chancedOutput(dustImpure,Cerium,1,5000,500)
                .chancedOutput(dustImpure,Cerium,1,3750,400)
                .fluidOutputs(CeriumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CeriumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Cerium,1,7500,750)
                .chancedOutput(dustImpure,Cerium,1,5000,500)
                .chancedOutput(dustImpure,Cerium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Monazite Centrifuging
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust,Monazite,4)
                .output(dust,CeriumPhosphate)
                .output(dust,LanthanumPhosphate)
                .output(dust,NeodymiumPhosphate)
                .output(dust,SamariumPhosphate)
                .buildAndRegister();

        //Nitrate to Sulfate Conversion
        //2 Ce(NO3)2 + 3 H2SO4 = Ce2(SO4)3 + 4 NO2 + O + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumIIINitrate,18)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust,CeriumIIISulfate,17)
                .fluidOutputs(NitrogenDioxide.getFluid(4000),Oxygen.getFluid(1000),Water.getFluid(3000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Desulfurizing of Cerium Sulfide
        //Ce2S3 + 3 H2CO3 = Ce2(CO3)3 + 3 H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumIIISulfide,5)
                .fluidInputs(CarbonicAcid.getFluid(3000))
                .output(dust,CeriumIIICarbonate,14)
                .fluidOutputs(HydrogenSulfide.getFluid(3000))
                .EUt(VA[LV])
                .duration(380)
                .buildAndRegister();

        //(Redox) Reactions in the EBF
        //Sulfate Decomposition
        //Ce2(SO4)3 = Ce2S3 + 6 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CeriumIIISulfate,17)
                .output(dust,CeriumIIISulfide,5)
                .fluidOutputs(Oxygen.getFluid(12000))
                .EUt(VA[EV])
                .duration(800)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Carbonate Decomposition
        //Ce2(CO3)3 = Ce2O3 + 3 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CeriumIIICarbonate,14)
                .output(dust,CeriumIIIOxide,5)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[EV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Oxide Reduction
        //Ce2O3 + 6 Li = 2 Ce + 3 Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CeriumIIIOxide,5)
                .input(dust,Lithium,6)
                .output(dust,Cerium,2)
                .output(dust,LithiumOxide,9)
                .EUt(VA[EV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Ce2O3 + 3 Mg = 2 Ce + 3 MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CeriumIIIOxide,5)
                .input(dust,Magnesium,3)
                .output(dust,Cerium,2)
                .output(dust,Magnesia,6)
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //PURIFICATION PROCESS
        //CeriumIVIodate Production
        //Ce + 4 HIO3 = Ce(IO3)4 + 2 H2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Cerium)
                .fluidInputs(IodicAcid.getFluid(4000))
                .output(dust,CeriumIVIodate,17)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .EUt(VA[LuV])
                .duration(240)
                .buildAndRegister();

        //Cerium Oxalate Conversion
        //2 Ce(IO3)4 + 24 H2C2O4 = Ce2(C2O4)3 + 4 I2 + 42 CO2 + 24 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumIVIodate,34)
                .fluidInputs(OxalicAcid.getFluid(24000))
                .output(dust,CeriumIIIOxalate,20)
                .output(dust,Iodine,8)
                .fluidOutputs(CarbonDioxide.getFluid(42000),Water.getFluid(24000))
                .EUt(VA[LuV])
                .duration(360)
                .buildAndRegister();

        //Oxalate Blasting
        //Ce2(C2O4)3 + 2 O2 = 2 CeO2 + 6 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CeriumIIIOxalate,20)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust,CeriumIVOxide,6)
                .fluidOutputs(CarbonDioxide.getFluid(6000))
                .EUt(VA[LuV])
                .duration(600)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Cerium Fluoride Step
        //2 CeO2 + 2 KI + 8 HF(H2O) = 2 KF + 2 CeF3 + 12 H2O + I2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumIVOxide,6)
                .input(dust,PotassiumIodide,4)
                .fluidInputs(HydrofluoricAcid.getFluid(8000))
                .output(dust,CeriumTrifluoride,8)
                .output(dust,PotassiumFluoride,4)
                .output(dust,Iodine,2)
                .fluidOutputs(Water.getFluid(12000))
                .EUt(VA[LuV])
                .duration(400)
                .buildAndRegister();

        //Cerium Fluoride Solution Mixing
        //2 CeF3 + 3 H2O = Solution in the proper relation for correct stoichiometry :hyperxd:
        MIXER_RECIPES.recipeBuilder()
                .input(dust,CeriumTrifluoride,2)
                .fluidInputs(DistilledWater.getFluid(3000))
                .fluidOutputs(CeriumTrifluorideSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(60)
                .buildAndRegister();

        //Electrolysis of Cerium Fluoride Solution
        //2 CeriumTrifluoride Solution = 4 Ce + 12 HF + 3 O2
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Cerium)
                .notConsumable(stick,Graphite)
                .fluidInputs(CeriumTrifluorideSolution.getFluid(2000))
                .output(dust,HighPurityCerium,4)
                .fluidOutputs(HydrogenFluoride.getFluid(12000),Oxygen.getFluid(6000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();
    }
}
