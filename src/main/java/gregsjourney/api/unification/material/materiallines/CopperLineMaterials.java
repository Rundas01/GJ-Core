package gregsjourney.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class CopperLineMaterials {
    private CopperLineMaterials(){}
    public static Material CopperSlag;
    public static Material CopperWaste;
    public static Material AcidicCopperSolution;
    public static Material CopperSulfate;
    public static Material HighPurityCopper;

    public static void init(){
        CopperSlag = new Material.Builder(4751, gjId("copper_slag"))
                .fluid()
                .color(avgColor(Copper,Steel))
                .build()
                .setFormula("Cu?",true);

        CopperWaste = new Material.Builder(4752, gjId("copper_waste"))
                .fluid()
                .color(CopperSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Cu?",true);

        AcidicCopperSolution = new Material.Builder(4753, gjId("acidic_copper_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Water, 4, Copper, 2, Sulfur, 2, Oxygen, 8, NitricAcid, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(H2O)4(CuSO4)2(HNO3)2", true);

        CopperSulfate = new Material.Builder(4754, gjId("copper_sulfate"))
                .dust()
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        HighPurityCopper = new Material.Builder(4800, gjId("high_purity_copper"))
                .dust()
                .color(Copper.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Cu*",true);
    }
}
