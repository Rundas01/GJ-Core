package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;

public class PotassiumLineMaterials {
    private PotassiumLineMaterials(){}
    public static Material PotassiumSlag;


    public static void init(){
        PotassiumSlag = new Material.Builder(3695,gcylrId("potassium_slag"))
                .fluid()
                .color(avgColor(Potassium,Steel))
                .build()
                .setFormula("K?",true);
    }
}
