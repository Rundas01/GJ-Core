package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class TitaniumLineMaterials {
    private TitaniumLineMaterials(){}
    public static Material TitaniumSlag;
    public static Material TitaniumWaste;
    public static Material SodiumTitanate;
    public static Material HighPurityTitanium;

    public static void init(){
        TitaniumSlag = new Material.Builder(5701,gjId("titanium_slag"))
                .fluid()
                .color(avgColor(Titanium,Steel))
                .build()
                .setFormula("Ti?",true);

        TitaniumWaste = new Material.Builder(5702,gjId("titanium_waste"))
                .fluid()
                .color(TitaniumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ti?",true);

        SodiumTitanate = new Material.Builder(5703,gjId("sodium_titanate"))
                .dust()
                .colorAverage()
                .components(Sodium,2,Titanium,1,Oxygen,3)
                .build();

        HighPurityTitanium = new Material.Builder(5750, gjId("high_purity_titanium"))
                .dust()
                .color(Titanium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ti*",true);
    }
}
