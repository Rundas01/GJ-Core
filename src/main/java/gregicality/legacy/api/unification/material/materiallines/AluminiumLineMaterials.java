package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class AluminiumLineMaterials {
    private AluminiumLineMaterials(){}
    public static Material BauxiteSlag;
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
    public static Material AluminiumSlag;
    public static Material AluminiumWaste;
    public static Material SodiumAluminate;
    public static Material SodiumAluminateSolution;
    public static Material AluminiumHydroxide;
    public static Material AluminiumIIIOxide;
    public static Material AluminiumTrifluoride;
    public static Material AluminiumBronze;
    public static Material AluminiumCopperChloride;
    public static Material AluminiumCopperChlorideSolution;
    public static Material HighPurityAluminium;

    public static void init(){
        AluminiumSlag = new Material.Builder(3601,gcylrId("aluminium_slag"))
                .fluid()
                .color(avgColor(Aluminium,Steel))
                .build()
                .setFormula("Al?",true);

        AluminiumWaste = new Material.Builder(3602,gcylrId("aluminium_waste"))
                .fluid()
                .color(AluminiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Al?",true);

        SodiumAluminate = new Material.Builder(3603, gcylrId("sodium_aluminate"))
                .dust()
                .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
                .colorAverage()
                .build();

        SodiumAluminateSolution = new Material.Builder(3605, gcylrId("sodium_aluminate_solution"))
                .fluid()
                .color(0x3f71bf)
                .components(SodiumAluminate,1,Water,1)
                .build();

        AluminiumHydroxide = new Material.Builder(3606, gcylrId("aluminium_hydroxide"))
                .dust()
                .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Al(OH)3", true);

        AluminiumIIIOxide = new Material.Builder(3607, gcylrId("aluminium_iii_oxide"))
                .dust().fluid()
                .components(Aluminium, 2, Oxygen, 3)
                .color(0xd0cff7)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumTrifluoride = new Material.Builder(3608, gcylrId("aluminium_trifluoride"))
                .dust()
                .components(Aluminium, 1, Fluorine, 3)
                .color(0x3d98bf)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumBronze = new Material.Builder(3609, gcylrId("aluminium_bronze"))
                .ingot()
                .components(Aluminium, 1, Copper, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumCopperChloride = new Material.Builder(3610, gcylrId("aluminium_copper_chloride"))
                .fluid()
                .components(Aluminium,1,Copper,1,Chlorine,4)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumCopperChlorideSolution = new Material.Builder(3611, gcylrId("aluminium_copper_chloride_solution"))
                .fluid()
                .components(AluminiumCopperChloride,1,Water,2)
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        HighPurityAluminium = new Material.Builder(3612, gcylrId("high_purity_aluminium"))
                .dust()
                .color(Aluminium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Aluminium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Al*",true);
    }
}
