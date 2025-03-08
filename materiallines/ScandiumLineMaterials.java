package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ScandiumLineMaterials {

    private ScandiumLineMaterials() {}

    public static Material ScandiumSlag;
    public static Material ScandiumWaste;
    public static Material ScandiumIIISulfate;
    public static Material HighPurityScandium;

    public static void init() {
        ScandiumSlag = new Material.Builder(5451, gjId("scandium_slag"))
                .fluid()
                .color(avgColor(Scandium, Steel))
                .build()
                .setFormula("Sc?", true);

        ScandiumWaste = new Material.Builder(5452, gjId("scandium_waste"))
                .fluid()
                .color(ScandiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Sc?", true);

        ScandiumIIISulfate = new Material.Builder(5453, gjId("scandium_iii_sulfate"))
                .dust()
                .colorAverage()
                .components(Scandium, 2, Sulfur, 3, Oxygen, 12)
                .build()
                .setFormula("Sc2(SO4)3");

        HighPurityScandium = new Material.Builder(5500, gjId("high_purity_scandium"))
                .dust()
                .color(Scandium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sc*", true);
    }
}
