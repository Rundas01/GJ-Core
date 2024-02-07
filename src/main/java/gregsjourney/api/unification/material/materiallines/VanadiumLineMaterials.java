package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregsjourney.api.utils.GJUtil.gjId;
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
    public static Material VanadiumTriiodide;
    public static Material HighPurityVanadium;

    public static void init(){
        ImpureVanadiumSlag = new Material.Builder(5751,gjId("impure_vanadium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("V?");

        VanadiumSlag = new Material.Builder(5752,gjId("vanadium_slag"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("V?");

        VanadiumWaste = new Material.Builder(5753,gjId("vanadium_waste"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("V?");

        SodiumMetaVanadate = new Material.Builder(5754,gjId("sodium_metavanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Sodium,1,Vanadium,1,Oxygen,3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumMetaVanadate = new Material.Builder(5755,gjId("ammonium_metavanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,1,Hydrogen,4,Nitrogen,1,Oxygen,3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumNitrite = new Material.Builder(5756,gjId("ammonium_nitrite"))
                .dust()
                .colorAverage()
                .iconSet(METALLIC)
                .components(Nitrogen,2,Hydrogen,4,Oxygen,2)
                .build()
                .setFormula("NH4NO2",true);

        VanadiumVOxide = new Material.Builder(5757,gjId("vanadium_v_oxide"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,2,Oxygen,5)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        VanadiumTriiodide = new Material.Builder(5758,gjId("vanadium_triiodide"))
                .dust()
                .colorAverage()
                .components(Vanadium,1,Iodine,3)
                .build();

        HighPurityVanadium = new Material.Builder(5800,gjId("high_purity_vanadium"))
                .dust()
                .colorAverage()
                .iconSet(SHINY)
                .build()
                .setFormula("V*",true);
    }
}
