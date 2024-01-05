package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregtech.api.recipes.RecipeMaps.*;

public class GCYLRComplexMaterialLines {
    private GCYLRComplexMaterialLines() {}

    public static void init() {
        changeRecipeMaps();
        AluminiumLine.init();
        AntimonyLine.init();
        ArsenicLine.init();
        BariumLine.init();
        BromineLine.init();
        CobaltLine.init();
        GalliumLine.init();
        SiliconSolarGradeLine.init();
        VanadiumLine.init();
        ZincLine.init();
        SilverLine.init();
        /*NickelLine.init();
        PalladiumLine.init();*/
    }

    private static void changeRecipeMaps() {
        BLAST_RECIPES.setMaxFluidOutputs(2);
        CHEMICAL_RECIPES.setMaxInputs(3);
        ELECTROMAGNETIC_SEPARATOR_RECIPES.setMaxFluidInputs(1);
        ELECTROMAGNETIC_SEPARATOR_RECIPES.setMaxFluidOutputs(1);
    }
}
