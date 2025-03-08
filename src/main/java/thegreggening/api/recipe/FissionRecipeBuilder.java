package thegreggening.api.recipe;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.PrimitiveProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import thegreggening.utils.TGLog;

public class FissionRecipeBuilder extends RecipeBuilder<FissionRecipeBuilder> {

    public FissionRecipeBuilder() {}

    public FissionRecipeBuilder(Recipe recipe, RecipeMap<FissionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public FissionRecipeBuilder(RecipeBuilder<FissionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public FissionRecipeBuilder copy() {
        return new FissionRecipeBuilder(this);
    }

    public FissionRecipeBuilder releasedEnergy(int energy) {
        if (energy <= 0) {
            TGLog.logger.error("Released Energy in a Fission Reaction cannot be less than or equal to 0", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(FissionEnergyProperty.getInstance(), energy);
        return this;
    }

    public FissionRecipeBuilder releasedNeutrons(int neutrons) {
        if (neutrons < 0) {
            TGLog.logger.error("Released Neutrons in a Fission Reaction cannot be less than 0", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(FissionNeutronProperty.getInstance(), neutrons);
        return this;
    }

    public ValidationResult<Recipe> build() {
        this.EUt(-1);
        this.applyProperty(PrimitiveProperty.getInstance(), true);
        return super.build();
    }
}
