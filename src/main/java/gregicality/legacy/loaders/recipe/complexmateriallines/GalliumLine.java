package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FLOTATION_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.GalliumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.Bauxite;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GalliumLine {
    private GalliumLine() {}

    public static void init() {
        //TODO: Gallium Erze

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureGalliumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(GalliumSlag.getFluid(1000))
                .fluidOutputs(GalliumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(GalliumSlag.getFluid(8000))
                .output(dustImpure,Bauxite)
                .fluidOutputs(GalliumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(GalliumWaste.getFluid(18000))
                .output(dustImpure,Bauxite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
