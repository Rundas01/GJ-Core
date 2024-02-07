package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class CaesiumLine {
    private CaesiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Pollucite Cracking
        //Cs2Al2Si4(H2O)2O12 + 8 HCl + 6 HNO3 = 2 CsCl + 2 AlCl3 + 4 SiO2 + 6 NO + 9 H2O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pollucite,26)
                .fluidInputs(AquaRegia.getFluid(4000),NitricAcid.getFluid(2000))
                .output(dust,CaesiumChloride,4)
                .output(dust,AluminiumTrichloride,8)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(PolluciteSlag.getFluid(2600),NitrousOxide.getFluid(6000),HydrogenPeroxide.getFluid(9000))
                .duration(400)
                .EUt(VA[EV])
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Cs2Al2Si4(H2O)2O12 + 8 HClO4 = 2 CsCl + 2 AlCl3 + 4 SiO2 + 6 H2O + 16 O2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Pollucite,26)
                .fluidInputs(PerchloricAcid.getFluid(8000))
                .output(dust,CaesiumChloride,6)
                .output(dust,AluminiumTrichloride,8)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(PolluciteSlag.getFluid(2600),Oxygen.getFluid(32000),Steam.getFluid(6000))
                .duration(200)
                .EUt(VA[ZPM])
                .blastFurnaceTemp(6000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(PolluciteSlag.getFluid(2600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(CaesiumSlag.getFluid(200),AluminiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(CaesiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Caesium)
                .chancedOutput(dustImpure,Caesium,1,8750,800)
                .chancedOutput(dustImpure,Caesium,1,7500,700)
                .chancedOutput(dustImpure,Caesium,1,6250,600)
                .chancedOutput(dustImpure,Caesium,1,5000,500)
                .chancedOutput(dustImpure,Caesium,1,3750,400)
                .fluidOutputs(CaesiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(CaesiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Caesium,1,7500,750)
                .chancedOutput(dustImpure,Caesium,1,5000,500)
                .chancedOutput(dustImpure,Caesium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Chloride to Nitrate Conversion
        //CsCl + HNO3 = CsNO3 + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CaesiumChloride,2)
                .fluidInputs(NitricAcid.getFluid(1000))
                .output(dust,CaesiumNitrate,5)
                .fluidOutputs(HydrogenChloride.getFluid(1000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Nitrate to Sulfate Conversion
        //2 CsNO3 + H2SO4 = Cs2SO4 + 2 NO2 + O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CaesiumNitrate,5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust,CaesiumSulfate,7)
                .fluidOutputs(NitrogenDioxide.getFluid(2000),Oxygen.getFluid(1000),Water.getFluid(1000))
                .EUt(VA[LV])
                .duration(420)
                .buildAndRegister();

        //Desulfurizing of Caesium Sulfide
        //Cs2S + H2CO3 = Cs2CO3 + H2S
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,CaesiumSulfide,3)
                .fluidInputs(CarbonicAcid.getFluid(1000))
                .output(dust,CaesiumCarbonate,6)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[LV])
                .duration(380)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //Sulfate Decomposition
        //Cs2SO4 = Cs2S + 2 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CaesiumSulfate,7)
                .output(dust,CaesiumSulfide,3)
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VA[EV])
                .duration(800)
                .blastFurnaceTemp(4000)
                .buildAndRegister();

        //Carbonate Decomposition
        //Cs2CO3 = Cs2O + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CaesiumCarbonate,6)
                .output(dust,CaesiumOxide,3)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[EV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Oxide Reduction
        //Cs2O + 2 Li = 2 Cs + Li2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust,CaesiumOxide,3)
                .input(dust,Lithium,2)
                .output(dust,Caesium,2)
                .output(dust,LithiumOxide,3)
                .EUt(VA[EV])
                .duration(600)
                .blastFurnaceTemp(2000)
                .buildAndRegister();
    }
}
