package gregicality.legacy.api.unification.ore;

import gregicality.legacy.api.unification.material.GCYLRMaterialIconTypes;
import gregicality.legacy.api.unification.properties.GCYLRPropertyKey;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;

import java.util.function.Predicate;

public class GCYLROrePrefix {
    public static OrePrefix nuclearFuel;
    public static OrePrefix depletedNuclearFuel;

    public static void init() {
        nuclearFuel = new OrePrefix("nuclearFuel", -1L, null, GCYLRMaterialIconTypes.nuclearFuel, 1L, GCYLROrePrefix.Conditions.hasNuclearProperty);
        depletedNuclearFuel = new OrePrefix("depletedNuclearFuel", -1L, null, GCYLRMaterialIconTypes.nuclearFuel, 1L, GCYLROrePrefix.Conditions.hasNuclearProperty);
    }

    public static class Conditions {
        public static final Predicate<Material> hasNuclearProperty;

        public Conditions() {}

        static {
            hasNuclearProperty = (mat) -> mat.hasProperty(GCYLRPropertyKey.NUCLEAR);
        }
    }
}
