package gregsjourney.api.unification.material;

import static gregtech.api.unification.material.Materials.*;

import gregsjourney.api.unification.property.GJMaterialFlags;
import org.jetbrains.annotations.ApiStatus;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

import gregsjourney.integration.forestry.ForestryModule;

@ApiStatus.Internal
public class GJMaterialFlagAddition {

    private GJMaterialFlagAddition() {}

    public static void init() {
        ForestryModule.registerMaterialFlags();
        Water.addFlags(GJMaterialFlags.SOLVENT);
        ToolProperty prop = Flint.getProperty(PropertyKey.TOOL);
        prop.setShouldIgnoreCraftingTools(false);
    }

    public static void initLate() {}
}
