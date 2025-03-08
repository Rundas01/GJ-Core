package thegreggening.api.unification.material;

public class TGMaterials {

    private TGMaterials() {}

    public static void init() {
        // Materials
        materialInit();
        // Flags
        TGMaterialFlagAddition.init();
    }

    private static void materialInit() {
        // 4001-4100
        TGMiscMaterials.init();
        // 10000-11000
        TGSpaceMaterials.init();
        TGNuclearMaterials.init();
    }
}
