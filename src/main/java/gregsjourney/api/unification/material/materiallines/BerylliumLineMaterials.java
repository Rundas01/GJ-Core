package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.unification.material.GJOreMaterials.Bertrandite;
import static gregsjourney.api.unification.material.GJOreMaterials.Phenakite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class BerylliumLineMaterials {
    private BerylliumLineMaterials(){}
    public static Material BerylliumSlag;
    public static Material BerylliumWaste;
    public static Material EmeraldSlag;
    public static Material BertranditeSlag;
    public static Material PhenakiteSlag;
    public static Material BerylliumOxide;
    public static Material BerylliumISulfide;
    public static Material BerylliumSulfate;
    public static Material BerylliumSulfateSolution;
    public static Material BerylliumHydroxide;
    public static Material AmmoniumTetrafluoroBeryllate;
    public static Material BerylliumDifluoride;
    public static Material BerylliumDichloride;
    public static Material BerylliumFluorideSolution;
    public static Material SodiumBeryllate;
    public static Material HighPurityBeryllium;

    public static void init(){
        BerylliumSlag = new Material.Builder(4301, gjId("beryllium_slag"))
                .fluid()
                .color(avgColor(Beryllium,Steel))
                .build()
                .setFormula("Be?",true);

        BerylliumWaste = new Material.Builder(4302, gjId("beryllium_waste"))
                .fluid()
                .color(BerylliumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Be?",true);

        EmeraldSlag = new Material.Builder(4303, gjId("emerald_slag"))
                .fluid()
                .color(avgColor(Emerald,Steel))
                .build()
                .setFormula("?");

        BertranditeSlag = new Material.Builder(4304, gjId("bertrandite_slag"))
                .fluid()
                .color(avgColor(Bertrandite,Steel))
                .build()
                .setFormula("Be?");

        PhenakiteSlag = new Material.Builder(4305, gjId("phenakite_slag"))
                .fluid()
                .color(avgColor(Phenakite,Steel))
                .build()
                .setFormula("Be?");

        BerylliumISulfide = new Material.Builder(4306, gjId("beryllium_i_sulfide"))
                .dust()
                .color(0x4f7615)
                .components(Beryllium,2,Sulfur,1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumSulfate = new Material.Builder(4307, gjId("beryllium_sulfate"))
                .dust()
                .color(0x608726)
                .components(Beryllium,1,Sulfur,1,Oxygen,4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumSulfateSolution = new Material.Builder(4308, gjId("beryllium_sulfate_solution"))
                .fluid()
                .colorAverage()
                .components(BerylliumSulfate,1,Water,1)
                .build();

        BerylliumHydroxide = new Material.Builder(4309, gjId("beryllium_hydroxide"))
                .dust()
                .components(Beryllium, 1, Oxygen, 2, Hydrogen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Be(OH)2", true);

        AmmoniumTetrafluoroBeryllate = new Material.Builder(4310, gjId("ammonium_tetrafluoro_beryllate"))
                .dust()
                .components(Nitrogen, 2, Hydrogen, 8, Beryllium, 1, Fluorine, 4)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2BeF4",true);

        BerylliumDifluoride = new Material.Builder(4311, gjId("beryllium_difluoride"))
                .dust()
                .components(Beryllium, 1, Fluorine, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumDichloride = new Material.Builder(4312, gjId("beryllium_dichloride"))
                .dust()
                .components(Beryllium, 1, Chlorine, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumFluorideSolution = new Material.Builder(4313, gjId("beryllium_fluoride_solution"))
                .fluid()
                .components(BerylliumDifluoride,1,Water,1)
                .colorAverage()
                .build();

        SodiumBeryllate = new Material.Builder(4314, gjId("sodium_beryllate"))
                .dust()
                .components(Sodium, 2, Beryllium, 1, Oxygen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumOxide = new Material.Builder(4315, gjId("beryllium_oxide"))
                .dust()
                .components(Beryllium, 1, Oxygen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityBeryllium = new Material.Builder(4350, gjId("high_purity_beryllium"))
                .dust()
                .color(Beryllium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Be*",true);
    }
}
