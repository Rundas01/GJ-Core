package gregsjourney.api.unification.material;

import gregsjourney.api.unification.property.CoolantProperty;
import gregsjourney.api.unification.property.GJPropertyKeys;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregsjourney.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;

public class GJMiscMaterials {

    private GJMiscMaterials() {}

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

    public static void init() {
        materialInit();
    }

    private static void materialInit() {
        HexafluoroSilicicAcid = new Material.Builder(11025, gjId("hexafluoro_silicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .colorAverage()
                .build();

        CombProcessingReagent = new Material.Builder(11026, gjId("comb_processing_reagent"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(FluoroantimonicAcid, 3, Mutagen, 1)
                .colorAverage()
                .build();

        MethylIsobutylCarbinol = new Material.Builder(11027, gjId("methyl_isobutyl_carbinol"))
                .fluid()
                .components(Carbon, 6, Hydrogen, 14, Oxygen, 1)
                .colorAverage()
                .build();

        NaKEutectic = new Material.Builder(11028, gjId("nak_eutectic"))
                .fluid()
                .components(Sodium, 1, Potassium, 4)
                .color(SodiumPotassium.getMaterialRGB())
                .build();

        HotNaKEutectic = new Material.Builder(11029, gjId("hot_nak_eutectic"))
                .liquid(new FluidBuilder().temperature(1058))
                .components(Sodium, 1, Potassium, 4)
                .color(SodiumPotassium.getMaterialRGB())
                .build();
        NaKEutectic.setProperty(GJPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotNaKEutectic, 10));

        LBEEutectic = new Material.Builder(11030, gjId("lbe_eutectic"))
                .dust().liquid(new FluidBuilder().temperature(296))
                .components(Lead, 45, Bismuth, 55)
                .colorAverage()
                .build();

        HotLBEEutectic = new Material.Builder(11031, gjId("hot_lbe_eutectic"))
                .liquid(new FluidBuilder().temperature(1943))
                .components(Lead, 45, Bismuth, 55)
                .colorAverage()
                .build();
        LBEEutectic.setProperty(GJPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotLBEEutectic, 20));

        LithiumFluoride = new Material.Builder(11032, gjId("lithium_fluoride"))
                .dust()
                .components(Lithium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        BerylliumDifluoride = new Material.Builder(11033, gjId("beryllium_difluoride"))
                .dust()
                .components(Beryllium, 1, Fluorine, 2)
                .colorAverage()
                .build();

        FLiBe = new Material.Builder(11034, gjId("flibe"))
                .dust().liquid(new FluidBuilder().temperature(732))
                .components(LithiumFluoride, 2, BerylliumDifluoride, 1)
                .colorAverage()
                .build();

        HotFLiBe = new Material.Builder(11035, gjId("hot_flibe"))
                .liquid(new FluidBuilder().temperature(1703))
                .components(LithiumFluoride, 2, BerylliumDifluoride, 1)
                .colorAverage()
                .build();
        FLiBe.setProperty(GJPropertyKeys.COOLANT_PROPERTY, new CoolantProperty(HotFLiBe, 30));
    }
}
