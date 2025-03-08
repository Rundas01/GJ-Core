package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;

public class NeodymiumLineMaterials {

    private NeodymiumLineMaterials() {}

    public static Material NeodymiumSlag;
    public static Material NeodymiumWaste;
    public static Material NeodymiumPhosphate;
    public static Material HighPurityNeodymium;

    public static void init() {
        NeodymiumSlag = new Material.Builder(5201, gjId("neodymium_slag"))
                .fluid()
                .color(avgColor(Neodymium, Steel))
                .build()
                .setFormula("Nd?", true);

        NeodymiumWaste = new Material.Builder(5202, gjId("neodymium_waste"))
                .fluid()
                .color(NeodymiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Nd?", true);

        NeodymiumPhosphate = new Material.Builder(5203, gjId("neodymium_phosphate"))
                .dust()
                .components(Neodymium, 1, Phosphorus, 1, Oxygen, 4)
                .colorAverage()
                .build();

        HighPurityNeodymium = new Material.Builder(5250, gjId("high_purity_neodymium"))
                .dust()
                .color(Neodymium.getMaterialRGB())
                .build()
                .setFormula("Nd*", true);
    }
}
