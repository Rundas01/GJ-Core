package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class SamariumLineMaterials {

    private SamariumLineMaterials() {}

    public static Material SamariumSlag;
    public static Material SamariumWaste;
    public static Material SamariumPhosphate;
    public static Material HighPuritySamarium;

    public static void init() {
        SamariumSlag = new Material.Builder(5401, gjId("samarium_slag"))
                .fluid()
                .color(avgColor(Samarium, Steel))
                .build()
                .setFormula("Sm?", true);

        SamariumWaste = new Material.Builder(5402, gjId("samarium_waste"))
                .fluid()
                .color(SamariumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Sm?", true);

        SamariumPhosphate = new Material.Builder(5403, gjId("samarium_phosphate"))
                .dust()
                .components(Samarium, 1, Phosphorus, 1, Oxygen, 4)
                .colorAverage()
                .build();

        HighPuritySamarium = new Material.Builder(5450, gjId("high_purity_samarium"))
                .dust()
                .color(Samarium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sm*", true);
    }
}
