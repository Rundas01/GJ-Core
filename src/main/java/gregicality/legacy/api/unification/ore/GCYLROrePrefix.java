package gregicality.legacy.api.unification.ore;

import gregicality.legacy.api.unification.material.icon.GCYLRMaterialIconTypes;
import gregtech.api.unification.ore.OrePrefix;

public class GCYLROrePrefix {
    public static OrePrefix nuclearFuel = new OrePrefix("nuclearFuel",-1, null, GCYLRMaterialIconTypes.nuclearFuel, 1L, mat -> mat.hasFlag(GCYLRMaterialFlags.GENERATE_NUCLEAR));

}
