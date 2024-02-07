package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;

public class MagnesiumLineMaterials {
    private MagnesiumLineMaterials(){}
    public static Material MagnesiumSlag;


    public static void init(){
        MagnesiumSlag = new Material.Builder(3696,gcylrId("magnesium_slag"))
                .fluid()
                .color(avgColor(Magnesium,Steel))
                .build()
                .setFormula("Mg?",true);
    }
}
