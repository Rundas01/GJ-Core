package gregsjourney.loaders.recipe.multiblock.orecracking;

import static gregsjourney.api.recipe.GJRecipeMaps.*;
import static gregsjourney.api.unification.material.GJMiscMaterials.*;
import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumIIIOxide;
import static gregsjourney.api.unification.material.materiallines.AluminiumLineMaterials.HighPurityAluminium;
import static gregsjourney.api.unification.material.materiallines.ArsenicLineMaterials.*;
import static gregsjourney.api.unification.material.materiallines.BoronLineMaterials.BoronTrichloride;
import static gregsjourney.api.unification.material.materiallines.BoronLineMaterials.SodiumBorohydride;
import static gregsjourney.api.unification.material.materiallines.CobaltLineMaterials.CobaltSlag;
import static gregsjourney.api.unification.material.materiallines.CobaltLineMaterials.CobaltTrinitrate;
import static gregsjourney.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregsjourney.api.unification.material.materiallines.IronLineMaterials.IronTrinitrate;
import static gregsjourney.api.unification.material.materiallines.LeadLineMaterials.LeadDinitrate;
import static gregsjourney.api.unification.material.materiallines.LeadLineMaterials.LeadSlag;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.HighPurityLithium;
import static gregsjourney.api.unification.material.materiallines.LithiumLineMaterials.LithiumOxide;
import static gregsjourney.api.unification.material.materiallines.MagnesiumLineMaterials.HighPurityMagnesium;
import static gregsjourney.api.unification.material.materiallines.SiliconLineMaterials.HighPuritySilicon;
import static gregsjourney.api.unification.material.materiallines.ThalliumLineMaterials.ThalliumSlag;
import static gregsjourney.api.unification.material.materiallines.ThalliumLineMaterials.ThalliumTrinitrate;
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
        //As4S4 + 40 HNO3 = 4 As(NO3)3 + 28 NO2 + 4 SO2 + 20 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Realgar,8)
                .fluidInputs(NitricAcid.getFluid(40000))
                .output(dust,ArsenicTrinitrate,52)
                .fluidOutputs(RealgarSlag.getFluid(800),SulfurDioxide.getFluid(4000),NitrogenDioxide.getFluid(28000),Steam.getFluid(20000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Arsenopyrite Cracking
        //FeAsS + 16 HNO3 = Fe(NO3)3 + As(NO3)3 + SO2 + 10 NO2 + 8 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Arsenopyrite,3)
                .fluidInputs(NitricAcid.getFluid(16000))
                .output(dust,ArsenicTrinitrate,13)
                .output(dust,IronTrinitrate,13)
                .fluidOutputs(ArsenopyriteSlag.getFluid(300),SulfurDioxide.getFluid(1000),NitrogenDioxide.getFluid(10000),Steam.getFluid(8000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Hutchinsonite Cracking
        //TlPbAs5S9 + 96 HNO3 = Tl(NO3)3 + Pb(NO3)2 + 5 As(NO3)5 + 66 NO2 + 9 SO2 + 48 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Hutchinsonite,16)
                .fluidInputs(NitricAcid.getFluid(96000))
                .output(dust,ArsenicPentanitrate,105)
                .output(dust,ThalliumTrinitrate,13)
                .output(dust,LeadDinitrate,9)
                .fluidOutputs(HutchinsoniteSlag.getFluid(1600),SulfurDioxide.getFluid(9000),NitrogenDioxide.getFluid(66000),Steam.getFluid(48000))
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //Skutterudite Cracking
        //CoAs3 + 24 HNO3 = 3 As(NO3)3 + Co(NO3)3 + 12 NO2 + 12 H2O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Skutterudite,4)
                .fluidInputs(NitricAcid.getFluid(24000))
                .output(dust,ArsenicTrinitrate,39)
                .output(dust,CobaltTrinitrate,13)
                .fluidOutputs(SkutteruditeSlag.getFluid(400),NitrogenDioxide.getFluid(12000),Steam.getFluid(12000))
                .duration(400)
                .EUt(VA[MV])
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

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(SkutteruditeSlag.getFluid(400))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(ArsenicSlag.getFluid(300),CobaltSlag.getFluid(100))
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

        //Redox Reactions in the RF
        //Nitrate -> Nitride
        //2 As(NO3)5 + 7 C = 2 AsN + 8 NO2 + 7 CO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicPentanitrate,42)
                .input(dust,Carbon,7)
                .output(dust,ArsenicNitride,4)
                .fluidOutputs(NitrogenDioxide.getFluid(8000),CarbonDioxide.getFluid(7000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //2 As(NO3)3 + C = 2 AsN + 8 NO2 + CO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrinitrate,26)
                .input(dust,Carbon)
                .output(dust,ArsenicNitride,4)
                .fluidOutputs(NitrogenDioxide.getFluid(8000),CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Nitride -> Oxide
        //2 AsN + 7 O = As2O3 + 2 NO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicNitride,4)
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust,ArsenicTrioxide,5)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //Arsenic Oxides Reduction
        //As2O3 + 6 Li = 2 As + 3 Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,HighPurityLithium,6)
                .output(dust,Arsenic,2)
                .output(dust,LithiumOxide,9)
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //As2O3 + 3 Mg = 2 As + 3 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,HighPurityMagnesium,3)
                .output(dust,Arsenic,2)
                .output(dust,Magnesia)
                .EUt(VA[MV])
                .duration(400)
                .buildAndRegister();

        //As2O3 + 2 Al = 2 As + Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,5)
                .input(dust,HighPurityAluminium,2)
                .output(dust,Arsenic,2)
                .output(dust,AluminiumIIIOxide,5)
                .EUt(VA[HV])
                .duration(600)
                .buildAndRegister();

        //2 As2O3 + 3 Si = 4 As + 3 SiO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicTrioxide,10)
                .input(dust,HighPuritySilicon,3)
                .output(dust,Arsenic,4)
                .output(dust,SiliconDioxide,9)
                .EUt(VA[EV])
                .duration(800)
                .buildAndRegister();

        //As2O5 + 10 Li = 2 As + 5 Li2O
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,7)
                .input(dust,HighPurityLithium,10)
                .output(dust,Arsenic,2)
                .output(dust,LithiumOxide,15)
                .duration(200)
                .EUt(VA[MV])
                .buildAndRegister();

        //As2O5 + 5 Mg = 2 As + 5 MgO
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,7)
                .input(dust,HighPurityMagnesium,5)
                .output(dust,Arsenic,2)
                .output(dust,Magnesia,10)
                .duration(400)
                .EUt(VA[MV])
                .buildAndRegister();

        //3 As2O5 + 10 Al = 6 As + 5 Al2O3
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,21)
                .input(dust,HighPurityAluminium,10)
                .output(dust,Arsenic,6)
                .output(dust,AluminiumIIIOxide,25)
                .duration(600)
                .EUt(VA[HV])
                .buildAndRegister();

        //2 As2O5 + 5 Si = 4 As + 5 SiO2
        REDOX_RECIPES.recipeBuilder()
                .input(dust,ArsenicVOxide,14)
                .input(dust,HighPuritySilicon,5)
                .output(dust,Arsenic,4)
                .output(dust,SiliconDioxide,15)
                .duration(800)
                .EUt(VA[EV])
                .buildAndRegister();


        //SEMICONDUCTOR GRADE ARSENIC
        //As + 3 Cl = AsCl3
        METALLURGIC_REACTION_SMELTER_RECIPES.recipeBuilder()
                .input(dust,Arsenic)
                .fluidInputs(Chlorine.getFluid(3000))
                .fluidOutputs(ArsenicTrichloride.getFluid(1000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //Arsenic Trichloride Distillation
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(ArsenicTrichloride.getFluid(2000))
                .fluidOutputs(ArsenicTrichloride.getFluid(1000))
                .fluidOutputs(SulfurDichloride.getFluid(250))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //AR(C)SINE
        //4 AsCl3 + 3 NaBH4 = 4 AsH3 + 3 NaCl + 3 BCl3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumBorohydride,18)
                .fluidInputs(ArsenicTrichloride.getFluid(4000))
                .output(dust,Salt,6)
                .fluidOutputs(Arsine.getFluid(4000),BoronTrichloride.getFluid(3000))
                .duration(120)
                .EUt(VA[LV])
                .buildAndRegister();

        //AsH3 -> As + 3 H
        ELECTROLYZER_RECIPES.recipeBuilder()
                .notConsumable(stick,Arsenic)
                .notConsumable(stick,Graphite)
                .fluidInputs(Arsine.getFluid(1000))
                .output(dust,HighPurityArsenic)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .duration(680)
                .EUt(VA[EV])
                .buildAndRegister();
    }
}
