package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;

public class CalciumLineMaterials {
    private CalciumLineMaterials(){}
    public static Material CalciumSlag;


    public static void init(){
        CalciumSlag = new Material.Builder(3748,gcylrId("calcium_slag"))
                .fluid()
                .color(avgColor(Calcium,Steel))
                .build()
                .setFormula("Ca?",true);
    }
}
