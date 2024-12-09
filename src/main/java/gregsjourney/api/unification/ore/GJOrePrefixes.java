package gregsjourney.api.unification.ore;

import gregsjourney.api.unification.material.GJMaterialIconTypes;
import gregsjourney.api.unification.property.GJMaterialFlags;
import gregtech.api.unification.ore.OrePrefix;

public class GJOrePrefixes {
    public static OrePrefix nuclearFuels = new OrePrefix("nuclearFuel", -1L, null, GJMaterialIconTypes.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, mat -> mat.hasFlag(GJMaterialFlags.GENERATE_NUCLEAR_FUELS));
    public static OrePrefix depletedNuclearFuels = new OrePrefix("depletedNuclearFuel", -1L, null, GJMaterialIconTypes.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, mat -> mat.hasFlag(GJMaterialFlags.GENERATE_NUCLEAR_FUELS));
}
