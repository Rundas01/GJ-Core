package thegreggening.utils;

import static gregtech.api.unification.material.Materials.*;

import java.util.*;
import java.util.function.Predicate;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.MaterialStack;

import thegreggening.TheGreggening;

public final class TGUtil {

    private TGUtil() {}

    public static @NotNull ResourceLocation tgId(@NotNull String path) {
        return new ResourceLocation(TheGreggening.MODID, path);
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

    public static List<List<Material>> generateCombinations(List<List<List<Material>>> nestedList) {
        List<List<Material>> result = new ArrayList<>();

        for (List<List<Material>> subList : nestedList) {
            // Compute Cartesian product for all lists in the current sublist
            List<List<Material>> combinations = cartesianProduct(subList);
            result.addAll(combinations);
        }

        return result;
    }

    private static List<List<Material>> cartesianProduct(List<List<Material>> lists) {
        List<List<Material>> result = new ArrayList<>();

        // Start with an empty combination
        result.add(new ArrayList<>());

        // For each list, build up the combinations
        for (List<Material> currentList : lists) {
            List<List<Material>> newResult = new ArrayList<>();

            for (List<Material> combination : result) {
                for (Material element : currentList) {
                    // Create a new combination by adding the current element
                    List<Material> newCombination = new ArrayList<>(combination);
                    newCombination.add(element);
                    newResult.add(newCombination);
                }
            }
            result = newResult; // Update result with the newly formed combinations
        }

        return result;
    }

    public static int getTotalFluidAmount(FluidTankList tankList) {
        int amount = 0;
        for (IMultipleTankHandler.MultiFluidTankEntry tank : tankList) {
            amount += tank.getFluidAmount();
        }
        return amount;
    }

    // 3 Na + (HNO3)(HCl)3 = 3 (NaCl)(H2O) + NO - H2O
    // 3 Cu + 2 (HNO3)(HCl)3 = 3 (CuCl2)(H2O) + 2 NO + H2O
    // Al + (HNO3)(HCl)3 = (AlCl3)(H2O) + NO + H2O
    // 3 Sn + 4 (HNO3)(HCl)3 = 3 (SnCl4)(H2O) + 4 NO + 5 H2O
    // 3 V + 5 (HNO3)(HCl)3 = 3 (VCl5)(H2O) + 5 NO + 7 H2O
    // U + 2 (HNO3)(HCl)3 = (UCl6)(H2O) + 2 NO + 3 H2O

    public static Map<List<Material>, Integer> aquaRegiaOxidations = new HashMap<>();
    public static Map<Integer, Tuple<Integer, List<Tuple<Material, Integer>>>> aquaRegiaComponents = new HashMap<>();

    public static void setupAquaRegiaOxidations() {
        aquaRegiaOxidations.put(Arrays.asList(Sodium, Lithium, Potassium, Rubidium, Caesium), 1);
        aquaRegiaOxidations.put(Arrays.asList(Beryllium, Magnesium, Calcium, Strontium, Barium, Lead, Copper, Silver,
                Zinc, Cadmium, Mercury, Nickel, Palladium, Platinum, Cobalt, Manganese), 2);
        aquaRegiaOxidations.put(Arrays.asList(Aluminium, Gallium, Indium, Thallium, Scandium, Yttrium, Lanthanum, Gold,
                Rhodium, Iron, Rhenium, Chrome), 3);
        aquaRegiaOxidations.put(Arrays.asList(Germanium, Tin, Titanium, Zirconium, Hafnium, Iridium, Ruthenium, Osmium),
                4);
        aquaRegiaOxidations.put(Arrays.asList(Vanadium, Niobium, Tantalum), 5);
        aquaRegiaOxidations.put(Arrays.asList(Uranium238, Molybdenum, Tungsten), 6);
    }

    public static void setupAquaRegiaComponents() {
        aquaRegiaComponents.put(1, new Tuple<>(3, Arrays.asList(new Tuple<>(NitricOxide, 1), new Tuple<>(Water, -1))));
        aquaRegiaComponents.put(2, new Tuple<>(3, Arrays.asList(new Tuple<>(NitricOxide, 2), new Tuple<>(Water, 1))));
        aquaRegiaComponents.put(3, new Tuple<>(1, Arrays.asList(new Tuple<>(NitricOxide, 1), new Tuple<>(Water, 1))));
        aquaRegiaComponents.put(4, new Tuple<>(3, Arrays.asList(new Tuple<>(NitricOxide, 4), new Tuple<>(Water, 5))));
        aquaRegiaComponents.put(5, new Tuple<>(3, Arrays.asList(new Tuple<>(NitricOxide, 5), new Tuple<>(Water, 7))));
        aquaRegiaComponents.put(6, new Tuple<>(1, Arrays.asList(new Tuple<>(NitricOxide, 2), new Tuple<>(Water, 3))));
    }

    public static boolean all(List<?> list, Predicate<Object> condition) {
        for (Object element : list) {
            if (!condition.test(element)) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(List<?> list, Predicate<Object> condition) {
        for (Object element : list) {
            if (condition.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasOrePrefix(ItemStack stack, OrePrefix prefix) {
        return OreDictUnifier.getPrefix(stack) == prefix;
    }

    public enum Dimensions {

        MOON(-28, "Luna"),
        MARS(-29, "Mars"),
        PHOBOS(-1012, "Phobos"),
        DEIMOS(-1013, "Deimos"),
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
