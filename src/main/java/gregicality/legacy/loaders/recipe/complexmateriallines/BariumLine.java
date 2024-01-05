package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FLOTATION_RECIPES;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ROASTING_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLRMiscMaterials.Wastewater;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Witherite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.LauricAcid;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumOxide;
import static gregicality.legacy.api.unification.material.materiallines.BariumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.stick;

public class BariumLine {
    private BariumLine(){}

    public static void init(){
        //HYDROXIDE ROUTE VIA ROASTING
        //Barite Roasting
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Barite,6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .output(dust,SodiumSulfate,7)
                .output(dust,BariumHydroxide,5)
                .fluidOutputs(ImpureBariumSlag.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Witherite Roasting
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Witherite,5)
                .output(dust,BariumOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(ImpureBariumSlag.getFluid(1000))
                .EUt(VA[HV])
                .duration(40)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureBariumSlag.getFluid(2000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .notConsumable(LauricAcid.getFluid(100))
                .fluidOutputs(BariumSlag.getFluid(1000))
                .fluidOutputs(BariumWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BariumSlag.getFluid(1000))
                .output(dust,Barite)
                .output(dust,Witherite)
                .fluidOutputs(Wastewater.getFluid(1000))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Hydroxide Treating with H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BariumHydroxide,5)
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .output(dust,BariumSulfide,2)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[MV])
                .duration(60)
                .buildAndRegister();

        //REDUCTION ROUTE VIA EBF AND CHEMREACTOR
        //Blasting Barite
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Barite)
                .input(dust,Carbon,2)
                .output(dust,BariumSulfide,2)
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .EUt(VA[HV])
                .duration(240)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //Barium Sulfide -> Barium Carbonate
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,BariumSulfide,2)
                .fluidInputs(DistilledWater.getFluid(1000),CarbonDioxide.getFluid(1000))
                .output(dust,BariumCarbonate,5)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[MV])
                .duration(60)
                .buildAndRegister();

        //Barium Carbonate Blasting
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumCarbonate,5)
                .output(dust,BariumOxide,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Barium Oxide Reduction
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumOxide,6)
                .input(dust,Aluminium,2)
                .circuitMeta(2)
                .output(dust,Barium,3)
                .output(dust,AluminiumOxide,5)
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumOxide,4)
                .input(dust,Silicon)
                .circuitMeta(3)
                .output(dust,Barium,2)
                .output(dust,SiliconDioxide,3)
                .EUt(VA[HV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //BARIUM REFINING
        //Barium Chlorate Production
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Barium)
                .fluidInputs(ChloricAcid.getFluid(1000))
                .output(dust,BariumChlorate,9)
                .fluidOutputs(ChlorineMonoxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[HV])
                .duration(150)
                .buildAndRegister();

        //Barium Chlorate Disproposition
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumChlorate,36)
                .output(dust,BariumPerchlorate,33)
                .output(dust,BariumChloride,3)
                .EUt(VA[HV])
                .duration(400)
                .blastFurnaceTemp(720)
                .buildAndRegister();

        //Barium Perchlorate Decomposition
        BLAST_RECIPES.recipeBuilder()
                .input(dust,BariumPerchlorate,11)
                .output(dust,BariumChloride,3)
                .fluidOutputs(Oxygen.getFluid(8000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(800)
                .buildAndRegister();

        //Barium Chloride Solution Mixing
        MIXER_RECIPES.recipeBuilder()
                .input(dust,BariumChloride)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BariumChlorideSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(60)
                .buildAndRegister();

        //Electrolyzing Barium Chloride into HP Barium
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Barium)
                .notConsumable(stick,Graphite)
                .fluidInputs(BariumChlorideSolution.getFluid(2000))
                .output(dust,HighPurityBarium,2)
                .fluidOutputs(HydrogenChloride.getFluid(4000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();
    }
}
