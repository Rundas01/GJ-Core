package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class CopperLineMaterials {
    private CopperLineMaterials(){}

    public static Material AcidicCopperSolution;
    public static Material CopperSulfate;

    public static void init(){
        AcidicCopperSolution = new Material.Builder(3641, gcylrId("acidic_copper_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Water, 4, Copper, 2, Sulfur, 2, Oxygen, 8, NitricAcid, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(H2O)4(CuSO4)2(HNO3)2", true);

        CopperSulfate = new Material.Builder(3642, gcylrId("copper_sulfate"))
                .dust()
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();
    }
}
