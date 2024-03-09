package gregsjourney.api.utils;

import forestry.apiculture.blocks.BlockAlveary;
import forestry.apiculture.blocks.BlockAlvearyType;
import forestry.core.items.ItemBlockForestry;
import gregsjourney.GregsJourney;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class GJUtil {
    private GJUtil() {}
    public static @NotNull ResourceLocation gjId(@NotNull String path) {
        return new ResourceLocation(GregsJourney.MODID, path);
    }

    public static int convertRGB2Hex(int r, int g, int b) {
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));
        String hexColor = String.format("%02X%02X%02X", r, g, b);
        return Integer.parseInt(hexColor, 16);
    }

    public static int avgColor(Material... materials) {
        int color = 0;
        for (Material material : materials) {
            color += material.getMaterialRGB();
        }
        color /= materials.length;
        return color;
    }

    private static Recipe find(RecipeMap<?> recipeMap, @Nullable Long voltage, List<ItemStack> items, List<FluidStack> fluids) {
        if (items == null || items.isEmpty()) items = Collections.emptyList();
        if (fluids == null || fluids.isEmpty()) fluids = Collections.emptyList();
        if (voltage != null){
            return recipeMap.findRecipe(voltage, items, fluids, true);
        }
        return recipeMap.findRecipe(voltage, items, fluids, false);
    }

    public static void removeByInput(RecipeMap<?> recipeMap, @Nullable Long voltage, List<ItemStack> items, List<FluidStack> fluids) {
        Recipe recipe = find(recipeMap ,voltage, items, fluids);
        if (recipe == null) {
            return;
        }
        recipeMap.removeRecipe(recipe);
    }

    public static FluidStack molaricFluidStack(Material fluid, int molarity){
        return fluid.getFluid(1000 * molarity);
    }

    public static ItemStack molaricItemStack(Material material, int molarity, OrePrefix prefix){
        return OreDictUnifier.get(prefix, material, getAmounts(material.getMaterialComponents()) * molarity);
    }

    public static ItemStack molaricDustStack(Material material, int molarity){
        return molaricItemStack(material, molarity, OrePrefix.dust);
    }

    public static ItemStack molaricIngotStack(Material material, int molarity){
        return molaricItemStack(material, molarity, OrePrefix.ingot);
    }

    public static int getAmounts(List<MaterialStack> materialStacks){
        int a = 0;
        for(MaterialStack stack : materialStacks){
            a += stack.amount;
        }
        return a;
    }

    public enum Dimensions{
        MOON(-28),
        MARS(-29),
        PHOBOS(-1012),
        DEIMOS(-1013),
        CERES(-1007),
        MERCURY(-1005),
        VENUS(-31),
        ASTEROIDS(-30),
        EUROPA(-1017),
        IO(-1014),
        GANYMEDE(-1016),
        CALLISTO(-1022),
        ENCELADUS(-1017),
        TITAN(-1018),
        MIRANDA(-1024),
        OBERON(-1019),
        PROTEUS(-1020),
        TRITON(-1021),
        PLUTO(-1008),
        HAUMEA(-1023),
        MAKEMAKE(-1011),
        PROXIMA(-1025),
        BARNADA(-1030),
        BARNADAC1(-1031),
        BARNADAC2(-1032),
        //TODO: Tau Ceti Dim ID
        CHALOS(-2543),
        DIONA(-2542),
        FRONOS(-2545),
        NIBIRU(-2544),
        KOENTUS(-2642);

        private final int dimension;

        Dimensions(int dim) {
            this.dimension = dim;
        }

        public int getDimension() {
            return dimension;
        }
    }
}
