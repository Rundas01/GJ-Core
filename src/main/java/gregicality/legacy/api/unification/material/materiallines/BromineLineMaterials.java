package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;

public class BromineLineMaterials {
    private BromineLineMaterials(){}

    public static Material SodiumBromide;
    public static Material SodiumBromideSolution;

    public static void init(){
        SodiumBromide = new Material.Builder(3690, gcylrId("sodium_bromide"))
                .dust()
                .components(Sodium, 1, Bromine, 1)
                .colorAverage()
                .build();

        SodiumBromideSolution = new Material.Builder(3691, gcylrId("sodium_bromide_solution"))
                .fluid()
                .components(SodiumBromide, 1, Water, 1)
                .colorAverage()
                .build();
    }
}
