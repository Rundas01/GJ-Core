package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class SilverLineMaterials {
    private SilverLineMaterials(){}
    public static Material SilverSlag;
    public static Material SilverWaste;
    public static Material SilverChloride;
    public static Material SodiumArgentate;
    public static Material HighPuritySilver;

    public static void init(){
        SilverSlag = new Material.Builder(5501,gjId("silver_slag"))
                .fluid()
                .color(avgColor(Silver,Steel))
                .build()
                .setFormula("Ag?",true);

        SilverWaste = new Material.Builder(5502,gjId("silver_waste"))
                .fluid()
                .color(SilverSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ag?",true);

        SilverChloride = new Material.Builder(5503, gjId("silver_chloride"))
                .dust()
                .components(Silver, 1, Chlorine, 1)
                .colorAverage()
                .build();

        SodiumArgentate = new Material.Builder(5504,gjId("sodium_argentate"))
                .dust()
                .components(Sodium,1,Silver,1,Oxygen,1)
                .colorAverage()
                .build();

        HighPuritySilver = new Material.Builder(5550, gjId("high_purity_silver"))
                .dust()
                .color(Silver.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Silver,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ag*",true);
    }
}
