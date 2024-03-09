package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ThalliumLineMaterials {

    private ThalliumLineMaterials() {}

    public static Material ThalliumSlag;
    public static Material ThalliumWaste;
    public static Material ThalliumIIIOxide;
    public static Material ThalliumTrinitrate;
    public static Material ThalliumTrichloride;
    public static Material HighPurityThallium;

    public static void init() {
        ThalliumSlag = new Material.Builder(5651, gjId("thallium_slag"))
                .fluid()
                .color(avgColor(Thallium, Steel))
                .build()
                .setFormula("Tl?", true);

        ThalliumWaste = new Material.Builder(5652, gjId("thallium_waste"))
                .fluid()
                .color(ThalliumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Tl?", true);

        ThalliumIIIOxide = new Material.Builder(5653, gjId("thallium_iii_oxide"))
                .dust()
                .colorAverage()
                .components(Thallium, 2, Oxygen, 3)
                .build();

        ThalliumTrinitrate = new Material.Builder(5654, gjId("thallium_trinitrate"))
                .dust()
                .components(Thallium, 1, Nitrogen, 3, Oxygen, 9)
                .colorAverage()
                .build()
                .setFormula("Tl(NO3)3", true);

        HighPurityThallium = new Material.Builder(5700, gjId("high_purity_thallium"))
                .dust()
                .color(Thallium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Tl*", true);
    }
}
