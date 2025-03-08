package thegreggening.api.unification.material;

import static thegreggening.utils.TGUtil.tgId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import thegreggening.api.unification.property.CoolantProperty;
import thegreggening.api.unification.property.TGPropertyKeys;

public class TGMiscMaterials {

    private TGMiscMaterials() {}

    public static Material HexafluoroSilicicAcid;
    public static Material CombProcessingReagent;
    public static Material MethylIsobutylCarbinol;
    public static Material NaKEutectic;
    public static Material HotNaKEutectic;
    public static Material LBEEutectic;
    public static Material HotLBEEutectic;
    public static Material FLiBe;
    public static Material HotFLiBe;
    public static Material LithiumFluoride;
    public static Material BerylliumDifluoride;
    public static Material FreeProtonGas;
    public static Material FreeNeutronGas;
    public static Material FreeElectronGas;

    public static void init() {
        materialInit();
    }

    private static void materialInit() {
        HexafluoroSilicicAcid = new Material.Builder(11025, tgId("hexafluoro_silicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .colorAverage()
                .build();

        CombProcessingReagent = new Material.Builder(11026, tgId("comb_processing_reagent"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(FluoroantimonicAcid, 3, Mutagen, 1)
                .colorAverage()
                .build();

        MethylIsobutylCarbinol = new Material.Builder(11027, tgId("methyl_isobutyl_carbinol"))
                .fluid()
                .components(Carbon, 6, Hydrogen, 14, Oxygen, 1)
                .colorAverage()
                .build();

        NaKEutectic = new Material.Builder(11028, tgId("nak_eutectic"))
                .fluid()
                .components(Sodium, 1, Potassium, 4)
                .color(SodiumPotassium.getMaterialRGB())
                .build();

        HotNaKEutectic = new Material.Builder(11029, tgId("hot_nak_eutectic"))
                .liquid(new FluidBuilder().temperature(1058))
                .components(Sodium, 1, Potassium, 4)
                .color(SodiumPotassium.getMaterialRGB())
                .build();
        NaKEutectic.setProperty(TGPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotNaKEutectic, 10));

        LBEEutectic = new Material.Builder(11030, tgId("lbe_eutectic"))
                .dust().liquid(new FluidBuilder().temperature(296))
                .components(Lead, 45, Bismuth, 55)
                .colorAverage()
                .build();

        HotLBEEutectic = new Material.Builder(11031, tgId("hot_lbe_eutectic"))
                .liquid(new FluidBuilder().temperature(1943))
                .components(Lead, 45, Bismuth, 55)
                .colorAverage()
                .build();
        LBEEutectic.setProperty(TGPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotLBEEutectic, 20));

        LithiumFluoride = new Material.Builder(11032, tgId("lithium_fluoride"))
                .dust()
                .components(Lithium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        BerylliumDifluoride = new Material.Builder(11033, tgId("beryllium_difluoride"))
                .dust()
                .components(Beryllium, 1, Fluorine, 2)
                .colorAverage()
                .build();

        FLiBe = new Material.Builder(11034, tgId("flibe"))
                .dust().liquid(new FluidBuilder().temperature(732))
                .components(LithiumFluoride, 2, BerylliumDifluoride, 1)
                .colorAverage()
                .build();

        HotFLiBe = new Material.Builder(11035, tgId("hot_flibe"))
                .liquid(new FluidBuilder().temperature(1703))
                .components(LithiumFluoride, 2, BerylliumDifluoride, 1)
                .colorAverage()
                .build();
        FLiBe.setProperty(TGPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotFLiBe, 30));

        FreeProtonGas = new Material.Builder(11036, tgId("free_proton_gas"))
                .gas()
                .color(0xAC1B1B)
                .build();
        FreeProtonGas.setFormula("p+");

        FreeElectronGas = new Material.Builder(11037, tgId("free_electron_gas"))
                .gas()
                .color(0x044c4c)
                .build();
        FreeElectronGas.setFormula("e-");

        FreeNeutronGas = new Material.Builder(11038, tgId("free_neutron_gas"))
                .gas()
                .color(0x3C60E8)
                .build();
        FreeNeutronGas.setFormula("n");
    }
}
