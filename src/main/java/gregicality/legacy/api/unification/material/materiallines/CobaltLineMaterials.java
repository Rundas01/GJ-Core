package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;

public class CobaltLineMaterials {
    private CobaltLineMaterials(){}
    public static Material CobaltSlag;
    public static Material CobaltWaste;
    public static Material HighPurityCobalt;

    public static void init(){
        CobaltSlag = new Material.Builder(3694, gcylrId("cobalt_slag"))
                .fluid()
                .color(avgColor(Cobalt,Steel))
                .build()
                .setFormula("Co?",true);

        CobaltWaste = new Material.Builder(3695, gcylrId("cobalt_waste"))
                .fluid()
                .color(CobaltSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Co?",true);

        HighPurityCobalt = new Material.Builder(3698, gcylrId("high_purity_cobalt"))
                .dust()
                .color(Cobalt.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Cobalt,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Co*",true);
    }
}
