package gregsjourney.loaders.recipe.singleblock;

import static gregsjourney.api.unification.material.GJSpaceMaterials.*;
import static gregsjourney.api.utils.GJUtil.Dimensions.*;
import static gregtech.api.GTValues.MV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.GAS_COLLECTOR_RECIPES;

public class GasGasGas {

    private GasGasGas() {}

    public static void init() {
        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidOutputs(MarsAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(MARS.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .fluidOutputs(MercuryAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(MERCURY.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .fluidOutputs(VenusAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(VENUS.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .fluidOutputs(TitanAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(TITAN.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .fluidOutputs(MirandaAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(MIRANDA.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .fluidOutputs(OberonAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(OBERON.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .fluidOutputs(ProteusAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(PROTEUS.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(11)
                .fluidOutputs(TritonAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(TRITON.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .fluidOutputs(PlutoAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(PLUTO.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .fluidOutputs(HaumeaAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(HAUMEA.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .fluidOutputs(MakemakeAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(MAKEMAKE.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .fluidOutputs(ProximaAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(PROXIMA.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(16)
                .fluidOutputs(BarnadaAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(BARNADA.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(17)
                .fluidOutputs(BarnadaC1Atmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(BARNADAC1.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(18)
                .fluidOutputs(BarnadaC2Atmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(BARNADAC2.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(19)
                .fluidOutputs(CetiAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                // .dimension(CETI.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(20)
                .fluidOutputs(ChalosAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(CHALOS.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(21)
                .fluidOutputs(DionaAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(DIONA.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(22)
                .fluidOutputs(FronosAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(FRONOS.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(23)
                .fluidOutputs(NibiruAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(NIBIRU.getDimension())
                .buildAndRegister();

        GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .fluidOutputs(KoentusAtmosphere.getFluid(10000))
                .EUt(VA[MV])
                .duration(200)
                .dimension(KOENTUS.getDimension())
                .buildAndRegister();
    }
}
