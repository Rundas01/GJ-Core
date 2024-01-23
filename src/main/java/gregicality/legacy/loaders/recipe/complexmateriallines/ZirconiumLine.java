package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Baddeleyite;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Zircon;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumCarbonateSolution;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.SodiumHydroxideSolution;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumWaste;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class ZirconiumLine {
    private ZirconiumLine() {}

    public static void init() {
        //ELEMENT ACQUISITION
        //Zircon Cracking
        //ZrSiO4 + 2 NaOH = ZrO2 + Na2SiO3 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Zircon,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,Baddeleyite,3)
                .output(dust,SodiumMetaSilicate,6)
                .fluidOutputs(ZirconSlag.getFluid(600),Steam.getFluid(1000))
                .EUt(VA[HV])
                .duration(300)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ZirconSlag.getFluid(600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ZirconiumSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ZirconiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Zirconium)
                .chancedOutput(dustImpure,Zirconium,1,8750,800)
                .chancedOutput(dustImpure,Zirconium,1,7500,700)
                .chancedOutput(dustImpure,Zirconium,1,6250,600)
                .chancedOutput(dustImpure,Zirconium,1,5000,500)
                .chancedOutput(dustImpure,Zirconium,1,3750,400)
                .fluidOutputs(ZirconiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ZirconiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Zirconium,1,7500,750)
                .chancedOutput(dustImpure,Zirconium,1,5000,500)
                .chancedOutput(dustImpure,Zirconium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //(Redox) Reactions in the EBF
        //Baddeleyite Reduction
        //ZrO2 + 2 C + 2 Cl2 = ZrCl4 + 2 CO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Baddeleyite,3)
                .input(dust,Carbon,2)
                .fluidInputs(Chlorine.getFluid(4000))
                .output(dust,ZirconiumTetrachloride,5)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .blastFurnaceTemp(1173)
                .EUt(VA[EV])
                .duration(320)
                .buildAndRegister();
    }
}
