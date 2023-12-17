package gregicality.legacy.integration.forestry.recipe;

import com.google.common.collect.ImmutableMap;
import forestry.api.recipes.ICentrifugeRecipe;
import forestry.api.recipes.RecipeManagers;
import forestry.core.ModuleCore;
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import gregicality.legacy.integration.forestry.GTCombType;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.integration.IntegrationModule;
import gregtech.integration.forestry.ForestryConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;

import static gregicality.legacy.integration.forestry.ForestryModule.combs;
import static gregtech.integration.forestry.ForestryUtil.apicultureEnabled;

public class GCYLRCombRecipes {

    public static void initForestryCombs() {
        if (!ModuleFactory.machineEnabled(MachineUIDs.CENTRIFUGE)) return;
        for (ICentrifugeRecipe recipe : RecipeManagers.centrifugeManager.recipes()) {
            ItemStack combStack = recipe.getInput();
            if (combStack.getItem().getRegistryName().getNamespace().equals(GTValues.MODID)) continue;
            RecipeBuilder<?> builder = RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                    .inputs(combStack.copy())
                    .duration(Voltage.ULV.getCentrifugeTime()).EUt(Voltage.ULV.getCentrifugeEnergy());

            boolean hadSomeOutput = false;
            for (Map.Entry<ItemStack, Float> entry : recipe.getAllProducts().entrySet()) {
                hadSomeOutput = true;
                if (entry.getValue() >= 1.0f) {
                    builder.outputs(entry.getKey());
                } else {
                    builder.chancedOutput(entry.getKey(), Math.max(1, Math.round(entry.getValue() * 10000)), 0);
                }
            }
            if (hadSomeOutput) {
                // For some reason Forestry occasionally has recipes that have no outputs at all, which will
                // cause us to error. Discard these if we come across them.
                builder.buildAndRegister();
            }
        }
    }

    // forgive me for the code i am about to write
    public static void initGTCombs() {
        addProcessGT(GTCombType.STELLITE100, new Material[]{GCYMMaterials.Stellite100}, Voltage.LV);
    }

    /**
     * Currently used separately for STEEL, GOLD, MOLYBDENUM, PLUTONIUM
     **/
    private static void addChemicalProcess(GTCombType comb, Material inMaterial, Material outMaterial, Voltage volt) {
        if (OreDictUnifier.get(OrePrefix.crushedPurified, outMaterial, 4).isEmpty() || OreDictUnifier.get(OrePrefix.crushed, inMaterial).isEmpty() || inMaterial.hasFlag(MaterialFlags.DISABLE_ORE_BLOCK))
            return;

        RecipeBuilder<?> builder = RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(GTUtility.copy(9, getCombStack(comb)))
                .input(OrePrefix.crushed, inMaterial)
                .fluidInputs(volt.getFluid())
                .output(OrePrefix.crushedPurified, outMaterial, 4)
                .duration(volt.getChemicalTime())
                .EUt(volt.getChemicalEnergy());

        OreProperty property = inMaterial.getProperty(PropertyKey.ORE);
        if (property != null && !property.getOreByProducts().isEmpty()) {
            Material byproduct = property.getOreByProducts().get(0);
            if (byproduct != null && byproduct.hasProperty(PropertyKey.FLUID)) {
                builder.fluidOutputs(byproduct.getFluid(GTValues.L));
            }
        }

        if (volt.compareTo(Voltage.IV) > 0) {
            builder.cleanroom(CleanroomType.CLEANROOM);
        }
        builder.buildAndRegister();
    }

    /**
     * Currently only used separately for GTCombType.MOLYBDENUM
     *
     * @param circuitNumber should not conflict with addProcessGT
     **/
    private static void addAutoclaveProcess(GTCombType comb, Material material, Voltage volt, int circuitNumber) {
        if (OreDictUnifier.get(OrePrefix.crushedPurified, material, 4).isEmpty()) return;

        RecipeBuilder<?> builder = RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTUtility.copy(9, getCombStack(comb)))
                .circuitMeta(circuitNumber)
                .fluidInputs(Materials.Mutagen.getFluid((int) Math.max(1, material.getMass() + volt.getMutagenAmount())))
                .output(OrePrefix.crushedPurified, material, 4)
                .duration((int) (material.getMass() * 128))
                .EUt(volt.getAutoclaveEnergy());

        if (volt.compareTo(Voltage.HV) > 0) {
            builder.cleanroom(CleanroomType.CLEANROOM);
        }
        builder.buildAndRegister();
    }

    /**
     * this only adds Chemical and AutoClave process.
     * If you need Centrifuge recipe. use  addCentrifugeToMaterial or addCentrifugeToItemStack
     *
     * @param volt     This determine the required Tier of process for this recipes. This decide the required aEU/t, progress time, required additional UU-Matter, requirement of cleanRoom, needed fluid stack for Chemical.
     * @param material result of Material that should be generated by this process.
     **/
    private static void addProcessGT(GTCombType comb, Material[] material, Voltage volt) {
        for (int i = 0; i < material.length; i++) {
            addChemicalProcess(comb, material[i], material[i], volt);
            addAutoclaveProcess(comb, material[i], volt, i + 1);
        }
    }

    /**
     * this method only adds Centrifuge based on Material. If volt is lower than MV than it will also adds forestry centrifuge recipe.
     *
     * @param comb      BeeComb
     * @param material  resulting Material of processing. must be less than or equal to 9.
     * @param chance    chance to get result, 10000 == 100%
     * @param volt      required Voltage Tier for this recipe, this also affect the duration, amount of UU-Matter, and needed liquid type and amount for chemical reactor
     * @param stackSize This parameter can be null, in that case stack size will be just 1. This handle the stackSize of the resulting Item, and Also the Type of Item. if this value is multiple of 9, than related Material output will be dust, if this value is multiple of 4 than output will be Small dust, else the output will be Tiny dust
     * @param beeWax    if this is null, than the comb will product default Bee wax. But if aMaterial is more than 5, beeWax will be ignored in Gregtech Centrifuge.
     * @param waxChance have same format like "chance"
     **/
    private static void addCentrifugeToMaterial(GTCombType comb, Material[] material, int[] chance, int[] stackSize, Voltage volt, ItemStack beeWax, int waxChance) {
        addCentrifugeToMaterial(comb, material, chance, stackSize, volt, volt.getCentrifugeTime(), beeWax, waxChance);
    }

    private static void addCentrifugeToMaterial(GTCombType comb, Material[] material, int[] chance, int[] stackSize, Voltage volt, int duration, ItemStack beeWax, int waxChance) {
        ItemStack[] output = new ItemStack[material.length + 1];
        stackSize = Arrays.copyOf(stackSize, material.length);
        chance = Arrays.copyOf(chance, output.length);
        chance[chance.length - 1] = waxChance;
        for (int i = 0; i < material.length; i++) {
            if (chance[i] == 0) continue;

            if (Math.max(1, stackSize[i]) % 9 == 0) {
                output[i] = OreDictUnifier.get(OrePrefix.dust, material[i], Math.max(1, stackSize[i]) / 9);
            } else if (Math.max(1, stackSize[i]) % 4 == 0) {
                output[i] = OreDictUnifier.get(OrePrefix.dustSmall, material[i], Math.max(1, stackSize[i]) / 4);
            } else {
                output[i] = OreDictUnifier.get(OrePrefix.dustTiny, material[i], Math.max(1, stackSize[i]));
            }
        }
        if (beeWax != ItemStack.EMPTY) {
            output[output.length - 1] = beeWax;
        } else {
            output[output.length - 1] = ModuleCore.getItems().beeswax.getItemStack();
        }

        addCentrifugeToItemStack(comb, output, chance, volt, duration);
    }

    /**
     * @param volt required Tier of system. If it's lower than MV, it will also add forestry centrifuge.
     * @param item must be less than or equal to 9.
     **/
    private static void addCentrifugeToItemStack(GTCombType comb, ItemStack[] item, int[] chance, Voltage volt) {
        addCentrifugeToItemStack(comb, item, chance, volt, volt.getCentrifugeTime());
    }

    private static void addCentrifugeToItemStack(GTCombType comb, ItemStack[] item, int[] chance, Voltage volt, int duration) {
        ItemStack combStack = getCombStack(comb);

        // Start of the Forestry Map
        ImmutableMap.Builder<ItemStack, Float> product = new ImmutableMap.Builder<>();
        // Start of the GregTech Map
        RecipeBuilder<?> builder = RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(combStack)
                .duration(duration)
                .EUt(volt.getCentrifugeEnergy());

        int numGTOutputs = 0;
        for (int i = 0; i < item.length; i++) {
            if (item[i] == null || item[i] == ItemStack.EMPTY) continue;
            // Add to Forestry Map
            product.put(item[i], chance[i] / 10000.0f);
            // Add to GregTech Map
            if (numGTOutputs < RecipeMaps.CENTRIFUGE_RECIPES.getMaxOutputs()) {
                if (chance[i] >= 10000) {
                    builder.outputs(item[i]);
                } else {
                    builder.chancedOutput(item[i], chance[i], 0);
                }
                numGTOutputs++;
            }
        }

        // Finalize Forestry Map
        if (volt.compareTo(Voltage.MV) < 0 || !ForestryConfig.harderForestryRecipes) {
            if (ModuleFactory.machineEnabled(MachineUIDs.CENTRIFUGE)) {
                RecipeManagers.centrifugeManager.addRecipe(40, combStack, product.build());
            }
        }
        // Finalize GregTech Map
        builder.buildAndRegister();
    }

    private enum Voltage {
        ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV, UHV, UEV, UIV, UXV, OPV, MAX;

        public int getVoltage() {
            return (int) GTValues.V[ordinal()];
        }

        public int getChemicalEnergy() {
            return getVoltage() * 3 / 4;
        }

        public int getAutoclaveEnergy() {
            return (int) ((getVoltage() * 3 / 4) * Math.max(1, Math.pow(2, 5 - ordinal())));
        }

        public int getCentrifugeEnergy() {
            return this == Voltage.ULV ? 5 : (getVoltage() / 16) * 15;
        }

        public int getChemicalTime() {
            return 64 + ordinal() * 32;
        }

        public int getCentrifugeTime() {
            if (ForestryConfig.harderForestryRecipes) {
                return 128 * (Math.max(1, ordinal()));
            } else {
                return 96 + ordinal() * 32;
            }
        }

        public FluidStack getFluid() {
            if (this.compareTo(Voltage.MV) < 0) {
                return Materials.Water.getFluid((this.compareTo(Voltage.ULV) > 0) ? 1000 : 500);
            } else if (this.compareTo(Voltage.HV) < 0) {
                return Materials.DistilledWater.getFluid(1000);
            } else if (this.compareTo(Voltage.EV) < 0) {
                return Materials.SulfuricAcid.getFluid(GTValues.L);
            } else if (this.compareTo(Voltage.IV) < 0) {
                return Materials.HydrochloricAcid.getFluid(GTValues.L);
            } else if (this.compareTo(Voltage.LUV) < 0) {
                return Materials.HydrofluoricAcid.getFluid((int) (Math.pow(2, this.compareTo(Voltage.HV)) * GTValues.L));
            } else {
                return Materials.FluoroantimonicAcid.getFluid((int) (Math.pow(2, this.compareTo(Voltage.LUV)) * GTValues.L));
            }
        }

        public int getMutagenAmount() {
            return 9 * ((this.compareTo(Voltage.MV) < 0) ? 10 : 10 * this.compareTo(Voltage.MV));
        }
    }

    public static @NotNull ItemStack getCombStack(@NotNull GTCombType type, int amount) {
        if (!ForestryConfig.enableGTBees) {
            IntegrationModule.logger.error("Tried to get GregTech Comb stack, but GregTech Bees config is not enabled!");
            return ItemStack.EMPTY;
        } else if (!apicultureEnabled()) {
            IntegrationModule.logger.error("Tried to get GregTech Comb stack, but Apiculture module is not enabled!");
            return ItemStack.EMPTY;
        } else {
            return new ItemStack(combs, amount, type.ordinal());
        }
    }

    public static @NotNull ItemStack getCombStack(@NotNull GTCombType type) {
        return getCombStack(type, 1);
    }

}
