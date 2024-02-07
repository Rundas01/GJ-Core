package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Livingstonite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregicality.legacy.api.unification.material.materiallines.MercuryLineMaterials.MercuryOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class AntimonyLine {

    private AntimonyLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Stibnite Cracking
        //Sb2S3 + 11 O = Sb2O5 + 3 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Stibnite,5)
                .fluidInputs(Oxygen.getFluid(11000))
                .output(dust,AntimonyVOxide,7)
                .fluidOutputs(StibniteSlag.getFluid(500),SulfurDioxide.getFluid(3000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Livingstonite Cracking
        //HgSb4S8 + 27 O = HgO + 2 Sb2O5 + 8 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Livingstonite,13)
                .fluidInputs(Oxygen.getFluid(27000))
                .output(dust,AntimonyVOxide,14)
                .output(dust,MercuryOxide,2)
                .fluidOutputs(LivingstoniteSlag.getFluid(1300),SulfurDioxide.getFluid(8000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(StibniteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AntimonySlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(LivingstoniteSlag.getFluid(1300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AntimonySlag.getFluid(400),MercurySlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AntimonySlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Antimony)
                .chancedOutput(dustImpure,Antimony,1,8750,800)
                .chancedOutput(dustImpure,Antimony,1,7500,700)
                .chancedOutput(dustImpure,Antimony,1,6250,600)
                .chancedOutput(dustImpure,Antimony,1,5000,500)
                .chancedOutput(dustImpure,Antimony,1,3750,400)
                .fluidOutputs(AntimonyWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(AntimonyWaste.getFluid(1000))
                .chancedOutput(dustImpure,Antimony,1,7500,750)
                .chancedOutput(dustImpure,Antimony,1,5000,500)
                .chancedOutput(dustImpure,Antimony,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //Oxide Reduction
        //Sb2O5 + 10 Li = 2 Sb + 5 Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,7)
                .input(dust,Lithium,10)
                .output(dust,Antimony,2)
                .output(dust,LithiumOxide,15)
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Sb2O5 + 5 Mg = 2 Sb + 5 MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,7)
                .input(dust,Magnesium,5)
                .output(dust,Antimony,2)
                .output(dust,Magnesia,5)
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //3 Sb2O5 + 10 Al = 6 Sb + 5 Al2O3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,21)
                .input(dust,Aluminium,10)
                .output(dust,Antimony,6)
                .output(dust,AluminiumIIIOxide,25)
                .EUt(VA[HV])
                .duration(600)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //2 Sb2O5 + 5 Si = 4 Sb + 5 SiO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,14)
                .input(dust,Carbon,5)
                .output(dust,Antimony,4)
                .output(dust,SiliconDioxide,15)
                .EUt(VA[EV])
                .duration(800)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        //PURIFICATION_PROCESS
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Antimony,2)
                .fluidInputs(Chlorine.getFluid(3000))
                .fluidOutputs(HighPurityAntimony.getFluid(144),ChloridicAntimonyTailings.getFluid(3000))
                .EUt(VA[EV])
                .duration(840)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ChloridicAntimonyTailings.getFluid(3000))
                .fluidOutputs(Iron3Chloride.getFluid(100),TinChloride.getFluid(100),LeadChloride.getFluid(100))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
