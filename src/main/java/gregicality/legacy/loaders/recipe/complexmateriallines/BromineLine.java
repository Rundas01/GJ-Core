package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.unification.material.materiallines.BromineLineMaterials.SodiumBromide;
import static gregicality.legacy.api.unification.material.materiallines.BromineLineMaterials.SodiumBromideSolution;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class BromineLine {
    private BromineLine(){}

    public static void init(){
        //BROMINE REGENERATION
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumBromide,2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumBromideSolution.getFluid(2000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumBromideSolution.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(1000))
                .duration(60)
                .EUt(VA[LV])
                .buildAndRegister();
    }
}
