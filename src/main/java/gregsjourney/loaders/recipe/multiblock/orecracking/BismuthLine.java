package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.api.unification.material.GJOreMaterials.Bismuthinite;
import static gregsjourney.api.unification.material.GJOreMaterials.Polarite;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.HighPurityAluminium;
import static gregsjourney.api.unification.material.materiallines.BismuthLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LeadLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.HighPurityLithium;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.MagnesiumLineMaterials.HighPurityMagnesium;
import static gregsjourney.api.unification.material.materiallines.PalladiumLineMaterials.PalladiumDinitrate;
import static gregsjourney.api.unification.material.materiallines.PalladiumLineMaterials.PalladiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class BismuthLine {
    private BismuthLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Bismuthinite Cracking
        //Bi2S3 + 32 HNO3 = 2 Bi(NO3)5 + 3 SO2 + 22 NO2 + 16 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bismuthinite,5)
                .fluidInputs(NitricAcid.getFluid(32000))
                .output(dust,BismuthPentanitrate,42)
                .fluidOutputs(BismuthiniteSlag.getFluid(500),SulfurDioxide.getFluid(3000),NitrogenDioxide.getFluid(22000),Steam.getFluid(16000))
                .EUt(VA[IV])
                .duration(380)
                .buildAndRegister();

        //Polarite Cracking
        //Pd2BiPb + 14 HNO3 = 2 Pd(NO3)2 + BiNO3 + Pb(NO3)2 + 7 NO2 + 7 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Polarite,4)
                .fluidInputs(NitricAcid.getFluid(14000))
                .output(dust,BismuthNitrate,5)
                .output(dust,PalladiumDinitrate,9)
                .output(dust,LeadDinitrate,9)
                .fluidOutputs(PolariteSlag.getFluid(400),NitrogenDioxide.getFluid(7000),Steam.getFluid(7000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BismuthiniteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BismuthSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PolariteSlag.getFluid(400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BismuthSlag.getFluid(100),PalladiumSlag.getFluid(200),LeadSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BismuthSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Bismuth)
                .chancedOutput(dustImpure,Bismuth,1,8750,800)
                .chancedOutput(dustImpure,Bismuth,1,7500,700)
                .chancedOutput(dustImpure,Bismuth,1,6250,600)
                .chancedOutput(dustImpure,Bismuth,1,5000,500)
                .chancedOutput(dustImpure,Bismuth,1,3750,400)
                .fluidOutputs(BismuthWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(BismuthWaste.getFluid(1000))
                .chancedOutput(dustImpure,Bismuth,1,7500,750)
                .chancedOutput(dustImpure,Bismuth,1,5000,500)
                .chancedOutput(dustImpure,Bismuth,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Nitrate Reduction
        //9 BiNO3 + 10 Al = 3 Bi3N + 5 Al2O3 + 6 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthNitrate,45)
                .input(dust,HighPurityAluminium,10)
                .output(dust,BismuthINitride,12)
                .output(dust,AluminiumIIIOxide,25)
                .fluidOutputs(NitrogenDioxide.getFluid(6000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //9 Bi(NO3)5 + 34 Al = 3 Bi3N + 17 Al2O3 + 42 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthPentanitrate,189)
                .input(dust,HighPurityAluminium,34)
                .output(dust,BismuthINitride,12)
                .output(dust,AluminiumIIIOxide,85)
                .fluidOutputs(NitrogenDioxide.getFluid(42000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Nitride Oxidation
        //2 Bi3N + 13 O = 3 Bi2O3 + 2 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthINitride,8)
                .fluidInputs(Oxygen.getFluid(13000))
                .output(dust,BismuthIIIOxide,15)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //2 BiN + 9 O = Bi2O5 + 2 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthIIINitride,4)
                .fluidInputs(Oxygen.getFluid(9000))
                .output(dust,BismuthVOxide,7)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Oxide Reduction
        //Bi2O5 + 10 Li = 2 Bi + 5 Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthVOxide,7)
                .input(dust,HighPurityLithium,10)
                .output(dust,Bismuth,2)
                .output(dust,LithiumOxide,15)
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Bi2O5 + 5 Mg = 2 Bi + 5 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthVOxide,7)
                .input(dust,HighPurityMagnesium,5)
                .output(dust,Bismuth,2)
                .output(dust,Magnesia,10)
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Bi2O3 + 3 Mg = 2 Bi + 3 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,5)
                .input(dust,Magnesium,3)
                .output(dust,Bismuth,2)
                .output(dust,Magnesia,6)
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Bi2O3 + 2 Al = 2 Bi + Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,5)
                .input(dust,Aluminium,2)
                .output(dust,Bismuth,2)
                .output(dust,AluminiumIIIOxide,5)
                .EUt(VA[HV])
                .duration(600)
                .buildAndRegister();

        //REFINEMENT PROCESS
    }
}
