package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregicality.legacy.api.unification.material.materiallines.BariumLineMaterials.BariumSlag;
import static gregicality.legacy.api.unification.material.materiallines.BariumLineMaterials.BariumWaste;
import static gregicality.legacy.api.unification.material.materiallines.BismuthLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BismuthLine {
    private BismuthLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Bismuthinite Cracking
        //Bi2S3 + 9 O = Bi2O3 + 3 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bismuthinite,5)
                .fluidInputs(Oxygen.getFluid(9000))
                .output(dust,BismuthIIIOxide,5)
                .fluidOutputs(BismuthiniteSlag.getFluid(500),SulfurDioxide.getFluid(3000))
                .EUt(VA[IV])
                .duration(380)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Polarite Cracking
        //2 PdBiPb + 7 O = 2 PdO + Bi2O3 + 2 PbO
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Polarite,6)
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust,BismuthIIIOxide,5)
                .output(dust,PalladiumOxide,4)
                .output(dust,LeadOxide,4)
                .fluidOutputs(PolariteSlag.getFluid(300))
                .EUt(VA[IV])
                .duration(400)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BismuthiniteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
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

        //(Redox) Reactions in the EBF
        //Oxide Reduction
        //Bi2O3 + 6 Li = 2 Bi + 3 Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,5)
                .input(dust,Lithium,6)
                .output(dust,Bismuth,2)
                .output(dust,LithiumOxide,9)
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Bi2O3 + 3 Mg = 2 Bi + 3 MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,5)
                .input(dust,Magnesium,3)
                .output(dust,Bismuth,2)
                .output(dust,Magnesia,6)
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //Bi2O3 + 2 Al = 2 Bi + Al2O3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,5)
                .input(dust,Aluminium,2)
                .output(dust,Bismuth,2)
                .output(dust,AluminiumIIIOxide,5)
                .EUt(VA[HV])
                .duration(600)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //2 Bi2O3 + 3 Si = 4 Bi + 3 SiO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,10)
                .input(dust,Silicon,3)
                .output(dust,Bismuth,4)
                .output(dust,SiliconDioxide,9)
                .EUt(VA[EV])
                .duration(800)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        //2 Bi2O3 + 3 C = 4 Bi + 3 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BismuthIIIOxide,10)
                .input(dust,Carbon,3)
                .output(dust,Bismuth,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[IV])
                .duration(1000)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //REFINEMENT PROCESS
    }
}
