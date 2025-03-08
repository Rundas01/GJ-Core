package thegreggening.utils;

import static thegreggening.utils.TGMaterialUtil.*;
import static gregtech.api.GTValues.VA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.MaterialStack;

public class TGRecipeUtil {

    public static List<Integer> calculateMoles(List<Material> inputs, List<Material> outputs) {
        int amountAtoms;

        List<Material> inputAtoms = new ArrayList<>();
        List<Material> outputAtoms = new ArrayList<>();

        if (inputs != null) {
            decomposeAndAdd(inputs, inputAtoms);
        }
        if (outputs != null) {
            decomposeAndAdd(outputs, outputAtoms);
        }

        List<Material> distinctAtoms = Stream.concat(inputAtoms.stream(), outputAtoms.stream()).distinct()
                .collect(Collectors.toList());
        amountAtoms = distinctAtoms.size();

        Double[][] A = new Double[amountAtoms][inputs.size() + outputs.size()];

        for (Material atom : distinctAtoms) {
            for (Material material : inputs) {
                A[distinctAtoms.indexOf(atom)][inputs.indexOf(material)] = (double) getComponentAmount(material, atom);
            }
            for (Material material : outputs) {
                A[distinctAtoms.indexOf(atom)][outputs.indexOf(material) +
                        inputs.size()] = (double) -getComponentAmount(material, atom);
            }
        }

        List<Double> rawCoefficients = Arrays.stream(MathUtil.solveZeroSystem(A)).collect(Collectors.toList());

        int factor = MathUtil.findFactor(rawCoefficients);

        return MathUtil.scaleAndRound(rawCoefficients, factor);
    }

    private static void decomposeAndAdd(List<Material> input, List<Material> output) {
        for (Material material : input) {
            if (material.isElement()) {
                output.add(material);
            } else {
                for (MaterialStack stack : material.getMaterialComponents()) {
                    output.add(stack.material);
                }
            }
        }
    }

    public static void registerMolaricRecipe(RecipeMap<SimpleRecipeBuilder> recipeMap, List<Material> inputs,
                                             List<Material> outputs, List<Material> fluidInputs,
                                             List<Material> fluidOutputs, int machineFactor, int itemFactor,
                                             int fluidFactor, int tier) {
        RecipeBuilder<SimpleRecipeBuilder> builder = getMolaricRecipe(recipeMap, inputs, outputs, fluidInputs,
                fluidOutputs, machineFactor, itemFactor, fluidFactor, tier);
        assert builder != null;
        builder.buildAndRegister();
    }

    public static RecipeBuilder<SimpleRecipeBuilder> getMolaricRecipe(RecipeMap<SimpleRecipeBuilder> recipeMap,
                                                                      List<Material> inputs, List<Material> outputs,
                                                                      List<Material> fluidInputs,
                                                                      List<Material> fluidOutputs, int machineFactor,
                                                                      int itemFactor, int fluidFactor, int tier) {
        if (inputs.size() > recipeMap.getMaxInputs()) {
            TGLog.logger.error("Cannot add recipe to " + recipeMap.getLocalizedName() + ". Too many item inputs!");
            return null;
        }
        if (outputs.size() > recipeMap.getMaxOutputs()) {
            TGLog.logger.error("Cannot add recipe to " + recipeMap.getLocalizedName() + ". Too many item outputs!");
            return null;
        }
        if (fluidInputs.size() > recipeMap.getMaxFluidInputs()) {
            TGLog.logger.error("Cannot add recipe to " + recipeMap.getLocalizedName() + ". Too many fluid inputs!");
            return null;
        }
        if (fluidOutputs.size() > recipeMap.getMaxFluidOutputs()) {
            TGLog.logger.error("Cannot add recipe to " + recipeMap.getLocalizedName() + ". Too many fluid outputs!");
            return null;
        }

        List<Material> totalInputs = Stream.concat(inputs.stream(), fluidInputs.stream()).collect(Collectors.toList());
        List<Material> totalOutputs = Stream.concat(outputs.stream(), fluidOutputs.stream())
                .collect(Collectors.toList());

        List<Integer> moles = calculateMoles(totalInputs, totalOutputs);

        List<ItemStack> inputs2Calculate = new ArrayList<>();
        List<FluidStack> fluidInputs2Calculate = new ArrayList<>();

        RecipeBuilder<SimpleRecipeBuilder> builder = recipeMap.recipeBuilder().EUt(VA[tier]);

        for (int i = 0; i < inputs.size(); i++) {
            ItemStack stack = molaricDust(inputs.get(i), "f", moles.get(i));
            inputs2Calculate.add(stack);
            builder.inputs(stack);
        }

        for (int i = 0; i < fluidInputs.size(); i++) {
            FluidStack stack = molaricFluid(fluidInputs.get(i), moles.get(i + inputs.size()));
            fluidInputs2Calculate.add(stack);
            builder.fluidInputs(stack);
        }

        for (int i = 0; i < outputs.size(); i++) {
            builder.outputs(molaricDust(outputs.get(i), "f", moles.get(i + inputs.size() + fluidInputs.size())));
        }

        for (int i = 0; i < fluidOutputs.size(); i++) {
            builder.fluidOutputs(molaricFluid(fluidOutputs.get(i),
                    moles.get(i + inputs.size() + fluidInputs.size() + outputs.size())));
        }

        builder.duration(
                calculateDuration(inputs2Calculate, fluidInputs2Calculate, machineFactor, itemFactor, fluidFactor));

        return builder;
    }

    public static int calculateDuration(List<ItemStack> inputs, List<FluidStack> fluidInputs, int machineFactor,
                                        int itemFactor, int fluidFactor) {
        double duration = 0;
        double duration2 = 0;
        if (inputs != null && !inputs.isEmpty()) {
            for (ItemStack stack : inputs) {
                duration += getFromItemStack(stack).getMass() * stack.getCount();
            }
            duration *= itemFactor;
            duration /= inputs.size();
        }
        if (fluidInputs != null && !fluidInputs.isEmpty()) {
            for (FluidStack stack : fluidInputs) {
                duration2 += getFromFluidStack(stack).getMass() * stack.amount / 1000.0;
            }
            duration2 *= fluidFactor;
            duration2 /= fluidInputs.size();
        }
        return (int) (duration + duration2) * machineFactor / 2000;
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
}
