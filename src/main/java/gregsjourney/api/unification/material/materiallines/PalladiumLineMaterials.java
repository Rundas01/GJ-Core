package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class PalladiumLineMaterials {
    private PalladiumLineMaterials(){}
    public static Material PalladiumSlag;
    public static Material PalladiumWaste;
    public static Material PalladiumDinitrate;
    public static Material HighPurityPalladium;

    public static void init(){
        PalladiumSlag = new Material.Builder(5201,gjId("palladium_slag"))
                .fluid()
                .color(avgColor(Palladium,Steel))
                .build()
                .setFormula("Pd?",true);

        PalladiumWaste = new Material.Builder(5202,gjId("palladium_waste"))
                .fluid()
                .color(PalladiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Pd?",true);

        PalladiumDinitrate = new Material.Builder(5203,gjId("palladium_dinitrate"))
                .dust()
                .components(Palladium,1,Nitrogen,2,Oxygen,6)
                .colorAverage()
                .build()
                .setFormula("Pd(NO3)2",true);

        HighPurityPalladium = new Material.Builder(5250,gjId("high_purity_palladium"))
                .dust()
                .color(Palladium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pd*",true);
    }
}
