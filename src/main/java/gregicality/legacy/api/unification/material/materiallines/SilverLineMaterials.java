package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.Chlorine;
import static gregtech.api.unification.material.Materials.Silver;

public class SilverLineMaterials {
    private SilverLineMaterials(){}

    public static Material SilverChloride;

    public static void init(){
        SilverChloride = new Material.Builder(3653, gcylrId("silver_chloride"))
                .dust()
                .components(Silver, 1, Chlorine, 1)
                .colorAverage()
                .build();
    }
}
