package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.Amblygonite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class LithiumLineMaterials {

    private LithiumLineMaterials() {}

    public static Material LithiumSlag;
    public static Material LithiumWaste;
    public static Material AmblygoniteSlag;
    public static Material SpodumeneSlag;
    public static Material LepidoliteSlag;
    public static Material LithiumHydroxide;
    public static Material LithiumHydroxideSolution;
    public static Material LithiumNitrate;
    public static Material LithiumCarbonate;
    public static Material LithiumOxide;
    public static Material LithiumSulfate;
    public static Material LithiumFluoride;
    public static Material HighPurityLithium;

    public static void init() {
        LithiumSlag = new Material.Builder(5001, gjId("lithium_slag"))
                .fluid()
                .color(avgColor(Lithium, Steel))
                .build()
                .setFormula("Li?", true);

        LithiumWaste = new Material.Builder(5002, gjId("lithium_waste"))
                .fluid()
                .color(LithiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Li?", true);

        AmblygoniteSlag = new Material.Builder(5003, gjId("amblygonite_slag"))
                .fluid()
                .color(avgColor(Amblygonite, Steel))
                .build()
                .setFormula("?", true);

        SpodumeneSlag = new Material.Builder(5004, gjId("spodumene_slag"))
                .fluid()
                .color(avgColor(Spodumene, Steel))
                .build()
                .setFormula("?", true);

        LepidoliteSlag = new Material.Builder(5005, gjId("lepidolite_slag"))
                .fluid()
                .color(avgColor(Lepidolite, Steel))
                .build()
                .setFormula("?", true);

        LithiumHydroxide = new Material.Builder(5006, gjId("lithium_hydroxide"))
                .dust()
                .components(Lithium, 1, Oxygen, 1, Hydrogen, 1)
                .colorAverage()
                .build();

        LithiumHydroxideSolution = new Material.Builder(5007, gjId("lithium_hydroxide_solution"))
                .fluid()
                .components(LithiumHydroxide, 1, Water, 1)
                .colorAverage()
                .build();

        LithiumNitrate = new Material.Builder(5008, gjId("lithium_nitrate"))
                .dust()
                .components(Lithium, 1, Nitrogen, 1, Oxygen, 3)
                .colorAverage()
                .build();

        LithiumCarbonate = new Material.Builder(5009, gjId("lithium_carbonate"))
                .dust()
                .components(Lithium, 2, Carbon, 1, Oxygen, 3)
                .colorAverage()
                .build();

        LithiumOxide = new Material.Builder(5010, gjId("lithium_oxide"))
                .dust()
                .components(Lithium, 2, Oxygen, 1)
                .colorAverage()
                .build();

        LithiumSulfate = new Material.Builder(5011, gjId("lithium_sulfate"))
                .dust()
                .components(Lithium, 2, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        LithiumFluoride = new Material.Builder(5012, gjId("lithium_fluoride"))
                .dust()
                .components(Lithium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        HighPurityLithium = new Material.Builder(5050, gjId("high_purity_lithium"))
                .dust()
                .components(Lithium, 1)
                .color(Lithium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Li*", true);
    }
}
