package gregsjourney.loaders.recipe.singleblock;

import gregtech.api.recipes.Recipe;

import java.util.Collection;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.V;
import static gregtech.api.recipes.RecipeMaps.COMBUSTION_GENERATOR_FUELS;
import static gregtech.api.recipes.RecipeMaps.GAS_TURBINE_FUELS;
import static gregtech.api.unification.material.Materials.*;

public class FuelChanges {
    private FuelChanges(){}

    public static void init(){
        Collection<Recipe> combustionFuels = COMBUSTION_GENERATOR_FUELS.getRecipeList();
        Collection<Recipe> gasFuels = GAS_TURBINE_FUELS.getRecipeList();

        for (Recipe combustionRecipe : combustionFuels) {
            COMBUSTION_GENERATOR_FUELS.removeRecipe(combustionRecipe);
        }
        for (Recipe gasRecipe : gasFuels) {
            GAS_TURBINE_FUELS.removeRecipe(gasRecipe);
        }

        // COMBUSTION GENERATOR
        // 2 H2 + O2 = 2 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(4), Oxygen.getFluid(2))
                .fluidOutputs(Steam.getFluid(2))
                .duration(8)
                .EUt((int) V[LV])
                .buildAndRegister();

        // CO + H2 + O2 = CO2 + H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(WoodGas.getFluid(1), Oxygen.getFluid(2))
                .fluidOutputs(CarbonDioxide.getFluid(1), Steam.getFluid(1))
                .duration(9)
                .EUt((int) V[LV])
                .buildAndRegister();

        // CH4 + 2 O2 = CO2 + 2 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Methane.getFluid(1), Oxygen.getFluid(4))
                .fluidOutputs(CarbonDioxide.getFluid(1), Steam.getFluid(2))
                .duration(13)
                .EUt((int) V[LV])
                .buildAndRegister();

        // CH4 + 2 O2 = CO2 + 2 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(NaturalGas.getFluid(1), Oxygen.getFluid(4))
                .fluidOutputs(CarbonDioxide.getFluid(1), Steam.getFluid(2))
                .duration(13)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 H2S + 3 O2 = 2 SO2 + 2 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(SulfuricGas.getFluid(2), Oxygen.getFluid(6))
                .fluidOutputs(SulfurDioxide.getFluid(2), Steam.getFluid(2))
                .duration(17)
                .EUt((int) V[LV])
                .buildAndRegister();

        // CH3CH2OH + 3 O2 = 2 CO2 + 3 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Ethanol.getFluid(1), Oxygen.getFluid(6))
                .fluidOutputs(CarbonDioxide.getFluid(2), Steam.getFluid(3))
                .duration(20)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 CH3OH + 3 O2 = 2 CO2 + 4 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Methanol.getFluid(2), Oxygen.getFluid(6))
                .fluidOutputs(CarbonDioxide.getFluid(2), Steam.getFluid(4))
                .duration(21)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C2H4 + 3 O2 = 2 CO2 + 2 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Ethylene.getFluid(1), Oxygen.getFluid(6))
                .fluidOutputs(CarbonDioxide.getFluid(2), Steam.getFluid(2))
                .duration(22)
                .EUt((int) V[LV])
                .buildAndRegister();

        // H2 + CH4 + N2 + CO + 5 O2 = 2 CO2 + 2 NO2 + 3 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(CoalGas.getFluid(1), Oxygen.getFluid(10))
                .fluidOutputs(CarbonDioxide.getFluid(2), NitrogenDioxide.getFluid(2), Steam.getFluid(3))
                .duration(23)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 CH4 + 2 CO + 5 O2 = 4 CO2 + 4 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(RefineryGas.getFluid(2), Oxygen.getFluid(10))
                .fluidOutputs(CarbonDioxide.getFluid(4), Steam.getFluid(4))
                .duration(36)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C4H8 + 6 O2 = 4 CO2 + 4 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Butene.getFluid(1), Oxygen.getFluid(12))
                .fluidOutputs(CarbonDioxide.getFluid(4), Steam.getFluid(4))
                .duration(42)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C2H6 + 7 O2 = 4 CO2 + 6 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Ethane.getFluid(2), Oxygen.getFluid(14))
                .fluidOutputs(CarbonDioxide.getFluid(4), Steam.getFluid(6))
                .duration(47)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C6H5OH + 7 O2 = 6 CO2 + 3 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Phenol.getFluid(1), Oxygen.getFluid(14))
                .fluidOutputs(CarbonDioxide.getFluid(6), Steam.getFluid(3))
                .duration(48)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C5H12 + 8 O2 = 5 CO2 + 6 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(HeavyFuel.getFluid(1), Oxygen.getFluid(16))
                .fluidOutputs(CarbonDioxide.getFluid(5), Steam.getFluid(6))
                .duration(54)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C5H12 + 8 O2 = 5 CO2 + 6 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Propane.getFluid(1), Oxygen.getFluid(16))
                .fluidOutputs(CarbonDioxide.getFluid(5), Steam.getFluid(6))
                .duration(55)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C6H5CH3 + 9 O2 = 7 CO2 + 4 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1), Oxygen.getFluid(18))
                .fluidOutputs(CarbonDioxide.getFluid(7), Steam.getFluid(4))
                .duration(62)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C2H8N2 + 2 N2O4 = 2 CO2 + 3 N2 + 4 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(RocketFuel.getFluid(1))
                .fluidOutputs(CarbonDioxide.getFluid(2), Nitrogen.getFluid(6), Steam.getFluid(4))
                .duration(62)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C4H6 + 11 O2 = 8 CO2 + 6 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Butadiene.getFluid(2), Oxygen.getFluid(22))
                .fluidOutputs(CarbonDioxide.getFluid(8), Steam.getFluid(6))
                .duration(76)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C3H6 + 12 O2 = 6 CO2 + 12 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Propene.getFluid(2), Oxygen.getFluid(24))
                .fluidOutputs(CarbonDioxide.getFluid(6), Steam.getFluid(4))
                .duration(87)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C4H10 + 13 O2 = 8 CO2 + 10 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Butane.getFluid(2), Oxygen.getFluid(26))
                .fluidOutputs(CarbonDioxide.getFluid(8), Steam.getFluid(10))
                .duration(88)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C4H10 + 13 O2 = 8 CO2 + 10 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(LightFuel.getFluid(1), Oxygen.getFluid(13))
                .fluidOutputs(CarbonDioxide.getFluid(4), Steam.getFluid(5))
                .duration(88)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C4H10 + C6H6 + 14 O2 = 10 CO2 + 8 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Gasoline.getFluid(1), Oxygen.getFluid(28))
                .fluidOutputs(CarbonDioxide.getFluid(10), Steam.getFluid(8))
                .duration(96)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C6H6 + 15 O2 = 12 CO2 + 6 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Benzene.getFluid(2), Oxygen.getFluid(30))
                .fluidOutputs(CarbonDioxide.getFluid(12), Steam.getFluid(6))
                .duration(104)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C4H10 + C5H12 + C6H6 + 22 O2 = 15 CO2 + 14 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Naphtha.getFluid(1), Oxygen.getFluid(44))
                .fluidOutputs(CarbonDioxide.getFluid(15), Steam.getFluid(14))
                .duration(127)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C4H10 + C5H12 + C6H6 + H2S + 24 O2 = 15 CO2 + SO2 + 15 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(SulfuricNaphtha.getFluid(1), Oxygen.getFluid(48))
                .fluidOutputs(CarbonDioxide.getFluid(15), SulfurDioxide.getFluid(1), Steam.getFluid(15))
                .duration(136)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C3H8 + 2 C4H10 + 23 O2 = 14 CO2 + 18 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(LPG.getFluid(2), Oxygen.getFluid(46))
                .fluidOutputs(CarbonDioxide.getFluid(14), Steam.getFluid(18))
                .duration(155)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C8H18 + 25 O2 = 16 CO2 + 18 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Octane.getFluid(2), Oxygen.getFluid(50))
                .fluidOutputs(CarbonDioxide.getFluid(16), Steam.getFluid(18))
                .duration(168)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 4 C6H5NO2 + 29 O2 = 4 NO2 + 24 CO2 + 10 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Nitrobenzene.getFluid(4), Oxygen.getFluid(58))
                .fluidOutputs(CarbonDioxide.getFluid(24), NitrogenDioxide.getFluid(4), Steam.getFluid(10))
                .duration(198)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C5H12 + 2 C6H6 + 31 O2 = 22 CO2 + 18 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(BioDiesel.getFluid(2), Oxygen.getFluid(62))
                .fluidOutputs(CarbonDioxide.getFluid(22), Steam.getFluid(18))
                .duration(212)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C5H12 + 2 C6H6 + 31 O2 = 22 CO2 + 18 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Diesel.getFluid(2), Oxygen.getFluid(62))
                .fluidOutputs(CarbonDioxide.getFluid(22), Steam.getFluid(18))
                .duration(212)
                .EUt((int) V[LV])
                .buildAndRegister();

        // C5H12 + C6H6 + C16H34 + 40 O2 = 27 CO2 + 26 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(CetaneBoostedDiesel.getFluid(1), Oxygen.getFluid(80))
                .fluidOutputs(CarbonDioxide.getFluid(27), Steam.getFluid(26))
                .duration(270)
                .EUt((int) V[LV])
                .buildAndRegister();

        // 2 C4H10 + 2 C6H6 + 2 C8H18 + 53 O2 = 36 CO2 + 34 H2O
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(HighOctaneGasoline.getFluid(2), Oxygen.getFluid(106))
                .fluidOutputs(CarbonDioxide.getFluid(36), Steam.getFluid(34))
                .duration(359)
                .EUt((int) V[LV])
                .buildAndRegister();

        // GAS TURBINE
        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(1))
                .duration(4)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(WoodGas.getFluid(1))
                .duration(4)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(NaturalGas.getFluid(1))
                .duration(6)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Methane.getFluid(1))
                .duration(6)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(SulfuricGas.getFluid(1))
                .duration(8)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(RefineryGas.getFluid(1))
                .duration(9)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Ethylene.getFluid(1))
                .duration(11)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(CoalGas.getFluid(1))
                .duration(11)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Ethane.getFluid(1))
                .duration(12)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Butadiene.getFluid(1))
                .duration(19)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Butene.getFluid(1))
                .duration(21)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Butane.getFluid(1))
                .duration(22)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Propene.getFluid(1))
                .duration(22)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(Propane.getFluid(1))
                .duration(27)
                .EUt((int) V[LV])
                .buildAndRegister();

        GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(LPG.getFluid(1))
                .duration(39)
                .EUt((int) V[LV])
                .buildAndRegister();
    }
}
