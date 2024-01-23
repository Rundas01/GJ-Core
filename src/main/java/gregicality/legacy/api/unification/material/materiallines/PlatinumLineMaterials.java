package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class PlatinumLineMaterials {
    private PlatinumLineMaterials(){}
    public static Material ImpurePlatinumSlag;
    public static Material PlatinumSlag;
    public static Material PlatinumWaste;
    public static Material SodiumPlatinate;
    public static Material HighPurityPlatinum;

    public static void init(){
        ImpurePlatinumSlag = new Material.Builder(3716,gcylrId("impure_platinum_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Pt?");

        PlatinumSlag = new Material.Builder(3717,gcylrId("platinum_slag"))
                .fluid()
                .color((Arsenic.getMaterialRGB()+ImpurePlatinumSlag.getMaterialRGB())/2)
                .build()
                .setFormula("Pt?");

        PlatinumWaste = new Material.Builder(3718,gcylrId("platinum_waste"))
                .fluid()
                .color(PlatinumSlag.getMaterialRGB()-10)
                .build()
                .setFormula("Pt?");

        SodiumPlatinate = new Material.Builder(3719, gcylrId("sodium_platinate"))
                .dust()
                .colorAverage()
                .components(Sodium,2,Platinum,1,Oxygen,4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityPlatinum = new Material.Builder(3720, gcylrId("high_purity_platinum"))
                .fluid()
                .color(Platinum.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Platinum,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Pt*",true);
    }
}
