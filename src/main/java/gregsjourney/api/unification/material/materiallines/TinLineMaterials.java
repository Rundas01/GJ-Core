package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class TinLineMaterials {
    private TinLineMaterials(){}
    public static Material TinSlag;
    public static Material TinWaste;
    public static Material TinTetrachloride;
    public static Material HighPurityTin;

    public static void init(){
        TinSlag = new Material.Builder(5651,gjId("tin_slag"))
                .fluid()
                .color(avgColor(Tin,Steel))
                .build()
                .setFormula("Sn?",true);

        TinWaste = new Material.Builder(5652,gjId("tin_waste"))
                .fluid()
                .color(TinSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Sn?",true);

        TinTetrachloride = new Material.Builder(5653,gjId("tin_tetrachloride"))
                .dust().fluid()
                .colorAverage()
                .components(Tin,1,Chlorine,4)
                .build();

        HighPurityTin = new Material.Builder(5700, gjId("high_purity_tin"))
                .dust()
                .color(Tin.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sn*",true);
    }
}
