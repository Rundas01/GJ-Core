package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class ScandiumLineMaterials {
    private ScandiumLineMaterials(){}
    public static Material ImpureScandiumSlag;
    public static Material ScandiumSlag;
    public static Material ScandiumWaste;
    public static Material ScandiumSulfate;
    public static Material HighPurityScandium;

    public static void init(){
        ImpureScandiumSlag = new Material.Builder(3721,gcylrId("impure_scandium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Sc?",true);

        ScandiumSlag = new Material.Builder(3722,gcylrId("scandium_slag"))
                .fluid()
                .color(avgColor(Scandium,ImpureScandiumSlag))
                .build()
                .setFormula("Sc?",true);

        ScandiumWaste = new Material.Builder(3723,gcylrId("scandium_waste"))
                .fluid()
                .color(ScandiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Sc?",true);

        ScandiumSulfate = new Material.Builder(3724, gcylrId("scandium_sulfate"))
                .dust()
                .colorAverage()
                .components(Scandium,2,Sulfur,4,Oxygen,12)
                .build()
                .setFormula("Sc2(SO4)3");

        HighPurityScandium = new Material.Builder(3725, gcylrId("high_purity_scandium"))
                .dust()
                .color(Scandium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Scandium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Sc*",true);
    }
}
