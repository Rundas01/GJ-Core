package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Witherite;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregicality.legacy.api.unification.material.materiallines.BariumLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BariumLine {
    private BariumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
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

        //Desulfurizing of Barium Sulfide
        //BaS + 2 H2CO3 = Ba(CO3)2 + H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BariumSulfide,2)
                .fluidInputs(CarbonicAcid.getFluid(2000))
                .output(dust,BariumCarbonate,9)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[LV])
                .duration(380)
                .buildAndRegister();

        //(Redox) Reactions in the EBF
        //Sulfate Decomposition
        //BaSO4 = BaS + 2 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Barite,6)
                .output(dust,BariumSulfide,2)
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VA[HV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Carbonate Decomposition
        //BaCO3 = BaO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Witherite,5)
                .output(dust,BariumOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Barium Oxide Reduction
        //BaO + 2 Li = Ba + Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumOxide,3)
                .input(dust,Lithium,2)
                .output(dust,Barium)
                .output(dust,LithiumOxide,3)
                .EUt(VA[EV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //BARIUM REFINING
        //Barium Chlorate Production
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Barium)
                .fluidInputs(ChloricAcid.getFluid(1000))
                .output(dust,BariumChlorate,9)
                .fluidOutputs(ChlorineMonoxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[HV])
                .duration(150)
                .buildAndRegister();

        //Barium Chlorate Disproposition
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumChlorate,36)
                .output(dust,BariumPerchlorate,33)
                .output(dust,BariumChloride,3)
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(720)
                .buildAndRegister();

        //Barium Perchlorate Decomposition
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
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Barium)
                .notConsumable(stick,Graphite)
                .fluidInputs(BariumChlorideSolution.getFluid(2000))
                .output(dust,HighPurityBarium,2)
                .fluidOutputs(HydrogenChloride.getFluid(4000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
