package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class MercuryLineMaterials {
    private MercuryLineMaterials(){}
    public static Material MercuryOxide;

    public static Material HighPurityMercury;

    public static void init(){
        MercuryOxide = new Material.Builder(6000, gcylrId("mercury_oxide"))
                .dust()
                .colorAverage()
                .components(Mercury,1,Oxygen,1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityMercury = new Material.Builder(6001, gcylrId("high_purity_mercury"))
                .fluid()
                .color(Mercury.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Mercury,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Hg*",true);
    }
}
