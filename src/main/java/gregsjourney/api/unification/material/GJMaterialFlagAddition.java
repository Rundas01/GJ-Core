package gregsjourney.api.unification.material;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD;

import gregsjourney.common.integration.forestry.ForestryModule;
import org.jetbrains.annotations.ApiStatus;

import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

@ApiStatus.Internal
public class GJMaterialFlagAddition {

    private GJMaterialFlagAddition() {}

    public static void init() {
        ForestryModule.registerMaterialFlags();
        ToolProperty prop = Flint.getProperty(PropertyKey.TOOL);
        prop.setShouldIgnoreCraftingTools(false);
        Graphite.addFlags(GENERATE_ROD);
        Antimony.setProperty(PropertyKey.ORE, new OreProperty());
        Antimony.addFlags(GENERATE_ROD);
        Arsenic.setProperty(PropertyKey.ORE, new OreProperty());
        Arsenic.addFlags(GENERATE_ROD);
        Barium.setProperty(PropertyKey.ORE, new OreProperty());
        Barium.addFlags(GENERATE_ROD);
        Beryllium.addFlags(GENERATE_ROD);
        Bismuth.setProperty(PropertyKey.ORE, new OreProperty());
        Bismuth.addFlags(GENERATE_ROD);
        Boron.setProperty(PropertyKey.ORE, new OreProperty());
        Bromine.setProperty(PropertyKey.FLUID, new FluidProperty());
        Caesium.setProperty(PropertyKey.ORE, new OreProperty());
        Calcium.setProperty(PropertyKey.ORE, new OreProperty());
        Cerium.setProperty(PropertyKey.ORE, new OreProperty());
        Silicon.addFlags(GENERATE_ROD);
    }

    public static void initLate() {}
}
