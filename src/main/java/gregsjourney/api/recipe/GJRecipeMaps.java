package gregsjourney.api.recipe;

import static gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

public final class GJRecipeMaps {

    private GJRecipeMaps() {}

    //Singleblock
    public static RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ROASTING_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BATCH_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BUBBLE_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> MIXING_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CATALYTIC_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> POLYMERIZATION_RECIPES;

    //Multiblock
    public static RecipeMap<SimpleRecipeBuilder> FLOTATION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ARC_RECIPES;
    public static RecipeMap<FuelRecipeBuilder> ADVANCED_STEAM_FUELS;
    public static RecipeMap<FuelRecipeBuilder> ADVANCED_COMBUSTION_FUELS;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ELECTROLYZER_RECIPES;
    public static RecipeMap<NoEnergyRecipeBuilder> HEAT_EXCHANGER_RECIPES;

    public static void init() {
        //Singleblock
        CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator", 2, 9,
                2, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        ROASTING_RECIPES = new RecipeMap<>("roaster", 2, 4,
                2, 4, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.FURNACE);

        BATCH_REACTOR_RECIPES = new RecipeMap<>("batch_reactor", 3, 3,
                3, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        BUBBLE_RECIPES = new RecipeMap<>("bubble_column_reactor", 0, 0,
                2, 2, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        MIXING_REACTOR_RECIPES = new RecipeMap<>("mixing_reactor", 1, 2,
                2, 2, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        CATALYTIC_REACTOR_RECIPES = new RecipeMap<>("catalytic_reactor", 3, 2,
                4, 2, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        POLYMERIZATION_RECIPES = new RecipeMap<>("polymerizer", 1, 1,
                2, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        //Multiblock
        FLOTATION_RECIPES = new RecipeMap<>("flotation_cell", 0, 0,
                2, 4, new SimpleRecipeBuilder(), false)
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

        ADVANCED_STEAM_FUELS = new RecipeMap<>("advanced_steam_fuels", 0, 0,
                1, 1, new FuelRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.TURBINE);

        ADVANCED_COMBUSTION_FUELS = new RecipeMap<>("advanced_combustion_fuels", 0, 0,
                2, 3, new FuelRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.COMBUSTION);

        ADVANCED_ELECTROLYZER_RECIPES = new RecipeMap<>("advanced_electrolyzer", 3, 3,
                3, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ELECTROLYZER);
    }
}
