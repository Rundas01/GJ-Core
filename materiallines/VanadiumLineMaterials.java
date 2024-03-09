package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import gregtech.api.unification.material.Material;

public class VanadiumLineMaterials {

    private VanadiumLineMaterials() {}

    public static Material VanadiumSlag;
    public static Material VanadiumWaste;
    public static Material SodiumMetaVanadate;
    public static Material AmmoniumMetaVanadate;
    public static Material AmmoniumNitrite;
    public static Material VanadiumVOxide;
    public static Material VanadiumTriiodide;
    public static Material VanadiumVSulfate;
    public static Material HighPurityVanadium;

    public static void init() {
        VanadiumSlag = new Material.Builder(5801, gjId("vanadium_slag"))
                .fluid()
                .color(avgColor(Vanadium, Steel))
                .build()
                .setFormula("V?");

        VanadiumWaste = new Material.Builder(5802, gjId("vanadium_waste"))
                .fluid()
                .color(VanadiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("V?");

        SodiumMetaVanadate = new Material.Builder(5803, gjId("sodium_metavanadate"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Vanadium, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumMetaVanadate = new Material.Builder(5804, gjId("ammonium_metavanadate"))
                .dust()
                .colorAverage()
                .components(Vanadium, 1, Hydrogen, 4, Nitrogen, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumNitrite = new Material.Builder(5805, gjId("ammonium_nitrite"))
                .dust()
                .colorAverage()
                .components(Nitrogen, 2, Hydrogen, 4, Oxygen, 2)
                .build()
                .setFormula("NH4NO2", true);

        VanadiumVOxide = new Material.Builder(5806, gjId("vanadium_v_oxide"))
                .dust()
                .colorAverage()
                .components(Vanadium, 2, Oxygen, 5)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        VanadiumTriiodide = new Material.Builder(5807, gjId("vanadium_triiodide"))
                .dust()
                .colorAverage()
                .components(Vanadium, 1, Iodine, 3)
                .build();

        HighPurityVanadium = new Material.Builder(5850, gjId("high_purity_vanadium"))
                .dust()
                .color(Vanadium.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("V*", true);
    }
}
