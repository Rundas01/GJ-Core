package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumTrifluoride;
import static gregsjourney.api.unification.material.materiallines.CaesiumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.HighPurityLithium;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class CaesiumLine {
    private CaesiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Pollucite Cracking
        //Cs2Al2Si4(H2O)2O12 + 8 HF = 2 CsF + 2 AlF3 + 4 SiO2 + 6 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pollucite,26)
                .fluidInputs(HydrofluoricAcid.getFluid(8000))
                .output(dust,CaesiumFluoride,4)
                .output(dust,AluminiumTrifluoride,8)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(PolluciteSlag.getFluid(2600),Steam.getFluid(14000))
                .duration(400)
                .EUt(VA[EV])
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PolluciteSlag.getFluid(2600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CaesiumSlag.getFluid(200),AluminiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CaesiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Caesium)
                .chancedOutput(dustImpure,Caesium,1,8750,800)
                .chancedOutput(dustImpure,Caesium,1,7500,700)
                .chancedOutput(dustImpure,Caesium,1,6250,600)
                .chancedOutput(dustImpure,Caesium,1,5000,500)
                .chancedOutput(dustImpure,Caesium,1,3750,400)
                .fluidOutputs(CaesiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CaesiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Caesium,1,7500,750)
                .chancedOutput(dustImpure,Caesium,1,5000,500)
                .chancedOutput(dustImpure,Caesium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the RF
        //Fluoride Oxidation
        //2 CsF + O = Cs2O + F2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CaesiumFluoride,4)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust,CaesiumOxide,3)
                .fluidOutputs(Fluorine.getFluid(2000))
                .EUt(VA[EV])
                .duration(300)
                .buildAndRegister();

        //Oxide Reduction
        //Cs2O + 2 Li = 2 Cs + Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,CaesiumOxide,3)
                .input(dust,HighPurityLithium,2)
                .output(dust,Caesium,2)
                .output(dust,LithiumOxide,3)
                .EUt(VA[EV])
                .duration(600)
                .buildAndRegister();
    }
}
