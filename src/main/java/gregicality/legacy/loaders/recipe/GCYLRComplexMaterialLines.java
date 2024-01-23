package gregicality.legacy.loaders.recipe;

import gregicality.legacy.loaders.recipe.complexmateriallines.*;

import static gregtech.api.recipes.RecipeMaps.*;

public class GCYLRComplexMaterialLines {
    private GCYLRComplexMaterialLines() {}

    public static void init() {
        changeRecipeMaps();
        AluminiumLine.init();
        AntimonyLine.init();
        ArsenicLine.init();
        BariumLine.init();
        BerylliumLine.init();
        BismuthLine.init();
        BromineLine.init();
        CobaltLine.init();
        GalliumLine.init();
        LithiumLine.init();
        ScandiumLine.init();
        SiliconLine.init();
        SilverLine.init();
        TitaniumLine.init();
        VanadiumLine.init();
        ZincLine.init();
        /*NickelLine.init();
        PalladiumLine.init();*/
    }

    private static void changeRecipeMaps() {
        BLAST_RECIPES.setMaxFluidOutputs(2);
        CHEMICAL_RECIPES.setMaxInputs(3);
        CENTRIFUGE_RECIPES.setMaxFluidInputs(2);
    }
}
