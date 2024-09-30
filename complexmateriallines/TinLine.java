package gregicality.legacy.loaders.recipe.complexmateriallines;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.unification.material.GCYLROrganicMaterials.MethylIsobutylCarbinol;
import static gregicality.legacy.api.unification.material.GCYLRUniversalChemicalMaterials.*;
import static gregicality.legacy.api.unification.material.materiallines.AluminiumLineMaterials.AluminiumOxide;
import static gregicality.legacy.api.unification.material.materiallines.CopperLineMaterials.CopperSlag;
import static gregicality.legacy.api.unification.material.materiallines.IronLineMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustImpure;

public class TinLine {
    private TinLine(){}

    public static void init(){
        //ELEMENT ACQUISITION

        //Redox Reactions in the EBF
        //SnO2 + C = Sn + CO2
        BLAST_RECIPES.recipeBuilder()
                .input(dust,Cassiterite,4)
                .input(dust,Carbon)
                .output(dust,Tin)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,CassiteriteSand,4)
                .input(dust,Carbon)
                .output(dust,Tin)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(300)
                .blastFurnaceTemp(2000)
                .buildAndRegister();
    }
}
