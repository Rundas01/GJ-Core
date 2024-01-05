package gregicality.legacy.api.recipe.builders;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.FusionEUToStartProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdvancedFusionRecipeBuilder extends RecipeBuilder<AdvancedFusionRecipeBuilder> {

    private int coilTier;
    private long euStart;
    private int euReturn;


    public AdvancedFusionRecipeBuilder(Recipe recipe, RecipeMap<AdvancedFusionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.coilTier = (int) recipe.getPropertyRaw("coil_tier");
        this.euStart = (long) recipe.getPropertyRaw("eu_to_start");
        this.euReturn = (int) recipe.getPropertyRaw("eu_return");
    }

    public AdvancedFusionRecipeBuilder() {}

    public AdvancedFusionRecipeBuilder(RecipeBuilder<AdvancedFusionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public AdvancedFusionRecipeBuilder(RecipeBuilder<AdvancedFusionRecipeBuilder> recipeBuilder, int coilTier, long euStart, int euReturn) {
        super(recipeBuilder);
        this.coilTier = coilTier;
        this.euStart = euStart;
        this.euReturn = euReturn;
    }

    @Override
    public AdvancedFusionRecipeBuilder copy() {
        return new AdvancedFusionRecipeBuilder(this, this.coilTier, this.euStart, this.euReturn);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        switch (key) {
            case "coilTier" -> {
                this.coilTier(((Number) value).intValue());
                return true;
            }
            case "eu_to_start" -> {
                this.EUToStart(((Number) value).longValue());
                return true;
            }
        }
        return super.applyProperty(key, value);
    }

    public AdvancedFusionRecipeBuilder coilTier(int coilTier) {
        if (coilTier <= 0) {
            GTLog.logger.error("Advanced Fusion Coil tier cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AdvancedFusionCoilTierProperty.getInstance(), coilTier);
        return this;
    }

    public int getCoilTier() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(AdvancedFusionCoilTierProperty.getInstance(), 0);
    }

    public AdvancedFusionRecipeBuilder EUToStart(long eu) {
        if (eu <= 0) {
            GTLog.logger.error("Advanced Fusion EU to start cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(FusionEUToStartProperty.getInstance(), eu);
        return this;
    }

    public long getEUToStart() {
        return this.recipePropertyStorage == null ? 0L :
                this.recipePropertyStorage.getRecipePropertyValue(FusionEUToStartProperty.getInstance(), 0L);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(FusionEUToStartProperty.getInstance().getKey(), getEUToStart())
                .append(AdvancedFusionCoilTierProperty.getInstance().getKey(), getCoilTier())
                .toString();
    }
}
