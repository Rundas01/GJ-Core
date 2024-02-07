package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.api.unification.material.GJOreMaterials.Colemanite;
import static gregsjourney.api.unification.material.GJOreMaterials.Kernite;
import static gregsjourney.api.unification.material.materiallines.BariumLineMaterials.BariumOxide;
import static gregsjourney.api.unification.material.materiallines.BariumLineMaterials.HighPurityBarium;
import static gregsjourney.api.unification.material.materiallines.BoronLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.CalciumLineMaterials.CalciumOxide;
import static gregsjourney.api.unification.material.materiallines.CalciumLineMaterials.CalciumSlag;
import static gregsjourney.api.unification.material.materiallines.SodiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
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

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BoronSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Boron)
                .chancedOutput(dustImpure,Boron,1,8750,800)
                .chancedOutput(dustImpure,Boron,1,7500,700)
                .chancedOutput(dustImpure,Boron,1,6250,600)
                .chancedOutput(dustImpure,Boron,1,5000,500)
                .chancedOutput(dustImpure,Boron,1,3750,400)
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

        //Boric Acid -> Dioxoboric Acid
        //3 H3BO3 = H3B3O6 + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BoricAcid,21)
                .output(dust,DioxoboricAcid,12)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[HV])
                .duration(650)
                .buildAndRegister();

        //Boron Trinitrate
        //2 H3B3O6 + 18 NaNO3 = 6 B(NO3)3 + 9 Na2O + 3 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,DioxoboricAcid,24)
                .input(dust,SodiumNitrate,90)
                .output(dust,BoronTrinitrate,78)
                .output(dust,SodiumOxide,27)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[EV])
                .duration(800)
                .buildAndRegister();

        //Redox Recipes in the RF
        //Boron Nitride
        //2 B(NO3)3 + 5 C = 2 BN + 5 CO2 + 4 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BoronTrinitrate,26)
                .input(dust,Carbon,5)
                .output(dust,BoronNitride,4)
                .fluidOutputs(NitrogenDioxide.getFluid(4000),CarbonDioxide.getFluid(5000))
                .EUt(VA[HV])
                .duration(650)
                .buildAndRegister();

        //Boron Oxide
        //2 BN + 5 O = B2O3 + 2 NO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BoronNitride,4)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust,BoronIIIOxide,5)
                .fluidOutputs(NitricOxide.getFluid(2000))
                .EUt(VA[HV])
                .duration(650)
                .buildAndRegister();

        //Oxide Reduction
        //B2O3 + 3 Ba = 2 B + 3 BaO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BoronIIIOxide,5)
                .input(dust,HighPurityBarium,3)
                .output(dust,Boron,2)
                .output(dust,BariumOxide,6)
                .EUt(VA[HV])
                .duration(650)
                .buildAndRegister();
    }
}
