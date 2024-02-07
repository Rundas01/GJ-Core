package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Gallium;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class SilverLineMaterials {
    private SilverLineMaterials(){}
    public static Material ImpureSilverSlag;
    public static Material SilverSlag;
    public static Material SilverWaste;
    public static Material SilverChloride;
    public static Material SodiumArgentate;
    public static Material HighPuritySilver;

    public static void init(){
        ImpureSilverSlag = new Material.Builder(3730,gcylrId("impure_silver_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Ag?",true);

        SilverSlag = new Material.Builder(3731,gcylrId("silver_slag"))
                .fluid()
                .color(avgColor(Silver,ImpureSilverSlag))
                .build()
                .setFormula("Ag?",true);

        SilverWaste = new Material.Builder(3732,gcylrId("silver_waste"))
                .fluid()
                .color(SilverSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ag?",true);

        SilverChloride = new Material.Builder(3733, gcylrId("silver_chloride"))
                .dust()
                .components(Silver, 1, Chlorine, 1)
                .colorAverage()
                .build();

        SodiumArgentate = new Material.Builder(3734,gcylrId("sodium_argentate"))
                .dust()
                .components(Sodium,1,Silver,1,Oxygen,1)
                .colorAverage()
                .build();

        HighPuritySilver = new Material.Builder(3735, gcylrId("high_purity_silver"))
                .dust()
                .color(Silver.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Silver,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ag*",true);
    }
}
