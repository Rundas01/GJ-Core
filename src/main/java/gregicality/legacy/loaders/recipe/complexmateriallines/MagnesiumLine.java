package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Kieserite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumSlag;
import static gregicality.legacy.api.unification.material.materiallines.MagnesiumLineMaterials.MagnesiumSlag;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class MagnesiumLine {
    private MagnesiumLine(){}

    public static void init(){
        //ELEMENT ACQUISITION
        //Saasbestos Cracking
        //Mg3Si2H4O9 + 2 H3PO4 = Mg3(PO4)2 + 2 SiO2 + 5 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Asbestos,18)
                .fluidInputs(PhosphoricAcid.getFluid(2000))
                .output(dust,MagnesiumPhosphate,13)
                .output(dust,SiliconDioxide,6)
                .fluidOutputs(AsbestosSlag.getFluid(1800),Steam.getFluid(5000))
                .duration(240)
                .EUt(VA[LV])
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Glauconite Sand Cracking (Komponenten ändern)
        //K2Mg4Al4H2O12 + 22 HCl = 4 MgCl2 + 2 KCl + 4 AlCl3 + 12 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,GlauconiteSand,24)
                .fluidInputs(HydrochloricAcid.getFluid(22000))
                .output(dust,MagnesiumDichloride,12)
                .output(dust,RockSalt,4)
                .output(dust,AluminiumTrichloride,16)
                .fluidOutputs(GlauconiteSandSlag.getFluid(2400),Steam.getFluid(34000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Talc Cracking
        //Mg3Si4H2O12 + 6 HCl = 3 MgCl2 + 4 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Talc,21)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,MagnesiumDichloride,9)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(TalcSlag.getFluid(2100),Steam.getFluid(10000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Soapstone Acidic Cracking
        //Mg3Si4H2O12 + 6 HCl = 3 MgCl2 + 4 SiO2 + 4 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Soapstone,21)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .output(dust,MagnesiumDichloride,9)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(SoapstoneSlag.getFluid(2100),Steam.getFluid(10000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Bentonite Cracking (Komponenten ändern)
        //Na2Mg6Si12(H2O)5O31 + 14 HCl = 2 NaCl + 6 MgCl2 + 12 SiO2 + 12 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Bentonite,66)
                .fluidInputs(HydrochloricAcid.getFluid(14000))
                .output(dust,MagnesiumDichloride,18)
                .output(dust,Salt,4)
                .output(dust,SiliconDioxide,36)
                .fluidOutputs(BentoniteSlag.getFluid(6600),Steam.getFluid(26000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Fullers Erde Acidic Cracking (Komponenten ändern)
        //Mg2Si4H2(H2O)4O11 + 4 HCl = 2 MgCl2 + 4 SiO2 + 7 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,FullersEarth,31)
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .output(dust,MagnesiumDichloride,6)
                .output(dust,SiliconDioxide,12)
                .fluidOutputs(FullersEarthSlag.getFluid(3100),Steam.getFluid(11000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Kieserite Cracking
        //MgSO4(H2O) + 4 HCl = MgCl2 + 3 H2O + SO2 + Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Kieserite,9)
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .output(dust,MagnesiumDichloride,3)
                .fluidOutputs(KieseriteSlag.getFluid(900),SulfurDioxide.getFluid(1000),Chlorine.getFluid(2000),Steam.getFluid(7000))
                .EUt(VA[MV])
                .duration(240)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(AsbestosSlag.getFluid(1800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(300))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(GlauconiteSandSlag.getFluid(2400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(400),AluminiumSlag.getFluid(400),PotassiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(TalcSlag.getFluid(2100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(300))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SoapstoneSlag.getFluid(2100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(300))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(BentoniteSlag.getFluid(6600))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(600),SodiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(FullersEarthSlag.getFluid(3100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(MagnesiumSlag.getFluid(200))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(MagnesiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Magnesium)
                .chancedOutput(dustImpure,Magnesium,1,8750,800)
                .chancedOutput(dustImpure,Magnesium,1,7500,700)
                .chancedOutput(dustImpure,Magnesium,1,6250,600)
                .chancedOutput(dustImpure,Magnesium,1,5000,500)
                .chancedOutput(dustImpure,Magnesium,1,3750,400)
                .chancedOutput(dustImpure,Magnesium,1,2500,300)
                .chancedOutput(dustImpure,Magnesium,1,1250,200)
                .chancedOutput(dustImpure,Magnesium,1,0,100)
                .fluidOutputs(MagnesiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(MagnesiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Magnesium,1,7500,750)
                .chancedOutput(dustImpure,Magnesium,1,5000,500)
                .chancedOutput(dustImpure,Magnesium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Redox Reactions in the EBF
        //MgCO3 = MgO + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Magnesite,5)
                .output(dust,Magnesia,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //MgSO4 = MgO + SO2 + 1/2 O2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,MagnesiumSulfate,6)
                .output(dust,Magnesia,2)
                .fluidOutputs(SulfurDioxide.getFluid(2000),Oxygen.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //2 MgO + C = 2 Mg + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Magnesia,4)
                .input(dust,Carbon)
                .output(dust,Magnesium,2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(4000)
                .buildAndRegister();
    }
}
