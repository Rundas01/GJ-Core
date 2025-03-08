package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.convertRGB2Hex;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

public class SiliconLineMaterials {

    private SiliconLineMaterials() {}

    public static Material TrichloroSilane;
    public static Material SiliconTetrachloride;
    public static Material HexachloroDisilane;
    public static Material DichloroSilane;
    public static Material HexafluoroSilicicAcid;
    public static Material HighPuritySilicon;

    public static void init() {
        TrichloroSilane = new Material.Builder(5501, gjId("trichloro_silane"))
                .fluid()
                .color(convertRGB2Hex(255, 255, 255))
                .components(Hydrogen, 1, Silicon, 1, Chlorine, 3)
                .build();

        SiliconTetrachloride = new Material.Builder(5502, gjId("silicon_tetrachloride"))
                .fluid()
                .color(convertRGB2Hex(220, 220, 220))
                .components(Silicon, 1, Chlorine, 4)
                .build();

        DichloroSilane = new Material.Builder(5503, gjId("dichloro_silane"))
                .fluid()
                .components(Hydrogen, 2, Silicon, 1, Chlorine, 2)
                .colorAverage()
                .build();

        HexachloroDisilane = new Material.Builder(5504, gjId("hexachloro_disilane"))
                .fluid()
                .components(Silicon, 2, Chlorine, 6)
                .colorAverage()
                .build();

        HexafluoroSilicicAcid = new Material.Builder(5505, gjId("hexafluoro_silicic_acic"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .colorAverage()
                .build();

        HighPuritySilicon = new Material.Builder(5550, gjId("high_purity_silicon"))
                .dust()
                .iconSet(SHINY)
                .color(Silicon.getMaterialRGB())
                .build()
                .setFormula("Si*", true);
    }
}
