package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicVOxide;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.CopperSulfate;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class LeadLine {
    private LeadLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Galena Cracking
        //PbS + 3 O = PbO + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Galena,2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust,LeadOxide,2)
                .fluidOutputs(GalenaSlag.getFluid(200),SulfurDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Cerussite Cracking
        //PbCO3 + 2 HNO3 = Pb(NO3)2 + H2O + CO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cerussite,5)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust,LeadDisulfate,9)
                .fluidOutputs(CerussiteSlag.getFluid(500),CarbonDioxide.getFluid(1000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Anglesite Cracking
        //PbSO4 + 2 HNO3 = Pb(NO3)2 + SO2 + H2O + O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Anglesite,6)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust,LeadDisulfate,9)
                .fluidOutputs(AnglesiteSlag.getFluid(600),SulfurDioxide.getFluid(1000),Oxygen.getFluid(1000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Mimetite Cracking
        //2 Pb5(AsO4)3Cl + 22 HNO3 = 10 Pb(NO3)2 + 3 As2O5 + Cl2 + 2 NO2 + 11 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Mimetite,42)
                .fluidInputs(NitricAcid.getFluid(22000))
                .output(dust,LeadDinitrate,90)
                .output(dust,ArsenicVOxide,21)
                .fluidOutputs(MimetiteSlag.getFluid(4200),NitrogenDioxide.getFluid(2000),Chlorine.getFluid(2000),Steam.getFluid(11000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Pyromorphite Cracking
        //4 Pb5(PO4)3Cl + 44 HNO3 = 20 Pb(NO3)2 + 3 P4O10 + 2 Cl2 + 4 NO2 + 22 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pyromorphite,84)
                .fluidInputs(NitricAcid.getFluid(44000))
                .output(dust,LeadDinitrate,180)
                .output(dust,PhosphorusPentoxide,42)
                .fluidOutputs(PyromorphiteSlag.getFluid(8400),NitrogenDioxide.getFluid(4000),Chlorine.getFluid(4000),Steam.getFluid(22000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Descloizite Cracking
        //2 PbZn(VO4)OH + 9 H2SO4 = 2 PbSO4 + 2 ZnSO4 + V2(SO4)5 + 10 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Descloizite,18)
                .fluidInputs(SulfuricAcid.getFluid(9000))
                .output(dust,LeadSulfate,12)
                .output(dust,ZincSulfate,12)
                .output(dust,VanadiumVSulfate,27)
                .fluidOutputs(DescloiziteSlag.getFluid(1800),Steam.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Mottramite Cracking
        //2 PbCu(VO4)OH + 9 H2SO4 = 2 PbSO4 + 2 CuSO4 + V2(SO4)5 + 10 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Mottramite,18)
                .fluidInputs(SulfuricAcid.getFluid(9000))
                .output(dust,LeadSulfate,12)
                .output(dust,CopperSulfate,12)
                .output(dust,VanadiumVSulfate,27)
                .fluidOutputs(MottramiteSlag.getFluid(1800),Steam.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(GalenaSlag.getFluid(200))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LeadSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(CerussiteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LeadSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(AnglesiteSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LeadSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LeadSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Lead)
                .chancedOutput(dustImpure,Lead,1,8750,800)
                .chancedOutput(dustImpure,Lead,1,7500,700)
                .chancedOutput(dustImpure,Lead,1,6250,600)
                .chancedOutput(dustImpure,Lead,1,5000,500)
                .chancedOutput(dustImpure,Lead,1,3750,400)
                .fluidOutputs(LeadWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(LeadWaste.getFluid(1000))
                .chancedOutput(dustImpure,Lead,1,7500,750)
                .chancedOutput(dustImpure,Lead,1,5000,500)
                .chancedOutput(dustImpure,Lead,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
