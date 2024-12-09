package gregsjourney.loaders.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

import static gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING;
import static gregtech.api.unification.ore.OrePrefix.*;

public class RecipeRemoval {
    public static void init(){
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE) && !material.hasFlag(NO_SMELTING) && !material.hasProperty(PropertyKey.BLAST)) {
                removeSmeltingRecipes(material);
            }
        }
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
