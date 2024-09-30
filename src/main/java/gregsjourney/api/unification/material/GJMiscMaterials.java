package gregsjourney.api.unification.material;

import static gregsjourney.api.unification.material.GJElements.Ad;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

public class GJMiscMaterials {

    private GJMiscMaterials() {}

    public static Material HexafluoroSilicicAcid;
    public static Material CombProcessingReagent;
    public static Material Thaumium;
    public static Material ConductiveIron;
    public static Material RedstoneAlloy;
    public static Material EnergeticAlloy;
    public static Material VibrantAlloy;
    public static Material ElectricalSteel;
    public static Material DarkSteel;
    public static Material PulsatingIron;
    public static Material AwakenedDraconium;
    public static Material Manasteel;
    public static Material Terrasteel;
    public static Material Elementium;
    public static Material GaiaSpirit;
    public static Material VoidMetal;
    public static Material MeteoricIron;
    public static Material Desh;
    public static Material Ardite;
    public static Material Manyullun;
    public static Material Uranium;
    public static Material Plutonium;

    public static void init() {
        materialInit();
    }

    private static void materialInit() {
        HexafluoroSilicicAcid = new Material.Builder(11025, gjId("hexafluoro_silicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID).temperature(Water.getFluid().getTemperature()))
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .colorAverage()
                .build();

        CombProcessingReagent = new Material.Builder(11026, gjId("comb_processing_reagent"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(FluoroantimonicAcid, 3, Mutagen, 1)
                .colorAverage()
                .build();

        ConductiveIron = new Material.Builder(11028, gjId("conductive_iron"))
                .dust().ingot()
                .components(Iron, 1, Redstone, 1)
                .color(0xd860f0)
                .iconSet(SHINY)
                .build();

        RedstoneAlloy = new Material.Builder(11029, gjId("redstone_alloy"))
                .dust().ingot()
                .components(Redstone, 1, Silicon, 1)
                .color(0xf54058)
                .iconSet(SHINY)
                .build();

        EnergeticAlloy = new Material.Builder(11030, gjId("energetic_alloy"))
                .dust().ingot()
                .components(Gold, 1, Redstone, 1, Glowstone, 1)
                .color(0xf26629)
                .iconSet(SHINY)
                .build();

        VibrantAlloy = new Material.Builder(11031, gjId("vibrant_alloy"))
                .dust().ingot()
                .components(EnergeticAlloy, 1, EnderPearl, 1)
                .color(0x2c1830)
                .iconSet(SHINY)
                .build();

        ElectricalSteel = new Material.Builder(11032, gjId("electrical_steel"))
                .dust().ingot()
                .components(Steel, 1, Silicon, 1)
                .color(0xb0afae)
                .iconSet(SHINY)
                .build();

        DarkSteel = new Material.Builder(11033, gjId("dark_steel"))
                .dust().ingot()
                .components(Steel, 1, Obsidian, 1)
                .color(0x4a4948)
                .build();

        PulsatingIron = new Material.Builder(11034, gjId("pulsating_iron"))
                .dust().ingot()
                .components(Iron, 1, EnderPearl, 1)
                .color(0x6bf75e)
                .iconSet(SHINY)
                .build();

        AwakenedDraconium = new Material.Builder(11035, gjId("awakened_draconium"))
                .dust().ingot()
                .color(0xfc650d)
                .iconSet(SHINY)
                .build()
                .setFormula("Dc*", true);

        Manasteel = new Material.Builder(11036, gjId("manasteel"))
                .dust().ingot()
                .color(0x3389FF)
                .iconSet(SHINY)
                .build()
                .setFormula("FeMa", true);

        Terrasteel = new Material.Builder(11037, gjId("terrasteel"))
                .dust().ingot()
                .color(0x53F900)
                .iconSet(SHINY)
                .build()
                .setFormula("FeMa2Tr", true);

        Elementium = new Material.Builder(11038, gjId("elven_elementium"))
                .dust().ingot()
                .color(0xF15CAE)
                .iconSet(SHINY)
                .build()
                .setFormula("El", true);

        GaiaSpirit = new Material.Builder(11039, gjId("gaia"))
                .dust().ingot()
                .color(0xA4DBC9)
                .build()
                .setFormula("Gs", true);

        Thaumium = new Material.Builder(11040, gjId("thaumium"))
                .dust().ingot()
                .color(0x2c1830)
                .build().setFormula("FeMa");

        VoidMetal = new Material.Builder(11041, gjId("void"))
                .dust().ingot()
                .color(0x200D36)
                .build().setFormula("FeMa");

        MeteoricIron = new Material.Builder(11042, gjId("meteoric_iron"))
                .dust().ingot()
                .color(0x817568)
                .build().setFormula("FeSp");

        Desh = new Material.Builder(11043, gjId("desh"))
                .dust().ingot()
                .color(0x313131)
                .build().setFormula("De");

        Ardite = new Material.Builder(11044, gjId("ardite"))
                .dust().ingot()
                .element(Ad)
                .color(0xe68632)
                .iconSet(SHINY)
                .build();

        Manyullun = new Material.Builder(11045, gjId("manyullun"))
                .dust().ingot()
                .components(Cobalt, 1, Ardite, 1)
                .color(0xad1aa8)
                .iconSet(SHINY)
                .build().setFormula("My");

        /*
         * Uranium = new Material.Builder(11046, gjId("uranium"))
         * .dust()
         * .element(U)
         * .color(Uranium235.getMaterialRGB())
         * .iconSet(SHINY)
         * .build();
         * 
         * Plutonium = new Material.Builder(11047, gjId("plutonium"))
         * .dust()
         * .element(Pu)
         * .color(Plutonium241.getMaterialRGB())
         * .iconSet(SHINY)
         * .build();
         */
    }
}
