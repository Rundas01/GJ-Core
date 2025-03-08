package thegreggening.api.recipe;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FissionEnergyProperty extends RecipeProperty<Integer> {

    public static final String KEY = "fission_energy";

    private static FissionEnergyProperty INSTANCE;

    private FissionEnergyProperty() { super(KEY, Integer.class); }

    public static FissionEnergyProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FissionEnergyProperty();
        }
        return INSTANCE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("tg.recipe.fission_energy", value), x, y, color);
    }
}
