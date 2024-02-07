package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumTrichloride;
import static gregsjourney.api.unification.material.materiallines.CalciumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.SodiumLineMaterials.SodiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class CalciumLine {
    private CalciumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Apatite Cracking
        //4 Ca5(PO4)3Cl + 44 HNO3 = 20 Ca(NO3)2 + 3 P4O10 + 2 Cl2 + 4 NO2 + 22 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Apatite,84)
                .fluidInputs(NitricAcid.getFluid(44000))
                .output(dust,CalciumDinitrate,180)
                .output(dust,PhosphorusPentoxide,42)
                .fluidOutputs(ApatiteSlag.getFluid(8400),Chlorine.getFluid(4000),NitrogenDioxide.getFluid(4000),Steam.getFluid(22000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Tricalcium Phosphate Cracking
        //Ca3(PO4)2 + 3 H2SO4 = 3 CaSO4 + 2 H3PO4
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,TricalciumPhosphate,13)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust,CalciumSulfate,18)
                .fluidOutputs(TricalciumPhosphateSlag.getFluid(1300),PhosphoricAcid.getFluid(2000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Zeolite Cracking
        //NaCa4Si27Al9(H2O)28O72 + 36 HCl = 4 CaCl2 + NaCl + 27 SiO2 + 9 AlCl3 + 46 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Zeolite,197)
                .fluidInputs(HydrochloricAcid.getFluid(36000))
                .output(dust,CalciumChloride,12)
                .output(dust,AluminiumTrichloride,36)
                .output(dust,Salt,2)
                .output(dust,SiliconDioxide,81)
                .fluidOutputs(ZeoliteSlag.getFluid(19700),Steam.getFluid(42000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Gypsum Cracking
        //CaSO4(H2O)2 + 2 HCl = CaCl2 + SO3 + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Gypsum,12)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust,CalciumChloride,3)
                .fluidOutputs(GypsumSlag.getFluid(1200),SulfurTrioxide.getFluid(1000),Steam.getFluid(3000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Fluoroapatite Cracking
        //4 Ca5(PO4)3F + 44 HNO3 = 20 Ca(NO3)2 + 3 P4O10 + 2 F2 + 4 NO2 + 22 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Fluorapatite,84)
                .fluidInputs(NitricAcid.getFluid(44000))
                .output(dust,CalciumDinitrate,180)
                .output(dust,PhosphorusPentoxide,42)
                .fluidOutputs(FluorapatiteSlag.getFluid(8400),Fluorine.getFluid(4000),NitrogenDioxide.getFluid(4000),Steam.getFluid(22000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Chloroapatite Cracking
        //4 Ca5(PO4)3Cl + 44 HNO3 = 20 Ca(NO3)2 + 3 P4O10 + 2 Cl2 + 4 NO2 + 22 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Chlorapatite,84)
                .fluidInputs(NitricAcid.getFluid(44000))
                .output(dust,CalciumDinitrate,180)
                .output(dust,PhosphorusPentoxide,42)
                .fluidOutputs(ChlorapatiteSlag.getFluid(8400),Chlorine.getFluid(4000),NitrogenDioxide.getFluid(4000),Steam.getFluid(22000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Hydroxyapatite Cracking
        //Ca5(PO4)3OH + 5 H2SO4 = 3 H3PO4 + 5 CaSO4 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Hydroxyapatite,22)
                .fluidInputs(SulfuricAcid.getFluid(5000))
                .output(dust,CalciumSulfate,30)
                .fluidOutputs(HydroxyapatiteSlag.getFluid(2200),PhosphoricAcid.getFluid(3000),Steam.getFluid(1000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ApatiteSlag.getFluid(2100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CalciumSlag.getFluid(500))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TricalciumPhosphateSlag.getFluid(1300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CalciumSlag.getFluid(300))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ZeoliteSlag.getFluid(19700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CalciumSlag.getFluid(400),AluminiumSlag.getFluid(900),SodiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(GypsumSlag.getFluid(1200))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CalciumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CalciumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Iron)
                .chancedOutput(dustImpure,Calcium,1,8750,800)
                .chancedOutput(dustImpure,Calcium,1,7500,700)
                .chancedOutput(dustImpure,Calcium,1,6250,600)
                .chancedOutput(dustImpure,Calcium,1,5000,500)
                .chancedOutput(dustImpure,Calcium,1,3750,400)
                .fluidOutputs(CalciumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CalciumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Calcium,1,7500,750)
                .chancedOutput(dustImpure,Calcium,1,5000,500)
                .chancedOutput(dustImpure,Calcium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Nitrate to Nitride Reduction
        //3 Ca(NO3)2 + 10 C = Ca3N2 + 10 CO + 4 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CalciumDinitrate,39)
                .input(dust,Carbon,10)
                .output(dust,CalciumIINitride,5)
                .fluidOutputs(CarbonMonoxide.getFluid(10000),NitrogenDioxide.getFluid(4000))
                .EUt(VA[MV])
                .duration(600)
                .buildAndRegister();

        //Sulfate to Sulfide Reduction
        //CaSO4 + 2 C = CaS + 2 CO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CalciumSulfate,6)
                .input(dust,Carbon,2)
                .output(dust,CalciumSulfide,4)
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(600)
                .buildAndRegister();

        //Chloride to Oxide Conversion
        //CaCl2 + O = CaO + Cl2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CalciumChloride,3)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust,CalciumOxide,2)
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(VA[MV])
                .duration(600)
                .buildAndRegister();

        //Oxide Reduction
        //CaO + 2 Li = Ca + Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CalciumOxide,2)
                .input(dust,Lithium,2)
                .output(dust,Calcium)
                .output(dust,LithiumOxide,3)
                .EUt(VA[MV])
                .duration(600)
                .buildAndRegister();

        //PURIFICATION PROCESS
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Calcium,2)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(HighPurityCalcium.getFluid(144),Hydrogen.getFluid(3000),SulfaticCalciumTailings.getFluid(3000))
                .EUt(VA[EV])
                .duration(820)
                .buildAndRegister();

        /*CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SulfaticCalciumTailings.getFluid(3000))
                .output(dust,MagnesiumSulfate)
                .output(dust,StrontiumSulfate)
                .output(dust,RadiumSulfate)
                .fluidOutputs(CalciumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(300)
                .buildAndRegister();*/
    }
}
