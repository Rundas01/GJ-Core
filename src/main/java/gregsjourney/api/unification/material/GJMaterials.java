package gregsjourney.api.unification.material;

public class GJMaterials {

    private GJMaterials() {}

    public static void init() {
        // Materials
        materialInit();
        // Flags
        GJMaterialFlagAddition.init();
    }

    private static void materialInit() {
        // 4001-4100
        GJMiscMaterials.init();
        // 10000-11000
        GJSpaceMaterials.init();
        GJNuclearMaterials.init();
    }
}
