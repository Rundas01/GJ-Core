package gregsjourney.loaders.recipe.multiblock.orecracking;

import gregtech.api.unification.OreDictUnifier;

import java.util.Collections;
import java.util.Objects;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.*;
import static gregsjourney.api.unification.material.GJOreMaterials.Cryolite;
import static gregsjourney.api.unification.material.GJOreMaterials.Petalite;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.CalciumLineMaterials.CalciumDiydroxide;
import static gregsjourney.api.unification.material.materiallines.CalciumLineMaterials.CalciumOxide;
import static gregsjourney.api.unification.material.materiallines.CopperLineMaterials.HighPurityCopper;
import static gregsjourney.api.unification.material.materiallines.GalliumLineMaterials.GalliumTrichloride;
import static gregsjourney.api.unification.material.materiallines.IronLineMaterials.SodiumFerrateII;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.PotassiumLineMaterials.PotassiumHydroxide;
import static gregsjourney.api.unification.material.materiallines.SodiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class AluminiumLine {
    public static void init(){
        //removals();
        additions();
    }

    private static void removals() {
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(dust, Ruby)),null)));
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(dust, Sapphire)),null)));
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(dust, GreenSapphire)),null)));
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(gem, Ruby)),null)));
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(gem, Sapphire)),null)));
        BLAST_RECIPES.removeRecipe(Objects.requireNonNull(BLAST_RECIPES.findRecipe(100, Collections.singletonList(OreDictUnifier.get(gem, GreenSapphire)),null)));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(120,Collections.singletonList(OreDictUnifier.get(crushed,Ruby,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(120,Collections.singletonList(OreDictUnifier.get(crushed,Sapphire,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
        MIXER_RECIPES.removeRecipe(Objects.requireNonNull(MIXER_RECIPES.findRecipe(120,Collections.singletonList(OreDictUnifier.get(crushed,GreenSapphire,2)),Collections.singletonList(AquaRegia.getFluid(3000)))));
    }

    private static void additions() {
        //ELEMENT ACQUISITION
        //Bauxite Cracking
        //Al2O3 + 2 NaOH = 2 NaAlO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bauxite,5)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .fluidOutputs(BauxiteSlag.getFluid(500),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Cryolite Cracking
        //Na3AlF6 + 4 NaOH = 6 NaF + NaAlO2 + 2 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Cryolite,10)
                .fluidInputs(SodiumHydroxideSolution.getFluid(4000))
                .output(dust,SodiumAluminate,4)
                .output(dust,SodiumFluoride,12)
                .fluidOutputs(CryoliteSlag.getFluid(1000),Steam.getFluid(2000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Petalite Cracking
        //2 LiAlSi4O10 + 2 NaOH = 2 NaAlO2 + Li2O + 8 SiO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Petalite,32)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,LithiumOxide,3)
                .output(dust,SiliconDioxide,24)
                .fluidOutputs(PetaliteSlag.getFluid(3200),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Grossular Cracking
        //Ca3Al2Si3O12 + 2 NaOH = 2 NaAlO2 + 3 CaO + 3 SiO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Grossular,20)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,CalciumOxide,6)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(GrossularSlag.getFluid(2000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Almandine Cracking
        //Al2Fe3Si3O12 + 8 NaOH = 2 NaAlO2 + 3 Na2FeO2 + 3 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Almandine,20)
                .fluidInputs(SodiumHydroxideSolution.getFluid(8000))
                .output(dust,SodiumAluminate,8)
                .output(dust,SodiumFerrateII,15)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(AlmandineSlag.getFluid(2000),Steam.getFluid(4000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Blue Topaz Cracking (Komponenten ändern)
        //Al2SiF2H2O5 + 2 NaOH = 2 NaAlO2 + SiO2 + 2 HF + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,BlueTopaz,12)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(BlueTopazSlag.getFluid(1200),HydrogenFluoride.getFluid(2000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Pyrope Cracking
        //Al2Mg3Si3O12 + 2 NaOH = 2 NaAlO2 + 3 MgO + 3 SiO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pyrope,20)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,Magnesia,6)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(PyropeSlag.getFluid(2000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Sodalite Cracking (Komponenten ändern)
        //Na8Cl2Al6Si6O24 + 2 NaOH = 6 NaAlO2 + 6 SiO2 + 2 HCl + 2 Na2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Sodalite,46)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,24)
                .output(dust,SodiumOxide,6)
                .output(dust,SiliconDioxide,18)
                .fluidOutputs(SodaliteSlag.getFluid(4600),HydrogenChloride.getFluid(2000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Lazurite Cracking (Komponenten ändern)
        //Na3CaSSi3Al3O12 + 2 NaOH = 3 NaAlO2 + Ca(OH)2 + SO2 + 3 SiO + Na2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Lazurite,23)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,12)
                .output(dust,CalciumDiydroxide,5)
                .output(dust,SodiumOxide,3)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(LazuriteSlag.getFluid(2300),SulfurDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Topaz Cracking (Komponenten ändern)
        //Al2SiO5HF + 2 NaOH = 2 NaAlO2 + SiO2 + HF + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Topaz,10)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(TopazSlag.getFluid(1000),HydrogenFluoride.getFluid(1000),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Kyanite Cracking
        //Al2SiO5 + 2 NaOH = 2 NaAlO2 + SiO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Kyanite,8)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .output(dust,SiliconDioxide,3)
                .fluidOutputs(KyaniteSlag.getFluid(800),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Mica Cracking
        //KAl3Si3F2O10 + 3 NaOH = 3 NaAlO2 + KOH + 3 SiO2 + 2 HF
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Mica,19)
                .fluidInputs(SodiumHydroxideSolution.getFluid(3000))
                .output(dust,SodiumAluminate,12)
                .output(dust,PotassiumHydroxide,2)
                .output(dust,SiliconDioxide,9)
                .fluidOutputs(MicaSlag.getFluid(1900),HydrogenFluoride.getFluid(2000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Alunite Cracking (Komponenten ändern)
        //KAl3(OH)6(SO4)2 + 3 NaOH = 3 NaAlO2 + KOH + 2 SO3 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Alunite,26)
                .fluidInputs(SodiumHydroxideSolution.getFluid(3000))
                .output(dust,SodiumAluminate,12)
                .output(dust,PotassiumHydroxide,2)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(AluniteSlag.getFluid(2600),SulfurTrioxide.getFluid(2000),Steam.getFluid(4000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Sapphire Cracking
        //Al2O3 + 2 NaOH = 2 NaAlO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Sapphire,5)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .fluidOutputs(SapphireSlag.getFluid(500),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Green Sapphire Cracking
        //Al2O3 + 2 NaOH = 2 NaAlO2 + H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,GreenSapphire,5)
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .output(dust,SodiumAluminate,8)
                .fluidOutputs(GreenSapphireSlag.getFluid(500),Steam.getFluid(1000))
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BauxiteSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AluminiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SapphireSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AluminiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(GreenSapphireSlag.getFluid(500))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AluminiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(KyaniteSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AluminiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TopazSlag.getFluid(1000))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(AluminiumSlag.getFluid(200))
                .EUt(VA[LV])
                .duration(200)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AluminiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Aluminium)
                .chancedOutput(dustImpure,Aluminium,1,8750,800)
                .chancedOutput(dustImpure,Aluminium,1,7500,700)
                .chancedOutput(dustImpure,Aluminium,1,6250,600)
                .chancedOutput(dustImpure,Aluminium,1,5000,500)
                .chancedOutput(dustImpure,Aluminium,1,3750,400)
                .fluidOutputs(AluminiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(AluminiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Aluminium,1,7500,750)
                .chancedOutput(dustImpure,Aluminium,1,5000,500)
                .chancedOutput(dustImpure,Aluminium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //Oxide Reduction
        //Al2O3 + 6 Li = 2 Al + 3 Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AluminiumIIIOxide,5)
                .input(dust,Lithium,6)
                .output(dust,Aluminium,2)
                .output(dust,LithiumOxide,9)
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Al2O3 + 3 Mg = 2 Al + 3 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,AluminiumIIIOxide,5)
                .input(dust,Magnesium,3)
                .output(dust,Aluminium,2)
                .output(dust,Magnesia)
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //Aluminate to Hydroxide Conversion
        //2 NaAlO2 + 3 H2O + CO2 = 2 Al(OH)3 + Na2CO3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumAluminateSolution.getFluid(2000),DistilledWater.getFluid(1000),CarbonDioxide.getFluid(1000))
                .output(dust,AluminiumHydroxide,14)
                .output(dust,SodaAsh,6)
                .EUt(20)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumAluminate,8)
                .fluidInputs(DistilledWater.getFluid(3000),CarbonDioxide.getFluid(1000))
                .output(dust,AluminiumHydroxide,14)
                .output(dust,SodaAsh,6)
                .EUt(20)
                .duration(300)
                .buildAndRegister();

        //Hydroxide Multiplication
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust,AluminiumHydroxide,1)
                .fluidInputs(SodiumAluminateSolution.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .output(dust,AluminiumHydroxide,7)
                .duration(300)
                .EUt(16)
                .buildAndRegister();

        //Hydroxide Blasting
        //2 Al(OH)3 = Al2O3 + 3 H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AluminiumHydroxide,14)
                .fluidOutputs(Steam.getFluid(3000))
                .output(dust,AluminiumIIIOxide,5)
                .duration(100)
                .blastFurnaceTemp(1400)
                .EUt(40)
                .buildAndRegister();

        //Fluoride Reduction Process in liquid Cryolite
        //Al2O3 + 2 AlF3 + 3 C + 3 H2O = 3 CO2 + 6 HF + 4 Al
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(Cryolite.getFluid(2592))
                .input(dust,AluminiumIIIOxide,5)
                .input(dust,AluminiumTrifluoride,8)
                .input(dust,Carbon,3)
                .fluidInputs(DistilledWater.getFluid(3000))
                .fluidOutputs(HydrogenFluoride.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .output(dust,Aluminium,4)
                .duration(100)
                .EUt(40)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(Cryolite.getFluid(2592))
                .input(dust,Sapphire,5)
                .input(dust,AluminiumTrifluoride,8)
                .input(dust,Carbon,3)
                .fluidInputs(DistilledWater.getFluid(3000))
                .fluidOutputs(HydrogenFluoride.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .output(dust,Aluminium,4)
                .duration(100)
                .EUt(40)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(Cryolite.getFluid(2592))
                .input(dust,GreenSapphire,5)
                .input(dust,AluminiumTrifluoride,8)
                .input(dust,Carbon,3)
                .fluidInputs(DistilledWater.getFluid(3000))
                .fluidOutputs(HydrogenFluoride.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .output(dust,Aluminium,4)
                .duration(100)
                .EUt(40)
                .buildAndRegister();

        //Trifluoride Production from HF
        //Al2O3 + 6 HF = 2 AlF3 + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .input(dust,AluminiumIIIOxide,5)
                .fluidOutputs(Water.getFluid(9000))
                .output(dust,AluminiumTrifluoride,8)
                .duration(300)
                .EUt(16)
                .buildAndRegister();

        //NaF Solution from NaOH and HF
        //HF + NaOH = NaF + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenFluoride.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(SodiumFluorideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(5)
                .EUt(16)
                .buildAndRegister();

        //Cryolite Production if ore cannot be found
        //AlF3 + 3 NaF = Na3AlF6
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,AluminiumTrifluoride,4)
                .input(dust,SodiumFluoride,6)
                .output(dust,Cryolite,10)
                .duration(180)
                .EUt(16)
                .buildAndRegister();

        //REFINEMENT PROCESS
        //Aluminium-Copper Alloy + Impurity
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Aluminium)
                .input(dust,HighPurityCopper)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(AluminiumBronze.getFluid(288),ChloridicAluminiumTailings.getFluid(2000))
                .EUt(VA[HV])
                .duration(420)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ChloridicAluminiumTailings.getFluid(2000))
                .output(dustTiny,GalliumTrichloride)
                .fluidOutputs(TitaniumTetrachloride.getFluid(100))
                .EUt(VA[MV])
                .duration(300)
                .buildAndRegister();

        //Acid Treatment
        //AlCu + 4 HCl = AlCuCl4 + 2 H2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,AluminiumBronze,2)
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .output(dust,AluminiumCopperChloride,6)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .EUt(VA[MV])
                .duration(120)
                .buildAndRegister();

        //Solution Mixing
        /*MIXER_RECIPES.recipeBuilder()
                .input(dust,AluminiumCopperChloride)
                .fluidInputs(DistilledWater.getFluid(2000))
                .fluidOutputs(AluminiumCopperChlorideSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(60)
                .buildAndRegister();

        //Electrolysis of AlCuCl4
        //AlCuCl4 + 2 H2O = Al + Cu + 4 HCl + O2
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Aluminium)
                .notConsumable(stick,Graphite)
                .fluidInputs(AluminiumCopperChlorideSolution.getFluid(1000))
                .output(dust,HighPurityAluminium)
                .output(dust,Copper)
                .fluidOutputs(HydrogenChloride.getFluid(4000),Oxygen.getFluid(2000))
                .EUt(VA[HV])
                .duration(660)
                .buildAndRegister();*/
    }
}
