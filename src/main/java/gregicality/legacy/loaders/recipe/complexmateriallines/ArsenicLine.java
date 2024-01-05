package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.IronIIIOxide;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.CobaltLineMaterials.SperryliteWaste;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.AcidicCopperSolution;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.CopperSulfate;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ArsenicLine {
    private ArsenicLine(){}

    public static void init(){
        removals();
        additions();
    }

    private static void removals() {}

    private static void additions() {
        //SPERRYLITE ROASTING
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Sperrylite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust,RoastedSperrylite)
                .output(dust,ArsenicTrioxide,5)
                .fluidOutputs(ImpureArsenicSlag.getFluid(2000))
                .duration(120)
                .EUt(VA[HV])
                .buildAndRegister();

        //REALGAR ROASTING
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Realgar)
                .fluidInputs(Oxygen.getFluid(14000))
                .output(dust,ArsenicTrioxide,10)
                .fluidOutputs(SulfurDioxide.getFluid(4000))
                .fluidOutputs(ImpureArsenicSlag.getFluid(2000))
                .duration(240)
                .EUt(VA[HV])
                .buildAndRegister();

        //ARSENOPYRITE ROASTING
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,Arsenopyrite,2)
                .fluidInputs(Oxygen.getFluid(10000))
                .output(dust,IronIIIOxide,5)
                .output(dust,ArsenicTrioxide,5)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .fluidOutputs(ImpureArsenicSlag.getFluid(2000))
                .duration(240)
                .EUt(VA[HV])
                .buildAndRegister();

        //SLAG TREATING
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureArsenicSlag.getFluid(3000))
                .fluidOutputs(ArsenicSlag.getFluid(2000),ArsenicWaste.getFluid(500))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        //ARSENOPYRITE AND COBALTITE ACID LEACHING
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Cobaltite,3)
                .fluidInputs(NitricAcid.getFluid(13000))
                .fluidInputs(DistilledWater.getFluid(3000))
                .output(dust,Sulfur,3)
                .fluidOutputs(CobaltiteLeachSolution.getFluid(1000))
                .fluidOutputs(NitricOxide.getFluid(7000))
                .duration(240)
                .EUt(VA[EV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Arsenopyrite,3)
                .fluidInputs(NitricAcid.getFluid(13000))
                .fluidInputs(DistilledWater.getFluid(3000))
                .output(dust,Sulfur,3)
                .fluidOutputs(ArsenopyriteLeachSolution.getFluid(1000))
                .fluidOutputs(NitricOxide.getFluid(7000))
                .duration(240)
                .EUt(VA[EV])
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Cobalt)
                .notConsumable(stick,Graphite)
                .fluidInputs(CobaltiteLeachSolution.getFluid(1000))
                .output(dust,Cobalt,3)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(AcidicArsenateSolution.getFluid(1000))
                .duration(240)
                .EUt(VA[EV])
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Iron)
                .notConsumable(stick,Graphite)
                .fluidInputs(ArsenopyriteLeachSolution.getFluid(1000))
                .output(dust,Arsenic,3)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(AcidicArsenateSolution.getFluid(1000))
                .duration(240)
                .EUt(VA[EV])
                .buildAndRegister();

        //ARSENATE PROCESSING
        ROASTING_RECIPES.recipeBuilder()
                .fluidInputs(AcidicArsenateSolution.getFluid(2000))
                .output(dust,ArsenicVOxide,21)
                .fluidOutputs(Steam.getFluid(15000))
                .fluidOutputs(NitrogenDioxide.getFluid(12000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .duration(120)
                .EUt(360)
                .buildAndRegister();

        //ARSENIC OXIDES REDUCTION
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Carbon,3)
                .input(dust,ArsenicTrioxide,5)
                .output(dust,Arsenic,2)
                .fluidOutputs(CarbonMonoxide.getFluid(3000))
                .blastFurnaceTemp(3000)
                .duration(30)
                .EUt(360)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,Carbon,5)
                .input(dust,ArsenicVOxide,7)
                .output(dust,Arsenic,2)
                .fluidOutputs(CarbonMonoxide.getFluid(5000))
                .duration(30)
                .EUt(VA[HV])
                .buildAndRegister();

        //ENARGITE ALKALINE SULFIDE LEACHING
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Enargite,14)
                .input(dust,SodiumSulfide,9)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .fluidInputs(DistilledWater.getFluid(4000))
                .output(dust,EnargiteResidue,6)
                .fluidOutputs(EnargiteSulfideLeachSolution.getFluid(1000))
                .duration(240)
                .EUt(VA[LV])
                .buildAndRegister();

        //SEPARATION OF ARSENIC FROM ANTIMONY
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ProustiteAlkalineLeachSolution.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,ArsenicVSulfide,5)
                .fluidOutputs(HydrogenSulfide.getFluid(3000))
                .fluidOutputs(SaltWater.getFluid(6000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(EnargiteSulfideLeachSolution.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,ArsenicVSulfide,7)
                .fluidOutputs(HydrogenSulfide.getFluid(5000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //REDUCTION OF ARSENIC SULFIDES
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,ArsenicVSulfide,5)
                .fluidInputs(Hydrogen.getFluid(10000))
                .output(dust,Arsenic,2)
                .fluidOutputs(HydrogenSulfide.getFluid(5000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVSulfide,7)
                .fluidInputs(Oxygen.getFluid(15000))
                .output(dust,ArsenicVOxide,7)
                .fluidOutputs(SulfurDioxide.getFluid(3000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //SEMICONDUCTOR GRADE ARSENIC
        //ARSENIC TRICHLORIDE DISTILLATION
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .fluidInputs(HydrogenChloride.getFluid(7000))
                .fluidOutputs(CrudeArsenicTrichloride.getFluid(2000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Arsenic)
                .fluidInputs(Chlorine.getFluid(3000))
                .fluidOutputs(CrudeArsenicTrichloride.getFluid(1000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrudeArsenicTrichloride.getFluid(2000))
                .fluidOutputs(ArsenicTrichloride.getFluid(1000))
                .fluidOutputs(SulfurDichloride.getFluid(250))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurDichloride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust,Sulfur)
                .fluidOutputs(HydrogenChloride.getFluid(2000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ArsenicTrichloride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(3000))
                .output(dust,HighPurityArsenic)
                .fluidOutputs(HydrogenChloride.getFluid(3000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,Sodium,3)
                .input(dust,HighPurityArsenic)
                .output(dust,SodiumArsenide,4)
                .duration(120)
                .EUt(VA[MV])
                .buildAndRegister();

        //AR(C)SINE
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumArsenide,4)
                .fluidInputs(DistilledWater.getFluid(3000))
                .output(dust,SodiumHydroxide,9)
                .fluidOutputs(Arsine.getFluid(1000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //ROASTED ORE PROCESSING
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,RoastedSperrylite)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust,Platinum)
                .fluidOutputs(SperryliteWaste.getFluid(250))
                .duration(120)
                .EUt(VA[MV])
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(SperryliteWaste.getFluid(1000))
                .notConsumable(stick,Nickel)
                .notConsumable(stick,Graphite)
                .output(dust,Cobalt)
                .output(dust,Nickel)
                .fluidOutputs(Chlorine.getFluid(4000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(120)
                .EUt(VA[MV])
                .buildAndRegister();

        //LEACH RESIDUE PROCESSING
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,EnargiteResidue,3)
                .fluidInputs(NitricAcid.getFluid(8000))
                .output(dustSmall,Gold)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(AcidicCopperSolution.getFluid(1000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcidicCopperSolution.getFluid(2000))
                .output(dust,CopperSulfate,24)
                .fluidOutputs(Steam.getFluid(10000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();
    }
}
