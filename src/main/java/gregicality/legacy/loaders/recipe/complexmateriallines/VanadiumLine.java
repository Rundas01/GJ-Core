package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FLOTATION_RECIPES;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ROASTING_RECIPES;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.VanadiumLineMaterials.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class VanadiumLine {
    private VanadiumLine() {}

    public static void init() {
        //VFe3O4 + oxygen in the EBF -> Fe3O4 + vanadium slag
        ROASTING_RECIPES.recipeBuilder()
                .input(dust,VanadiumMagnetite)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust,Magnetite)
                .fluidOutputs(ImpureVanadiumSlag.getFluid(1000))
                .EUt(1024)
                .duration(400)
                .buildAndRegister();

        FLOTATION_RECIPES.recipeBuilder()
                .fluidInputs(ImpureVanadiumSlag.getFluid(3000))
                .fluidOutputs(VanadiumSlag.getFluid(2000),VanadiumWaste.getFluid(500))
                .EUt(16)
                .duration(200)
                .buildAndRegister();

        //vanadium slag + 2 NaCl -> 2 sodiummetavanadate (NaVO3) + slag + Cl2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Salt,4)
                .fluidInputs(VanadiumSlag.getFluid(1000))
                .output(dust,SodiumMetaVanadate,10)
                .fluidOutputs(Chlorine.getFluid(2000),VanadiumWaste.getFluid(500))
                .blastFurnaceTemp(3000)
                .EUt(800)
                .duration(200)
                .buildAndRegister();

        //12 NaVO3 + 4 HNO2 + 3 NH4NO2 + 6 NO -> 2 ammoniumpolyvanadate (H8N2O16V6) + 12 NaNO2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumMetaVanadate,60)
                .input(dust,AmmoniumNitrite,24)
                .fluidInputs(NitrousAcid.getFluid(4000),NitrousOxide.getFluid(6000))
                .output(dust,AmmoniumPolyVanadate,64)
                .output(dust,SodiumNitrite,48)
                .EUt(480)
                .duration(240)
                .buildAndRegister();

        //2 H8N2O16V6 + 7 O2 -> 6 V2O5 + 8 H2O + 4 NO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,AmmoniumPolyVanadate,64)
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust,VanadiumPentoxide,42)
                .fluidOutputs(Steam.getFluid(8000),NitrogenDioxide.getFluid(4000))
                .blastFurnaceTemp(2500)
                .EUt(600)
                .duration(300)
                .buildAndRegister();

        //V2O5 + 5 Ca -> 2 V + 5 CaO
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,VanadiumPentoxide,7)
                .input(dust,Calcium,5)
                .output(dust,Vanadium,2)
                .output(dust,CalciumOxide,10)
                .EUt(480)
                .duration(240)
                .buildAndRegister();
    }
}
