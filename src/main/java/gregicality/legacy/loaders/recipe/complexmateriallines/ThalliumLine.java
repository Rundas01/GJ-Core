package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicSlag;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicVOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ThalliumLine {
    private ThalliumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Lorandite Cracking
        //2 TlAsS + 6 O2 = Tl2O3 + As2O5 + 2 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Lorandite,6)
                .fluidInputs(Oxygen.getFluid(12000))
                .output(dust,ThalliumIIIOxide,15)
                .output(dust,ArsenicVOxide,8)
                .fluidOutputs(LoranditeSlag.getFluid(600),SulfurDioxide.getFluid(2000))
                .EUt(VA[IV])
                .duration(400)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Bowieite Cracking
        //Rh2S3 + 9 O = Rh2O3 + 3 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bowieite,5)
                .fluidInputs(Oxygen.getFluid(9000))
                .output(dust,RhodiumIIIOxide,5)
                .fluidOutputs(BowieiteSlag.getFluid(500),SulfurDioxide.getFluid(3000))
                .EUt(VA[IV])
                .duration(400)
                .blastFurnaceTemp(5000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(LoranditeSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ThalliumSlag.getFluid(100),ArsenicSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BowieiteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(RhodiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Rhodium)
                .chancedOutput(dustImpure,Rhodium,1,8750,800)
                .chancedOutput(dustImpure,Rhodium,1,7500,700)
                .chancedOutput(dustImpure,Rhodium,1,6250,600)
                .chancedOutput(dustImpure,Rhodium,1,5000,500)
                .chancedOutput(dustImpure,Rhodium,1,3750,400)
                .fluidOutputs(RhodiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Rhodium,1,7500,750)
                .chancedOutput(dustImpure,Rhodium,1,5000,500)
                .chancedOutput(dustImpure,Rhodium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
