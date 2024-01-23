package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class BismuthLineMaterials {
    private BismuthLineMaterials(){}

    public static Material ImpureBismuthSlag;
    public static Material BismuthSlag;
    public static Material BismuthWaste;
    public static Material SodiumBismutate;
    public static Material HighPurityBismuth;

    public static void init(){
        ImpureBismuthSlag = new Material.Builder(3685, gcylrId("impure_bismuth_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Bi?",true);

        BismuthSlag = new Material.Builder(3686, gcylrId("bismuth_slag"))
                .fluid()
                .color(avgColor(Bismuth,ImpureBismuthSlag))
                .build()
                .setFormula("Bi?",true);

        BismuthWaste = new Material.Builder(3687, gcylrId("bismuth_waste"))
                .fluid()
                .color(BismuthSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Bi?",true);

        SodiumBismutate = new Material.Builder(3688, gcylrId("sodium_bismutate"))
                .dust()
                .components(Sodium, 1, Bismuth, 1, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityBismuth = new Material.Builder(3689, gcylrId("high_purity_bismuth"))
                .dust()
                .color(Bismuth.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Bismuth,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Bi*",true);
    }
}
