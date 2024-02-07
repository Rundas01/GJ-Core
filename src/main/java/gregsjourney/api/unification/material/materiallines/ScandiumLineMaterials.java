package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class ScandiumLineMaterials {
    private ScandiumLineMaterials(){}
    public static Material ScandiumSlag;
    public static Material ScandiumWaste;
    public static Material ScandiumIIISulfate;
    public static Material HighPurityScandium;

    public static void init(){
        ScandiumSlag = new Material.Builder(5401,gjId("scandium_slag"))
                .fluid()
                .color(avgColor(Scandium,Steel))
                .build()
                .setFormula("Sc?",true);

        ScandiumWaste = new Material.Builder(5402,gjId("scandium_waste"))
                .fluid()
                .color(ScandiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Sc?",true);

        ScandiumIIISulfate = new Material.Builder(5403, gjId("scandium_iii_sulfate"))
                .dust()
                .colorAverage()
                .components(Scandium,2,Sulfur,3,Oxygen,12)
                .build()
                .setFormula("Sc2(SO4)3");

        HighPurityScandium = new Material.Builder(5450, gjId("high_purity_scandium"))
                .dust()
                .color(Scandium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sc*",true);
    }
}
