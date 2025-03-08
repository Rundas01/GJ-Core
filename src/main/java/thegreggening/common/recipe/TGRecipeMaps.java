package thegreggening.common.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import thegreggening.api.recipe.FissionRecipeBuilder;
import thegreggening.api.recipe.NoEnergyRecipeBuilder;
import thegreggening.common.TGConfigHolder;

import static gregtech.api.recipes.RecipeMaps.*;

public final class TGRecipeMaps {
    // Singleblock
    public static RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ROASTING_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BATCH_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BUBBLE_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> DISSOLUTION_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> MIXING_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CATALYTIC_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> POLYMERIZATION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CRYSTALLIZATION_RECIPES;
    public static RecipeMap<FuelRecipeBuilder> DECAY_GENERATOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ISOTOPIC_STABILIZER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> DECAY_HASTENER_RECIPES;
    public static RecipeMap<FuelRecipeBuilder> ROCKET_ENGINE_FUELS;
    public static RecipeMap<FuelRecipeBuilder> PLASMA_FUELS;
    public static RecipeMap<SimpleRecipeBuilder> MATTER_FABRICATOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> REPLICATOR_RECIPES;

    // Multiblock
    public static RecipeMap<SimpleRecipeBuilder> COMB_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> FLOTATION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ARC_RECIPES;
    public static RecipeMap<FuelRecipeBuilder> ADVANCED_COMBUSTION_FUELS;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ELECTROLYZER_RECIPES;
    public static RecipeMap<NoEnergyRecipeBuilder> HEAT_EXCHANGER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CLARIFYING_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> REACTION_FURNACE_RECIPES;
    public static RecipeMap<FissionRecipeBuilder> FISSION_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> FUEL_REPROCESSOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ISOTOPIC_ENRICHER_RECIPES;

    public static void init() {
        // Singleblock
        CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator", 2, 9,
                2, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        ROASTING_RECIPES = new RecipeMap<>("roaster", 2, 6,
                2, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.FURNACE);

        BATCH_REACTOR_RECIPES = new RecipeMap<>("batch_reactor", 3, 6,
                3, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        BUBBLE_RECIPES = new RecipeMap<>("gas_bubble_reactor", 0, 3,
                2, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        DISSOLUTION_REACTOR_RECIPES = new RecipeMap<>("dissolution_reactor", 1, 3,
                1, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        MIXING_REACTOR_RECIPES = new RecipeMap<>("mixing_reactor", 0, 3,
                2, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        CATALYTIC_REACTOR_RECIPES = new RecipeMap<>("catalytic_reactor", 2, 3,
                3, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        POLYMERIZATION_RECIPES = new RecipeMap<>("polymerizer", 1, 1,
                2, 3, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        CRYSTALLIZATION_RECIPES = new RecipeMap<>("crystallizer", 0, 0,
                1, 1, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.COOLING);

        DECAY_GENERATOR_RECIPES = new RecipeMap<>("decay_generator", 2, 1,
                1, 1, new FuelRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.SCIENCE);

        ISOTOPIC_STABILIZER_RECIPES = new RecipeMap<>("isotopic_stabilizer", 1, 1,
                1, 1, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.SCIENCE);

        DECAY_HASTENER_RECIPES = new RecipeMap<>("decay_hastener", 2, 1,
                1, 1, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.SCIENCE);

        ROCKET_ENGINE_FUELS = new RecipeMap<>("rocket_engine", 0, 0,
                1, 3, new FuelRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.COMBUSTION);

        PLASMA_FUELS = new RecipeMap<>("plasma_turbine", 0, 0,
                1, 1, new FuelRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ARC);

        MATTER_FABRICATOR_RECIPES = new RecipeMap<>("matter_fabrication", 1, 0,
                1, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.REPLICATOR);

        REPLICATOR_RECIPES = new RecipeMap<>("replication", 1, 1,
                3, 1, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.REPLICATOR);

        // Multiblock
        ISOTOPIC_ENRICHER_RECIPES = new RecipeMap<>("isotopic_enricher", 0, 0,
                1, 6, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CENTRIFUGE);

        COMB_RECIPES = new RecipeMap<>("comb_processor", 2, 6,
                1, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CENTRIFUGE);

        FLOTATION_RECIPES = new RecipeMap<>("flotation_cell", 0, 0,
                3, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        ADVANCED_ARC_RECIPES = new RecipeMap<>("advanced_arc_furnace", 4, 2,
                4, 2, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSmallRecipeMap(ARC_FURNACE_RECIPES)
                        .setSound(GTSoundEvents.ARC);

        HEAT_EXCHANGER_RECIPES = new RecipeMap<>("heat_exchanger", 0, 0,
                2, 2, new NoEnergyRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.BOILER);

        ADVANCED_COMBUSTION_FUELS = new RecipeMap<>("advanced_combustion_fuels", 0, 0,
                2, 3, new FuelRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.COMBUSTION);

        ADVANCED_ELECTROLYZER_RECIPES = new RecipeMap<>("advanced_electrolyzer", 3, 6,
                3, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ELECTROLYZER);

        CLARIFYING_RECIPES = new RecipeMap<>("clarification_plant", 0, 1,
                1, 1, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.BATH);

        REACTION_FURNACE_RECIPES = new RecipeMap<>("reaction_furnace", 3, 6,
                3, 6, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.FURNACE);

        FISSION_REACTOR_RECIPES = new RecipeMap<>("fission_reactor", 2, 1,
                0, 0, new FissionRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.SCIENCE);

        FUEL_REPROCESSOR_RECIPES = new RecipeMap<>("fuel_reprocessor", 1, TGConfigHolder.options.maxFuelSplit,
                0, TGConfigHolder.options.maxFuelSplit, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.SCIENCE);

        // Change Recipe Maps
        PYROLYSE_RECIPES.setMaxFluidOutputs(2);
        CENTRIFUGE_RECIPES.setMaxFluidInputs(2);
        COMPRESSOR_RECIPES.setMaxFluidInputs(1);
        COMPRESSOR_RECIPES.setMaxFluidOutputs(1);
        ASSEMBLER_RECIPES.setMaxFluidInputs(3);
        CIRCUIT_ASSEMBLER_RECIPES.setMaxFluidInputs(3);
        ELECTROLYZER_RECIPES.setMaxFluidInputs(2);
    }
}
