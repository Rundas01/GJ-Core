package gregicality.legacy.loaders.recipe.multiblock;

import net.minecraft.init.Items;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public final class BioReactorRecipes {

    private BioReactorRecipes() {}

    public static void init() {
        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Copper)
                .input(dust, Tin)
                .fluidInputs(Lava.getFluid(1000),Water.getFluid(1000))
                .output(Items.NETHER_STAR, 2)
                .duration(160).EUt(VA[EV]).buildAndRegister();
    }
}
