package gregsjourney.loaders.recipe;

import gregsjourney.loaders.recipe.singleblock.FuelChanges;
import gregsjourney.loaders.recipe.singleblock.GasGasGas;

public final class GJRecipeLoader {

    private GJRecipeLoader() {}

    public static void init() {
        FuelChanges.init();
        GasGasGas.init();
        //ForestryModule.registerRecipes();
        //ThaumcraftModule.registerRecipes();
    }
}
