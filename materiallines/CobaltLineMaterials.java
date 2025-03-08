package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class CobaltLineMaterials {

    private CobaltLineMaterials() {}

    public static Material CobaltSlag;
    public static Material CobaltWaste;
    public static Material CobaltiteSlag;
    public static Material CobaltTrinitrate;
    public static Material CobaltIINitride;
    public static Material CobaltIIIOxide;
    public static Material HighPurityCobalt;

    public static void init() {
        CobaltSlag = new Material.Builder(4701, gjId("cobalt_slag"))
                .fluid()
                .color(avgColor(Cobalt, Steel))
                .build()
                .setFormula("Co?", true);

        CobaltWaste = new Material.Builder(4702, gjId("cobalt_waste"))
                .fluid()
                .color(CobaltSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Co?", true);

        CobaltiteSlag = new Material.Builder(4703, gjId("cobaltite_slag"))
                .fluid()
                .color(avgColor(Cobaltite, Steel))
                .build()
                .setFormula("?");

        CobaltTrinitrate = new Material.Builder(4704, gjId("cobalt_trinitrate"))
                .dust()
                .colorAverage()
                .components(Cobalt, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Co(NO3)3", true);

        CobaltIINitride = new Material.Builder(4705, gjId("cobalt_ii_nitride"))
                .dust()
                .colorAverage()
                .components(Cobalt, 3, Nitrogen, 2)
                .build();

        CobaltIIIOxide = new Material.Builder(4706, gjId("cobalt_iii_oxide"))
                .dust()
                .colorAverage()
                .components(Cobalt, 2, Oxygen, 3)
                .build();

        HighPurityCobalt = new Material.Builder(4750, gjId("high_purity_cobalt"))
                .dust()
                .color(Cobalt.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Co*", true);
    }
}
