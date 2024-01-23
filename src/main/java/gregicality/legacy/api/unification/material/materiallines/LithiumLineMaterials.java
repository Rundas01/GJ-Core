package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class LithiumLineMaterials {
    private LithiumLineMaterials(){}
    public static Material ImpureLithiumSlag;
    public static Material LithiumSlag;
    public static Material LithiumWaste;
    public static Material LithiumHydroxide;
    public static Material LithiumHydroxideSolution;
    public static Material LithiumNitrate;
    public static Material LithiumCarbonate;
    public static Material LithiumOxide;
    public static Material HighPurityLithium;

    public static void init(){
        ImpureLithiumSlag = new Material.Builder(8000,gcylrId("impure_lithium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Li?",true);

        LithiumSlag = new Material.Builder(8001,gcylrId("lithium_slag"))
                .fluid()
                .color(avgColor(Lithium,ImpureLithiumSlag))
                .build()
                .setFormula("Li?",true);

        LithiumWaste = new Material.Builder(8002,gcylrId("lithium_waste"))
                .fluid()
                .color(LithiumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Li?",true);

        LithiumHydroxide = new Material.Builder(8003, gcylrId("lithium_hydroxide"))
                .dust()
                .components(Lithium,1,Oxygen,1,Hydrogen,1)
                .colorAverage()
                .build();

        LithiumHydroxideSolution = new Material.Builder(8004, gcylrId("lithium_hydroxide_solution"))
                .fluid()
                .components(LithiumHydroxide,1,Water,1)
                .colorAverage()
                .build();

        LithiumNitrate = new Material.Builder(8005,gcylrId("lithium_nitrate"))
                .dust()
                .components(Lithium,1,Nitrogen,1,Oxygen,3)
                .colorAverage()
                .build();

        LithiumCarbonate = new Material.Builder(8006,gcylrId("lithium_carbonate"))
                .dust()
                .components(Lithium,2,Carbon,1,Oxygen,3)
                .colorAverage()
                .build();

        LithiumOxide = new Material.Builder(8007,gcylrId("lithium_oxide"))
                .dust()
                .components(Lithium,2,Oxygen,1)
                .colorAverage()
                .build();

        HighPurityLithium = new Material.Builder(8008,gcylrId("high_purity_lithium"))
                .dust()
                .components(Lithium,1)
                .color(Lithium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Lithium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Li*",true);
    }
}
