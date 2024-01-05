package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class ZincLine {
    private ZincLine() {}

    public static void init() {
        //2 ZnO + C -> Zn + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Zincite,2)
                .input(dust,Carbon)
                .output(dust,Zinc)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .blastFurnaceTemp(1473)
                .EUt(460)
                .duration(160)
                .buildAndRegister();


    }
}
