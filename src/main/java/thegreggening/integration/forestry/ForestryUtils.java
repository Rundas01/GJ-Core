package thegreggening.integration.forestry;

import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.items.EnumHoneyComb;
import gregtech.api.unification.material.Material;
import gregtech.integration.forestry.bees.GTCombType;
import net.minecraft.item.ItemStack;
import thegreggening.integration.forestry.item.TGCombType;

import java.util.ArrayList;
import java.util.List;

import static thegreggening.utils.TGMaterialUtil.getFromString;

public class ForestryUtils {
    public static ItemStack getForestryComb(EnumHoneyComb type) {
        return getForestryComb(type, 1);
    }

    public static ItemStack getForestryComb(EnumHoneyComb type, int amount) {
        return ModuleApiculture.getItems().beeComb.get(type, amount);
    }

    public static ItemStack getGTComb(GTCombType type) {
        return getGTComb(type, 1);
    }

    public static ItemStack getGTComb(GTCombType type, int amount) {
        return new ItemStack(gregtech.integration.forestry.ForestryModule.COMBS, amount, type.ordinal());
    }

    public static ItemStack getTGComb(TGCombType type) {
        return getTGComb(type, 1);
    }

    public static ItemStack getTGComb(TGCombType type, int amount) {
        return new ItemStack(thegreggening.integration.forestry.ForestryModule.COMBS, amount, type.ordinal());
    }

    public static List<ItemStack> getAllGTCombs() {
        List<ItemStack> combs = new ArrayList<>();
        for (int i = 0; i < GTCombType.values().length; i++) {
            combs.add(new ItemStack(gregtech.integration.forestry.ForestryModule.COMBS, 1, i));
        }
        return combs;
    }

    public static List<ItemStack> getAllTGCombs() {
        List<ItemStack> combs = new ArrayList<>();
        for (int i = 0; i < TGCombType.values().length; i++) {
            combs.add(new ItemStack(thegreggening.integration.forestry.ForestryModule.COMBS, 1, i));
        }
        return combs;
    }

    public static String getGTCombName(GTCombType comb) {
        return comb.name;
    }

    public static String getTGCombName(TGCombType comb) {
        return comb.name;
    }

    public static Material getGTCombMaterial(GTCombType comb) {
        return getFromString(getGTCombName(comb));
    }

    public static Material getTGCombMaterial(TGCombType comb) {
        return getFromString(getTGCombName(comb));
    }
}
