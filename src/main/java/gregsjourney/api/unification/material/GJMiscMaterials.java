package gregsjourney.api.unification.material;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class GJMiscMaterials {

    private GJMiscMaterials() {}

    public static Material DinitrogenPentoxide;
    public static Material SodiumHydroxideSolution;
    public static Material HydrogenFluoride;
    public static Material HydrogenChloride;
    public static Material NitrousAcid;
    public static Material SulfurDichloride;
    public static Material ChloricAcid;
    public static Material ChlorineMonoxide;
    public static Material AmmoniumBifluoride;
    public static Material AmmoniumFluoride;
    public static Material IodicAcid;
    public static Material MethylIsobutylCarbinol;
    public static Material OxalicAcid;

    public static void init() {
        // Materials
        materialInit();
    }

    private static void materialInit() {
        DinitrogenPentoxide = new Material.Builder(4001, gjId("dinitrogen_pentoxide"))
                .gas()
                .components(Nitrogen, 2, Oxygen, 5)
                .colorAverage()
                .build();

        SodiumHydroxideSolution = new Material.Builder(4002,gjId("sodium_hydroxide_solution"))
                .fluid()
                .color(SodiumHydroxide.getMaterialRGB()-20)
                .components(SodiumHydroxide,1,Water,1)
                .build();

        HydrogenFluoride = new Material.Builder(4003, gjId("hydrogen_fluoride"))
                .gas()
                .components(Hydrogen, 1, Fluorine, 1)
                .colorAverage()
                .build();

        HydrogenChloride = new Material.Builder(4004, gjId("hydrogen_chloride"))
                .gas()
                .components(Hydrogen, 1, Chlorine, 1)
                .colorAverage()
                .build();

        NitrousAcid = new Material.Builder(4005,gjId("nitrous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,1,Nitrogen,1,Oxygen,2)
                .build();

        SulfurDichloride = new Material.Builder(4006, gjId("sulfur_dichloride"))
                .fluid()
                .components(Sulfur, 1, Chlorine, 2)
                .colorAverage()
                .build();

        ChloricAcid = new Material.Builder(4007,gjId("chloric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,1,Chlorine,1,Oxygen,3)
                .build();

        ChlorineMonoxide = new Material.Builder(4008, gjId("chlorine_monoxide"))
                .fluid()
                .components(Chlorine,1,Oxygen,1)
                .colorAverage()
                .build();

        AmmoniumBifluoride = new Material.Builder(4009, gjId("ammonium_bifluoride"))
                .dust()
                .components(Nitrogen,1,Fluorine,2,Hydrogen,5)
                .colorAverage()
                .build()
                .setFormula("NH4HF2",true);

        AmmoniumFluoride = new Material.Builder(4010, gjId("ammonium_fluoride"))
                .dust()
                .components(Nitrogen,1,Hydrogen,4,Fluorine,1)
                .colorAverage()
                .build();

        IodicAcid = new Material.Builder(4011,gjId("iodic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,3,Iodine,1,Oxygen,3)
                .build();

        MethylIsobutylCarbinol = new Material.Builder(4012,gjId("methyl_isobutyl_carbinol"))
                .fluid()
                .colorAverage()
                .components(Carbon,6,Hydrogen,14,Oxygen,1)
                .build();

        OxalicAcid = new Material.Builder(4013,gjId("oxalic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,2,Carbon,2,Oxygen,4)
                .build();
    }
}
