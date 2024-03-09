package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class PotassiumLineMaterials {

    private PotassiumLineMaterials() {}

    public static Material PotassiumSlag;
    public static Material PotassiumWaste;
    public static Material PotassiumIodide;
    public static Material PotassiumFluoride;
    public static Material PotassiumNitrateSolution;
    public static Material PotassiumNitrite;
    public static Material PotassiumHydroxide;
    public static Material PotassiumSulfate;
    public static Material PotassiumOxide;
    public static Material PotassiumChlorate;
    public static Material PotassiumPerchlorate;
    public static Material PotassiumBisulfate;
    public static Material HighPurityPotassium;

    public static void init() {
        PotassiumSlag = new Material.Builder(5351, gjId("potassium_slag"))
                .fluid()
                .color(avgColor(Potassium, Steel))
                .build()
                .setFormula("K?", true);

        PotassiumWaste = new Material.Builder(5352, gjId("potassium_waste"))
                .fluid()
                .color(PotassiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("K?", true);

        PotassiumIodide = new Material.Builder(5353, gjId("potassium_iodide"))
                .dust()
                .components(Potassium, 1, Iodine, 1)
                .colorAverage()
                .build();

        PotassiumFluoride = new Material.Builder(5354, gjId("potassium_fluoride"))
                .dust()
                .components(Potassium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        PotassiumHydroxide = new Material.Builder(5355, gjId("potassium_hydroxide"))
                .dust()
                .colorAverage()
                .components(Potassium, 1, Oxygen, 1, Hydrogen, 1)
                .build();

        PotassiumNitrateSolution = new Material.Builder(5356, gjId("potassium_nitrate_solution"))
                .fluid()
                .colorAverage()
                .components(Saltpeter, 1, Water, 1)
                .build();

        PotassiumNitrite = new Material.Builder(5357, gjId("potassium_nitrite"))
                .dust()
                .colorAverage()
                .components(Potassium, 1, Nitrogen, 1, Oxygen, 2)
                .build();

        PotassiumSulfate = new Material.Builder(5358, gjId("potassium_sulfate"))
                .dust()
                .components(Potassium, 2, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumOxide = new Material.Builder(5359, gjId("potassium_oxide"))
                .dust()
                .components(Potassium, 2, Oxygen, 1)
                .colorAverage()
                .build();

        PotassiumChlorate = new Material.Builder(5360, gjId("potassium_chlorate"))
                .dust()
                .components(Potassium, 1, Chlorine, 1, Oxygen, 3)
                .colorAverage()
                .build();

        PotassiumPerchlorate = new Material.Builder(5361, gjId("potassium_perchlorate"))
                .dust()
                .components(Potassium, 1, Chlorine, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumBisulfate = new Material.Builder(5362, gjId("potassium_bisulfate"))
                .dust()
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        HighPurityPotassium = new Material.Builder(5400, gjId("high_purity_potassium"))
                .dust()
                .color(Potassium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("K*", true);
    }
}
