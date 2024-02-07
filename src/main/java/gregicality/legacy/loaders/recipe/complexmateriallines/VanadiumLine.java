package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROreMaterials.Vanadinite;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.IronSlag;
import static gregicality.legacy.api.unification.material.materiallines.VanadiumLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumLine {
    private VanadiumLine() {}

    public static void init() {
        //ELEMENT ACQUISITION
        //Vanadium Magnetite Cracking
        //VFe3O4 + 14 HClO4 = VCl5 + 3 FeCl3 + 7 H2O + 53 O
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,VanadiumMagnetite,8)
                .fluidInputs(PerchloricAcid.getFluid(14000))
                .output(dust,VanadiumPentachloride,6)
                .fluidOutputs(VanadiumMagnetiteSlag.getFluid(800),Iron3Chloride.getFluid(3000),Oxygen.getFluid(53000),Steam.getFluid(7000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(6000)
                .buildAndRegister();

        //Vanadinite Cracking
        //2 Pb5(VO4)3Cl + 52 HNO3 = 6 V(NO3)5 + 10 Pb(NO3)2 + 2 NO2 + 26 H2O + Cl2
        ORE_CRACKING_RECIPES.recipeBuilder()
                .input(dust,Vanadinite,42)
                .fluidInputs(NitricAcid.getFluid(52000))
                .output(dust,VanadiumPentanitrate,126)
                .output(dust,LeadDinitrate,90)
                .fluidOutputs(VanadiniteSlag.getFluid(4200),Chlorine.getFluid(2000),NitrogenDioxide.getFluid(2000),Steam.getFluid(26000))
                .EUt(VA[EV])
                .duration(400)
                .blastFurnaceTemp(6000)
                .buildAndRegister();

        //Slag Flotation
        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(VanadiumMagnetiteSlag.getFluid(800))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(VanadiumSlag.getFluid(100),IronSlag.getFluid(100))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(VanadiniteSlag.getFluid(2100))
                .notConsumable(MethylIsobutylCarbinol.getFluid(100))
                .fluidOutputs(VanadiumSlag.getFluid(300),LeadSlag.getFluid(500))
                .EUt(VA[HV])
                .duration(80)
                .buildAndRegister();

        //Slag Separation
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(VanadiumSlag.getFluid(1000),DistilledWater.getFluid(1000))
                .output(dustImpure,Vanadium)
                .chancedOutput(dustImpure,Vanadium,1,8750,800)
                .chancedOutput(dustImpure,Vanadium,1,7500,700)
                .chancedOutput(dustImpure,Vanadium,1,6250,600)
                .chancedOutput(dustImpure,Vanadium,1,5000,500)
                .chancedOutput(dustImpure,Vanadium,1,3750,400)
                .fluidOutputs(VanadiumWaste.getFluid(100))
                .EUt(VA[LV])
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(VanadiumWaste.getFluid(1000))
                .chancedOutput(dustImpure,Vanadium,1,7500,750)
                .chancedOutput(dustImpure,Vanadium,1,5000,500)
                .chancedOutput(dustImpure,Vanadium,1,2500,250)
                .EUt(VA[LV])
                .duration(20)
                .buildAndRegister();

        //Vanadate Ion Exchange
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumMetaVanadate,5)
                .input(dust,AmmoniumChloride,6)
                .output(dust,AmmoniumMetaVanadate,8)
                .output(dust,Salt,2)
                .EUt(VA[MV])
                .duration(260)
                .buildAndRegister();

        //Vanadate Blasting
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AmmoniumMetaVanadate,18)
                .output(dust,VanadiumVOxide,7)
                .fluidOutputs(Ammonia.getFluid(2000),Steam.getFluid(1000))
                .EUt(720)
                .duration(800)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        //Pentoxide Reduction
        //V2O5 + 5 Ca -> 2 V + 5 CaO
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,VanadiumPentoxide,7)
                .input(dust,Calcium,5)
                .output(dust,Vanadium,2)
                .output(dust,CalciumOxide,10)
                .EUt(480)
                .duration(240)
                .buildAndRegister();

        //PURIFICATION PROCESS
        //Vanadium Iodide Production
        //2 V + 3 I2 = 2 VI3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Vanadium,2)
                .input(dust,Iodine,6)
                .output(dust,VanadiumIodide,8)
                .EUt(720)
                .duration(6000)
                .buildAndRegister();

        //Electrolysis of Vanadium Iodide
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(wireFine,Tungsten)
                .input(dust,VanadiumIodide,8)
                .output(dust,HighPurityVanadium,2)
                .output(dust,Iodine,6)
                .EUt(800)
                .duration(3000)
                .buildAndRegister();
    }
}
