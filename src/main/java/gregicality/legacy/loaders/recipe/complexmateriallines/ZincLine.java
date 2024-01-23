package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Hydrozincite;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Smithsonite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumCarbonateSolution;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ZincLine {
    private ZincLine() {}

    public static void init() {
        //ELEMENT ACQUISITION
        //Sphalerite Cracking
        //ZnS + 3 O = ZnO + SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Sphalerite,2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust,ZincOxide,2)
                .fluidOutputs(SphaleriteSlag.getFluid(200),SulfurDioxide.getFluid(1000))
                .EUt(VA[LV])
                .duration(300)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Smithsonite Cracking
        //ZnCO3 + H2SO4 = ZnSO4 + CO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Smithsonite,5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,ZincSulfate,6)
                .fluidOutputs(SmithsoniteSlag.getFluid(500),CarbonDioxide.getFluid(1000),Steam.getFluid(1000))
                .EUt(VA[LV])
                .duration(300)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Hydrozincite Cracking
        //Zn5(CO3)2(OH)6 + 5 H2SO4 = 5 ZnSO4 + 2 CO2 + 8 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Hydrozincite,25)
                .fluidInputs(SulfuricAcid.getFluid(5000))
                .output(dust,ZincSulfate,30)
                .fluidOutputs(HydrozinciteSlag.getFluid(2500),CarbonDioxide.getFluid(2000),Steam.getFluid(8000))
                .EUt(VA[LV])
                .duration(300)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SphaleriteSlag.getFluid(200))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ZincSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SmithsoniteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ZincSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ZincSlag.getFluid(1000))
                .output(dustImpure,Zinc)
                .chancedOutput(dustImpure,Zinc,1,8750,700)
                .chancedOutput(dustImpure,Zinc,1,7500,600)
                .chancedOutput(dustImpure,Zinc,1,6250,500)
                .chancedOutput(dustImpure,Zinc,1,5000,400)
                .chancedOutput(dustImpure,Zinc,1,3750,300)
                .chancedOutput(dustImpure,Zinc,1,2500,200)
                .chancedOutput(dustImpure,Zinc,1,1250,100)
                .fluidOutputs(ZincWaste.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ZincWaste.getFluid(1000))
                .chancedOutput(dustImpure,Zinc,1,7500,750)
                .chancedOutput(dustImpure,Zinc,1,5000,500)
                .chancedOutput(dustImpure,Zinc,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Zinc Oxide Reduction
        //2 ZnO + C -> Zn + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Zincite,2)
                .input(dust,Carbon)
                .output(dust,Zinc)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .blastFurnaceTemp(1473)
                .EUt(460)
                .duration(160)
                .buildAndRegister();
    }
}
