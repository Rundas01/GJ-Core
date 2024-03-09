package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.Bismuthinite;
import static gregsjourney.api.unification.material.GJOreMaterials.Polarite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class BismuthLineMaterials {

    private BismuthLineMaterials() {}

    public static Material BismuthSlag;
    public static Material BismuthWaste;
    public static Material BismuthiniteSlag;
    public static Material PolariteSlag;
    public static Material SodiumBismutate;
    public static Material BismuthIIIOxide;
    public static Material BismuthVOxide;
    public static Material BismuthNitrate;
    public static Material BismuthPentanitrate;
    public static Material BismuthIIINitride;
    public static Material BismuthINitride;
    public static Material HighPurityBismuth;

    public static void init() {
        BismuthSlag = new Material.Builder(4351, gjId("bismuth_slag"))
                .fluid()
                .color(avgColor(Bismuth, Steel))
                .build()
                .setFormula("Bi?", true);

        BismuthWaste = new Material.Builder(4352, gjId("bismuth_waste"))
                .fluid()
                .color(BismuthSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Bi?", true);

        BismuthiniteSlag = new Material.Builder(4353, gjId("bismuthinite_slag"))
                .fluid()
                .color(avgColor(Bismuthinite, Steel))
                .build()
                .setFormula("?");

        PolariteSlag = new Material.Builder(4354, gjId("polarite_slag"))
                .fluid()
                .color(avgColor(Polarite, Steel))
                .build()
                .setFormula("?");

        SodiumBismutate = new Material.Builder(4355, gjId("sodium_bismutate"))
                .dust()
                .components(Sodium, 1, Bismuth, 1, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BismuthIIIOxide = new Material.Builder(4356, gjId("bismuth_iii_oxide"))
                .dust()
                .components(Bismuth, 2, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BismuthVOxide = new Material.Builder(4357, gjId("bismuth_v_oxide"))
                .dust()
                .components(Bismuth, 2, Oxygen, 5)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BismuthNitrate = new Material.Builder(4358, gjId("bismuth_nitrate"))
                .dust()
                .components(Bismuth, 1, Nitrogen, 1, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BismuthPentanitrate = new Material.Builder(4359, gjId("bismuth_pentanitrate"))
                .dust()
                .components(Bismuth, 1, Nitrogen, 5, Oxygen, 15)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Bi(NO3)5", true);

        BismuthINitride = new Material.Builder(4360, gjId("bismuth_iii_nitride"))
                .dust()
                .components(Bismuth, 1, Nitrogen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BismuthIIINitride = new Material.Builder(4361, gjId("bismuth_i_nitride"))
                .dust()
                .components(Bismuth, 3, Nitrogen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityBismuth = new Material.Builder(4400, gjId("high_purity_bismuth"))
                .dust()
                .color(Bismuth.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Bi*", true);
    }
}
