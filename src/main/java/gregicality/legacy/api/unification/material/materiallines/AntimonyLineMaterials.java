package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public class AntimonyLineMaterials {
    private AntimonyLineMaterials(){}
    public static Material AntimonySlag;
    public static Material AntimonyWaste;
    public static Material AntimonySulfide;
    public static Material AntimonyDross;
    public static Material SodiumAntimonate;
    public static Material HighPurityAntimony;

    public static void init(){
        AntimonySlag = new Material.Builder(3614,gcylrId("antimony_slag"))
                .fluid()
                .color(avgColor(Antimony,Steel))
                .build()
                .setFormula("Sb?",true);

        AntimonyWaste = new Material.Builder(3615,gcylrId("antimony_waste"))
                .fluid()
                .color(AntimonySlag.getMaterialRGB()-20)
                .build()
                .setFormula("Sb?",true);

        AntimonySulfide = new Material.Builder(3616,gcylrId("antimony_sulfide"))
                .dust()
                .components(Antimony, 2, Sulfur, 5)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AntimonyDross = new Material.Builder(3617, gcylrId("antimony_dross"))
                .dust().liquid(new FluidBuilder().temperature(600))
                .components(Calcium, 1, Magnesium, 1, Antimony, 2)
                .color(0x3c3a45)
                .build();

        SodiumAntimonate = new Material.Builder(3618,gcylrId("sodium_antimonate"))
                .dust()
                .components(Sodium,1,Antimony,1,Oxygen,2)
                .colorAverage()
                .build();

        HighPurityAntimony = new Material.Builder(3619, gcylrId("high_purity_antimony"))
                .dust()
                .color(Antimony.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Antimony,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Sb*",true);
    }
}
