package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.SodiumAluminate;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.SodiumFerrate;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ChromeLine {
    private ChromeLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Chromite Cracking
        //FeCr2O4 + 11 HCl + 3 HNO3 = 2 CrCl4 + FeCl3 + 3 NO2 + 7 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Chromite,7)
                .fluidInputs(AquaRegia.getFluid(3000),HydrochloricAcid.getFluid(5000))
                .output(dust,ChromeTetrachloride,20)
                .fluidOutputs(ChromiteSlag.getFluid(700),Iron3Chloride.getFluid(1000),NitrogenDioxide.getFluid(3000),Steam.getFluid(7000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //2 FeCr2O4 + 22 HClO4 = 4 CrCl4 + 2 FeCl3 + 85 O + 11 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Chromite,14)
                .fluidInputs(PerchloricAcid.getFluid(22000))
                .output(dust,ChromTetrachloride,20)
                .fluidOutputs(ChromiteSlag.getFluid(1400),Iron3Chloride.getFluid(2000),Oxygen.getFluid(85000),Steam.getFluid(11000))
                .EUt(VA[LuV])
                .duration(200)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Ruby Cracking
        //CrAl2O3 + 10 HCl + 4 HNO3 = CrCl4 + 2 AlCl3 + 4 NO2 + 7 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Ruby,6)
                .fluidInputs(AquaRegia.getFluid(4000),HydrochloricAcid.getFluid(2000))
                .output(dust,ChromeTetrachloride,5)
                .output(dust,AluminiumTrichloride,8)
                .fluidOutputs(RubySlag.getFluid(600),NitrogenDioxide.getFluid(4000),Steam.getFluid(7000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //CrAl2O3 + 10 HClO4 = CrCl4 + 2 AlCl3 + 19 O2 + 5 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Ruby,6)
                .fluidInputs(PerchloricAcid.getFluid(10000))
                .output(dust,ChromeTetrachloride,5)
                .output(dust,AluminiumTrichloride,8)
                .fluidOutputs(RubySlag.getFluid(600),Oxygen.getFluid(38000),Steam.getFluid(5000))
                .EUt(VA[LuV])
                .duration(200)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ChromiteSlag.getFluid(1400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ChromeSlag.getFluid(200),IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(RubySlag.getFluid(1200))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ChromeSlag.getFluid(100),AluminiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ChromeSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Chrome)
                .chancedOutput(dustImpure,Chrome,1,8750,800)
                .chancedOutput(dustImpure,Chrome,1,7500,700)
                .chancedOutput(dustImpure,Chrome,1,6250,600)
                .chancedOutput(dustImpure,Chrome,1,5000,500)
                .chancedOutput(dustImpure,Chrome,1,3750,400)
                .fluidOutputs(ChromeWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ChromeWaste.getFluid(1000))
                .chancedOutput(dustImpure,Chrome,1,7500,750)
                .chancedOutput(dustImpure,Chrome,1,5000,500)
                .chancedOutput(dustImpure,Chrome,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Nitrate to Sulfate Conversion
        //2 Cr(NO3)3 + 3 H2SO4 = Cr2(SO4)3 + 6 NO2 + 3 O + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,ChromeTrinitrate,26)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust,ChromeIIISulfate,17)
                .fluidOutputs(NitrogenDioxide.getFluid(6000),Oxygen.getFluid(3000),Water.getFluid(3000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Desulfurizing of Chrome Sulfide
        //Cr2S3 + 3 H2CO3 = Cr2(CO3)3 + 3 H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,ChromeIIISulfide,5)
                .fluidInputs(CarbonicAcid.getFluid(3000))
                .output(dust,ChromeIIICarbonate,14)
                .fluidOutputs(HydrogenSulfide.getFluid(3000))
                .EUt(VA[LV])
                .duration(380)
                .buildAndRegister();

        //(Redox) Reactions in the EBF
        //Sulfate Decomposition
        //Cr2(SO4)3 = Cr2S3 + 6 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ChromeIIISulfate,17)
                .output(dust,ChromeIIISulfide,5)
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
    }
}
