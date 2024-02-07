package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.*;
import static gregsjourney.api.unification.material.GJOreMaterials.Witherite;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.HighPurityAluminium;
import static gregsjourney.api.unification.material.materiallines.BariumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BariumLine {
    private BariumLine(){}
    public static void init(){
        //ELEMENT ACQUISITION
        //Barite Cracking
        //BaSO4 + 4 CO = BaS + 4 CO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Barite,6)
                .fluidInputs(CarbonMonoxide.getFluid(4000))
                .output(dust,BariumSulfide,2)
                .fluidOutputs(BariteSlag.getFluid(600),CarbonDioxide.getFluid(4000))
                .EUt(VA[HV])
                .duration(480)
                .buildAndRegister();

        //Witherite Cracking
        //BaCO3 + H2SO4 = BaS + CO2 + H2O + 2 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Witherite,5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,BariumSulfide,2)
                .fluidOutputs(WitheriteSlag.getFluid(500),CarbonDioxide.getFluid(1000),Oxygen.getFluid(4000),Steam.getFluid(1000))
                .EUt(VA[HV])
                .duration(480)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BariteSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BariumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(WitheriteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(BariumSlag.getFluid(100))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BariumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Barium)
                .chancedOutput(dustImpure,Barium,1,8750,800)
                .chancedOutput(dustImpure,Barium,1,7500,700)
                .chancedOutput(dustImpure,Barium,1,6250,600)
                .chancedOutput(dustImpure,Barium,1,5000,500)
                .chancedOutput(dustImpure,Barium,1,3750,400)
                .fluidOutputs(BariumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(BariumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Barium,1,7500,750)
                .chancedOutput(dustImpure,Barium,1,5000,500)
                .chancedOutput(dustImpure,Barium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Sulfide -> Carbonate
        //BaS + CO2 + H2O = BaCO3 + H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BariumSulfide,2)
                .fluidInputs(CarbonDioxide.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dust,BariumCarbonate,5)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[HV])
                .duration(240)
                .buildAndRegister();

        //Carbonate -> Oxide
        //BaCO3 = BaO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumCarbonate,5)
                .output(dust,BariumOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[EV])
                .duration(460)
                .blastFurnaceTemp(3100)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Barium Oxide Reduction
        //3 BaO + 2 Al = 3 Ba + Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,BariumOxide,6)
                .input(dust,HighPurityAluminium,2)
                .output(dust,Barium,3)
                .output(dust,AluminiumIIIOxide,5)
                .EUt(VA[EV])
                .duration(200)
                .buildAndRegister();

        //BARIUM REFINING
        //Barium Chlorate Production
        //3 Ba + 8 HClO3 = 3 Ba(ClO3)2 + 2 ClO + 4 H2O
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Barium,3)
                .fluidInputs(ChloricAcid.getFluid(8000))
                .output(dust,BariumChlorate,27)
                .fluidOutputs(ChlorineMonoxide.getFluid(2000))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[HV])
                .duration(150)
                .buildAndRegister();

        //Barium Chlorate Disproposition
        //4 Ba(ClO3)2 = BaCl2 + 3 Ba(ClO4)2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BariumChlorate,36)
                .output(dust,BariumPerchlorate,33)
                .output(dust,BariumChloride,3)
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        //Barium Perchlorate Decomposition
        //Ba(ClO4)2 = BaCl2 + 4 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumPerchlorate,11)
                .output(dust,BariumChloride,3)
                .fluidOutputs(Oxygen.getFluid(8000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(800)
                .buildAndRegister();

        //Barium Chloride Solution Mixing
        MIXER_RECIPES.recipeBuilder()
                .input(dust,BariumChloride)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BariumChlorideSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(60)
                .buildAndRegister();

        //Electrolyzing Barium Chloride into HP Barium
        //BaCl2 + H2O = Ba + 2 HCl + O
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Barium)
                .notConsumable(stick,Graphite)
                .fluidInputs(BariumChlorideSolution.getFluid(1000))
                .output(dust,HighPurityBarium)
                .fluidOutputs(HydrogenChloride.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
