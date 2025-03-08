package thegreggening.common.recipe;

import static gregtech.api.recipes.GTRecipeHandler.removeAllRecipes;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING;
import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

public class RecipeRemoval {

    public static void init() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE) && !material.hasFlag(NO_SMELTING) &&
                    !material.hasProperty(PropertyKey.BLAST)) {
                removeSmeltingRecipes(material);
            }
        }
        wipeRecipeMaps();
    }

    private static void wipeRecipeMaps() {
        removeAllRecipes(CHEMICAL_RECIPES);
        removeAllRecipes(COMBUSTION_GENERATOR_FUELS);
        removeAllRecipes(SEMI_FLUID_GENERATOR_FUELS);
        removeAllRecipes(GAS_TURBINE_FUELS);
        removeAllRecipes(PRIMITIVE_BLAST_FURNACE_RECIPES);
        removeAllRecipes(DISTILLATION_RECIPES);
        removeAllRecipes(DISTILLERY_RECIPES);
        //removeAllRecipes(BLAST_RECIPES);
    }

    private static void removeSmeltingRecipes(Material material) {
        if (!OreDictUnifier.get(ore, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(ore, material));
        }
        if (!OreDictUnifier.get(oreAndesite, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreAndesite, material));
        }
        if (!OreDictUnifier.get(oreBasalt, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreBasalt, material));
        }
        if (!OreDictUnifier.get(oreBlackgranite, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreBlackgranite, material));
        }
        if (!OreDictUnifier.get(oreDiorite, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreDiorite, material));
        }
        if (!OreDictUnifier.get(oreEndstone, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreEndstone, material));
        }
        if (!OreDictUnifier.get(oreGranite, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreGranite, material));
        }
        if (!OreDictUnifier.get(oreMarble, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreMarble, material));
        }
        if (!OreDictUnifier.get(oreNetherrack, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreNetherrack, material));
        }
        if (!OreDictUnifier.get(oreRedgranite, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreRedgranite, material));
        }
        if (!OreDictUnifier.get(oreRedSand, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreRedSand, material));
        }
        if (!OreDictUnifier.get(oreSand, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(oreSand, material));
        }
        if (!OreDictUnifier.get(crushed, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(crushed, material));
        }
        if (!OreDictUnifier.get(crushedPurified, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(crushedPurified, material));
        }
        if (!OreDictUnifier.get(crushedCentrifuged, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(crushedCentrifuged, material));
        }
        if (!OreDictUnifier.get(dustImpure, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(dustImpure, material));
        }
        if (!OreDictUnifier.get(dustPure, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(dustPure, material));
        }
        if (!material.isElement() && !OreDictUnifier.get(dust, material).isEmpty()) {
            ModHandler.removeFurnaceSmelting(OreDictUnifier.get(dust, material));
        }
    }
}
