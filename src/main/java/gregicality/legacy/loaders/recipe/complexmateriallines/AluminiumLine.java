package gregicality.legacy.loaders.recipe.complexmateriallines;

import gregtech.api.unification.OreDictUnifier;

import java.util.Collections;
import java.util.Objects;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FLOTATION_RECIPES;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ROASTING_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Cryolite;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class AluminiumLine {
    public static void init(){
        //removals();
        additions();
    }

    private static void removals() {
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(dust, Ruby)),null)));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(1920,Collections.singletonList(OreDictUnifier.get(crushed,Ruby,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(1920,Collections.singletonList(OreDictUnifier.get(crushed,Sapphire,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(1920,Collections.singletonList(OreDictUnifier.get(crushed,GreenSapphire,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
    }

    private static void additions() {
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Bauxite,3)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .fluidOutputs(ImpureSodiumAluminateSolution.getFluid(3000))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureSodiumAluminateSolution.getFluid(3000))
                .fluidOutputs(SodiumAluminateSolution.getFluid(2000),RedMud.getFluid(1000))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumAluminateSolution.getFluid(2000),DistilledWater.getFluid(1000),CarbonDioxide.getFluid(1000))
                .output(dust,AluminiumHydroxide,2)
                .fluidOutputs(ImpureSodaAshSolution.getFluid(1000))
                .EUt(20)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust,AluminiumHydroxide,1)
                .fluidInputs(SodiumAluminateSolution.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(ImpureSodiumHydroxideSolution.getFluid(1000))
                .output(dust,AluminiumHydroxide,7)
                .duration(300)
                .EUt(16)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,AluminiumHydroxide,14)
                .fluidOutputs(Steam.getFluid(3000))
                .output(dust,AluminiumOxide,5)
                .duration(100)
                .blastFurnaceTemp(1400)
                .EUt(40)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(Cryolite.getFluid(2592))
                .input(dust,AluminiumOxide,5)
                .input(dust,AluminiumTrifluoride,8)
                .input(dust,Carbon,3)
                .fluidInputs(DistilledWater.getFluid(3000))
                .fluidOutputs(HydrogenFluoride.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .output(dust,Aluminium,4)
                .duration(100)
                .EUt(40)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .input(dust,AluminiumOxide,5)
                .fluidOutputs(Water.getFluid(3000))
                .output(dust,AluminiumTrifluoride,8)
                .duration(300)
                .EUt(16)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenFluoride.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(SodiumFluorideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(5)
                .EUt(16)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,AluminiumTrifluoride,4)
                .input(dust,SodiumFluoride,6)
                .output(dust,Cryolite,10)
                .duration(180)
                .EUt(16)
                .buildAndRegister();

        ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder()
                .fluidInputs(RedMud.getFluid(2000))
                .output(dustSmall,IronIIIOxide,2)
                .fluidOutputs(ConcentratedRedMud.getFluid(1000))
                .duration(200)
                .EUt(96)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(ConcentratedRedMud.getFluid(2000))
                .output(ingot,Iron)
                .output(dust,RedMudSlag)
                .blastFurnaceTemp(1600)
                .duration(300)
                .EUt(VA[MV]*2)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .input(dust,RedMudSlag,4)
                .output(dustTiny,Rutile,3)
                .output(dust,LeachedRedMudSlag)
                .duration(100)
                .EUt(VA[HV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .input(dust,AluminiumHydroxide,14)
                .fluidOutputs(AluminiumSulfateSolution.getFluid(1000))
                .duration(100)
                .EUt(96)
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(AluminiumSulfateSolution.getFluid(1000))
                .output(dust,AluminiumSulfate,17)
                .fluidOutputs(Water.getFluid(6000))
                .duration(100)
                .EUt(96)
                .buildAndRegister();

        ROASTING_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .input(dust,Aluminium,2)
                .output(dust,AluminiumSulfate)
                .fluidOutputs(Hydrogen.getFluid(6000))
                .duration(100)
                .EUt(120)
                .buildAndRegister();
    }
}
