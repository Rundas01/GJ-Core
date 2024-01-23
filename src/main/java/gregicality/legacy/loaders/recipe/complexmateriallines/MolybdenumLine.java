package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Ferrimolybdite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumWaste;
import static gregicality.legacy.api.unification.material.materiallines.CalciumLineMaterials.CalciumCarbonate;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class MolybdenumLine {
    private MolybdenumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Molybdenite Cracking
        //MoS2 + 7 O = MoO3 + 2 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Molybdenite,3)
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust,MolybdenumTrioxide,4)
                .fluidOutputs(MolybdeniteSlag.getFluid(300),SulfurDioxide.getFluid(2000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Powellelite Cracking
        //CaMoO4 + 2 NaOH = Ca(OH)2 + Na2MoO4
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Powellite,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumMolybdate,7)
                .output(dust,CalciumHydroxide,5)
                .fluidOutputs(PowelliteSlag.getFluid(600),Steam.getFluid(2000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Wulfenite Cracking
        //PbMoO4 + 4 NaOH = Na2PbO2 + Na2MoO4 + 2 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Wulfenite,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(4000))
                .output(dust,SodiumMolybdate,7)
                .output(dust,SodiumPlumbate,5)
                .fluidOutputs(WulfeniteSlag.getFluid(600),Steam.getFluid(6000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Ferrimolybdite Cracking
        //Fe2(MoO4)3(H2O)8 + 6 NaOH = 2 Fe(OH)3 + 3 Na2MoO4 + 8 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Ferrimolybdite,41)
                .fluidInputs(SodiumHydroxideSolution.getFluid(6000))
                .output(dust,SodiumMolybdate,7)
                .output(dust,IronTrihydroxide,14)
                .fluidOutputs(FerrimolybditeSlag.getFluid(4100),Steam.getFluid(14000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(MolybdeniteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MolybdenumSlag.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PowelliteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MolybdenumSlag.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(WulfeniteSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MolybdenumSlag.getFluid(1000),LeadSlag.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(MolybdenumSlag.getFluid(1000))
                .output(dustImpure,Molybdenum)
                .chancedOutput(dustImpure,Molybdenum,1,8750,700)
                .chancedOutput(dustImpure,Molybdenum,1,7500,600)
                .chancedOutput(dustImpure,Molybdenum,1,6250,500)
                .chancedOutput(dustImpure,Molybdenum,1,5000,400)
                .chancedOutput(dustImpure,Molybdenum,1,3750,300)
                .chancedOutput(dustImpure,Molybdenum,1,2500,200)
                .chancedOutput(dustImpure,Molybdenum,1,1250,100)
                .fluidOutputs(MolybdenumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(MolybdenumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Molybdenum,1,7500,750)
                .chancedOutput(dustImpure,Molybdenum,1,5000,500)
                .chancedOutput(dustImpure,Molybdenum,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
