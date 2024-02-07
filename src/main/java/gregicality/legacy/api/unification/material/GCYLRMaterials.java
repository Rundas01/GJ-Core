package gregicality.legacy.api.unification.material;

import gregicality.legacy.common.GCYLRConfigHolder;

public class GCYLRMaterials {

    //Isotopes 3040 - X
    //Fusion Moderators 3200 - 3202
    //Bacteria 3203 - 3209
    //Organic Materials 3210 - Z
    //Complex Material Lines Materials 3600 - 3995
    //Polymerization Materials 3995 - 3999
    //Universal Materials 4000 - B
    //Ore Materials 7000 - A
    //Misc Materials 8000

    private GCYLRMaterials() {}

    public static void init() {
        GCYLRIsotopes.init();
        GCYLRFusionModeratorMaterials.init();
        GCYLRBacteriaMaterials.init();
        GCYLROrganicMaterials.init();
        if(GCYLRConfigHolder.recipes.realisticRecipes){
            GCYLRComplexMaterialLinesMaterials.init();
            GCYLRPolymerizationMaterials.init();
            GCYLRUniversalChemicalMaterials.init();
        }
        GCYLROreMaterials.register();
        GCYLRMiscMaterials.init();
        //Flags
        GCYLRMaterialFlagAddition.init();
    }
}
