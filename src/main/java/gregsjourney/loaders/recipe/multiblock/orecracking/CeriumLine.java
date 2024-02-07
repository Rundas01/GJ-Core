package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.*;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.HighPurityAluminium;
import static gregsjourney.api.unification.material.materiallines.CeriumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LanthanumLineMaterials.LanthanumPhosphate;
import static gregsjourney.api.unification.material.materiallines.ManganeseLineMaterials.ManganeseSulfate;
import static gregsjourney.api.unification.material.materiallines.ManganeseLineMaterials.PotassiumPermanganate;
import static gregsjourney.api.unification.material.materiallines.NeodymiumLineMaterials.NeodymiumPhosphate;
import static gregsjourney.api.unification.material.materiallines.PotassiumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.SamariumLineMaterials.SamariumPhosphate;
import static gregsjourney.api.unification.material.materiallines.SodiumLineMaterials.SodiumFluoride;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CeriumLine {
    private CeriumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Bastnasite Cracking
        //CeCFO3 + 2 NaOH = NaCeO2 + NaF + CO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bastnasite,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumCerateIII,4)
                .output(dust,SodiumFluoride,2)
                .fluidOutputs(BastnasiteSlag.getFluid(600),CarbonDioxide.getFluid(1000),Steam.getFluid(3000))
                .EUt(VA[EV])
                .duration(400)
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

        //Cerate -> Hydroxide
        //2 NaCeO2 + 3 H2O + CO2 = 2 Ce(OH)3 + Na2CO3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumCerateIII,8)
                .fluidInputs(DistilledWater.getFluid(3000),CarbonDioxide.getFluid(1000))
                .output(dust,CeriumTrihydroxide,14)
                .output(dust,SodaAsh,6)
                .EUt(VA[EV])
                .duration(300)
                .buildAndRegister();

        //Hydroxide -> Sulfate
        //10 Ce(OH)3 + 2 KMnO4 + 23 H2SO4 = 10 Ce(SO4)2 + 2 MnSO4 + K2SO4 + 38 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumTrihydroxide,70)
                .input(dust,PotassiumPermanganate,12)
                .fluidInputs(SulfuricAcid.getFluid(23000))
                .output(dust,CeriumDisulfate,110)
                .output(dust,ManganeseSulfate,12)
                .output(dust,PotassiumSulfate,7)
                .fluidOutputs(Water.getFluid(38000))
                .EUt(VA[IV])
                .duration(6000)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Sulfate to Sulfide Reduction
        //2 Ce(SO4)2 + 7 C = Ce2S3 + 7 CO2 + SO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CeriumDisulfate,22)
                .input(dust,Carbon,7)
                .output(dust,CeriumIIISulfide,5)
                .fluidOutputs(CarbonDioxide.getFluid(7000),SulfurDioxide.getFluid(1000))
                .EUt(VA[EV])
                .duration(800)
                .buildAndRegister();

        //Cerium Sulfide Oxidation
        //Ce2S3 + 5 O2 = 2 CeO2 + 3 SO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CeriumIIISulfide,5)
                .fluidInputs(Oxygen.getFluid(10000))
                .output(dust,CeriumDioxide,6)
                .fluidOutputs(SulfurDioxide.getFluid(3000))
                .EUt(VA[EV])
                .duration(800)
                .buildAndRegister();

        //Oxide Reduction
        //3 CeO2 + 4 Al = 3 Ce + 2 Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CeriumDioxide,9)
                .input(dust,HighPurityAluminium,4)
                .output(dust,Cerium,3)
                .output(dust,AluminiumIIIOxide,10)
                .EUt(VA[EV])
                .duration(400)
                .buildAndRegister();

        //PURIFICATION PROCESS
        //CeriumIVIodate Production
        //Ce + 4 HIO3 = Ce(IO3)4 + 2 H2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Cerium)
                .fluidInputs(IodicAcid.getFluid(4000))
                .output(dust,CeriumTetraiodate,17)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .EUt(VA[LuV])
                .duration(240)
                .buildAndRegister();

        //Cerium Oxalate Conversion
        //2 Ce(IO3)4 + 24 H2C2O4 = Ce2(C2O4)3 + 4 I2 + 42 CO2 + 24 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumTetraiodate,34)
                .fluidInputs(OxalicAcid.getFluid(24000))
                .output(dust,CeriumIIIOxalate,20)
                .output(dust,Iodine,8)
                .fluidOutputs(CarbonDioxide.getFluid(42000),Water.getFluid(24000))
                .EUt(VA[LuV])
                .duration(360)
                .buildAndRegister();

        //Cerium Fluoride Step
        //3 I2 + 6 KF + Ce2(C2O4)3 = 6 KI + 2 CeF3 + 6 CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CeriumIIIOxalate,20)
                .input(dust,PotassiumFluoride,12)
                .input(dust,Iodine,6)
                .output(dust,CeriumTrifluoride,8)
                .output(dust,PotassiumIodide,12)
                .fluidOutputs(CarbonDioxide.getFluid(6000))
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
        //2 Cerium Trifluoride Solution = 4 Ce + 12 HF + 3 O2
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
