package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.api.unification.material.GJMiscMaterials.NitrousAcid;
import static gregsjourney.api.unification.material.GJOreMaterials.Livingstonite;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.HighPurityAluminium;
import static gregsjourney.api.unification.material.materiallines.AntimonyLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LeadLineMaterials.LeadDichloride;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.HighPurityLithium;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.MagnesiumLineMaterials.HighPurityMagnesium;
import static gregsjourney.api.unification.material.materiallines.MercuryLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.SiliconLineMaterials.HighPuritySilicon;
import static gregsjourney.api.unification.material.materiallines.TinLineMaterials.TinTetrachloride;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class AntimonyLine {

    private AntimonyLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Stibnite Cracking
        //Sb2S3 + 32 HNO3 = 2 Sb(NO3)5 + 22 NO2 + 3 SO2 + 16 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Stibnite,5)
                .fluidInputs(NitrousAcid.getFluid(32000))
                .output(dust,AntimonyPentanitrate,42)
                .fluidOutputs(StibniteSlag.getFluid(500),SulfurDioxide.getFluid(3000),NitrogenDioxide.getFluid(22000),Steam.getFluid(16000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        //Livingstonite Cracking
        //HgSb4S8 + 80 HNO3 = 4 Sb(NO3)5 + Hg(NO3)4 + 56 NO2 + 8 SO2 + 40 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Livingstonite,13)
                .fluidInputs(NitricAcid.getFluid(80000))
                .output(dust,AntimonyPentanitrate,84)
                .output(dust,MercuryTetranitrate,17)
                .fluidOutputs(LivingstoniteSlag.getFluid(1300),SulfurDioxide.getFluid(8000),NitrogenDioxide.getFluid(56000),Steam.getFluid(48000))
                .EUt(VA[HV])
                .duration(400)
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

        //Redox Reactions in the RF
        //Nitrate -> Nitride
        //2 Sb(NO3)5 + 7 C = 2 SbN + 8 NO2 + 7 CO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyPentanitrate,42)
                .input(dust,Carbon,7)
                .output(dust,AntimonyNitride,4)
                .fluidOutputs(NitrogenDioxide.getFluid(8000),CarbonDioxide.getFluid(7000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Nitride -> Oxide
        //2 SbN + 9 O = Sb2O5 + 2 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyNitride,4)
                .fluidInputs(Oxygen.getFluid(9000))
                .output(dust,AntimonyVOxide,7)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Oxide Reduction
        //Sb2O5 + 10 Li = 2 Sb + 5 Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,7)
                .input(dust,HighPurityLithium,10)
                .output(dust,Antimony,2)
                .output(dust,LithiumOxide,15)
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Sb2O5 + 5 Mg = 2 Sb + 5 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,7)
                .input(dust,HighPurityMagnesium,5)
                .output(dust,Antimony,2)
                .output(dust,Magnesia,5)
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //3 Sb2O5 + 10 Al = 6 Sb + 5 Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,21)
                .input(dust,HighPurityAluminium,10)
                .output(dust,Antimony,6)
                .output(dust,AluminiumIIIOxide,25)
                .EUt(VA[HV])
                .duration(600)
                .buildAndRegister();

        //2 Sb2O5 + 5 Si = 4 Sb + 5 SiO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AntimonyVOxide,14)
                .input(dust,HighPuritySilicon,5)
                .output(dust,Antimony,4)
                .output(dust,SiliconDioxide,15)
                .EUt(VA[EV])
                .duration(800)
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
                .fluidOutputs(Iron3Chloride.getFluid(100),TinTetrachloride.getFluid(100),LeadDichloride.getFluid(100))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
