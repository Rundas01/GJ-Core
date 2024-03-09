package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class StainlessSteelLineMaterials {

    private StainlessSteelLineMaterials() {}

    public static Material CrudeStainlessSteel;
    public static Material Ferrochromium;

    public static void init() {
        CrudeStainlessSteel = new Material.Builder(15000, gjId("crude_stainless_steel"))
                .fluid()
                .color(avgColor(StainlessSteel, Carbon))
                .build()
                .setFormula("?", true);

        Ferrochromium = new Material.Builder(15001, gjId("ferrochromium"))
                .dust()
                .components(Iron, 1, Chrome, 1)
                .colorAverage()
                .build();
    }
}
