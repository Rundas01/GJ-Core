package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class CaesiumLineMaterials {
    private CaesiumLineMaterials(){}
    public static Material CaesiumSlag;
    public static Material CaesiumWaste;
    public static Material PolluciteSlag;
    public static Material CaesiumFluoride;
    public static Material CaesiumOxide;
    public static Material HighPurityCaesium;


    public static void init(){
        CaesiumSlag = new Material.Builder(4501,gjId("caesium_slag"))
                .fluid()
                .color(avgColor(Caesium,Steel))
                .build()
                .setFormula("Cs?",true);

        CaesiumWaste = new Material.Builder(4502,gjId("caesium_waste"))
                .fluid()
                .color(CaesiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Cs?",true);

        PolluciteSlag = new Material.Builder(4503,gjId("pollucite_slag"))
                .fluid()
                .color(avgColor(Pollucite,Steel))
                .build()
                .setFormula("?");

        CaesiumFluoride = new Material.Builder(4504,gjId("caesium_fluoride"))
                .dust()
                .colorAverage()
                .components(Caesium,1,Fluorine,1)
                .build();

        CaesiumOxide = new Material.Builder(4505,gjId("caesium_oxide"))
                .dust()
                .colorAverage()
                .components(Caesium,2,Oxygen,1)
                .build();

        HighPurityCaesium = new Material.Builder(4550,gjId("high_purity_caesium"))
                .dust()
                .color(Caesium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Cs*",true);
    }
}
