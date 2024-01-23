package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
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

public class IronLine {
    private IronLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Chalcopyrite Basic Cracking
        //6 CuFeS2 + 11 Na2CO3 = 3 Fe2(CO3)2 + 3 Cu2CO3 + 11 Na2S + 2 CO2 + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Chalcopyrite,24)
                .fluidInputs(SodiumCarbonateSolution.getFluid(11000))
                .output(dust,IronIIICarbonate,33)
                .output(dust,CopperCarbonate,18)
                .output(dust,SodiumSulfide,33)
                .fluidOutputs(ChalcopyriteSlag.getFluid(400),SulfurDioxide.getFluid(1000),CarbonDioxide.getFluid(2000),Steam.getFluid(11000))
                .duration(240)
                .EUt(VA[LV])
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Pyrite Basic Cracking
        //3 FeS2 + 5 Na2CO3 = 3 FeCO3 + 5 Na2S + 2 CO2 + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pyrite,9)
                .fluidInputs(SodiumCarbonateSolution.getFluid(5000))
                .output(dust,IronIICarbonate,15)
                .output(dust,SodiumSulfide,15)
                .fluidOutputs(PyriteSlag.getFluid(300),SulfurDioxide.getFluid(1000),CarbonDioxide.getFluid(2000),Steam.getFluid(5000))
                .duration(240)
                .EUt(VA[LV])
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Olivine Acidic Cracking (Komponenten ändern)
        //Mg2Fe2(SiO4)2 + 8 NaHSO4 = 4 Na2SO4 + 2 MgSO4 + 2 FeSO4 + 2 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Olivine,14)
                .fluidInputs(SodiumBisulfateSolution.getFluid(8000))
                .output(dust,IronIISulfate,12)
                .output(dust,MagnesiumSulfate,12)
                .output(dust,SodiumSulfate,28)
                .fluidOutputs(OlivineSlag.getFluid(1400),Steam.getFluid(12000))
                .duration(240)
                .EUt(VA[LV])
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Centrifuging Amethyst Dust (Komponenten ändern)
        //2 (Fe,Si)O2 = FeO2 + SiO2
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust,Amethyst,2)
                .output(dust,IronIIOxide)
                .output(dust,SiliconDioxide)
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //Centrifuging DIOtomite Dust (Komponenten ändern)
        //16 (SiO2)14(Fe2O3)(Al2O3) = Fe2O3 + Al2O3 + 14 SiO2
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust,Diatomite,16)
                .output(dust,IronIIIOxide)
                .output(dust,AluminiumOxide,1)
                .output(dust,SiliconDioxide,14)
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ChalcopyriteSlag.getFluid(400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(IronSlag.getFluid(100),CopperSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PyriteSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(OlivineSlag.getFluid(1400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(IronSlag.getFluid(200),MagnesiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(IronSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Iron)
                .chancedOutput(dustImpure,Iron,1,8750,800)
                .chancedOutput(dustImpure,Iron,1,7500,700)
                .chancedOutput(dustImpure,Iron,1,6250,600)
                .chancedOutput(dustImpure,Iron,1,5000,500)
                .chancedOutput(dustImpure,Iron,1,3750,400)
                .chancedOutput(dustImpure,Iron,1,2500,300)
                .chancedOutput(dustImpure,Iron,1,1250,200)
                .chancedOutput(dustImpure,Iron,1,0,100)
                .fluidOutputs(IronWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(IronWaste.getFluid(1000))
                .chancedOutput(dustImpure,Iron,1,7500,750)
                .chancedOutput(dustImpure,Iron,1,5000,500)
                .chancedOutput(dustImpure,Iron,1,2500,250)
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
