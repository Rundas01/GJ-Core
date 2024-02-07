package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class ChromeLineMaterials {
    private ChromeLineMaterials(){}
    public static Material ChromeSlag;
    public static Material ChromeWaste;
    public static Material RubySlag;
    public static Material ChromiteSlag;
    public static Material ChromeTrifluoride;
    public static Material SodiumHypochromite;
    public static Material ChromeIIISulfate;
    public static Material ChromeSulfide;
    public static Material ChromeIIIOxide;
    public static Material HighPurityChrome;

    public static void init(){
        ChromeSlag = new Material.Builder(4651,gjId("chrome_slag"))
                .fluid()
                .color(avgColor(Chrome,Steel))
                .build()
                .setFormula("Cr?",true);

        ChromeWaste = new Material.Builder(4652,gjId("chrome_waste"))
                .fluid()
                .color(ChromeSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ba?",true);

        RubySlag = new Material.Builder(4653,gjId("ruby_slag"))
                .fluid()
                .color(avgColor(Ruby,Steel))
                .build()
                .setFormula("?");

        ChromiteSlag = new Material.Builder(4654,gjId("chromite_slag"))
                .fluid()
                .color(avgColor(Chromite,Steel))
                .build()
                .setFormula("?");

        ChromeTrifluoride = new Material.Builder(4655, gjId("chrome_trifluoide"))
                .dust()
                .components(Chrome, 1, Fluorine, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        SodiumHypochromite = new Material.Builder(4656,gjId("sodium_hypochromite"))
                .dust()
                .colorAverage()
                .components(Sodium,2,Chrome,1,Oxygen,2)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        ChromeIIISulfate = new Material.Builder(4657,gjId("chrome_iii_sulfate"))
                .dust()
                .colorAverage()
                .components(Chrome,2,Sulfur,3,Oxygen,12)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Cr2(SO4)3",true);

        ChromeSulfide = new Material.Builder(4658, gjId("chrome_sulfide"))
                .dust()
                .components(Chrome, 1, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        ChromeIIIOxide = new Material.Builder(4659, gjId("chrome_iii_oxide"))
                .dust()
                .components(Chrome, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();
        
        HighPurityChrome = new Material.Builder(4700, gjId("high_purity_chrome"))
                .dust()
                .color(Chrome.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Cr*",true);
    }
}
