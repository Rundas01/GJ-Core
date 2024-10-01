package gregsjourney.api.utils;

import gregsjourney.GregsJourney;
import gregsjourney.api.unification.property.GJMaterialFlags;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static gregtech.api.unification.ore.OrePrefix.dust;

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

    public static int weightedAvgColor(MaterialStack[] stacks) {
        int color = 0;
        for (MaterialStack stack : stacks) {
            color += stack.material.getMaterialRGB() * stack.amount;
        }
        color /= stacks.length;
        return color;
    }

    private static Recipe find(RecipeMap<?> recipeMap, @Nullable Long voltage, List<ItemStack> items,
                               List<FluidStack> fluids) {
        if (items == null || items.isEmpty()) items = Collections.emptyList();
        if (fluids == null || fluids.isEmpty()) fluids = Collections.emptyList();
        if (voltage != null) {
            return recipeMap.findRecipe(voltage, items, fluids, true);
        }
        return recipeMap.findRecipe(voltage, items, fluids, false);
    }

    public static void removeByInput(RecipeMap<?> recipeMap, @Nullable Long voltage, List<ItemStack> items,
                                     List<FluidStack> fluids) {
        Recipe recipe = find(recipeMap, voltage, items, fluids);
        if (recipe == null) {
            return;
        }
        recipeMap.removeRecipe(recipe);
    }

    public static void setupFluidType(Material mat, FluidStorageKey key, int temp) {
        if (mat.getProperty(PropertyKey.FLUID) == null) {
            FluidProperty property = new FluidProperty();
            property.getStorage().enqueueRegistration(key, new FluidBuilder().temperature(temp));
            mat.setProperty(PropertyKey.FLUID, property);
        } else {
            FluidProperty property = mat.getProperty(PropertyKey.FLUID);
            if (property.getStorage().getQueuedBuilder(key) != null) {
                property.getStorage().getQueuedBuilder(key).temperature(temp);
            } else {
                property.getStorage().enqueueRegistration(key, new FluidBuilder().temperature(temp));
            }
        }
        if (mat.getProperty(PropertyKey.FLUID).getStorage().getQueuedBuilder(FluidStorageKeys.LIQUID) == null) {
            setupFluidType(mat, FluidStorageKeys.LIQUID, temp);
        }
    }

    public static void setupFluidType(Material mat, FluidStorageKey key) {
        setupFluidType(mat, key, 300);
    }

    public static void setTemperature(Material material, int temp) {
        material.getFluid().setTemperature(temp);
    }

    public static ItemStack molaricDust(String material, int moles) {
        Material mat = getFromString(material);
        assert mat != null;
        return OreDictUnifier.get(dust, mat, moles * getToalComponentAmounts(mat));
    }

    public static ItemStack molaricDust(String material) {
        return molaricDust(material, 1);
    }

    public static FluidStack molaricFluid(String material, int moles, boolean moleIs144) {
        Material mat = getFromString(material);
        assert mat != null;
        if (moleIs144) {
            return mat.getFluid(moles * 144);
        }
        return mat.getFluid(moles * 1000);
    }

    public static FluidStack molaricFluid(String material, int moles) {
        return molaricFluid(material, moles, false);
    }

    public static FluidStack molaricFluid(String material) {
        return molaricFluid(material, 1, false);
    }

    public static FluidStack molaricFluid(String material, boolean moleIs144) {
        return molaricFluid(material, 1, moleIs144);
    }

    public static int getToalComponentAmounts(Material material) {
        int amounts = 0;
        for (MaterialStack stack : material.getMaterialComponents()) {
            amounts += stack.amount;
        }
        return amounts;
    }

    public static int getComponentAmount(Material material, Material component) {
        for (MaterialStack stack : material.getMaterialComponents()) {
            if (stack.material == component) {
                return (int) stack.amount;
            }
        }
        return 0;
    }

    public static @Nullable Material getFromString(String name) {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.getName().equals(name)) {
                return material;
            }
        }
        return null;
    }

    public static @Nullable Material getSolidMaterialFromSolution(Material material) {
        for (MaterialStack stack : material.getMaterialComponents()) {
            if (!stack.material.hasFlag(GJMaterialFlags.SOLVENT)) {
                return stack.material;
            }
        }
        return null;
    }

    public static @Nullable Material getLiquidMaterialFromSolution(Material material) {
        for (MaterialStack stack : material.getMaterialComponents()) {
            if (stack.material.hasFlag(GJMaterialFlags.SOLVENT)) {
                return stack.material;
            }
        }
        return null;
    }

    public static MaterialStack[] convertComponents(Object... args) {
        MaterialStack[] materialStacks = new MaterialStack[args.length / 2];

        for (int i = 0; i < args.length / 2; i++) {
            Object first = args[2 * i];
            Object second = args[2 * i + 1];

            if (first instanceof Material) {
                long quantity;
                // Check if the second argument is a Material (consecutive materials case)
                if (second instanceof Material) {
                    // If consecutive Materials, set quantity to 1
                    quantity = 1;
                } else {
                    // Otherwise, parse the second argument as the quantity
                    quantity = Long.parseLong(String.valueOf(second));
                }

                materialStacks[i] = new MaterialStack((Material) first, quantity);
            } else {
                throw new IllegalArgumentException("Expected Material at position " + (2 * i));
            }
        }

        return materialStacks;
    }

    public static String getFormula(MaterialStack[] stacks) {
        StringBuilder formula = new StringBuilder();
        for (MaterialStack stack : stacks) {
            formula.append("(");
            formula.append(stack.material.getChemicalFormula());
            formula.append(")");
            formula.append(stack.amount);
        }
        return formula.toString();
    }

    public enum Dimensions {

        MOON(-28, "Luna"),
        MARS(-29, "Mars"),
        PHOBOS(-1012, "Phobos"),
        DEIMOS(-1013, "Phobos"),
        CERES(-1007, "Ceres"),
        MERCURY(-1005, "Mercury"),
        VENUS(-31, "Venus"),
        ASTEROIDS(-30, "Asteroid Belt"),
        EUROPA(-1017, "Europa"),
        IO(-1014, "Io"),
        GANYMEDE(-1016, "Ganymede"),
        CALLISTO(-1022, "Callisto"),
        ENCELADUS(-1017, "Enceladus"),
        TITAN(-1018, "Titan"),
        MIRANDA(-1024, "Miranda"),
        OBERON(-1019, "Oberon"),
        PROTEUS(-1020, "Proteus"),
        TRITON(-1021, "Triton"),
        PLUTO(-1008, "Pluto"),
        HAUMEA(-1023, "Haumea"),
        MAKEMAKE(-1011, "Makemake"),
        PROXIMA(-1025, "Proxima"),
        BARNADA(-1030, "Barnada"),
        BARNADAC1(-1031, "Barnada C1"),
        BARNADAC2(-1032, "Barnada C2"),
        // TODO: Tau Ceti Dim ID
        // TODO: Centauri Dim ID
        CHALOS(-2543, "Chalos"),
        DIONA(-2542, "Diona"),
        FRONOS(-2545, "Fronos"),
        NIBIRU(-2544, "Nibiru"),
        KOENTUS(-2642, "Koentus"),
        ERIS(-21, "Eris"),
        IAPETUS(-1511, "Iapetus"),
        KEPLER(-22, "Kepler-22-B"),
        RHEA(-1507, "Rhea"),
        TITANIA(-1510, "Titania"),
        UNDERGARDEN(138, "Undergarden");

        private final int dimension;
        private final String name;

        Dimensions(int dim, String name) {
            this.dimension = dim;
            this.name = name;
        }

        public int getDimension() {
            return dimension;
        }

        public String getName() {
            return name;
        }
    }
}
