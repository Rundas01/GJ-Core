package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class MercuryLineMaterials {

    private MercuryLineMaterials() {}

    public static Material MercurySlag;
    public static Material MercuryWaste;
    public static Material CinnabarSlag;
    public static Material MercuryOxide;
    public static Material MercuryTetranitrate;
    public static Material HighPurityMercury;

    public static void init() {
        MercurySlag = new Material.Builder(5151, gjId("mercury_slag"))
                .fluid()
                .color(avgColor(Mercury, Steel))
                .build()
                .setFormula("Hg?", true);

        MercuryWaste = new Material.Builder(5152, gjId("mercury_waste"))
                .fluid()
                .color(MercurySlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Hg?", true);

        CinnabarSlag = new Material.Builder(5153, gjId("cinnabar_slag"))
                .fluid()
                .color(avgColor(Cinnabar, Steel))
                .build()
                .setFormula("?", true);

        MercuryOxide = new Material.Builder(5154, gjId("mercury_oxide"))
                .dust()
                .colorAverage()
                .components(Mercury, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        MercuryTetranitrate = new Material.Builder(5155, gjId("mercury_tetranitrate"))
                .dust()
                .components(Mercury, 1, Nitrogen, 4, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("Hg(NO3)4", true);

        HighPurityMercury = new Material.Builder(5200, gjId("high_purity_mercury"))
                .fluid()
                .color(Mercury.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Hg*", true);
    }
}
