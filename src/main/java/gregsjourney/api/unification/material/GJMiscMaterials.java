package gregsjourney.api.unification.material;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;

public class GJMiscMaterials {

    private GJMiscMaterials() {}
    public static Material SulfurDichloride;
    public static Material AmmoniumBifluoride;
    public static Material AmmoniumFluoride;
    public static Material IodicAcid;
    public static Material OxalicAcid;
    public static Material SulfurSilicate;
    public static Material ColdLava;
    public static Material CalciumHydroxide;

    public static void init() {
        // Materials
        materialInit();
    }

    private static void materialInit() {

        SulfurDichloride = new Material.Builder(11006, gjId("sulfur_dichloride"))
                .fluid()
                .components(Sulfur, 1, Chlorine, 2)
                .colorAverage()
                .build();

        AmmoniumBifluoride = new Material.Builder(11009, gjId("ammonium_bifluoride"))
                .dust()
                .components(Nitrogen, 1, Fluorine, 2, Hydrogen, 5)
                .colorAverage()
                .build()
                .setFormula("NH4HF2", true);

        AmmoniumFluoride = new Material.Builder(11010, gjId("ammonium_fluoride"))
                .dust()
                .components(Nitrogen, 1, Hydrogen, 4, Fluorine, 1)
                .colorAverage()
                .build();

        IodicAcid = new Material.Builder(11011, gjId("iodic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen, 3, Iodine, 1, Oxygen, 3)
                .build();

        OxalicAcid = new Material.Builder(11013, gjId("oxalic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen, 2, Carbon, 2, Oxygen, 4)
                .build();

        SulfurSilicate = new Material.Builder(11028, gjId("sulfur_silicate"))
                .dust()
                .colorAverage()
                .components(Sulfur, 4, Silicon, 6, Oxygen, 24)
                .build()
                .setFormula("S4(SiO4)6",true);

        ColdLava = new Material.Builder(11023, gjId("cold_lava"))
                .liquid(new FluidBuilder().temperature(473))
                .color(Lava.getMaterialRGB()-10)
                .build();

        CalciumHydroxide = new Material.Builder(11024, gjId("calcium_hydroxide"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Hydrogen, 2, Oxygen, 2)
                .build()
                .setFormula("Ca(OH)2",true);
    }
}
