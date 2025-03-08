package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.unification.material.GJOreMaterials.Braggite;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class PalladiumLineMaterials {

    private PalladiumLineMaterials() {}

    public static Material PalladiumSlag;
    public static Material PalladiumWaste;
    public static Material BraggiteSlag;
    public static Material PalladiumDinitrate;
    public static Material PalladiumTetranitrate;
    public static Material HighPurityPalladium;

    public static void init() {
        PalladiumSlag = new Material.Builder(5251, gjId("palladium_slag"))
                .fluid()
                .color(avgColor(Palladium, Steel))
                .build()
                .setFormula("Pd?", true);

        PalladiumWaste = new Material.Builder(5252, gjId("palladium_waste"))
                .fluid()
                .color(PalladiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Pd?", true);

        BraggiteSlag = new Material.Builder(5253, gjId("braggite_slag"))
                .fluid()
                .color(avgColor(Braggite, Steel))
                .build()
                .setFormula("?", true);

        PalladiumDinitrate = new Material.Builder(5254, gjId("palladium_dinitrate"))
                .dust()
                .components(Palladium, 1, Nitrogen, 2, Oxygen, 6)
                .colorAverage()
                .build()
                .setFormula("Pd(NO3)2", true);

        PalladiumTetranitrate = new Material.Builder(5255, gjId("palladium_tetranitrate"))
                .dust()
                .components(Palladium, 1, Nitrogen, 4, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("Pd(NO3)4", true);

        HighPurityPalladium = new Material.Builder(5300, gjId("high_purity_palladium"))
                .dust()
                .color(Palladium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pd*", true);
    }
}
