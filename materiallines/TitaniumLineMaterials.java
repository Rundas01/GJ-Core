package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class TitaniumLineMaterials {

    private TitaniumLineMaterials() {}

    public static Material TitaniumSlag;
    public static Material TitaniumWaste;
    public static Material IlmeniteSlag;
    public static Material HighPurityTitanium;

    public static void init() {
        TitaniumSlag = new Material.Builder(5751, gjId("titanium_slag"))
                .fluid()
                .color(avgColor(Titanium, Steel))
                .build()
                .setFormula("Ti?", true);

        TitaniumWaste = new Material.Builder(5752, gjId("titanium_waste"))
                .fluid()
                .color(TitaniumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Ti?", true);

        IlmeniteSlag = new Material.Builder(5753, gjId("ilmenite_slag"))
                .fluid()
                .color(avgColor(Ilmenite, Steel))
                .build()
                .setFormula("?", true);

        HighPurityTitanium = new Material.Builder(5800, gjId("high_purity_titanium"))
                .dust()
                .color(Titanium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ti*", true);
    }
}
