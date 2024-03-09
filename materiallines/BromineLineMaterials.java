package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;

public class BromineLineMaterials {

    private BromineLineMaterials() {}

    public static Material SodiumBromide;
    public static Material SodiumBromideSolution;

    public static void init() {
        SodiumBromide = new Material.Builder(4451, gjId("sodium_bromide"))
                .dust()
                .components(Sodium, 1, Bromine, 1)
                .colorAverage()
                .build();

        SodiumBromideSolution = new Material.Builder(4452, gjId("sodium_bromide_solution"))
                .fluid()
                .components(SodiumBromide, 1, Water, 1)
                .colorAverage()
                .build();
    }
}
