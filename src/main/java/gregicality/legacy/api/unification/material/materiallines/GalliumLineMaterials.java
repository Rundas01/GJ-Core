package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class GalliumLineMaterials {
    private GalliumLineMaterials(){}
    public static Material ImpureGalliumSlag;
    public static Material GalliumSlag;
    public static Material GalliumWaste;
    public static Material HighPurityGallium;

    public static void init(){
        ImpureGalliumSlag = new Material.Builder(3705,gcylrId("impure_gallium_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Ga?",true);

        GalliumSlag = new Material.Builder(3706,gcylrId("gallium_slag"))
                .fluid()
                .color(avgColor(Gallium,ImpureGalliumSlag))
                .build()
                .setFormula("Ga?",true);

        GalliumWaste = new Material.Builder(3707,gcylrId("gallium_waste"))
                .fluid()
                .color(GalliumSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Ga?",true);

        HighPurityGallium = new Material.Builder(3708, gcylrId("high_purity_gallium"))
                .dust()
                .color(Gallium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Gallium,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ga*",true);
    }
}
