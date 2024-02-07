package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class LanthanumLineMaterials {
    private LanthanumLineMaterials(){}
    public static Material LanthanumSlag;
    public static Material LanthanumWaste;
    public static Material LanthanumPhosphate;
    public static Material HighPurityLanthanum;

    public static void init(){
        LanthanumSlag = new Material.Builder(5901,gjId("lanthanum_slag"))
                .fluid()
                .color(avgColor(Lanthanum,Steel))
                .build()
                .setFormula("La?",true);

        LanthanumWaste = new Material.Builder(5902,gjId("lanthanum_waste"))
                .fluid()
                .color(LanthanumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("La?",true);

        LanthanumPhosphate = new Material.Builder(5903,gjId("lanthanum_phosphate"))
                .dust()
                .components(Lanthanum,1,Phosphorus,1,Oxygen,4)
                .colorAverage()
                .build();

        HighPurityLanthanum = new Material.Builder(5950,gjId("high_purity_lanthanum"))
                .dust()
                .color(Lanthanum.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("La*",true);
    }
}
