package gregicality.legacy.loaders.recipe;

import gregicality.legacy.common.GCYLRConfigHolder;
import gregicality.legacy.loaders.recipe.complexmateriallines.GCYLRComplexMaterialLines;
import gregicality.legacy.loaders.recipe.multiblock.*;
import gregicality.legacy.loaders.recipe.singleblock.DecayGeneratorRecipes;
import gregicality.legacy.loaders.recipe.singleblock.IsotopicStabilizerRecipes;

public final class GCYLRRecipeLoader {

    private GCYLRRecipeLoader() {}

    public static void init() {
        GCYLRMetaTileEntityLoader.init();
        GCYLRCasingLoader.init();
        LargeMixerRecipes.init();
        LargeCentrifugeRecipes.init();
        LargeEngraverRecipes.init();
        BioReactorRecipes.init();
        AdvancedFusionReactorRecipes.init();
        DecayGeneratorRecipes.init();
        IsotopicStabilizerRecipes.init();
        //NuclearReactorRecipes.init();
        if(GCYLRConfigHolder.recipes.realisticRecipes) {
            GCYLRComplexMaterialLines.init();
        }
        GCYLRMiscRecipes.init();
    }
}
