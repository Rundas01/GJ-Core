package gregicality.legacy.loaders.recipe.complexmateriallines;

import gregtech.api.unification.OreDictUnifier;

import java.util.Collections;
import java.util.Objects;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ROASTING_RECIPES;
import static gregicality.legacy.api.unification.material.materiallines.CobaltLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class CobaltLine {
    private CobaltLine() {}

    public static void init() {
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(VA[MV], Collections.singletonList(OreDictUnifier.get(dust, Cobaltite)), Collections.singletonList(Oxygen.getFluid(3000)))));

        //12 CoAsS + 29 O2 -> 4 Co3O4 + 6 As2O3 + 12 SO2
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Cobaltite,36)
                .fluidInputs(Oxygen.getFluid(58000))
                .output(dust,CobaltTrioxide,28)
                .output(dust,ArsenicTrioxide,30)
                .fluidOutputs(SulfurDioxide.getFluid(12000))
                .EUt(460)
                .duration(160)
                .buildAndRegister();

        //2 Co3O4 + C -> 6 CoO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CobaltTrioxide,14)
                .input(dust,Carbon)
                .output(dust,CobaltOxide,12)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .blastFurnaceTemp(1600)
                .EUt(460)
                .duration(160)
                .buildAndRegister();

        //CoO + C -> Co + CO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CobaltOxide,2)
                .input(dust,Carbon)
                .output(dust,Cobalt)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .blastFurnaceTemp(1473)
                .EUt(460)
                .duration(160)
                .buildAndRegister();

        //2 As2O3 + 3 C -> 4 As + 3 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,10)
                .input(dust,Carbon,3)
                .output(dust,Arsenic,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .blastFurnaceTemp(1473)
                .EUt(460)
                .duration(160)
                .buildAndRegister();
    }
}
