package gregsjourney.loaders.recipe;

import gregsjourney.api.recipe.NoEnergyRecipeBuilder;
import gregsjourney.api.unification.property.*;
import gregsjourney.utils.GJLog;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.builders.UniversalDistillationRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static gregsjourney.api.unification.material.GJMiscMaterials.MethylIsobutylCarbinol;
import static gregsjourney.utils.GJMaterialUtil.*;
import static gregsjourney.utils.GJNuclearUtil.*;
import static gregsjourney.utils.GJRecipeUtil.calculateDuration;
import static gregsjourney.common.recipe.GJRecipeMaps.*;
import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;
import static gregtech.api.unification.ore.OrePrefix.*;

public class AutogeneratedRecipes {
    public static void init() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.INGOT) && material.hasFlag(MORTAR_GRINDABLE)) {
                registerNuggetMortaringRecipe(material);
            }
            if (material.hasFlag(GJMaterialFlags.CRYSTALLIZABLE) && material.hasProperty(GJPropertyKeys.CRYSTALLIZATION_PROPERTY)) {
                addCrystallizationAndDehydrationRecipes(material);
            }
            if (material.hasFlag(GJMaterialFlags.DISTILLABLE) && material.hasProperty(GJPropertyKeys.DISTILLATION_PROPERTY)) {
                addDistillationRecipes(material);
            }
            if (material.hasFlag(GJMaterialFlags.ORE_LEACH) && material.hasProperty(GJPropertyKeys.LEACH_PROPERTY)) {
                addLeachProcessingRecipes(material);
            }
            if (material.hasFlag(GJMaterialFlags.ORE_SLAG)) {
                addSlagProcessingRecipe(material);
            }
            if (material.hasFlag(GJMaterialFlags.ROASTED_ORE) && material.hasProperty(GJPropertyKeys.ROASTED_ORE_PROPERTY)) {
                addRoastedOreProcessingRecipe(material);
            }
            if (material.hasFlag(GJMaterialFlags.AUTOGENERATED_SOLUTION_MIXING_RECIPE)) {
                addSolutionMixingRecipe(material);
            }
            if (material.hasProperty(PropertyKey.ORE) && !material.hasFlag(NO_SMELTING) && !material.hasFlag(DISABLE_ORE_BLOCK) && !material.hasProperty(PropertyKey.BLAST)) {
                addSmeltingRecipes(material);
            }
            if (material.hasFlag(GJMaterialFlags.AUTOGENERATED_ORE_TAILING_RECIPE) && material.hasProperty(GJPropertyKeys.ORE_TAILING_PROPERTY)) {
                addTailingSiftingRecipe(material);
            }
            if (material.hasProperty(GJPropertyKeys.COOLANT_PROPERTY)) {
                addHeatExchangerRecipe(material);
            }
        }
        for (Material material : nuclearMaterials) {
            addDecayGeneratorRecipes(material);
            addIsotopicStabilizerRecipe(material);
            addDecayHastenerRecipes(material);
            if (material.hasFlag(GJMaterialFlags.FISSIONABLE)) {
                addFissionRecipes(material);
            }
            if (getBreedingResultMaterial(material) != null) {
                addBreederRecipes(material);
            }
        }
    }

    private static void addHeatExchangerRecipe(Material material) {
        CoolantProperty property = material.getProperty(GJPropertyKeys.COOLANT_PROPERTY);
        int conversion = property.conversion();

        HEAT_EXCHANGER_RECIPES.recipeBuilder()
                .fluidInputs(property.hotCoolant().getFluid(conversion), Water.getFluid(conversion))
                .fluidOutputs(material.getFluid(conversion), Steam.getFluid(conversion * 160))
                .duration((int) property.hotCoolant().getMass() * 10).buildAndRegister();

        HEAT_EXCHANGER_RECIPES.recipeBuilder()
                .fluidInputs(property.hotCoolant().getFluid(conversion), DistilledWater.getFluid(conversion))
                .fluidOutputs(material.getFluid(conversion), Steam.getFluid(conversion * 160))
                .duration((int) property.hotCoolant().getMass()).buildAndRegister();
    }

    private static void addFissionRecipes(Material material) {
        int i = 1;
        List<Tuple<List<Material>,Double>> allReactions = getFissionReactions(material);
        for (Tuple<List<Material>,Double> reaction : allReactions) {
            RecipeBuilder<NoEnergyRecipeBuilder> builder = FISSION_REACTOR_RECIPES.recipeBuilder()
                    .circuitMeta(i)
                    .input(ingot, material)
                    .duration(4 * reaction.getSecond().intValue());
            for (Material m : reaction.getFirst()) {
                if (m.hasProperty(PropertyKey.INGOT)) {
                    builder.output(ingot, m);
                } else {
                    builder.fluidOutputs(m.getFluid(1000));
                }

            }
            builder.buildAndRegister();
            i++;
        }
    }

    private static void addBreederRecipes(Material breederMaterial) {
        int i;
        for (Material breedingMaterial : nuclearMaterials) {
            if (!breedingMaterial.hasFlag(GJMaterialFlags.FISSIONABLE)) {
                continue;
            }
            i = 1;
            List<Tuple<List<Material>,Double>> allReactions = getFissionReactions(breedingMaterial);
            for (Tuple<List<Material>,Double> reaction : allReactions) {
                RecipeBuilder<NoEnergyRecipeBuilder> builder = BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .circuitMeta(i)
                        .input(ingot, breederMaterial)
                        .input(ingot, breedingMaterial)
                        .output(ingot, getBreedingResultMaterial(breederMaterial))
                        .duration(reaction.getSecond().intValue());
                for (Material m : reaction.getFirst()) {
                    if (m.hasProperty(PropertyKey.INGOT)) {
                        builder.output(ingot, m);
                    } else {
                        builder.fluidOutputs(m.getFluid(1000));
                    }
                }
                builder.buildAndRegister();
                i++;
            }
        }
    }

    private static void addDecayGeneratorRecipes(Material material) {
        NuclearProperty property = material.getProperty(GJPropertyKeys.NUCLEAR_PROPERTY);
        if (material.hasProperty(PropertyKey.INGOT)) {
            if (property.alphaMaterial() != null) {
                if (property.alphaMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(1)
                            .output(ingot, property.alphaMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.alphaEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(1)
                            .fluidOutputs(property.alphaMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.alphaEnergy() * property.tier())).buildAndRegister();
                }
            }
            if (property.betaPlusMaterial() != null) {
                if (property.betaPlusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(2)
                            .output(ingot, property.betaPlusMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.betaPlusEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(2)
                            .fluidOutputs(property.betaPlusMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.betaPlusEnergy() * property.tier())).buildAndRegister();
                }
            }
            if (property.betaMinusMaterial() != null) {
                if (property.betaMinusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(3)
                            .output(ingot, property.betaMinusMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.betaMinusEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(3)
                            .fluidOutputs(property.betaMinusMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.betaMinusEnergy() * property.tier())).buildAndRegister();
                }
            }
        } else {
            if (property.alphaMaterial() != null) {
                if (property.alphaMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(1)
                            .output(ingot, property.alphaMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.alphaEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(1)
                            .fluidOutputs(property.alphaMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.alphaEnergy() * property.tier())).buildAndRegister();
                }
            }
            if (property.betaPlusMaterial() != null) {
                if (property.betaPlusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(2)
                            .output(ingot, property.betaPlusMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.betaPlusEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(2)
                            .fluidOutputs(property.betaPlusMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.betaPlusEnergy() * property.tier())).buildAndRegister();
                }
            }
            if (property.betaMinusMaterial() != null) {
                if (property.betaMinusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(3)
                            .output(ingot, property.betaMinusMaterial())
                            .EUt(VA[LV]).duration((int) (100 * property.betaMinusEnergy() * property.tier())).buildAndRegister();
                } else {
                    DECAY_GENERATOR_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(3)
                            .fluidOutputs(property.betaMinusMaterial().getFluid(1000))
                            .EUt(VA[LV]).duration((int) (100 * property.betaMinusEnergy() * property.tier())).buildAndRegister();
                }
            }
        }
    }

    private static void addDecayHastenerRecipes(Material material) {
        NuclearProperty property = material.getProperty(GJPropertyKeys.NUCLEAR_PROPERTY);
        if (material.hasProperty(PropertyKey.INGOT)) {
            if (property.alphaMaterial() != null) {
                if (property.alphaMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(1)
                            .output(ingot, property.alphaMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.alphaEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(1)
                            .fluidOutputs(property.alphaMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.alphaEnergy())).buildAndRegister();
                }
            }
            if (property.betaPlusMaterial() != null) {
                if (property.betaPlusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(2)
                            .output(ingot, property.betaPlusMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 *property.betaPlusEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(2)
                            .fluidOutputs(property.betaPlusMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaPlusEnergy())).buildAndRegister();
                }
            }
            if (property.betaMinusMaterial() != null) {
                if (property.betaMinusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(3)
                            .output(ingot, property.betaMinusMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaMinusEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .input(ingot, material)
                            .circuitMeta(3)
                            .fluidOutputs(property.betaMinusMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaMinusEnergy())).buildAndRegister();
                }
            }
        } else {
            if (property.alphaMaterial() != null) {
                if (property.alphaMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(1)
                            .output(ingot, property.alphaMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.alphaEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(1)
                            .fluidOutputs(property.alphaMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.alphaEnergy())).buildAndRegister();
                }
            }
            if (property.betaPlusMaterial() != null) {
                if (property.betaPlusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(2)
                            .output(ingot, property.betaPlusMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaPlusEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(2)
                            .fluidOutputs(property.betaPlusMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaPlusEnergy())).buildAndRegister();
                }
            }
            if (property.betaMinusMaterial() != null) {
                if (property.betaMinusMaterial().hasProperty(PropertyKey.INGOT)) {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(3)
                            .output(ingot, property.betaMinusMaterial())
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaMinusEnergy())).buildAndRegister();
                } else {
                    DECAY_HASTENER_RECIPES.recipeBuilder()
                            .fluidInputs(material.getFluid(1000))
                            .circuitMeta(3)
                            .fluidOutputs(property.betaMinusMaterial().getFluid(1000))
                            .EUt(VA[property.tier()]).duration((int) (1000 * property.betaMinusEnergy())).buildAndRegister();
                }
            }
        }
    }

    private static void addIsotopicStabilizerRecipe(Material material) {
        NuclearProperty property = material.getProperty(GJPropertyKeys.NUCLEAR_PROPERTY);
        Material stableIsotope = property.stableIsotope();
        if (stableIsotope == null) {
            return;
        }
        long freeNeutrons = Math.abs(material.getNeutrons() - stableIsotope.getNeutrons());
        if (material.hasProperty(PropertyKey.INGOT)) {
            if (stableIsotope.hasProperty(PropertyKey.INGOT)) {
                ISOTOPIC_STABILIZER_RECIPES.recipeBuilder()
                        .input(ingot, material)
                        .output(ingot, stableIsotope)
                        .EUt(VA[property.tier()]).duration((int) (material.getMass() * (freeNeutrons + 1))).buildAndRegister();
            } else {
                ISOTOPIC_STABILIZER_RECIPES.recipeBuilder()
                        .input(ingot, material)
                        .fluidOutputs(stableIsotope.getFluid(1000))
                        .EUt(VA[property.tier()]).duration((int) (material.getMass() * (freeNeutrons + 1))).buildAndRegister();
            }
        } else {
            if (stableIsotope.hasProperty(PropertyKey.INGOT)) {
                ISOTOPIC_STABILIZER_RECIPES.recipeBuilder()
                        .fluidInputs(material.getFluid(1000))
                        .output(ingot, stableIsotope)
                        .EUt(VA[property.tier()]).duration((int) (material.getMass() * (freeNeutrons + 1))).buildAndRegister();
            } else {
                ISOTOPIC_STABILIZER_RECIPES.recipeBuilder()
                        .fluidInputs(material.getFluid(1000))
                        .fluidOutputs(stableIsotope.getFluid(1000))
                        .EUt(VA[property.tier()]).duration((int) (material.getMass() * (freeNeutrons + 1))).buildAndRegister();
            }
        }
    }

    private static void registerNuggetMortaringRecipe(Material material) {
        ModHandler.addShapedRecipe(String.format("mortar_grind_%s_nugget", material),
                OreDictUnifier.get(dustTiny, material), "X", "m", 'X',
                new UnificationEntry(nugget, material));
    }

    private static void addSmeltingRecipes(Material material) {
        OreProperty property = material.getProperty(PropertyKey.ORE);
        Material smeltingMaterial = property.getDirectSmeltResult() == null ? material : property.getDirectSmeltResult();
        if (OreDictUnifier.get(nugget, smeltingMaterial, property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial)).isEmpty() && OreDictUnifier.get(dustTiny, smeltingMaterial, property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial)).isEmpty()) return;
        if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ore, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            if (ConfigHolder.worldgen.allUniqueStoneTypes) {
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreAndesite, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreBasalt, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreBlackgranite, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreDiorite, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreEndstone, material), OreDictUnifier.get(nugget, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreGranite, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreMarble, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreNetherrack, material), OreDictUnifier.get(nugget, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreRedgranite, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreRedSand, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreSand, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            }
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(crushed, material), OreDictUnifier.get(nugget, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(crushedPurified, material), OreDictUnifier.get(nugget, smeltingMaterial, 6 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(dustImpure, material), OreDictUnifier.get(nugget, smeltingMaterial, 6 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(dustPure, material), OreDictUnifier.get(nugget, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
        }
        else {
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ore, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            if (ConfigHolder.worldgen.allUniqueStoneTypes) {
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreAndesite, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreBasalt, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreBlackgranite, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreDiorite, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreEndstone, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreGranite, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreMarble, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreNetherrack, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreRedgranite, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreRedSand, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
                ModHandler.addSmeltingRecipe(OreDictUnifier.get(oreSand, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            }
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(crushed, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 4 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(crushedPurified, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 6 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(dustImpure, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 6 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(dustPure, material), OreDictUnifier.get(dustTiny, smeltingMaterial, 8 * property.getOreMultiplier() * getComponentAmount(material, smeltingMaterial) / getTotalAtomAmounts(material)), 0.5f);
        }
    }

    private static void addTailingSiftingRecipe(Material material) {
        OreTailingProperty property = material.getProperty(GJPropertyKeys.ORE_TAILING_PROPERTY);
        List<ItemStack> inputs = Collections.singletonList(OreDictUnifier.get(dust, material, property.outputs().size()));
        RecipeBuilder<SimpleRecipeBuilder> builder = SIFTER_RECIPES.recipeBuilder().input(dust, material, property.outputs().size()).EUt(VA[property.tier()]).duration(calculateDuration(inputs, null, 100, 100, 0));
        for (Material output : property.outputs()) {
            builder.chancedOutput(dustImpure, output, 10000 / property.outputs().size(), 500);
        }
        builder.buildAndRegister();
    }

    private static void addSolutionMixingRecipe(Material material) {
        SolutionMixingProperty property = material.getProperty(GJPropertyKeys.SOLUTION_MIXING_PROPERTY);
        RecipeBuilder<SimpleRecipeBuilder> builder = MIXER_RECIPES.recipeBuilder().EUt(VA[property.tier()]);
        List<ItemStack> input = Collections.singletonList(OreDictUnifier.get(dust, property.dustMaterial(), property.molesDust() * getTotalComponentAmounts(property.dustMaterial())));
        List<FluidStack> fluidInput = Collections.singletonList(property.solventMaterial().getFluid(1000 * property.molesSolvent()));
        if (input.get(0).isEmpty()) {
            GJLog.logger.debug("Dust Material: " + property.dustMaterial().getName());
            GJLog.logger.debug("Fluid Material: " + material.getName());
        }
        builder.inputs(input.get(0)).fluidInputs(fluidInput.get(0)).fluidOutputs(material.getFluid(fluidInput.get(0).amount)).duration(calculateDuration(input, fluidInput, 50, 75, 75)).buildAndRegister();
    }

    private static void addRoastedOreProcessingRecipe(Material material) {
        RoastedOreProperty property = material.getProperty(GJPropertyKeys.ROASTED_ORE_PROPERTY);
        RecipeBuilder<SimpleRecipeBuilder> builder = CENTRIFUGE_RECIPES.recipeBuilder().EUt(VA[property.tier()]);
        List<ItemStack> outputs = material.getMaterialComponents().stream().map(c -> OreDictUnifier.get(dust, c.material, (int) c.amount * getTotalAtomAmounts(c.material))).collect(Collectors.toList());
        List<ItemStack> input = Collections.singletonList(OreDictUnifier.get(dust, material, getTotalAtomAmounts(material)));
        builder.inputs(input.get(0)).outputs(outputs).duration(calculateDuration(input, null, 50, 100, 0)).buildAndRegister();
    }

    private static void addSlagProcessingRecipe(Material material) {}

    private static void addLeachProcessingRecipes(Material material) {
        LeachProperty property = material.getProperty(GJPropertyKeys.LEACH_PROPERTY);
        Material impureSlag = getFromString("impure_" + material.getName().split("_")[0] + "_slag");
        Material slag = getFromString(material.getName().split("_")[0] + "_slag");
        if (impureSlag == null) {
            return;
        }

        int flotationAmount = getTotalComponentAmounts(material) * 1000;
        RecipeBuilder<SimpleRecipeBuilder> builder = FLOTATION_RECIPES.recipeBuilder().fluidInputs(material.getFluid(flotationAmount));
        builder.fluidInputs(property.flotationAgent().getFluid(10 * property.tier()));
        builder.fluidInputs(MethylIsobutylCarbinol.getFluid(100 * property.tier()));
        for (MaterialStack stack : material.getMaterialComponents()) {
            builder.fluidOutputs(stack.material.getFluid((int) (stack.amount * 1000)));
        }
        builder.fluidOutputs(impureSlag.getFluid(flotationAmount / 10));
        builder.EUt(VA[property.tier()]).duration(2 * (int) material.getMass()).buildAndRegister();

        CLARIFYING_RECIPES.recipeBuilder()
                .fluidInputs(impureSlag.getFluid(flotationAmount / 10))
                .output(dust, slag, getTotalComponentAmounts(material))
                .fluidOutputs(Water.getFluid(flotationAmount / 20))
                .EUt(VA[property.tier()]).duration((int) material.getMass()).buildAndRegister();
    }

    private static void addDistillationRecipes(Material material) {
        DistillationProperty property = material.getProperty(GJPropertyKeys.DISTILLATION_PROPERTY);
        int i = 1;
        RecipeBuilder<UniversalDistillationRecipeBuilder> builder = DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000 * material.getMaterialComponents().size()))
                .EUt(VA[property.tier() + 1]).duration((int) (100 * material.getMass()));
        for (MaterialStack stack : material.getMaterialComponents()) {
            builder.fluidOutputs(stack.material.getFluid((int) (1000 * stack.amount)));
            DISTILLERY_RECIPES.recipeBuilder()
                    .circuitMeta(i)
                    .fluidInputs(material.getFluid(1000 * material.getMaterialComponents().size()))
                    .fluidOutputs(stack.material.getFluid((int) (1000 * stack.amount)))
                    .EUt(VA[property.tier()]).duration((int) (10 * material.getMass())).buildAndRegister();
            i++;
        }
        builder.buildAndRegister();
    }

    private static void addCrystallizationAndDehydrationRecipes(Material material) {
        CrystallizationProperty property = material.getProperty(GJPropertyKeys.CRYSTALLIZATION_PROPERTY);
        Material crystallizedSolution = property.crystallizedSolution();
        Material dustMaterial = getSolidMaterialFromSolution(material);
        Material solventMaterial = getLiquidMaterialFromSolution(material);

        assert crystallizedSolution != null;

        CRYSTALLIZATION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(1000))
                .fluidOutputs(crystallizedSolution.getFluid(1000))
                .EUt(VA[property.tier()]).duration((int) (100 * material.getMass())).buildAndRegister();

        assert dustMaterial != null;
        assert solventMaterial != null;

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(crystallizedSolution.getFluid(1000))
                .output(dust, dustMaterial, getTotalComponentAmounts(dustMaterial) * getComponentAmount(material, dustMaterial))
                .fluidOutputs(solventMaterial.getFluid(1000 * getComponentAmount(material, solventMaterial)))
                .EUt(VA[property.tier()]).duration((int) (50 * material.getMass())).buildAndRegister();
    }
}