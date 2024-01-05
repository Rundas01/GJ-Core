package gregicality.legacy.api.unification.material;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;

public class GCYLRMiscMaterials {
    private GCYLRMiscMaterials() {}

    public static Material Wastewater;

    public static void init(){
        Wastewater = new Material.Builder(4056, gcylrId("wastewater"))
                .fluid()
                .color(0x2d402f)
                .build()
                .setFormula("?",true);
    }
}
