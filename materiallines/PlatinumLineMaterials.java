package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.unification.material.GJOreMaterials.Sperrylite;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class PlatinumLineMaterials {

    private PlatinumLineMaterials() {}

    public static Material PlatinumSlag;
    public static Material PlatinumWaste;
    public static Material CooperiteSlag;
    public static Material SperryliteSlag;
    public static Material SodiumPlatinate;
    public static Material PlatinumTetranitrate;
    public static Material HighPurityPlatinum;

    public static void init() {
        PlatinumSlag = new Material.Builder(5301, gjId("platinum_slag"))
                .fluid()
                .color(avgColor(Platinum, Steel))
                .build()
                .setFormula("Pt?");

        PlatinumWaste = new Material.Builder(5302, gjId("platinum_waste"))
                .fluid()
                .color(PlatinumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Pt?");

        CooperiteSlag = new Material.Builder(5303, gjId("cooperite_slag"))
                .fluid()
                .color(avgColor(Cooperite, Steel))
                .build()
                .setFormula("?");

        SperryliteSlag = new Material.Builder(5304, gjId("sperrylite_slag"))
                .fluid()
                .color(avgColor(Sperrylite, Steel))
                .build()
                .setFormula("?");

        SodiumPlatinate = new Material.Builder(5305, gjId("sodium_platinate"))
                .dust()
                .colorAverage()
                .components(Sodium, 2, Platinum, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        PlatinumTetranitrate = new Material.Builder(5306, gjId("platinum_tetranitrate"))
                .dust()
                .components(Platinum, 1, Nitrogen, 4, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("Pt(NO3)4", true);

        HighPurityPlatinum = new Material.Builder(5350, gjId("high_purity_platinum"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pt*", true);
    }
}
