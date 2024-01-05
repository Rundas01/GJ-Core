package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static gregtech.api.unification.material.info.MaterialIconSet.METALLIC;

public class VanadiumLineMaterials {
    private VanadiumLineMaterials(){}

    public static Material ImpureVanadiumSlag;
    public static Material VanadiumSlag;
    public static Material SodiumMetaVanadate;
    public static Material AmmoniumPolyVanadate;
    public static Material AmmoniumNitrite;
    public static Material VanadiumPentoxide;
    public static Material VanadiumWaste;

    public static void init(){
        ImpureVanadiumSlag = new Material.Builder(3654,gcylrId("impure_vanadium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("V?");

        VanadiumSlag = new Material.Builder(3655,gcylrId("vanadium_slag"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("V?");

        SodiumMetaVanadate = new Material.Builder(3656,gcylrId("sodium_metavanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,2,Oxygen,5)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumPolyVanadate = new Material.Builder(3657,gcylrId("ammonium_polyvanadate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,6,Hydrogen,8,Nitrogen,2,Oxygen,16)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AmmoniumNitrite = new Material.Builder(3658,gcylrId("ammonium_nitrite"))
                .dust()
                .colorAverage()
                .iconSet(METALLIC)
                .components(Nitrogen,2,Hydrogen,4,Oxygen,2)
                .build()
                .setFormula("NH4NO2?",true);

        VanadiumPentoxide = new Material.Builder(3659,gcylrId("vanadium_pentoxide"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,2,Oxygen,5)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        VanadiumWaste = new Material.Builder(3660,gcylrId("vanadium_waste"))
                .fluid()
                .colorAverage()
                .iconSet(DULL)
                .components(Vanadium,2,Oxygen,5)
                .flags(DISABLE_DECOMPOSITION)
                .build();
    }
}
