package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class PlatinumLineMaterials {
    private PlatinumLineMaterials(){}
    public static Material PlatinumSlag;
    public static Material PlatinumWaste;
    public static Material SodiumPlatinate;
    public static Material HighPurityPlatinum;

    public static void init(){
        PlatinumSlag = new Material.Builder(5251,gjId("platinum_slag"))
                .fluid()
                .color(avgColor(Platinum,Steel))
                .build()
                .setFormula("Pt?");

        PlatinumWaste = new Material.Builder(5252,gjId("platinum_waste"))
                .fluid()
                .color(PlatinumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Pt?");

        SodiumPlatinate = new Material.Builder(5253, gjId("sodium_platinate"))
                .dust()
                .colorAverage()
                .components(Sodium,2,Platinum,1,Oxygen,4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityPlatinum = new Material.Builder(5300, gjId("high_purity_platinum"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pt*",true);
    }
}
