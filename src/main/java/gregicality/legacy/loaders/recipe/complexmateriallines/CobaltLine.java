package gregicality.legacy.loaders.recipe.complexmateriallines;

import gregtech.api.unification.OreDictUnifier;

import java.util.Collections;
import java.util.Objects;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.CobaltLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CobaltLine {
    private CobaltLine() {}

    public static void init() {
        removals();
        additions();
    }

    private static void removals() {
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(VA[MV], Collections.singletonList(OreDictUnifier.get(dust, Cobaltite)), Collections.singletonList(Oxygen.getFluid(3000)))));
    }

    private static void additions() {
        //Cobaltite Cracking
        //3 CoAsS + 18 HCl + 10 HNO3 = 3 CoCl3 + 3 AsCl3 + 3 SO2 + 10 NO + 14 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cobaltite,9)
                .fluidInputs(AquaRegia.getFluid(9000),NitricAcid.getFluid(1000))
                .output(dust,CobaltTrichloride,12)
                .output(dust,ArsenicTrichloride,12)
                .fluidOutputs(CobaltiteSlag.getFluid(300),SulfurDioxide.getFluid(2000),NitrousOxide.getFluid(10000),Steam.getFluid(14000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //CoAsS + 6 HClO4 = CoCl3 + AsCl3 + SO2 + 19 O + 3 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cobaltite,3)
                .fluidInputs(PerchloricAcid.getFluid(6000))
                .output(dust,CobaltTrichloride,4)
                .output(dust,ArsenicTrichloride,4)
                .fluidOutputs(CobaltiteSlag.getFluid(300),SulfurDioxide.getFluid(1000),Oxygen.getFluid(19000),Steam.getFluid(3000))
                .EUt(VA[ZPM])
                .duration(200)
                .blastFurnaceTemp(6000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(CobaltiteSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CobaltSlag.getFluid(100))
                .fluidOutputs(ArsenicSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CobaltSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Cobalt)
                .chancedOutput(dustImpure,Cobalt,1,8750,800)
                .chancedOutput(dustImpure,Cobalt,1,7500,700)
                .chancedOutput(dustImpure,Cobalt,1,6250,600)
                .chancedOutput(dustImpure,Cobalt,1,5000,500)
                .chancedOutput(dustImpure,Cobalt,1,3750,400)
                .fluidOutputs(CobaltWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CobaltWaste.getFluid(1000))
                .chancedOutput(dustImpure,Chrome,1,7500,750)
                .chancedOutput(dustImpure,Chrome,1,5000,500)
                .chancedOutput(dustImpure,Chrome,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //CoO + C -> Co + CO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CobaltOxide,2)
                .input(dust,Carbon)
                .output(dust,Cobalt)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .blastFurnaceTemp(1473)
                .EUt(460)
                .duration(160)
                .buildAndRegister();
    }
}
