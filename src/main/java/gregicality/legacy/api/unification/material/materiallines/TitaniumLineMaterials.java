package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class TitaniumLineMaterials {
    private TitaniumLineMaterials(){}
    public static Material ImpureTitaniumSlag;
    public static Material TitaniumSlag;
    public static Material TitaniumWaste;
    public static Material SodiumTitanate;
    public static Material HighPurityTitanium;

    public static void init(){
        ImpureTitaniumSlag = new Material.Builder(3736,gcylrId("impure_titanium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Ti?",true);

        TitaniumSlag = new Material.Builder(3737,gcylrId("titanium_slag"))
                .fluid()
                .color(avgColor(Titanium,ImpureTitaniumSlag))
                .build()
                .setFormula("Ti?",true);

        TitaniumWaste = new Material.Builder(3738,gcylrId("titanium_waste"))
                .fluid()
                .color(TitaniumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ti?",true);

        SodiumTitanate = new Material.Builder(3739,gcylrId("sodium_titanate"))
                .dust()
                .colorAverage()
                .components(Sodium,2,Titanium,1,Oxygen,3)
                .build();

        HighPurityTitanium = new Material.Builder(3740, gcylrId("high_purity_titanium"))
                .dust()
                .color(Titanium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Titanium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ti*",true);
    }
}
