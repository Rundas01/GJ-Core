package gregsjourney.utils;

import gregsjourney.api.unification.property.GJMaterialFlags;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.FluidUnifier;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.material.Materials.Oxygen;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GJMaterialUtil {

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

    public static int getTotalAtomAmounts(Material material) {
        if (material.isElement()) {
            return 1;
        }
        if (material.getMaterialComponents() == null) {
            return 1;
        }
        if (material.getMaterialComponents().isEmpty()) {
            return 1;
        }
        int amounts = 0;
        for (MaterialStack stack : material.getMaterialComponents()) {
            amounts += stack.amount * getTotalAtomAmounts(stack.material);
        }
        return amounts;
    }

    public static int getTotalComponentAmounts(Material material) {
        if (material.isElement()) {
            return 1;
        }
        if (material.getMaterialComponents() == null) {
            return 1;
        }
        if (material.getMaterialComponents().isEmpty()) {
            return 1;
        }
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

    public static @Nullable Material getMetalFromMetalOxide(Material material) {
        for (MaterialStack stack : material.getMaterialComponents()) {
            if (stack.material != Oxygen) {
                return stack.material;
            }
        }
        return null;
    }

    public static MaterialStack[] convertComponents(Object... args) {
        int nonMaterials = 0;

        // Count the number of non-Material elements
        for (Object obj : args) {
            if (!(obj instanceof Material)) {
                nonMaterials += 1;
            }
        }

        // Create an array for the MaterialStacks, sized based on the number of materials
        MaterialStack[] materialStacks = new MaterialStack[args.length - nonMaterials];

        // Index for materialStacks
        int stackIndex = 0;

        // Iterate through args array
        for (int i = 0; i < args.length; i++) {
            // If the current element is a Material and either it's the last element or the next element is also a Material
            if (i == args.length - 1 || (args[i] instanceof Material && args[i + 1] instanceof Material)) {
                materialStacks[stackIndex++] = new MaterialStack((Material) args[i], 1L);
            }
            // If the current element is a Material and the next element is an Integer (for the quantity)
            else if (args[i] instanceof Material && args[i + 1] instanceof Integer) {
                materialStacks[stackIndex++] = new MaterialStack((Material) args[i], Long.parseLong(String.valueOf(args[i + 1])));
                i++; // Skip the next element (since it is the quantity we already processed)
            }
            // If neither condition is met, the arguments are malformed
            else {
                throw new RuntimeException("Arguments malformed!");
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
            if (stack.amount > 0) {
                formula.append(stack.amount);
            }
        }
        return formula.toString();
    }

    public static List<ItemStack> molaricDusts(Object... args) {
        int amount = args.length;
        List<ItemStack> dusts = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (i == amount - 1) {
                if (args[i] instanceof String) {
                    dusts.add(molaricDust(getFromString((String) args[i]), "f", 1));
                } else {
                    dusts.add(molaricDust((Material) args[i], "f", 1));
                }
            } else {
                if ((args[i] instanceof Material || args[i] instanceof String) && args[i + 1] instanceof Integer) {
                    if (args[i] instanceof String) {
                        dusts.add(molaricDust(getFromString((String) args[i]), "f", (Integer) args[i + 1]));
                    } else {
                        dusts.add(molaricDust((Material) args[i], "f", (Integer) args[i + 1]));
                    }
                    i++;
                } else {
                    if (args[i] instanceof String) {
                        dusts.add(molaricDust(getFromString((String) args[i]), "f", 1));
                    } else {
                        dusts.add(molaricDust((Material) args[i], "f", 1));
                    }
                }
            }
        }
        return dusts;
    }
    public static ItemStack molaricDust(Material material, String size, int moles) {
        return switch (size) {
            case "f" -> OreDictUnifier.get(dust, material, moles * getTotalAtomAmounts(material));
            case "s" -> OreDictUnifier.get(dustSmall, material, moles * getTotalAtomAmounts(material));
            case "t" -> OreDictUnifier.get(dustTiny, material, moles * getTotalAtomAmounts(material));
            default -> ItemStack.EMPTY;
        };
    }

    public static ItemStack molaricDust(String material, String size, int moles) {
        return molaricDust(getFromString(material), size, moles);
    }

    public static ItemStack molaricDust(Material material, String size) {
        return molaricDust(material, size, 1);
    }

    public static ItemStack molaricDust(String material, String size) {
        return molaricDust(getFromString(material), size, 1);
    }

    public static ItemStack molaricDust(Material material, int moles) {
        return molaricDust(material, "f", moles);
    }

    public static ItemStack molaricDust(String material, int moles) {
        return molaricDust(getFromString(material), "f", moles);
    }

    public static ItemStack molaricDust(Material material) {
        return molaricDust(material, "f", 1);
    }

    public static ItemStack molaricDust(String material) {
        return molaricDust(getFromString(material), "f", 1);
    }

    private static ItemStack metaItem(OrePrefix prefix, Material material, int amount) {
        return OreDictUnifier.get(prefix, material, amount);
    }

    public static ItemStack plate(String material, int amount) {
        return metaItem(plate, getFromString(material), amount);
    }
    public static ItemStack plate(String material) {
        return plate(material, 1);
    }
    public static ItemStack gear(String material, int amount) {
        return metaItem(gear, getFromString(material), amount);
    }
    public static ItemStack gear(String material) {
        return gear(material, 1);
    }
    public static ItemStack smallGear(String material, int amount) {
        return metaItem(gearSmall, getFromString(material), amount);
    }
    public static ItemStack smallGear(String material) {
        return smallGear(material, 1);
    }
    public static ItemStack rod(String material, int amount) {
        return metaItem(stick, getFromString(material), amount);
    }
    public static ItemStack rod(String material) {
        return rod(material, 1);
    }
    public static ItemStack longRod(String material, int amount) {
        return metaItem(stickLong, getFromString(material), amount);
    }
    public static ItemStack longRod(String material) {
        return longRod(material, 1);
    }
    public static ItemStack bolt(String material, int amount) {
        return metaItem(bolt, getFromString(material), amount);
    }
    public static ItemStack bolt(String material) {
        return bolt(material, 1);
    }
    public static ItemStack screw(String material, int amount) {
        return metaItem(screw, getFromString(material), amount);
    }
    public static ItemStack screw(String material) {
        return screw(material, 1);
    }
    public static ItemStack spring(String material, int amount) {
        return metaItem(spring, getFromString(material), amount);
    }
    public static ItemStack spring(String material) {
        return spring(material, 1);
    }
    public static ItemStack smallSpring(String material, int amount) {
        return metaItem(springSmall, getFromString(material), amount);
    }
    public static ItemStack smallSpring(String material) {
        return smallSpring(material, 1);
    }
    public static ItemStack frame(String material, int amount) {
        return metaItem(frameGt, getFromString(material), amount);
    }
    public static ItemStack frame(String material) {
        return frame(material, 1);
    }
    public static ItemStack foil(String material, int amount) {
        return metaItem(foil, getFromString(material), amount);
    }
    public static ItemStack foil(String material) {
        return foil(material, 1);
    }
    public static ItemStack ring(String material, int amount) {
        return metaItem(ring, getFromString(material), amount);
    }
    public static ItemStack ring(String material) {
        return ring(material, 1);
    }
    public static ItemStack ingot(String material, int amount) {
        return metaItem(ingot, getFromString(material), amount);
    }
    public static ItemStack ingot(String material) {
        return ingot(material, 1);
    }

    public static ItemStack molaricIngot(String material, int moles) {
        return metaItem(ingot, getFromString(material), moles);
    }
    public static ItemStack molaricIngot(String material) {
        return ingot(material, 1);
    }

    public static ItemStack dust(String material, int amount) {
        return OreDictUnifier.get(dust, getFromString(material), amount);
    }
    public static ItemStack dust(String material) {
        return dust(material, 1);
    }
    public static ItemStack cable(String material, int thickness, int amount) {
        Material mat = getFromString(material);
        return switch (thickness) {
            case 1 -> metaItem(cableGtSingle, mat, amount);
            case 2 -> metaItem(cableGtDouble, mat, amount);
            case 4 -> metaItem(cableGtQuadruple, mat, amount);
            case 8 -> metaItem(cableGtOctal, mat, amount);
            case 16 -> metaItem(cableGtHex, mat, amount);
            default -> ItemStack.EMPTY;
        };
    }

    public static ItemStack wire(String material, int thickness, int amount) {
        Material mat = getFromString(material);
        return switch (thickness) {
            case 1 -> metaItem(wireGtSingle, mat, amount);
            case 2 -> metaItem(wireGtDouble, mat, amount);
            case 4 -> metaItem(wireGtQuadruple, mat, amount);
            case 8 -> metaItem(wireGtOctal, mat, amount);
            case 16 -> metaItem(wireGtHex, mat, amount);
            default -> ItemStack.EMPTY;
        };
    }
    public static ItemStack cable(String material, int thickness) { return cable(material, thickness, 1); }
    public static ItemStack wire(String material, int thickness) {
        return wire(material, thickness, 1);
    }
    public static ItemStack fineWire(String material, int amount) {
        return metaItem(wireFine, getFromString(material), amount);
    }
    public static ItemStack fineWire(String material) {
        return fineWire(material, 1);
    }
    public static ItemStack gem(String material, int amount) {
        return metaItem(gem, getFromString(material), amount);
    }
    public static ItemStack gem(String material) {
        return gem(material, 1);
    }

    public static List<FluidStack> molaricFluids(Object... args) {
        int amount = args.length;
        List<FluidStack> fluids = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            if (i == amount - 1) {
                // Only one argument left; check if it's String or Material
                if (args[i] instanceof String) {
                    fluids.add(molaricFluid(getFromString((String) args[i]), 1));
                } else if (args[i] instanceof Material) {
                    fluids.add(molaricFluid((Material) args[i], 1));
                } else {
                    throw new IllegalArgumentException("Unexpected argument type at position " + i + ": " + args[i].getClass().getName());
                }
            } else {
                // More than one argument left
                if ((args[i] instanceof Material || args[i] instanceof String) &&
                        (args[i + 1] instanceof Integer || args[i + 1] instanceof Double || args[i + 1] instanceof BigDecimal)) {

                    // Handle string case
                    if (args[i] instanceof String) {
                        if (args[i + 1] instanceof Double) {
                            fluids.add(molaricFluid(getFromString((String) args[i]), (Double) args[i + 1]));
                        } else if (args[i + 1] instanceof BigDecimal) {
                            fluids.add(molaricFluid(getFromString((String) args[i]), ((BigDecimal) args[i + 1]).doubleValue()));
                        } else {
                            fluids.add(molaricFluid(getFromString((String) args[i]), (Integer) args[i + 1]));
                        }
                        // Handle Material case
                    } else if (args[i] instanceof Material) {
                        if (args[i + 1] instanceof Double) {
                            fluids.add(molaricFluid((Material) args[i], (Double) args[i + 1]));
                        } else if (args[i + 1] instanceof BigDecimal) {
                            fluids.add(molaricFluid((Material) args[i], ((BigDecimal) args[i + 1]).doubleValue()));
                        } else {
                            fluids.add(molaricFluid((Material) args[i], (Integer) args[i + 1]));
                        }
                    }
                    // Skip the next argument since it is the quantity
                    i++;
                } else {
                    // No quantity provided for the current Material or String
                    if (args[i] instanceof String) {
                        fluids.add(molaricFluid(getFromString((String) args[i]), 1));
                    } else if (args[i] instanceof Material) {
                        fluids.add(molaricFluid((Material) args[i], 1));
                    } else {
                        throw new IllegalArgumentException("Unexpected argument type at position " + i + ": " + args[i].getClass().getName());
                    }
                }
            }
        }
        return fluids;
    }

    public static FluidStack molaricFluid(Material material, double moles) {
        return material.getFluid((int) (1000 * moles));
    }

    public static FluidStack molaricFluid(String material, double moles) {
        return molaricFluid(getFromString(material), moles);
    }

    public static FluidStack molaricFluid(Material material, int moles, boolean moleIs144) {
        if (moleIs144) {
            return material.getFluid(144 * moles);
        }
        return material.getFluid(1000 * moles);
    }

    public static FluidStack molaricFluid(String material, int moles, boolean moleIs144) {
        return molaricFluid(getFromString(material), moles, moleIs144);
    }

    public static FluidStack molaricFluid(String material, boolean moleIs144) {
        return molaricFluid(getFromString(material), 1, moleIs144);
    }

    public static FluidStack molaricFluid(Material material, int moles) {
        return material.getFluid(1000 * moles);
    }

    public static FluidStack molaricFluid(String material, int moles) {
        return molaricFluid(getFromString(material), moles);
    }

    public static FluidStack molaricFluid(Material material) {
        return material.getFluid(1000);
    }

    public static FluidStack molaricFluid(String material) {
        return molaricFluid(getFromString(material), 1, false);
    }

    public static Material getFromItemStack(ItemStack stack) {
        return OreDictUnifier.getMaterial(stack).material;
    }

    public static Material getFromFluidStack(FluidStack stack) {return FluidUnifier.getMaterialFromFluid(stack.getFluid());}
}
