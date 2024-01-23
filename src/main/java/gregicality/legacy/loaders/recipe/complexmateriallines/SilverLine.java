package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.PotassiumNitrateSolution;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumCarbonateSolution;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.AntimonySlag;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.AntimonyWaste;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.SilverLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class SilverLine {
    private SilverLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Proustite Cracking
        //2 Ag3AsS3 + 13 O2 = 3 Ag2O3 + As2O5 + 6 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Proustite,14)
                .fluidInputs(Oxygen.getFluid(26000))
                .output(dust,SilverIIIOxide,15)
                .output(dust,ArsenicVOxide,10)
                .fluidOutputs(ProustiteSlag.getFluid(1400),SulfurDioxide.getFluid(6000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Stephanite Cracking
        //2 Ag5SbS4 + 18 O2 = 5 Ag2O3 + Sb2O5 + 8 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Stephanite,20)
                .fluidInputs(Oxygen.getFluid(36000))
                .output(dust,SilverIIIOxide,25)
                .output(dust,AntimonyVOxide,10)
                .fluidOutputs(StephaniteSlag.getFluid(2000),SulfurDioxide.getFluid(8000))
                .EUt(VA[HV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Acanthite Cracking
        //2 Ag2S + 5 O2 = 2 Ag2O3 + 2 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Acanthite,6)
                .fluidInputs(Oxygen.getFluid(10000))
                .output(dust,SilverIIIOxide,25)
                .fluidOutputs(AcanthiteSlag.getFluid(600),SulfurDioxide.getFluid(2000))
                .EUt(VA[HV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Aguilarite Cracking
        //Ag4SeS + 11 O = 2 Ag2O3 + SeO3 + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Aguilarite,6)
                .fluidInputs(Oxygen.getFluid(11000))
                .output(dust,SilverIIIOxide,10)
                .output(dust,SeleniumTrioxide,4)
                .fluidOutputs(AguilariteSlag.getFluid(600),SulfurDioxide.getFluid(1000))
                .EUt(VA[HV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ProustiteSlag.getFluid(700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(SilverSlag.getFluid(300),ArsenicSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(StephaniteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(SilverSlag.getFluid(500),AntimonySlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(AcanthiteSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(SilverSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SilverSlag.getFluid(1000))
                .output(dustImpure,Silver)
                .chancedOutput(dustImpure,Silver,1,8750,700)
                .chancedOutput(dustImpure,Silver,1,7500,600)
                .chancedOutput(dustImpure,Silver,1,6250,500)
                .chancedOutput(dustImpure,Silver,1,5000,400)
                .chancedOutput(dustImpure,Silver,1,3750,300)
                .chancedOutput(dustImpure,Silver,1,2500,200)
                .chancedOutput(dustImpure,Silver,1,1250,100)
                .fluidOutputs(AntimonyWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SilverWaste.getFluid(18000))
                .output(dustImpure,Silver)
                .output(dustImpure,Silver)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
