package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumWaste;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumCarbonate;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class RhodiumLine {
    private RhodiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Rhodplumsite Cracking
        //2 Rh3Pb2S2 + 21 O = 3 Rh2O3 + 4 PbO + 4 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Rhodplumsite,14)
                .fluidInputs(Oxygen.getFluid(21000))
                .output(dust,RhodiumIIIOxide,15)
                .output(dust,LeadOxide,8)
                .fluidOutputs(RhodplumsiteSlag.getFluid(700),SulfurDioxide.getFluid(4000))
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
                .fluidInputs(RhodplumsiteSlag.getFluid(700))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(RhodiumSlag.getFluid(300),LeadSlag.getFluid(200))
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
