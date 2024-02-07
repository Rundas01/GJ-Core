package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.CalciumOxide;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumOxide;
import static gregicality.legacy.api.unification.material.materiallines.AntimonyLineMaterials.AntimonySlag;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicSlag;
import static gregicality.legacy.api.unification.material.materiallines.CalciumLineMaterials.CalciumSlag;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.SilverLineMaterials.SilverSlag;
import static gregicality.legacy.api.unification.material.materiallines.SodiumLineMaterials.SodiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class BoronLine {
    private BoronLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Kernite Cracking
        //Na2B4O6(OH)2(H2O)3 + 2 H2SO4 = Na2O + 4 H3BO3 + 2 SO2 + O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Kernite,25)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust,BoricAcid,28)
                .output(dust,SodiumOxide,3)
                .fluidOutputs(KerniteSlag.getFluid(2500),SulfurDioxide.getFluid(2000),Oxygen.getFluid(2000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Colemanite Cracking
        //Ca2B6O11(H2O)5 + 4 H2SO4 = 2 CaO + 6 H3BO3 + 4 SO2 + 2 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Colemanite,34)
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .output(dust,BoricAcid,42)
                .output(dust,CalciumOxide,4)
                .fluidOutputs(ColemaniteSlag.getFluid(3400),SulfurDioxide.getFluid(4000),Oxygen.getFluid(4000))
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(KerniteSlag.getFluid(2500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BoronSlag.getFluid(400),SodiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ColemaniteSlag.getFluid(3400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BoronSlag.getFluid(600),CalciumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(UlexiteSlag.getFluid(4000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BoronSlag.getFluid(500),SodiumSlag.getFluid(100),CalciumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BoronSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Boron)
                .chancedOutput(dustImpure,Boron,1,8750,800)
                .chancedOutput(dustImpure,Boron,1,7500,700)
                .chancedOutput(dustImpure,Boron,1,6250,600)
                .chancedOutput(dustImpure,Boron,1,5000,500)
                .chancedOutput(dustImpure,Boron,1,3750,400)
                .chancedOutput(dustImpure,Boron,1,2500,300)
                .chancedOutput(dustImpure,Boron,1,1250,200)
                .chancedOutput(dustImpure,Boron,1,0,100)
                .fluidOutputs(BoronWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(BoronWaste.getFluid(1000))
                .chancedOutput(dustImpure,Boron,1,7500,750)
                .chancedOutput(dustImpure,Boron,1,5000,500)
                .chancedOutput(dustImpure,Boron,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
