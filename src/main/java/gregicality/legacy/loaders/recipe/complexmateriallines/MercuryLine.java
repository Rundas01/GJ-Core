package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumCarbonateSolution;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class MercuryLine {
    private MercuryLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Cinnabar Basic Cracking
        //HgS + Na2CO3 = HgCO3 + Na2S
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cinnabar,2)
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .output(dust,MercuryCarbonate,5)
                .output(dust,SodiumSulfide,3)
                .fluidOutputs(ImpureMercurySlag.getFluid(1000),Steam.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureMercurySlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MercurySlag.getFluid(1000))
                .fluidOutputs(MercuryWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(MercurySlag.getFluid(8000))
                .output(dustImpure,Cinnabar)
                .fluidOutputs(MercuryWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(MercuryWaste.getFluid(18000))
                .output(dustImpure,Cinnabar)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
