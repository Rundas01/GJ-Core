package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.AntimonySlag;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.SodiumAntimonate;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicSlag;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.SilverLineMaterials.SilverSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class CopperLine {
    private CopperLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Tetrahedrite Cracking
        //Cu3SbS3Fe + 20 HNO3 = 3 Cu(NO3)2 + Sb(NO3)3 + Fe(NO3)3 + 3 SO2 + 8 NO + 10 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Tetrahedrite,8)
                .fluidInputs(NitricAcid.getFluid(20000))
                .output(dust,CopperDinitrate,27)
                .output(dust,AntimonyTrinitrate,13)
                .output(dust,IronTrinitrate,13)
                .fluidOutputs(TetrahedriteSlag.getFluid(800),SulfurDioxide.getFluid(3000),NitrousOxide.getFluid(8000),Steam.getFluid(10000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Bornite Cracking
        //2 Cu5FeS4 + 29 O = 10 CuO + Fe2O3 + 8 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bornite,20)
                .fluidInputs(Oxygen.getFluid(29000))
                .output(dust,CopperOxide,20)
                .output(dust,IronIIIOxide,5)
                .fluidOutputs(BorniteSlag.getFluid(2000),SulfurDioxide.getFluid(8000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Chalcocite Cracking
        //Cu2S + 2 O2 = 2 CuO + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Chalcocite,3)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust,CopperOxide,4)
                .fluidOutputs(ChalcociteSlag.getFluid(300),SulfurDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Malachite Cracking
        //Cu2CH2O5 + 2 H2SO4 = 2 CuSO4 + CO2 + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Malachite,10)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust,CopperSulfate,12)
                .fluidOutputs(MalachiteSlag.getFluid(1000),CarbonDioxide.getFluid(1000),Steam.getFluid(3000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Enargite Cracking
        //2 Cu3AsS4 + 25 O = 6 CuO + As2O3 + 8 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Enargite,16)
                .fluidInputs(Oxygen.getFluid(25000))
                .output(dust,CopperOxide,12)
                .output(dust,ArsenicTrioxide,5)
                .fluidOutputs(EnargiteSlag.getFluid(1600),SulfurDioxide.getFluid(8000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Crookesite Cracking
        //Cu7TlAgSe4 + 36 HCl = 7 CuCl2 + TlCl3 + AgCl3 + 4 SeCl4 + 18 H2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Crookesite,13)
                .fluidInputs(HydrochloricAcid.getFluid(36000))
                .output(dust,CopperDichloride,21)
                .output(dust,SeleniumTetrachloride,20)
                .output(dust,ThalliumTrichloride,4)
                .output(dust,SilverTrichloride,4)
                .fluidOutputs(CrookesiteSlag.getFluid(1300),Hydrogen.getFluid(36000),Steam.getFluid(36000))
                .EUt(VA[EV])
                .duration(260)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Azurite Cracking
        //Cu3(CO3)2(OH)2 + 3 H2SO4 = 3 CuSO4 + 2 CO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Azurite,15)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust,CopperSulfate,18)
                .fluidOutputs(AzuriteSlag.getFluid(1500),CarbonDioxide.getFluid(2000),Steam.getFluid(4000))
                .EUt(VA[EV])
                .duration(260)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Stannite Cracking
        //2 Cu2FeSnS4 + 27 O = 4 CuO + 2 SnO2 + Fe2O3 + 8 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Stannite,16)
                .fluidInputs(Oxygen.getFluid(27000))
                .output(dust,CopperOxide,8)
                .output(dust,TinDioxide,6)
                .output(IronIIIOxide,5)
                .fluidOutputs(StanniteSlag.getFluid(1600),SulfurDioxide.getFluid(8000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TetrahedriteSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(300),IronSlag.getFluid(100),AntimonySlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BorniteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(500),IronSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ChalcociteSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(MalachiteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(EnargiteSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(300),ArsenicSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(CrookesiteSlag.getFluid(1300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CopperSlag.getFluid(700),SeleniumSlag.getFluid(400),ThalliumSlag.getFluid(100),SilverSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CopperSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Copper)
                .chancedOutput(dustImpure,Copper,1,8750,800)
                .chancedOutput(dustImpure,Copper,1,7500,700)
                .chancedOutput(dustImpure,Copper,1,6250,600)
                .chancedOutput(dustImpure,Copper,1,5000,500)
                .chancedOutput(dustImpure,Copper,1,3750,400)
                .chancedOutput(dustImpure,Copper,1,2500,300)
                .chancedOutput(dustImpure,Copper,1,1250,200)
                .chancedOutput(dustImpure,Copper,1,0,100)
                .fluidOutputs(CopperWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CopperWaste.getFluid(1000))
                .chancedOutput(dustImpure,Copper,1,7500,750)
                .chancedOutput(dustImpure,Copper,1,5000,500)
                .chancedOutput(dustImpure,Copper,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
