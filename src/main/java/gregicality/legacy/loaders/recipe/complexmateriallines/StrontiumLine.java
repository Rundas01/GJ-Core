package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Celestine;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Strontianite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.CopperSlag;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class StrontiumLine {
    private StrontiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Celestine Cracking
        //SrSO4 + 4 HCl = SrCl2 + SO2 + 2 H2O + Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Celestine,6)
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .output(dust,StrontiumDichloride,3)
                .fluidOutputs(CelestineSlag.getFluid(600),SulfurDioxide.getFluid(1000),Chlorine.getFluid(2000),Steam.getFluid(2000))
                .duration(240)
                .EUt(VA[EV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Strontianite Cracking
        //SrCO3 + 2 HCl = SrCl2 + CO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Strontianite,5)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust,StrontiumDichloride,3)
                .fluidOutputs(StrontianiteSlag.getFluid(500),CarbonDioxide.getFluid(1000),Steam.getFluid(2000))
                .duration(240)
                .EUt(VA[EV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(CelestineSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(StrontiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(StrontianiteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(StrontiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(StrontiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Strontium)
                .chancedOutput(dustImpure,Strontium,1,8750,800)
                .chancedOutput(dustImpure,Strontium,1,7500,700)
                .chancedOutput(dustImpure,Strontium,1,6250,600)
                .chancedOutput(dustImpure,Strontium,1,5000,500)
                .chancedOutput(dustImpure,Strontium,1,3750,400)
                .fluidOutputs(StrontiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(StrontiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Strontium,1,7500,750)
                .chancedOutput(dustImpure,Strontium,1,5000,500)
                .chancedOutput(dustImpure,Strontium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //2 FeHO2 = Fe2O3 + H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BrownLimonite,8)
                .output(dust,IronIIIOxide,5)
                .fluidOutputs(Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,YellowLimonite,8)
                .output(dust,IronIIIOxide,5)
                .fluidOutputs(Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //4 Fe3O4 + O2 = 6 Fe2O3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Magnetite,28)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust,IronIIIOxide,30)
                .EUt(VA[MV])
                .duration(600)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //2 Fe2O3 + 3 C = 4 Fe + 3 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BandedIron,10)
                .input(dust,Carbon,3)
                .output(dust,Iron,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[MV])
                .duration(600)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,IronIIIOxide,10)
                .input(dust,Carbon,3)
                .output(dust,Iron,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[MV])
                .duration(600)
                .blastFurnaceTemp(2000)
                .buildAndRegister();
    }
}
