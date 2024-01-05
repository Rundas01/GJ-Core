package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class AluminiumLineMaterials {
    private AluminiumLineMaterials(){}

    public static Material SodiumAluminate;
    public static Material ImpureSodiumAluminateSolution;
    public static Material SodiumAluminateSolution;
    public static Material AluminiumSulfate;
    public static Material AluminiumSulfateSolution;
    public static Material AluminiumHydroxide;
    public static Material AluminiumOxide;
    public static Material AluminiumTrifluoride;
    public static Material SodiumFluoride;
    public static Material SodiumFluorideSolution;
    public static Material IronIIIOxide;
    public static Material ImpureSodaAshSolution;
    public static Material RedMud;
    public static Material ConcentratedRedMud;
    public static Material RedMudSlag;
    public static Material LeachedRedMudSlag;

    public static void init(){
        SodiumAluminate = new Material.Builder(3600, gcylrId("sodium_aluminate"))
                .dust()
                .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
                .colorAverage()
                .build();

        SodiumFluoride = new Material.Builder(3601, gcylrId("sodium_fluoride"))
                .fluid().dust()
                .components(Sodium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        ImpureSodiumAluminateSolution = new Material.Builder(3602, gcylrId("impure_sodium_aluminate_solution"))
                .fluid()
                .color(0x5b80ba)
                .build()
                .setFormula("?",true);

        SodiumAluminateSolution = new Material.Builder(3603, gcylrId("sodium_aluminate_solution"))
                .fluid()
                .color(0x3f71bf)
                .components(SodiumAluminate,1,Water,1)
                .build();

        AluminiumHydroxide = new Material.Builder(3604, gcylrId("aluminium_hydroxide"))
                .dust()
                .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Al(OH)3", true);

        AluminiumOxide = new Material.Builder(3605, gcylrId("aluminium_oxide"))
                .dust().fluid()
                .components(Aluminium, 2, Oxygen, 3)
                .color(0xd0cff7)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumTrifluoride = new Material.Builder(3606, gcylrId("aluminium_trifluoride"))
                .dust()
                .components(Aluminium, 1, Fluorine, 3)
                .color(0x3d98bf)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AluminiumSulfate = new Material.Builder(3607, gcylrId("aluminium_sulfate"))
                .dust()
                .components(Aluminium, 2, Sulfur, 3, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("Al2(SO4)3", true);

        SodiumFluoride = new Material.Builder(3608, gcylrId("sodium_fluoride"))
                .fluid().dust()
                .components(Sodium, 1, Fluorine, 1)
                .colorAverage()
                .build();

        IronIIIOxide = new Material.Builder(3609, gcylrId("iron_iii_oxide"))
                .dust()
                .components(Iron, 2, Oxygen, 3)
                .color(0x8F0C03)
                .build();

        SodiumFluorideSolution = new Material.Builder(3610, gcylrId("sodium_fluoride_solution"))
                .fluid()
                .components(SodiumFluoride, 1, Water, 1)
                .colorAverage()
                .build();

        RedMud = new Material.Builder(3612, gcylrId("red_mud"))
                .fluid()
                .color(0x913f2d)
                .build()
                .setFormula("?",true);

        ConcentratedRedMud = new Material.Builder(3613, gcylrId("concentrated_red_mud"))
                .fluid()
                .color(0x824133)
                .build()
                .setFormula("?",true);

        RedMudSlag = new Material.Builder(3614, gcylrId("red_mud_slag"))
                .dust()
                .color(0x85001B)
                .build()
                .setFormula("?",true);

        LeachedRedMudSlag = new Material.Builder(3615, gcylrId("leached_red_mud_slag"))
                .dust()
                .color(0x652724)
                .build()
                .setFormula("?",true);

        ImpureSodaAshSolution = new Material.Builder(3616, gcylrId("impure_soda_ash_solution"))
                .fluid()
                .color(0x91919b)
                .build()
                .setFormula("?",true);

        AluminiumSulfateSolution = new Material.Builder(3617, gcylrId("aluminium_sulfate_solution"))
                .fluid()
                .components(AluminiumSulfate, 1, Water, 1)
                .colorAverage()
                .build();
    }
}
