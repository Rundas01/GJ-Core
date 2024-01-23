package gregicality.legacy.loaders.recipe;

import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public final class GCYLRMiscRecipes {

    private GCYLRMiscRecipes() {}

    public static void init() {
        PotassiumPermanganateRecipes();
        ChloricAcidRecipes();
        MiscChemicalRecipes();
    }

    private static void PotassiumPermanganateRecipes() {
        //Managanese Dioxide Oxidation
        //3 MnO2 + KClO3 + 6 KOH -> 3 K2MnO4 + KCl + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,ManaganeseDioxide,9)
                .input(dust,PotassiumChlorate,5)
                .input(dust,PotassiumHydroxide,12)
                .output(dust,PotassiumManganateVI,21)
                .output(dust,RockSalt,2)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[HV])
                .duration(1000)
                .buildAndRegister();

        //Manganate Disproportion
        //3 K2MnO4 = 2 KMnO4 + K4MnO4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumManganateVI,21)
                .output(dust,PotassiumPermanganate,12)
                .output(dust,PotassiumManganateIV,9)
                .EUt(VA[HV])
                .duration(1000)
                .buildAndRegister();

        //Manganate IV to Dioxide Conversion
        //K4MnO4 + 2 H2O = MnO2 + 4 KOH
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumManganateIV,9)
                .fluidInputs(DistilledWater.getFluid(2000))
                .output(dust,ManaganeseDioxide,3)
                .output(dust,PotassiumHydroxide,12)
                .EUt(VA[HV])
                .duration(1000)
                .buildAndRegister();
    }

    private static void ChloricAcidRecipes() {
        //Chloric Acid from Chlorine Monoxide
        //2 ClO + H2O = HClO3 + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ChlorineMonoxide.getFluid(2000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(ChloricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[HV])
                .duration(400)
                .buildAndRegister();

        //Hypochlrite from Chlorine and Sodium Hydroxide
        //Cl2 + 2 NaOH = NaCl + NaClO + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumHydroxide,6)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust,SodiumHypochlorite,4)
                .output(dust,Salt,2)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[LV])
                .duration(320)
                .buildAndRegister();

        //Hypochlorite Disproportion
        //3 NaClO = 2 NaCl + NaClO3
        BLAST_RECIPES.recipeBuilder()
                .input(dust,SodiumHypochlorate,9)
                .output(dust,SodiumChlorate,5)
                .output(dust,Salt,4)
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2400)
                .buildAndRegister();

        //Chlorate Disproportion
        //4 NaClO3 = NaCl + 3 NaClO4
        BLAST_RECIPES.recipeBuilder()
                .input(dust,SodiumChlorate,20)
                .output(dust,SodiumPerchlorate,18)
                .output(dust,Salt,2)
                .EUt(VA[MV])
                .duration(400)
                .blastFurnaceTemp(2400)
                .buildAndRegister();

        //Chloric Acid recovery from salts
        //2 NaClO3 + H2SO4 = Na2SO4 + 2 HClO3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumChlorate,10)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,SodiumSulfate,7)
                .fluidOutputs(ChloricAcid.getFluid(2000))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        //Perchloric Acid recovery from salts
        //2 NaClO4 + H2SO4 = Na2SO4 + 2 HClO4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumPerchlorate,12)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,SodiumSulfate,7)
                .fluidOutputs(PerchloricAcid.getFluid(2000))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        //Potassium Chlorate Production
        //HClO3 + KOH = KClO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumHydroxide,3)
                .fluidInputs(ChloricAcid.getFluid(1000))
                .output(dust,PotassiumChlorate,5)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[LV])
                .duration(260)
                .buildAndRegister();

        //SCl2 + H2 = S + 2 HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurDichloride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust,Sulfur)
                .fluidOutputs(HydrogenChloride.getFluid(2000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();
    }

    private static void MiscChemicalRecipes() {
        //Sodium Hydroxide Solution
        MIXER_RECIPES.recipeBuilder()
                .input(dust,SodiumHydroxide)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .EUt(24)
                .duration(40)
                .buildAndRegister();

        //Sodium Fluorosilicate
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HexafluoroSilicicAcid.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(2000))
                .output(dust,SodiumFluorosilicate,9)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[EV])
                .duration(400)
                .buildAndRegister();

        //Carbon Blasting into CO and CO2
        //C + O = CO
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Carbon)
                .circuitMeta(1)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(120)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //C + O2 = CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Carbon)
                .circuitMeta(2)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(240)
                .blastFurnaceTemp(1000)
                .buildAndRegister();

        //Carbonic Acid
        //CO2 + H2O = H2CO3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonDioxide.getFluid(1000),DistilledWater.getFluid(1000))
                .fluidOutputs(CarbonicAcid.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Sodium Carbonate
        //2 Na + H2CO3 = Na2CO3 + H2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Sodium,2)
                .fluidInputs(CarbonicAcid.getFluid(1000))
                .output(dust,SodiumCarbonate)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Sodium Carbonate Solution
        MIXER_RECIPES.recipeBuilder()
                .input(dust,SodiumCarbonate)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(SodiumCarbonateSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Potassium Nitrate
        //2 K + 2 HNO3 = 2 KNO3 + H2 (divided by 2)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Potassium)
                .fluidInputs(NitricAcid.getFluid(1000))
                .output(dust,PotassiumNitrate,5)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Potassium Nitrate Solution
        MIXER_RECIPES.recipeBuilder()
                .input(dust,PotassiumNitrate)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(PotassiumNitrateSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Sodium Bisulfate
        //H2SO4 + NaCl = NaHSO4 + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Salt,2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,SodiumBisulfate,7)
                .fluidOutputs(HydrogenChloride.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Sodium Bisulfate Solution
        MIXER_RECIPES.recipeBuilder()
                .input(dust,SodiumBisulfate)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(SodiumBisulfateSolution.getFluid(1000))
                .EUt(VA[LV])
                .duration(80)
                .buildAndRegister();

        //Oxidation of Hydrogen Chloride/- Iodide to Potassium Chlorate/- Iodate
        //6 KMnO4 + HI + 7 KOH = 6 K2MnO4 + KIO3 + 4 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumPermanagante,36)
                .input(dust,PotassiumHydroxide,21)
                .fluidInputs(HydrogenIodide.getFluid(1000))
                .output(dust,PotassiumManganateVI,42)
                .output(dust,PotassiumIodate,5)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();

        //6 KMnO4 + HCl + 7 KOH = 6 K2MnO4 + KClO3 + 4 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumPermanagante,36)
                .input(dust,PotassiumHydroxide,21)
                .fluidInputs(HydrogenChloride.getFluid(1000))
                .output(dust,PotassiumManganateVI,42)
                .output(dust,PotassiumChlorate,5)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();

        //Acid Recovery with H2SO4
        //2 KIO3 + H2SO4 = K2SO4 + 2 HIO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,PotassiumIodate,10)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,PotassiumSulfate,7)
                .fluidOutputs(IodicAcid.getFluid(2000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();
    }
}
