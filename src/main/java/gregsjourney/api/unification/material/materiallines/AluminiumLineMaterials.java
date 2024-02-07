package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.unification.material.GJOreMaterials.Cryolite;
import static gregsjourney.api.unification.material.GJOreMaterials.Petalite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class AluminiumLineMaterials {
    private AluminiumLineMaterials(){}
    public static Material AluminiumSlag;
    public static Material AluminiumWaste;
    public static Material BauxiteSlag;
    public static Material SapphireSlag;
    public static Material GreenSapphireSlag;
    public static Material CryoliteSlag;
    public static Material PetaliteSlag;
    public static Material GrossularSlag;
    public static Material AlmandineSlag;
    public static Material BlueTopazSlag;
    public static Material PyropeSlag;
    public static Material SodaliteSlag;
    public static Material LazuriteSlag;
    public static Material TopazSlag;
    public static Material KyaniteSlag;
    public static Material MicaSlag;
    public static Material AluniteSlag;
    public static Material SodiumAluminate;
    public static Material SodiumAluminateSolution;
    public static Material AluminiumHydroxide;
    public static Material AluminiumIIIOxide;
    public static Material AluminiumTrifluoride;
    public static Material AluminiumTrichloride;
    public static Material AluminiumBronze;
    public static Material AluminiumCopperChloride;
    public static Material AluminiumCopperChlorideSolution;
    public static Material ChloridicAluminiumTailings;
    public static Material HighPurityAluminium;

    public static void init(){
        AluminiumSlag = new Material.Builder(4101,gjId("aluminium_slag"))
                .fluid()
                .color(avgColor(Aluminium,Steel))
                .build()
                .setFormula("Al?",true);

        AluminiumWaste = new Material.Builder(4102,gjId("aluminium_waste"))
                .fluid()
                .color(AluminiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Al?",true);

        BauxiteSlag = new Material.Builder(4103,gjId("bauxite_slag"))
                .fluid()
                .color(avgColor(Bauxite,Steel))
                .build()
                .setFormula("?");

        SapphireSlag = new Material.Builder(4104,gjId("sapphire_slag"))
                .fluid()
                .color(avgColor(Sapphire,Steel))
                .build()
                .setFormula("?");

        GreenSapphireSlag = new Material.Builder(4105,gjId("green_sapphire_slag"))
                .fluid()
                .color(avgColor(GreenSapphire,Steel))
                .build()
                .setFormula("?");

        CryoliteSlag = new Material.Builder(4106,gjId("cryolite_slag"))
                .fluid()
                .color(avgColor(Cryolite,Steel))
                .build()
                .setFormula("?");

        PetaliteSlag = new Material.Builder(4107,gjId("petalite_slag"))
                .fluid()
                .color(avgColor(Petalite,Steel))
                .build()
                .setFormula("?");

        GrossularSlag = new Material.Builder(4108,gjId("grossular_slag"))
                .fluid()
                .color(avgColor(Grossular,Steel))
                .build()
                .setFormula("?");

        AlmandineSlag = new Material.Builder(4109,gjId("almandine_slag"))
                .fluid()
                .color(avgColor(Almandine,Steel))
                .build()
                .setFormula("?");

        TopazSlag = new Material.Builder(4110,gjId("topaz_slag"))
                .fluid()
                .color(avgColor(Topaz,Steel))
                .build()
                .setFormula("?");

        BlueTopazSlag = new Material.Builder(4111,gjId("blue_topaz_slag"))
                .fluid()
                .color(avgColor(BlueTopaz,Steel))
                .build()
                .setFormula("?");

        PyropeSlag = new Material.Builder(4112,gjId("pyrope_slag"))
                .fluid()
                .color(avgColor(Pyrope,Steel))
                .build()
                .setFormula("?");

        SodaliteSlag = new Material.Builder(4113,gjId("sodalite_slag"))
                .fluid()
                .color(avgColor(Sodalite,Steel))
                .build()
                .setFormula("?");

        LazuriteSlag = new Material.Builder(4114,gjId("lazurite_slag"))
                .fluid()
                .color(avgColor(Lazurite,Steel))
                .build()
                .setFormula("?");

        KyaniteSlag = new Material.Builder(4115,gjId("kyanite_slag"))
                .fluid()
                .color(avgColor(Kyanite,Steel))
                .build()
                .setFormula("?");

        MicaSlag = new Material.Builder(4116,gjId("mica_slag"))
                .fluid()
                .color(avgColor(Mica,Steel))
                .build()
                .setFormula("?");

        AluniteSlag = new Material.Builder(4117,gjId("alunite_slag"))
                .fluid()
                .color(avgColor(Alunite,Steel))
                .build()
                .setFormula("?");

        SodiumAluminate = new Material.Builder(4118, gjId("sodium_aluminate"))
                .dust()
                .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
                .colorAverage()
                .build();

        SodiumAluminateSolution = new Material.Builder(4119, gjId("sodium_aluminate_solution"))
                .fluid()
                .color(0x3f71bf)
                .components(SodiumAluminate,1,Water,1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        AluminiumHydroxide = new Material.Builder(4120, gjId("aluminium_hydroxide"))
                .dust()
                .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Al(OH)3", true);

        AluminiumIIIOxide = new Material.Builder(4121, gjId("aluminium_iii_oxide"))
                .dust().fluid()
                .components(Aluminium, 2, Oxygen, 3)
                .color(0xd0cff7)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumTrifluoride = new Material.Builder(4122, gjId("aluminium_trifluoride"))
                .dust()
                .components(Aluminium, 1, Fluorine, 3)
                .color(0x3d98bf)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumTrichloride = new Material.Builder(4123, gjId("aluminium_trichloride"))
                .dust()
                .components(Aluminium, 1, Chlorine, 3)
                .color(0x2c88d8)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumBronze = new Material.Builder(4124, gjId("aluminium_bronze"))
                .ingot().fluid()
                .components(Aluminium, 1, Copper, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumCopperChloride = new Material.Builder(4125, gjId("aluminium_copper_chloride"))
                .dust()
                .components(Aluminium,1,Copper,1,Chlorine,4)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumCopperChlorideSolution = new Material.Builder(4126, gjId("aluminium_copper_chloride_solution"))
                .fluid()
                .components(AluminiumCopperChloride,1,Water,2)
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ChloridicAluminiumTailings = new Material.Builder(4127, gjId("aluminium_copper_chloride_solution"))
                .fluid()
                .color(avgColor(Aluminium,Steel,HydrochloricAcid))
                .build()
                .setFormula("Cl?",true);

        HighPurityAluminium = new Material.Builder(4150, gjId("high_purity_aluminium"))
                .dust()
                .color(Aluminium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Al*",true);
    }
}
