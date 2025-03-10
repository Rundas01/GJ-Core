package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;

import gregtech.api.unification.material.Material;

public class SodiumLineMaterials {

    private SodiumLineMaterials() {}

    public static Material SodiumSlag;
    public static Material SodiumWaste;
    public static Material SodiumSulfate;
    public static Material SodiumSilicate;
    public static Material SodiumSulfateSolution;
    public static Material SodiumBisulfateSolution;
    public static Material SodiumNitrate;
    public static Material SodiumNitrateSolution;
    public static Material SodiumNitrite;
    public static Material SodiumOxide;
    public static Material SodiumFluoride;
    public static Material SodiumFluorideSolution;
    public static Material SodiumCarbonateSolution;

    public static void init() {
        SodiumSlag = new Material.Builder(5601, gjId("sodium_slag"))
                .fluid()
                .color(avgColor(Sodium, Steel))
                .build()
                .setFormula("Na?", true);

        SodiumWaste = new Material.Builder(5602, gjId("sodium_waste"))
                .fluid()
                .color(SodiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Na?", true);

        SodiumSulfate = new Material.Builder(5603, gjId("sodium_sulfate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        SodiumSilicate = new Material.Builder(5604, gjId("sodium_silicate"))
                .dust()
                .components(Sodium, 4, Silicon, 1, Oxygen, 4)
                .colorAverage()
                .build();

        SodiumSulfateSolution = new Material.Builder(5605, gjId("sodium_sulfate_solution"))
                .fluid()
                .components(SodiumSulfate, 1, Water, 1)
                .colorAverage()
                .build();

        SodiumFluoride = new Material.Builder(5606, gjId("sodium_fluoride"))
                .dust()
                .components(Sodium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        SodiumFluorideSolution = new Material.Builder(5607, gjId("sodium_fluoride_solution"))
                .fluid()
                .components(SodiumFluoride, 1, Water, 1)
                .colorAverage()
                .build();

        SodiumNitrate = new Material.Builder(5608, gjId("sodium_nitrate"))
                .dust()
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .colorAverage()
                .build();

        SodiumNitrite = new Material.Builder(5609, gjId("sodium_nitrite"))
                .dust()
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 2)
                .colorAverage()
                .build();

        SodiumNitrateSolution = new Material.Builder(5610, gjId("sodium_nitrate_solution"))
                .fluid()
                .components(SodiumNitrate, 1, Water, 1)
                .colorAverage()
                .build();

        SodiumBisulfateSolution = new Material.Builder(5611, gjId("sodium_bisulfate_solution"))
                .fluid()
                .colorAverage()
                .components(SodiumBisulfate, 1, Water, 1)
                .build();

        SodiumCarbonateSolution = new Material.Builder(5612, gjId("sodium_carbonate_solution"))
                .fluid()
                .color(0x91919b)
                .components(SodaAsh, 1, Water, 1)
                .build();

        SodiumOxide = new Material.Builder(5650, gjId("sodium_oxide"))
                .dust()
                .colorAverage()
                .components(Sodium, 2, Oxygen, 1)
                .build();
    }
}
