package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Thortveitite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.SodiumAluminate;
import static gregicality.legacy.api.unification.material.materiallines.ScandiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ManganeseLine {
    private ManganeseLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Spessartine Oxidation Smelting
        //Al2Mn3Si3O12 + 3 KNO3 + 4 Na2CO3 = 3 KNO2 + 2 NaAlO2 + 3 Na2MnO3 + 3 SiO2 + 4 CO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Spessartine,20)
                .fluidInputs(SodiumCarbonateSolution.getFluid(4000),PotassiumNitrateSolution.getFluid(3000))
                .output(dust,SodiumManganate,18)
                .output(dust,SodiumAluminate,8)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(ImpureManganeseSlag.getFluid(1000),Steam.getFluid(7000))
                .EUt(VA[MV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureScandiumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ScandiumSlag.getFluid(1000))
                .fluidOutputs(ScandiumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ScandiumSlag.getFluid(8000))
                .output(dustImpure,Thortveitite)
                .fluidOutputs(ScandiumWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ScandiumWaste.getFluid(18000))
                .output(dustImpure,Thortveitite)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //MnO2 + C = Mn + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Pyrolusite,3)
                .input(dust,Carbon)
                .output(dust,Manganese)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(600)
                .blastFurnaceTemp(2000)
                .buildAndRegister();
    }
}
