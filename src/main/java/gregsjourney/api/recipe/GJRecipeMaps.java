package gregsjourney.api.recipe;

import static gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES;

import gregsjourney.common.GJConfigHolder;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

public final class GJRecipeMaps {

    private GJRecipeMaps() {}

    public static RecipeMap<SimpleRecipeBuilder> FLOTATION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> METALLURGIC_REACTION_SMELTER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ARC_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ORE_CRACKING_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> REDOX_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES;

    public static void init() {
        FLOTATION_RECIPES = new RecipeMap<>("flotation_cell", 0, 0,
                2, 4, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        METALLURGIC_REACTION_SMELTER_RECIPES = new RecipeMap<>("metallurgic_reaction_smelter", 3, 2,
                3, 2, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.FURNACE);

        ADVANCED_ARC_RECIPES = new RecipeMap<>("advanced_arc_furnace", 4, 2,
                4, 2, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSmallRecipeMap(ARC_FURNACE_RECIPES)
                        .setSound(GTSoundEvents.ARC);

        ORE_CRACKING_RECIPES = new RecipeMap<>("ore_cracking", 1, 4,
                1, 4, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ARC);

        REDOX_RECIPES = new RecipeMap<>("redox_furnace", 2, 2,
                2, 2, new SimpleRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ARC);

        CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator", 2, 9,
                2, 3, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);
    }
}
