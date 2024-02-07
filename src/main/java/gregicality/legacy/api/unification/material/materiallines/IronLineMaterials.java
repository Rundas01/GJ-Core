package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class IronLineMaterials {
    private IronLineMaterials(){}
    public static Material ImpureIronSlag;
    public static Material IronSlag;
    public static Material IronWaste;
    public static Material IronIISulfate;
    public static Material IronCarbonate;
    public static Material SodiumFerrateII;
    public static Material HighPurityIron;

    public static void init(){
        ImpureIronSlag = new Material.Builder(3709,gcylrId("impure_iron_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Fe?",true);

        IronSlag = new Material.Builder(3710,gcylrId("iron_slag"))
                .fluid()
                .color(avgColor(Iron,ImpureIronSlag))
                .build()
                .setFormula("Fe?",true);

        IronWaste = new Material.Builder(3711,gcylrId("iron_waste"))
                .fluid()
                .color(IronSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Fe?",true);

        IronIISulfate = new Material.Builder(3712,gcylrId("iron_ii_sulfate"))
                .dust()
                .colorAverage()
                .components(Iron,1,Sulfur,1,Oxygen,4)
                .build();

        IronCarbonate = new Material.Builder(3713,gcylrId("iron_carbonate"))
                .dust()
                .colorAverage()
                .components(Iron,1,Carbon,1,Oxygen,3)
                .build();

        SodiumFerrateII = new Material.Builder(3714, gcylrId("sodium_ferrate_ii"))
                .dust()
                .colorAverage()
                .components(Sodium, 2, Iron, 1, Oxygen, 2)
                .build();

        HighPurityIron = new Material.Builder(3715, gcylrId("high_purity_iron"))
                .dust()
                .color(Iron.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Iron,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Fe*",true);
    }
}
