package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class GalliumLineMaterials {

    private GalliumLineMaterials() {}

    public static Material GalliumSlag;
    public static Material GalliumWaste;
    public static Material GalliumTrichloride;
    public static Material HighPurityGallium;

    public static void init() {
        GalliumSlag = new Material.Builder(4801, gjId("gallium_slag"))
                .fluid()
                .color(avgColor(Gallium, Steel))
                .build()
                .setFormula("Ga?", true);

        GalliumWaste = new Material.Builder(4802, gjId("gallium_waste"))
                .fluid()
                .color(GalliumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Ga?", true);

        GalliumTrichloride = new Material.Builder(4803, gjId("gallium_trichloride"))
                .dust()
                .colorAverage()
                .components(Gallium, 1, Chlorine, 3)
                .build();

        HighPurityGallium = new Material.Builder(4850, gjId("high_purity_gallium"))
                .dust()
                .color(Gallium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ga*", true);
    }
}
