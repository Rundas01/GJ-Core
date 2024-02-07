package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Amblygonite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class LithiumLine {
    private LithiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Amblygonite Basic Carcking
        //2 LiAlPO4F + 4 Na2CO3 = 2 NaF + Li2CO3 + Al2(CO3)3 + 2 Na3PO4
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Amblygonite,16)
                .fluidInputs(SodiumCarbonateSolution.getFluid(4000))
                .output(dust,LithiumCarbonate,6)
                .output(dust,AluminiumCarbonate,17)
                .output(dust,SodiumFluoride,4)
                .output(dust,SodiumPhosphate,16)
                .fluidOutputs(AmblygoniteSlag.getFluid(1000),Steam.getFluid(4000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Spondumene Acidic Cracking
        //2 LiAlSi2O6 + 8 NaHSO4 = 4 Na2SO4 + Li2SO4 + Al2(SO4)3 + 4 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Spodumene,20)
                .fluidInputs(SodiumBisulfateSolution.getFluid(8000))
                .output(dust,LithiumSulfate,7)
                .output(dust,AluminiumSulfate,17)
                .output(dust,SiliconDioxide,12)
                .output(dust,SodiumSulfate,28)
                .fluidOutputs(SpodumeneSlag.getFluid(1000),Steam.getFluid(12000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Lepidolite Acidic Cracking (Komponenten anpassen)
        //2 KLi3Al4F2O7 + 30 NaHSO4 = 15 Na2SO4 + 3 Li2SO4 + 4 Al2(SO4)3 + 2 KF + 14 H2O + 2 HF
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Lepidolite,36)
                .fluidInputs(SodiumBisulfateSolution.getFluid(30000))
                .output(dust,LithiumSulfate,21)
                .output(dust,AluminiumSulfate,68)
                .output(dust,PotassiumFluoride,4)
                .output(dust,SodiumSulfate,105)
                .fluidOutputs(LepidoliteSlag.getFluid(1000),HydrogenFluoride.getFluid(2000),Steam.getFluid(44000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(AmblygoniteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LithiumSlag.getFluid(500))
                .fluidOutputs(AluminiumSlag.getFluid(500))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SpodumeneSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LithiumSlag.getFluid(500))
                .fluidOutputs(AluminiumSlag.getFluid(500))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(LepidoliteSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(LithiumSlag.getFluid(300),AluminiumSlag.getFluid(400),PotassiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(LithiumSlag.getFluid(1000))
                .output(dustImpure,Lithium)
                .chancedOutput(dustImpure,Lithium,1,8750,700)
                .chancedOutput(dustImpure,Lithium,1,7500,600)
                .chancedOutput(dustImpure,Lithium,1,6250,500)
                .chancedOutput(dustImpure,Lithium,1,5000,400)
                .chancedOutput(dustImpure,Lithium,1,3750,300)
                .chancedOutput(dustImpure,Lithium,1,2500,200)
                .chancedOutput(dustImpure,Lithium,1,1250,100)
                .fluidOutputs(LithiumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(LithiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Lithium,1,7500,750)
                .chancedOutput(dustImpure,Lithium,1,5000,500)
                .chancedOutput(dustImpure,Lithium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
