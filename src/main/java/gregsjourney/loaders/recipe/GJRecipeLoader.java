package gregsjourney.loaders.recipe;

import gregsjourney.loaders.recipe.singleblock.AutogeneratedRecipes;
import gregsjourney.loaders.recipe.singleblock.GasGasGas;

public final class GJRecipeLoader {

    private GJRecipeLoader() {}

    public static void init() {
        GasGasGas.init();
        AutogeneratedRecipes.init();
        // ForestryModule.registerRecipes();
        // ThaumcraftModule.registerRecipes();
    }
}
