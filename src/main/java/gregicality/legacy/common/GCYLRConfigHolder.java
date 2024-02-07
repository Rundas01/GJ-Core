package gregicality.legacy.common;

import gregicality.legacy.GregicalityLegacyReimagined;
import gregtech.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GregicalityLegacyReimagined.MODID, name = GregicalityLegacyReimagined.NAME + '/' + GTValues.MODID)
public class GCYLRConfigHolder {
    @Config.Comment("Config options for GCYLR Machines")
    @Config.Name("GCYLR Machine Options")
    @Config.RequiresMcRestart
    public static GCYLRMachineOptions machines = new GCYLRMachineOptions();
    @Config.Comment("Config options for GregTech Recipes")
    @Config.Name("GT Recipe Options")
    @Config.RequiresMcRestart
    public static GCYLRRecipeOptions recipes = new GCYLRRecipeOptions();

    public static class GCYLRMachineOptions {
        @Config.Comment({"Up to how many elements a fission fuel can split into.", "Default: 2"})
        public int fuelSplitAmount = 2;

        @Config.Comment({"How many different fission processes a material can undergo.", "Default: 1"})
        public int fuelSplitOffset = 1;

        @Config.Comment({"Whether depleted fuel pellets are allowed to contain liquids and gases.", "Default: false"})
        public boolean allowLiquidsInReprocessing = false;
    }

    public static class GCYLRRecipeOptions {
        @Config.Comment({"If set to true, several GregTech recipes will be replaced by more complex recipes which are inspired by real life processes." +
                "Will make the game more difficult, but also more interesting.", "Default: true"})
        public boolean realisticRecipes = true;
    }
}
