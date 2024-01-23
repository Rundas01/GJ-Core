package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;

public class SodiumLineMaterials {
    private SodiumLineMaterials(){}
    public static Material SodiumSlag;


    public static void init(){
        SodiumSlag = new Material.Builder(3693,gcylrId("sodium_slag"))
                .fluid()
                .color(avgColor(Sodium,Steel))
                .build()
                .setFormula("Na?",true);
    }
}
