package gregicality.legacy.api.recipe;

import gregicality.legacy.api.recipe.builders.AdvancedFusionRecipeBuilder;
import gregicality.legacy.common.GCYLRConfigHolder;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import static gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FUSION_RECIPES;

public final class GCYLRRecipeMaps {

    public static RecipeMap<FuelRecipeBuilder> DECAY_GENERATOR_FUELS;
    public static RecipeMap<SimpleRecipeBuilder> ISOTOPIC_STABILIZER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> FISSION_REACTOR_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> BREEDER_REACTOR_RECIPES;
    public static RecipeMap<AdvancedFusionRecipeBuilder> ADVANCED_FUSION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> LARGE_MIXER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> FLOTATION_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> METALLURGIC_REACTION_SMELTER_RECIPES;
    public static RecipeMap<SimpleRecipeBuilder> ADVANCED_ARC_RECIPES;
    public static RecipeMap<BlastRecipeBuilder> ORE_CRACKING_RECIPES;

    private GCYLRRecipeMaps() {}

    public static void init() {

        if(GCYLRConfigHolder.recipes.realisticRecipes){
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

            ORE_CRACKING_RECIPES = new RecipeMap<>("ore_cracking", 1,4,
                    3, 5, new BlastRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ARC);
        }

        DECAY_GENERATOR_FUELS = new RecipeMap<>("decay_generator", 2, 1,
                1, 1, new FuelRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.SCIENCE);

        CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator", 2, Math.max(9,GCYLRConfigHolder.machines.fuelSplitAmount),
                2, Math.max(3,GCYLRConfigHolder.machines.fuelSplitAmount), new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        BIO_REACTOR_RECIPES = new RecipeMap<>("bio_reactor", 3, 3,
                5, 2, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHEMICAL_REACTOR);

        ADVANCED_FUSION_RECIPES = new RecipeMap<>("advanced_fusion_reactor", 0, 0,
                3, 3, new AdvancedFusionRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ARC)
                .setSmallRecipeMap(FUSION_RECIPES);

        ISOTOPIC_STABILIZER_RECIPES = new RecipeMap<>("isotopic_stabilizer", 1, 1,
                1, 1, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.SCIENCE);

        if(GCYLRConfigHolder.machines.allowLiquidsInReprocessing){
            FISSION_REACTOR_RECIPES = new RecipeMap<>("fission_reactor", 2, GCYLRConfigHolder.machines.fuelSplitAmount,
                    0, GCYLRConfigHolder.machines.fuelSplitAmount, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.SCIENCE);
        }else{
            FISSION_REACTOR_RECIPES = new RecipeMap<>("fission_reactor", 2, GCYLRConfigHolder.machines.fuelSplitAmount,
                    0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.SCIENCE);
        }

        if(GCYLRConfigHolder.machines.allowLiquidsInReprocessing){
            BREEDER_REACTOR_RECIPES = new RecipeMap<>("breeder_reactor", 3, GCYLRConfigHolder.machines.fuelSplitAmount + 1,
                    0, GCYLRConfigHolder.machines.fuelSplitAmount + 1, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.SCIENCE);
        }else{
            BREEDER_REACTOR_RECIPES = new RecipeMap<>("breeder_reactor", 3, GCYLRConfigHolder.machines.fuelSplitAmount + 1,
                    0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.SCIENCE);
        }

        LARGE_MIXER_RECIPES = new RecipeMap<>("large_mixer", 9, 2,
                6, 2, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                .setSound(GTSoundEvents.MIXER);
    }
}
