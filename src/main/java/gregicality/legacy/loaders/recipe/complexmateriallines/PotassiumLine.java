package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.CalciumOxide;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumOxide;
import static gregicality.legacy.api.unification.material.materiallines.CalciumLineMaterials.CalciumSlag;
import static gregicality.legacy.api.unification.material.materiallines.MagnesiumLineMaterials.MagnesiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.PotassiumLineMaterials.PotassiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.SodiumLineMaterials.SodiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class PotassiumLine {
    private PotassiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Kainite Cracking
        //KMg(SO4)Cl(H2O)3 + 4 HCl = KCl + MgCl2 + 5 H2O + SO2 + Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Kainite,17)
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .output(dust,PotassiumChloride,2)
                .output(dust,MagnesiumDichloride,3)
                .fluidOutputs(KainiteSlag.getFluid(1700),SulfurDioxide.getFluid(1000),Chlorine.getFluid(2000),Steam.getFluid(9000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Polyhalite Cracking
        //K2Ca2Mg(SO4)4(H2O)2 + 16 HCl = 2 KCl + 2 CaCl2 + MgCl2 + 4 SO2 + 10 H2O + 4 Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Polyhalite,31)
                .fluidInputs(HydrochloricAcid.getFluid(16000))
                .output(dust,RockSalt,4)
                .output(dust,CalciumDichloride,6)
                .output(dust,MagnesiumDichloride,3)
                .fluidOutputs(PolyhaliteSlag.getFluid(3100),SulfurDioxide.getFluid(4000),Chlorine.getFluid(8000),Steam.getFluid(26000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Langbeinite Cracking
        //K2Mg2(SO4)3 + 12 HCl = 2 KCl + 2 MgCl2 + 6 H2O + 3 SO2 + 3 Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Langbeinite,19)
                .fluidInputs(HydrochloricAcid.getFluid(12000))
                .output(dust,RockSalt,4)
                .output(dust,MagnesiumDichloride,6)
                .fluidOutputs(LangbeiniteSlag.getFluid(1900),SulfurDioxide.getFluid(3000),Chlorine.getFluid(6000),Steam.getFluid(18000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(KainiteSlag.getFluid(1700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(PotassiumSlag.getFluid(100),SodiumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PolyhaliteSlag.getFluid(3100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(PotassiumSlag.getFluid(200),CalciumSlag.getFluid(200),MagnesiumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Potassium)
                .chancedOutput(dustImpure,Potassium,1,8750,800)
                .chancedOutput(dustImpure,Potassium,1,7500,700)
                .chancedOutput(dustImpure,Potassium,1,6250,600)
                .chancedOutput(dustImpure,Potassium,1,5000,500)
                .chancedOutput(dustImpure,Potassium,1,3750,400)
                .fluidOutputs(PotassiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Potassium,1,7500,750)
                .chancedOutput(dustImpure,Potassium,1,5000,500)
                .chancedOutput(dustImpure,Potassium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
