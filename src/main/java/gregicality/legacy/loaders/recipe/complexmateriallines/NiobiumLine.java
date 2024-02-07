package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Columbite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumBisulfateSolution;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumSulfate;
import static gregicality.legacy.api.unification.material.materiallines.CalciumLineMaterials.CalciumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class NiobiumLine {
    private NiobiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Pyrochlore Acidic Cracking
        //Ca2Nb2O7 + 14 NaHSO4 = 7 Na2SO4 + Nb2(SO4)5 + 7 H2O + 2 CaSO4
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pyrochlore,11)
                .fluidInputs(SodiumBisulfateSolution.getFluid(14000))
                .output(dust,NiobiumVSulfate,27)
                .output(dust,CalciumSulfate,12)
                .output(dust,SodiumSulfate,49)
                .fluidOutputs(PyrochloreSlag.getFluid(1100),Steam.getFluid(11000))
                .duration(240)
                .EUt(VA[IV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Columbite Cracking
        //FeMnNb2O6 + 20 HF + 2 H2SO4 = FeF2 + MnF4 + 2 H2NbF7 + 10 H2O + 2 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Columbite,10)
                .fluidInputs(HydrofluoricAcid.getFluid(20000),SulfuricAcid.getFluid(2000))
                .output(dust,NiobiumVHydroHeptafluoríde,20)
                .output(dust,IronDifluoride,4)
                .output(dust,ManagneseTetrafluoride,5)
                .fluidOutputs(ColumbiteSlag.getFluid(1000),SulfurDioxide.getFluid(2000),Steam.getFluid(30000))
                .duration(240)
                .EUt(VA[IV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PyrochloreSlag.getFluid(1100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(NiobiumSlag.getFluid(200),CalciumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(NiobiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Niobium)
                .chancedOutput(dustImpure,Niobium,1,8750,800)
                .chancedOutput(dustImpure,Niobium,1,7500,700)
                .chancedOutput(dustImpure,Niobium,1,6250,600)
                .chancedOutput(dustImpure,Niobium,1,5000,500)
                .chancedOutput(dustImpure,Niobium,1,3750,400)
                .chancedOutput(dustImpure,Niobium,1,2500,300)
                .chancedOutput(dustImpure,Niobium,1,1250,200)
                .chancedOutput(dustImpure,Niobium,1,0,100)
                .fluidOutputs(NiobiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(NiobiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Niobium,1,7500,750)
                .chancedOutput(dustImpure,Niobium,1,5000,500)
                .chancedOutput(dustImpure,Niobium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
