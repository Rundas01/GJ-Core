package gregicality.legacy.api.recipe.builders;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.TextFormattingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class AdvancedFusionCoilTierProperty extends RecipeProperty<Integer> {
    public static final String KEY = "coilTier";
    private static AdvancedFusionCoilTierProperty INSTANCE;

    protected AdvancedFusionCoilTierProperty() {
        super("coilTier", Integer.class);
    }

    public static AdvancedFusionCoilTierProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdvancedFusionCoilTierProperty();
        }
        return INSTANCE;
    }

    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gcylr.recipe.coilTier", TextFormattingUtil.formatLongToCompactString(this.castValue(value))), x, y, color);
    }
}
