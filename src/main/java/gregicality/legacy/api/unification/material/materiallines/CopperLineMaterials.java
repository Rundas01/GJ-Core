package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class CopperLineMaterials {
    private CopperLineMaterials(){}
    public static Material ImpureCopperSlag;
    public static Material CopperSlag;
    public static Material CopperWaste;
    public static Material AcidicCopperSolution;
    public static Material CopperSulfate;
    public static Material HighPurityCopper;

    public static void init(){
        ImpureCopperSlag = new Material.Builder(3699, gcylrId("impure_copper_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("Cu?",true);

        CopperSlag = new Material.Builder(3700, gcylrId("copper_slag"))
                .fluid()
                .color(avgColor(Cobalt,ImpureCopperSlag))
                .build()
                .setFormula("Cu?",true);

        CopperWaste = new Material.Builder(3701, gcylrId("copper_waste"))
                .fluid()
                .color(CopperSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Cu?",true);

        AcidicCopperSolution = new Material.Builder(3702, gcylrId("acidic_copper_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Water, 4, Copper, 2, Sulfur, 2, Oxygen, 8, NitricAcid, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(H2O)4(CuSO4)2(HNO3)2", true);

        CopperSulfate = new Material.Builder(3703, gcylrId("copper_sulfate"))
                .dust()
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        HighPurityCopper = new Material.Builder(3704, gcylrId("high_purity_copper"))
                .dust()
                .color(Copper.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .components(Copper,1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Cu*",true);
    }
}
