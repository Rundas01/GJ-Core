package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class BerylliumLineMaterials {
    private BerylliumLineMaterials(){}

    public static Material ImpureBerylliumSlag;
    public static Material BerylliumSlag;
    public static Material BerylliumWaste;
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
        ImpureBerylliumSlag = new Material.Builder(3643, gcylrId("impure_beryllium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Be?",true);

        BerylliumSlag = new Material.Builder(3674, gcylrId("beryllium_slag"))
                .fluid()
                .color(avgColor(Beryllium,ImpureBerylliumSlag))
                .build()
                .setFormula("Be?",true);

        BerylliumWaste = new Material.Builder(3675, gcylrId("beryllium_waste"))
                .fluid()
                .color(BerylliumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Be?",true);

        BerylliumSulfate = new Material.Builder(3676, gcylrId("beryllium_sulfate"))
                .dust()
                .color(0x608726)
                .components(Beryllium,1,Sulfur,1,Oxygen,4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumSulfateSolution = new Material.Builder(3677, gcylrId("beryllium_sulfate_solution"))
                .fluid()
                .colorAverage()
                .components(BerylliumSulfate,1,Water,1)
                .build();

        BerylliumHydroxide = new Material.Builder(3678, gcylrId("beryllium_hydroxide"))
                .dust()
                .components(Beryllium, 1, Oxygen, 2, Hydrogen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Be(OH)2", true);

        AmmoniumTetrafluoroBeryllate = new Material.Builder(3679, gcylrId("ammonium_tetrafluoro_beryllate"))
                .dust()
                .components(Nitrogen, 2, Hydrogen, 8, Beryllium, 1, Fluorine, 4)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2BeF4",true);

        BerylliumDifluoride = new Material.Builder(3680, gcylrId("beryllium_difluoride"))
                .dust()
                .components(Beryllium, 1, Fluorine, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumDichloride = new Material.Builder(3681, gcylrId("beryllium_dichloride"))
                .dust()
                .components(Beryllium, 1, Chlorine, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BerylliumFluorideSolution = new Material.Builder(3682, gcylrId("beryllium_fluoride_solution"))
                .fluid()
                .components(BerylliumDifluoride,1,Water,1)
                .colorAverage()
                .build();

        SodiumBeryllate = new Material.Builder(3683, gcylrId("sodium_beryllate"))
                .dust()
                .components(Sodium, 2, Beryllium, 1, Oxygen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        HighPurityBeryllium = new Material.Builder(3684, gcylrId("high_purity_beryllium"))
                .dust()
                .components(Beryllium, 1)
                .color(Beryllium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Be*",true);
    }
}
