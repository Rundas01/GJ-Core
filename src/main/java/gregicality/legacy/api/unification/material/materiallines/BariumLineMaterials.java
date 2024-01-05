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
    public static Material BariumCarbonate;
    public static Material BariumChlorate;
    public static Material BariumPerchlorate;
    public static Material BariumChloride;
    public static Material BariumChlorideSolution;
    public static Material BariumHydroxide;
    public static Material HighPurityBarium;

    public static void init(){
        ImpureBariumSlag = new Material.Builder(3661,gcylrId("impure_barium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Ba?",true);

        BariumSlag = new Material.Builder(3662,gcylrId("barium_slag"))
                .fluid()
                .color(avgColor(Barium,ImpureBariumSlag))
                .build()
                .setFormula("Ba?",true);

        BariumWaste = new Material.Builder(3663,gcylrId("barium_waste"))
                .fluid()
                .color(BariumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ba?",true);

        BariumOxide = new Material.Builder(3664, gcylrId("barium_oxide"))
                .dust()
                .components(Barium, 1, Oxygen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BariumCarbonate = new Material.Builder(3665, gcylrId("barium_carbonate"))
                .dust()
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BariumChlorate = new Material.Builder(3666,gcylrId("barium_chlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO3)2",true);

        BariumPerchlorate = new Material.Builder(3667,gcylrId("barium_perchlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,8)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO4)2",true);

        BariumChloride = new Material.Builder(3668, gcylrId("barium_chloride"))
                .dust()
                .components(Barium, 1, Chlorine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        BariumChlorideSolution = new Material.Builder(3669, gcylrId("barium_chloride_solution"))
                .fluid()
                .components(BariumChloride, 1, Water, 1)
                .colorAverage()
                .build();

        BariumHydroxide = new Material.Builder(3670, gcylrId("barium_hydroxide"))
                .dust()
                .components(Barium, 1, Hydrogen, 2, Oxygen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(OH)2",true);
        
        HighPurityBarium = new Material.Builder(3671, gcylrId("high_purity_barium"))
                .dust()
                .components(Barium,1)
                .color(Barium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ba*",true);
    }
}
