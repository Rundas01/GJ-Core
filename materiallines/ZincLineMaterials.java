package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import gregtech.api.unification.material.Material;

public class ZincLineMaterials {

    private ZincLineMaterials() {}

    public static Material ZincSlag;
    public static Material ZincWaste;
    public static Material ZincSulfate;
    public static Material HighPurityZinc;

    public static void init() {
        ZincSlag = new Material.Builder(5752, gjId("zinc_slag"))
                .fluid()
                .color(avgColor(Zinc, Steel))
                .build()
                .setFormula("Zn?");

        ZincWaste = new Material.Builder(5753, gjId("zinc_waste"))
                .fluid()
                .color(ZincWaste.getMaterialRGB() - 20)
                .build()
                .setFormula("Zn?");

        ZincSulfate = new Material.Builder(5754, gjId("zinc_sulfate"))
                .dust()
                .colorAverage()
                .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityZinc = new Material.Builder(5800, gjId("high_purity_zinc"))
                .dust()
                .color(Zinc.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Zn*", true);
    }
}
