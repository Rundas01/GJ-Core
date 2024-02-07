package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class MagnesiumLineMaterials {
    private MagnesiumLineMaterials(){}
    public static Material MagnesiumSlag;
    public static Material MagnesiumWaste;
    public static Material MagnesiumSulfate;
    public static Material MagnesiumDifluoride;
    public static Material HighPurityMagnesium;

    public static void init(){
        MagnesiumSlag = new Material.Builder(5051,gjId("magnesium_slag"))
                .fluid()
                .color(avgColor(Magnesium,Steel))
                .build()
                .setFormula("Mg?",true);

        MagnesiumWaste = new Material.Builder(5052,gjId("magnesium_waste"))
                .fluid()
                .color(MagnesiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Mg?",true);

        MagnesiumSulfate = new Material.Builder(5053,gjId("magnesium_sulfate"))
                .dust()
                .colorAverage()
                .components(Magnesium,1,Sulfur,1,Oxygen,4)
                .build();

        MagnesiumDifluoride = new Material.Builder(5054, gjId("magnesium_difluoride"))
                .dust()
                .components(Magnesium,1,Fluorine,2)
                .colorAverage()
                .build();

        HighPurityMagnesium = new Material.Builder(5100,gjId("high_purity_magnesium"))
                .dust()
                .color(Magnesium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Mg*",true);
    }
}
