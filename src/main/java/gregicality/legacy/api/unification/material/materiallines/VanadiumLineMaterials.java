package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public class VanadiumLineMaterials {
    private VanadiumLineMaterials(){}
    public static Material ImpureVanadiumSlag;
    public static Material VanadiumSlag;
    public static Material VanadiumWaste;
    public static Material SodiumMetaVanadate;
    public static Material AmmoniumMetaVanadate;
    public static Material AmmoniumNitrite;
    public static Material VanadiumVOxide;
    public static Material VanadiumIodide;
    public static Material HighPurityVanadium;

    public static void init(){
        ImpureVanadiumSlag = new Material.Builder(3741,gcylrId("impure_vanadium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("V?");

        VanadiumSlag = new Material.Builder(3742,gcylrId("vanadium_slag"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("V?");

        VanadiumWaste = new Material.Builder(3743,gcylrId("vanadium_waste"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("V?");

        SodiumMetaVanadate = new Material.Builder(3744,gcylrId("sodium_metavanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Sodium,1,Vanadium,1,Oxygen,3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumMetaVanadate = new Material.Builder(3745,gcylrId("ammonium_metavanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,1,Hydrogen,4,Nitrogen,1,Oxygen,3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumNitrite = new Material.Builder(3746,gcylrId("ammonium_nitrite"))
                .dust()
                .colorAverage()
                .iconSet(METALLIC)
                .components(Nitrogen,2,Hydrogen,4,Oxygen,2)
                .build()
                .setFormula("NH4NO2?",true);

        VanadiumVOxide = new Material.Builder(3747,gcylrId("vanadium_v_oxide"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,2,Oxygen,5)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        VanadiumIodide = new Material.Builder(7100,gcylrId("vanadium_iodide"))
                .dust()
                .colorAverage()
                .components(Vanadium,1,Iodine,3)
                .build();

        HighPurityVanadium = new Material.Builder(3748,gcylrId("high_purity_vanadium"))
                .dust()
                .colorAverage()
                .iconSet(SHINY)
                .components(Vanadium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("V*",true);
    }
}
