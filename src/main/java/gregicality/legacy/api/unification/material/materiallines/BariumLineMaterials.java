package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class BariumLineMaterials {
    private BariumLineMaterials(){}

    public static Material ImpureBariumSlag;
    public static Material BariumSlag;
    public static Material BariumWaste;
    public static Material BariumOxide;
    public static Material BariumChlorate;
    public static Material BariumPerchlorate;
    public static Material BariumChloride;
    public static Material BariumChlorideSolution;
    public static Material BariumHydroxide;
    public static Material HighPurityBarium;

    public static void init(){
        ImpureBariumSlag = new Material.Builder(3632,gcylrId("impure_barium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Ba?",true);

        BariumSlag = new Material.Builder(3633,gcylrId("barium_slag"))
                .fluid()
                .color(avgColor(Barium,ImpureBariumSlag))
                .build()
                .setFormula("Ba?",true);

        BariumWaste = new Material.Builder(3634,gcylrId("barium_waste"))
                .fluid()
                .color(BariumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ba?",true);

        BariumOxide = new Material.Builder(3635, gcylrId("barium_oxide"))
                .dust()
                .components(Barium, 1, Oxygen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BariumChlorate = new Material.Builder(3637,gcylrId("barium_chlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO3)2",true);

        BariumPerchlorate = new Material.Builder(3638,gcylrId("barium_perchlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,8)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO4)2",true);

        BariumChloride = new Material.Builder(3639, gcylrId("barium_chloride"))
                .dust()
                .components(Barium, 1, Chlorine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        BariumChlorideSolution = new Material.Builder(3640, gcylrId("barium_chloride_solution"))
                .fluid()
                .components(BariumChloride, 1, Water, 1)
                .colorAverage()
                .build();

        BariumHydroxide = new Material.Builder(3641, gcylrId("barium_hydroxide"))
                .dust()
                .components(Barium, 1, Hydrogen, 2, Oxygen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(OH)2",true);
        
        HighPurityBarium = new Material.Builder(3642, gcylrId("high_purity_barium"))
                .dust()
                .components(Barium,1)
                .color(Barium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba*",true);
    }
}
