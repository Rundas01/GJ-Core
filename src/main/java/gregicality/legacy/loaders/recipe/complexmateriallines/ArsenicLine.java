package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIISulfate;
import static gregicality.legacy.api.unification.material.materiallines.ArsenicLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.AcidicCopperSolution;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.CopperSulfate;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
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
        //ELEMENT ACQUISITION
        //Realgar Cracking
        //As4S4 + 9 O2 = 2 As2O5 + 4 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Realgar,8)
                .fluidInputs(Oxygen.getFluid(18000))
                .output(dust,ArsenicVOxide,14)
                .fluidOutputs(RealgarSlag.getFluid(800),SulfurDioxide.getFluid(4000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Arsenopyrite Cracking
        //2 FeAsS + 6 O2 = As2O5 + Fe2O3 + 2 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Arsenopyrite,6)
                .fluidInputs(Oxygen.getFluid(12000))
                .output(dust,ArsenicVOxide,7)
                .output(dust,IronIIIOxide,5)
                .fluidOutputs(ArsenopyriteSlag.getFluid(600),SulfurDioxide.getFluid(2000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Hutchinsonite Cracking
        //2 TlPbAs5S9 + 51 O = 5 As2O5 + 2 Tl2O3 + 2 PbO + 9 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Hutchinsonite,32)
                .fluidInputs(Oxygen.getFluid(51000))
                .output(dust,ArsenicVOxide,7)
                .output(dust,ThalliumIIIOxide,10)
                .output(dust,LeadOxide,4)
                .fluidOutputs(HutchinsoniteSlag.getFluid(1600),SulfurDioxide.getFluid(9000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Hutchinsonite Cracking
        //2 TlPbAs5S9 + 51 O = 5 As2O5 + 2 Tl2O3 + 2 PbO + 9 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Hutchinsonite,32)
                .fluidInputs(Oxygen.getFluid(51000))
                .output(dust,ArsenicVOxide,7)
                .output(dust,ThalliumIIIOxide,10)
                .output(dust,LeadOxide,4)
                .fluidOutputs(HutchinsoniteSlag.getFluid(1600),SulfurDioxide.getFluid(9000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Skutterudite Cracking
        //2 CoAs3 + 17 O = 2 CoO + 3 As2O5
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Skutterudite,8)
                .fluidInputs(Oxygen.getFluid(17000))
                .output(dust,ArsenicVOxide,21)
                .output(dust,CobaltOxide,4)
                .fluidOutputs(SkutteruditeSlag.getFluid(400))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Orpiment Cracking
        //As2S3 + 11 O = As2O5 + 3 SO2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Orpiment,5)
                .fluidInputs(Oxygen.getFluid(11000))
                .output(dust,ArsenicVOxide,7)
                .fluidOutputs(SkutteruditeSlag.getFluid(500),SulfurDioxide.getFluid(3000))
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(RealgarSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ArsenicSlag.getFluid(400))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ArsenopyriteSlag.getFluid(300))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ArsenicSlag.getFluid(100),IronSlag.getFluid(100))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(HutchinsoniteSlag.getFluid(1600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ArsenicSlag.getFluid(500),ThalliumSlag.getFluid(100),LeadSlag.getFluid(100))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ArsenicSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Arsenic)
                .chancedOutput(dustImpure,Arsenic,1,8750,800)
                .chancedOutput(dustImpure,Arsenic,1,7500,700)
                .chancedOutput(dustImpure,Arsenic,1,6250,600)
                .chancedOutput(dustImpure,Arsenic,1,5000,500)
                .chancedOutput(dustImpure,Arsenic,1,3750,400)
                .fluidOutputs(ArsenicWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(ArsenicWaste.getFluid(1000))
                .chancedOutput(dustImpure,Arsenic,1,7500,750)
                .chancedOutput(dustImpure,Arsenic,1,5000,500)
                .chancedOutput(dustImpure,Arsenic,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //Arsenic Oxides Reduction
        //As2O3 + 6 Li = 2 As + 3 Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,Lithium,6)
                .output(dust,Arsenic,2)
                .output(dust,LithiumOxide,9)
                .EUt(VA[MV])
                .duration(200)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //As2O3 + 3 Mg = 2 As + 3 MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,Magnesium,3)
                .output(dust,Arsenic,2)
                .output(dust,Magnesia)
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //As2O3 + 2 Al = 2 As + Al2O3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,Aluminium,2)
                .output(dust,Arsenic,2)
                .output(dust,AluminiumIIIOxide,5)
                .EUt(VA[HV])
                .duration(600)
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //2 As2O3 + 3 Si = 4 As + 3 SiO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,10)
                .input(dust,Carbon,3)
                .output(dust,Arsenic,4)
                .output(dust,SiliconDioxide,9)
                .EUt(VA[EV])
                .duration(800)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        //2 As2O3 + 3 C = 4 As + 3 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,10)
                .input(dust,Carbon,3)
                .output(dust,Arsenic,4)
                .output(dust,SiliconDioxide,9)
                .EUt(VA[IV])
                .duration(1000)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //As2O5 + 10 Li = 2 As + 5 Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,7)
                .input(dust,Lithium,10)
                .output(dust,Arsenic,2)
                .output(dust,LithiumOxide,15)
                .duration(200)
                .EUt(VA[MV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //As2O5 + 5 Mg = 2 As + 5 MgO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,7)
                .input(dust,Magnesium,5)
                .output(dust,Arsenic,2)
                .output(dust,Magnesia,10)
                .duration(400)
                .EUt(VA[MV])
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        //3 As2O5 + 10 Al = 6 As + 5 Al2O3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,21)
                .input(dust,Aluminium,10)
                .output(dust,Arsenic,6)
                .output(dust,AluminiumIIIOxide,25)
                .duration(600)
                .EUt(VA[HV])
                .blastFurnaceTemp(3000)
                .buildAndRegister();

        //2 As2O5 + 5 Si = 4 As + 5 SiO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,14)
                .input(dust,Silicon,5)
                .output(dust,Arsenic,4)
                .output(dust,SiliconDioxide,15)
                .duration(800)
                .EUt(VA[EV])
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        //2 As2O5 + 5 C = 4 As + 5 CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,14)
                .input(dust,Carbon,5)
                .output(dust,Arsenic,4)
                .fluidOutputs(CarbonDioxide.getFluid(5000))
                .duration(1000)
                .EUt(VA[IV])
                .blastFurnaceTemp(4000)
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
    }
}
