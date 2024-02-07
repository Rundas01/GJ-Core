package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class NeodymiumLineMaterials {
    private NeodymiumLineMaterials(){}
    public static Material NeodymiumSlag;
    public static Material NeodymiumWaste;
    public static Material NeodymiumPhosphate;
    public static Material HighPurityNeodymium;

    public static void init(){
        NeodymiumSlag = new Material.Builder(5151,gjId("neodymium_slag"))
                .fluid()
                .color(avgColor(Neodymium,Steel))
                .build()
                .setFormula("Nd?",true);

        NeodymiumWaste = new Material.Builder(5152,gjId("neodymium_waste"))
                .fluid()
                .color(NeodymiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Nd?",true);

        NeodymiumPhosphate = new Material.Builder(5153,gjId("neodymium_phosphate"))
                .dust()
                .components(Neodymium,1,Phosphorus,1,Oxygen,4)
                .colorAverage()
                .build();

        HighPurityNeodymium = new Material.Builder(5200,gjId("high_purity_neodymium"))
                .dust()
                .color(Neodymium.getMaterialRGB())
                .build()
                .setFormula("Nd*",true);
    }
}
