package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.ArsenicVOxide;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.SodiumArsenate;
import static gregicality.legacy.api.unification.material.materiallines.PlatinumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class PalladiumPlatinumLine {
    private PalladiumPlatinumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Cooperite Oxidation Smelting
        //Pt3NiSPd + 4 Na2CO3 + 14 KNO3 = 14 KNO2 + 3 Na2PtO4 + NiO + Na2PdO3 + 4 CO2 + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cooperite,6)
                .fluidInputs(SodiumCarbonateSolution.getFluid(4000),PotassiumNitrateSolution.getFluid((14000)))
                .output(dust,SodiumPlatinate,21)
                .output(dust,SodiumPalladate,6)
                .output(dust,NickelOxide,2)
                .fluidOutputs(ImpurePtPdSlag.getFluid(1000),SulfurDioxide.getFluid(1000),CarbonDioxide.getFluid(4000),Steam.getFluid(18000))
                .EUt(VA[IV])
                .duration(1000)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Sperryllite Oxidation Smelting
        //As2Pt + 8 KNO3 + 2 Na2CO3 = 2 NaAsO3 + Na2PtO4 + 2 CO2 + 8 KNO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Sperrylite,4)
                .fluidInputs(SodiumCarbonateSolution.getFluid(2000),PotassiumNitrateSolution.getFluid(8000))
                .output(dust,SodiumPlatinate,7)
                .output(dust,SodiumArsenate,10)
                .output(dust,PotassiumNitrite,32)
                .fluidOutputs(ImpurePlatinumSlag.getFluid(500),ImpureArsenicSlag.getFluid(500),CarbonDioxide.getFluid(2000),Steam.getFluid(10000))
                .duration(800)
                .EUt(VA[IV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Braggite Cracking
        //PdPtNiS + 4 O2 = PdO2 + PtO3 + NiO + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Braggite,4)
                .fluidInputs(Oxygen.getFluid(8000))
                .output(dust,PalladiumDioxide,3)
                .output(dust,PlatinumTrioxide,4)
                .output(dust,NickelOxide,2)
                .fluidOutputs(BraggiteSlag.getFluid(400),SulfurDioxide.getFluid(1000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpurePlatinumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(PlatinumSlag.getFluid(1000))
                .fluidOutputs(PlatinumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpurePalladiumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(PalladiumSlag.getFluid(1000))
                .fluidOutputs(PalladiumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ImpurePtPdSlag.getFluid(2000))
                .fluidOutputs(ImpurePlatinumSlag.getFluid(1000),ImpurePalladiumSlag.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumSlag.getFluid(8000))
                .output(dustImpure,Sperrylite)
                .fluidOutputs(PlatinumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumWaste.getFluid(18000))
                .output(dustImpure,Sperrylite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PalladiumSlag.getFluid(8000))
                //.output(dustImpure,)
                .fluidOutputs(PalladiumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PalladiumWaste.getFluid(18000))
                //.output(dustImpure,)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();
    }
}
