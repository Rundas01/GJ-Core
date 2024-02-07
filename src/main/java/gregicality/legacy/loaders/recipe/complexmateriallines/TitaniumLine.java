package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.CalciumLineMaterials.CalciumSlag;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.PotassiumLineMaterials.PotassiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.TitaniumLineMaterials.TitaniumSlag;
import static gregicality.legacy.api.unification.material.materiallines.TitaniumLineMaterials.TitaniumWaste;
import static gregicality.legacy.api.unification.material.materiallines.VanadiumLineMaterials.VanadiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class TitaniumLine {
    private TitaniumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Perovskite Cracking
        //CaTiO3 + 6 HCl = CaCl2 + TiCl4 + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Perovskite,5)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,CalciumChloride,3)
                .fluidOutputs(PerovskiteSlag.getFluid(500),TitaniumTetrachloride.getFluid(1000),Steam.getFluid(3000))
                .EUt(VA[EV])
                .duration(240)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Titanomagnetite Cracking
        //2 FeTiO4 + 14 HClO4 = 2 FeCl3 + 2 TiCl4 + 57 O + 7 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Titanomagnetite,12)
                .fluidInputs(PerchloricAcid.getFluid(14000))
                .fluidOutputs(TitanomagnetiteSlag.getFluid(1200),TitaniumTetrachloride.getFluid(2000),Iron3Chloride.getFluid(2000),Oxygen.getFluid(57000),Steam.getFluid(7000))
                .EUt(VA[EV])
                .duration(240)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Vanadiferrous Titanomagnetite Cracking
        //VFeTiO4 + 12 HClO4 = VCl5 + FeCl3 + TiCl4 + 23 O2 + 6 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,VanadiferousTitanomagnetite,7)
                .fluidInputs(PerchloricAcid.getFluid(12000))
                .output(dust,VanadiumPentachloride,5)
                .fluidOutputs(VanadiferrousTitanomagnetiteSlag.getFluid(700),TitaniumTetrachloride.getFluid(1000),Oxygen.getFluid(46000),Steam.getFluid(6000))
                .EUt(VA[EV])
                .duration(240)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Ilmenite Cracking
        //FeTiO3 + 7 HCl = FeCl3 + TiCl4 + 3 H2O + H
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Ilmenite,5)
                .fluidInputs(HydrochloricAcid.getFluid(7000))
                .fluidOutputs(IlmeniteSlag.getFluid(500),TitaniumTetrachloride.getFluid(1000),Iron3Chloride.getFluid(1000),Hydrogen.getFluid(1000),Steam.getFluid(3000))
                .EUt(VA[EV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Titanite Cracking
        //KTiSiO5 + 6 HCl = KCl + TiCl4 + SiO2 + 3 H2O + Cl
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Titanite,8)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,RockSalt,2)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(TitaniteSlag.getFluid(800),TitaniumTetrachloride.getFluid(1000),Chlorine.getFluid(1000),Steam.getFluid(9000))
                .EUt(VA[EV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PerovskiteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TitaniumSlag.getFluid(100),CalciumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TitanomagnetiteSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TitaniumSlag.getFluid(100),IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(VanadiferousTitanomagnetiteSlag.getFluid(700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TitaniumSlag.getFluid(100),VanadiumSlag.getFluid(100),IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(IlmeniteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TitaniumSlag.getFluid(100),IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TitaniteSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(TitaniumSlag.getFluid(100),PotassiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TitaniumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Titanium)
                .chancedOutput(dustImpure,Titanium,1,8750,800)
                .chancedOutput(dustImpure,Titanium,1,7500,700)
                .chancedOutput(dustImpure,Titanium,1,6250,600)
                .chancedOutput(dustImpure,Titanium,1,5000,500)
                .chancedOutput(dustImpure,Titanium,1,3750,400)
                .fluidOutputs(TitaniumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(TitaniumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Titanium,1,7500,750)
                .chancedOutput(dustImpure,Titanium,1,5000,500)
                .chancedOutput(dustImpure,Titanium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
