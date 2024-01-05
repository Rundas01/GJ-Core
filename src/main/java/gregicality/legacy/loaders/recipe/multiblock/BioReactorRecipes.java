package gregicality.legacy.loaders.recipe.multiblock;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.BIO_REACTOR_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLRBacteriaMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public final class BioReactorRecipes {

    private BioReactorRecipes() {}

    public static void init() {
        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Glucose, 24)
                .input(dust, StreptococcusPyogenes)
                .output(dust, Sorbose, 24)
                .duration(3200).EUt(120).buildAndRegister();
    }
}
