package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.unification.material.GJOreMaterials.Witherite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class BariumLineMaterials {
    private BariumLineMaterials(){}
    public static Material BariumSlag;
    public static Material BariumWaste;
    public static Material BariteSlag;
    public static Material WitheriteSlag;
    public static Material BariumOxide;
    public static Material BariumChlorate;
    public static Material BariumPerchlorate;
    public static Material BariumChloride;
    public static Material BariumChlorideSolution;
    public static Material BariumHydroxide;
    public static Material BariumCarbonate;
    public static Material HighPurityBarium;

    public static void init(){
        BariumSlag = new Material.Builder(4251,gjId("barium_slag"))
                .fluid()
                .color(avgColor(Barium,Steel))
                .build()
                .setFormula("Ba?",true);

        BariumWaste = new Material.Builder(4252,gjId("barium_waste"))
                .fluid()
                .color(BariumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ba?",true);

        BariteSlag = new Material.Builder(4253,gjId("barite_slag"))
                .fluid()
                .color(avgColor(Barite,Steel))
                .build()
                .setFormula("?");

        WitheriteSlag = new Material.Builder(4254,gjId("witherite_slag"))
                .fluid()
                .color(avgColor(Witherite,Steel))
                .build()
                .setFormula("?");

        BariumOxide = new Material.Builder(4255, gjId("barium_oxide"))
                .dust()
                .components(Barium, 1, Oxygen, 1)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        BariumChlorate = new Material.Builder(4256 ,gjId("barium_chlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO3)2",true);

        BariumPerchlorate = new Material.Builder(4257 ,gjId("barium_perchlorate"))
                .dust()
                .colorAverage()
                .components(Barium,1,Chlorine,2,Oxygen,8)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(ClO4)2",true);

        BariumChloride = new Material.Builder(4258 , gjId("barium_chloride"))
                .dust()
                .components(Barium, 1, Chlorine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        BariumChlorideSolution = new Material.Builder(4259, gjId("barium_chloride_solution"))
                .fluid()
                .components(BariumChloride, 1, Water, 1)
                .colorAverage()
                .build();

        BariumHydroxide = new Material.Builder(4260, gjId("barium_hydroxide"))
                .dust()
                .components(Barium, 1, Hydrogen, 2, Oxygen, 2)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ba(OH)2",true);

        BariumCarbonate = new Material.Builder(4261, gjId("barium_carbonate"))
                .dust()
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();
        
        HighPurityBarium = new Material.Builder(4300, gjId("high_purity_barium"))
                .dust()
                .color(Barium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ba*",true);
    }
}
