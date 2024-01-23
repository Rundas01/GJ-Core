package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Carnotite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.PotassiumLineMaterials.PotassiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.VanadiumLineMaterials.VanadiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class UraniumLine {
    private UraniumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Bitchblende Cracking
        //(UO2)3ThPb + 12 H2SO4 = 3 U(SO4)2 + PbSO4 + Th(SO4)2 + 12 H2O + 3 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pitchblende,11)
                .fluidInputs(SulfuricAcid.getFluid(12000))
                .output(dust,UraniumDisulfate,33)
                .output(dust,ThoriumDisulfate,11)
                .output(dust,LeadSulfate,6)
                .fluidOutputs(PitchblendeSlag.getFluid(1100),NitrogenDioxide.getFluid(3000),Steam.getFluid(12000))
                .duration(240)
                .EUt(VA[LuV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Carnotite Cracking
        //K2(UO2)2(VO4)2(H2O)3 + 24 HNO3 = 2 KNO3 + 2 U(NO3)6 + 2 V(NO3)5 + 15 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Carnotite,27)
                .fluidInputs(NitricAcid.getFluid(24000))
                .output(dust,UraniumHexanitrate,50)
                .output(dust,VanadiumPentanitrate,42)
                .output(dust,PotassiumNitrate,10)
                .fluidOutputs(CarnotiteSlag.getFluid(2700),Steam.getFluid(15000))
                .duration(240)
                .EUt(VA[LuV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PitchblendeSlag.getFluid(1100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(UraniumSlag.getFluid(300),ThoriumSlag.getFluid(100),LeadSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(CarnotiteSlag.getFluid(2700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(UraniumSlag.getFluid(200),VanadiumSlag.getFluid(200),PotassiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(UraniumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Uranium238)
                .chancedOutput(dustImpure,Uranium238,1,8750,800)
                .chancedOutput(dustImpure,Uranium238,1,7500,700)
                .chancedOutput(dustImpure,Uranium238,1,6250,600)
                .chancedOutput(dustImpure,Uranium238,1,5000,500)
                .chancedOutput(dustImpure,Uranium238,1,3750,400)
                .chancedOutput(dustImpure,Uranium238,1,2500,300)
                .chancedOutput(dustImpure,Uranium238,1,1250,200)
                .chancedOutput(dustImpure,Uranium238,1,0,100)
                .fluidOutputs(UraniumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(UraniumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Uranium238,1,7500,750)
                .chancedOutput(dustImpure,Uranium238,1,5000,500)
                .chancedOutput(dustImpure,Uranium238,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //UO2 + C = U + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Uraninite,3)
                .input(dust,Carbon)
                .output(dust,Uranium235)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[IV])
                .duration(600)
                .blastFurnaceTemp(4000)
                .buildAndRegister();
    }
}
