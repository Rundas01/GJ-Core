package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class SamariumLineMaterials {
    private SamariumLineMaterials(){}
    public static Material SamariumSlag;
    public static Material SamariumWaste;
    public static Material SamariumPhosphate;
    public static Material HighPuritySamarium;

    public static void init(){
        SamariumSlag = new Material.Builder(5351,gjId("samarium_slag"))
                .fluid()
                .color(avgColor(Samarium,Steel))
                .build()
                .setFormula("Sm?",true);

        SamariumWaste = new Material.Builder(5352,gjId("samarium_waste"))
                .fluid()
                .color(SamariumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Sm?",true);

        SamariumPhosphate = new Material.Builder(5353,gjId("samarium_phosphate"))
                .dust()
                .components(Samarium,1,Phosphorus,1,Oxygen,4)
                .colorAverage()
                .build();

        HighPuritySamarium = new Material.Builder(5400,gjId("high_purity_samarium"))
                .dust()
                .color(Samarium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sm*",true);
    }
}
