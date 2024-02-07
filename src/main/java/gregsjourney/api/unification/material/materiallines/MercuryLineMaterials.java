package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class MercuryLineMaterials {
    private MercuryLineMaterials(){}
    public static Material MercurySlag;
    public static Material MercuryWaste;
    public static Material MercuryOxide;
    public static Material MercuryTetranitrate;
    public static Material HighPurityMercury;

    public static void init(){
        MercurySlag = new Material.Builder(5101,gjId("mercury_slag"))
                .fluid()
                .color(avgColor(Lead,Steel))
                .build()
                .setFormula("Hg?",true);

        MercuryWaste = new Material.Builder(5102,gjId("mercury_waste"))
                .fluid()
                .color(MercurySlag.getMaterialRGB()-20)
                .build()
                .setFormula("Hg?",true);

        MercuryOxide = new Material.Builder(5103, gjId("mercury_oxide"))
                .dust()
                .colorAverage()
                .components(Mercury,1,Oxygen,1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        MercuryTetranitrate = new Material.Builder(5014,gjId("mercury_tetranitrate"))
                .dust()
                .components(Mercury,1,Nitrogen,4,Oxygen,12)
                .colorAverage()
                .build()
                .setFormula("Hg(NO3)4",true);

        HighPurityMercury = new Material.Builder(5150, gjId("high_purity_mercury"))
                .fluid()
                .color(Mercury.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Hg*",true);
    }
}
