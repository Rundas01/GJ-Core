package gregsjourney.loaders.recipe;

import gregsjourney.loaders.recipe.multiblock.orecracking.*;
import gregsjourney.loaders.recipe.singleblock.FuelChanges;
import gregtech.api.gui.GuiTextures;

import static gregtech.api.recipes.RecipeMaps.*;

public final class GJRecipeLoader {

    private GJRecipeLoader() {}

    public static void init() {
        changeRecipeMaps();
        FuelChanges.init();
        materialLines();
    }

    private static void changeRecipeMaps() {
        ASSEMBLER_RECIPES.setMaxFluidInputs(3);
        CIRCUIT_ASSEMBLER_RECIPES.setMaxFluidInputs(3);
        COMBUSTION_GENERATOR_FUELS.setMaxFluidInputs(2);
        COMBUSTION_GENERATOR_FUELS.setMaxFluidOutputs(3);
        COMBUSTION_GENERATOR_FUELS.setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2);
        CHEMICAL_RECIPES.setMaxInputs(3);
        CENTRIFUGE_RECIPES.setMaxFluidInputs(2);
    }

    private static void materialLines() {
        AluminiumLine.init();
        AntimonyLine.init();
        ArsenicLine.init();
        BariumLine.init();
        BerylliumLine.init();
        BismuthLine.init();
        BoronLine.init();
        BromineLine.init();
        CaesiumLine.init();
        CalciumLine.init();
        CeriumLine.init();
    }
}
