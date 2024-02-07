package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.unification.material.materiallines.SiliconLineMaterials.*;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class SiliconLine {
    private SiliconLine() {}

    public static void init() {
        //HCl + Cl2 + Si -> HSiCl3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Silicon)
                .fluidInputs(Chlorine.getFluid(2000),HydrochloricAcid.getFluid(1000))
                .fluidOutputs(TrichloroSilane.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        //4 HSiCl3 -> 3 SiCl4 + SiH4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(TrichloroSilane.getFluid(4000))
                .fluidOutputs(SiliconTetrachloride.getFluid(3000),Silane.getFluid(1000))
                .EUt(30)
                .duration(240)
                .buildAndRegister();

        //4 Na + SiCl4 -> 4 NaCl + Si*
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Sodium,4)
                .fluidInputs(SiliconTetrachloride.getFluid(1000))
                .output(dust,Salt,4)
                .output(dust,HighPuritySilicon)
                .EUt(30)
                .duration(100)
                .buildAndRegister();
    }
}
