package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class NickelLine {
    private NickelLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Pentlandenite Basic Cracking
        //Ni9S8 + 8 Na2CO3 = 8 Na2S + 7 NiCO3 + Ni2CO3
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pentlandite,17)
                .fluidInputs(SodiumCarbonateSolution.getFluid(8000))
                .output(dust,NickelIICarbonate,35)
                .output(dust,NickelICarbonate,6)
                .output(dust,SodiumSulfide,24)
                .fluidOutputs(PentlanditeSlag.getFluid(1000),Steam.getFluid(8000))
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PentlanditeSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(NickelSlag.getFluid(1000))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(NickelSlag.getFluid(1000))
                .output(dustImpure,Nickel)
                .chancedOutput(dustImpure,Nickel,1,8750,700)
                .chancedOutput(dustImpure,Nickel,1,7500,600)
                .chancedOutput(dustImpure,Nickel,1,6250,500)
                .chancedOutput(dustImpure,Nickel,1,5000,400)
                .chancedOutput(dustImpure,Nickel,1,3750,300)
                .chancedOutput(dustImpure,Nickel,1,2500,200)
                .chancedOutput(dustImpure,Nickel,1,1250,100)
                .fluidOutputs(NickelWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(NickelWaste.getFluid(1000))
                .chancedOutput(dustImpure,Nickel,1,7500,750)
                .chancedOutput(dustImpure,Nickel,1,5000,500)
                .chancedOutput(dustImpure,Nickel,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //2 NiO + C = 2 Ni + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Garnierite,4)
                .input(dust,Carbon)
                .output(dust,Nickel,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,NickelOxide,4)
                .input(dust,Carbon)
                .output(dust,Nickel,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //2 Ni2CO3 + O2 = 4 NiO + 2 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,NickelICarbonate,12)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust,NickelOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //2 NiCO3 = NiO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,NickelIICarbonate,10)
                .output(dust,NickelOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();
    }
}
