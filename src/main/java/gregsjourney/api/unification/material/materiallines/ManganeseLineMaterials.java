package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class ManganeseLineMaterials {
    private ManganeseLineMaterials(){}
    public static Material ManganeseSlag;
    public static Material ManganeseWaste;
    public static Material ManganeseSulfate;
    public static Material ManganeseDioxide;
    public static Material PotassiumPermanganate;
    public static Material PotassiumManganate;
    public static Material HighPurityManganese;

    public static void init(){
        ManganeseSlag = new Material.Builder(5055,gjId("manganese_slag"))
                .fluid()
                .color(avgColor(Manganese,Steel))
                .build()
                .setFormula("Mn?",true);

        ManganeseWaste = new Material.Builder(5056,gjId("manganese_waste"))
                .fluid()
                .color(ManganeseSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Mn?",true);

        ManganeseSulfate = new Material.Builder(5057,gjId("manganese_sulfate"))
                .dust()
                .colorAverage()
                .components(Magnesium,1,Sulfur,1,Oxygen,4)
                .build();

        ManganeseDioxide = new Material.Builder(5058, gjId("manganese_dioxide"))
                .dust()
                .components(Magnesium,1,Fluorine,2)
                .colorAverage()
                .build();

        PotassiumPermanganate = new Material.Builder(5059, gjId("potassium_permanganate"))
                .dust()
                .components(Potassium,1,Manganese,1,Oxygen,4)
                .colorAverage()
                .build();

        PotassiumManganate = new Material.Builder(5060, gjId("potassium_manganate"))
                .dust()
                .components(Potassium,1,Manganese,1,Oxygen,3)
                .colorAverage()
                .build();

        HighPurityManganese = new Material.Builder(5099,gjId("high_purity_manganese"))
                .dust()
                .color(Manganese.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Mn*",true);
    }
}
