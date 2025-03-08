package thegreggening.api.unification.ore;

import gregtech.api.unification.ore.OrePrefix;

import thegreggening.api.unification.material.TGMaterialIconTypes;
import thegreggening.api.unification.property.TGMaterialFlags;

public class TGOrePrefixes {

    public static OrePrefix nuclearFuel = new OrePrefix("nuclearFuel", -1L, null, TGMaterialIconTypes.nuclearFuel,
            OrePrefix.Flags.ENABLE_UNIFICATION, mat -> mat.hasFlag(TGMaterialFlags.GENERATE_NUCLEAR_FUELS));
    public static OrePrefix depletedNuclearFuel = new OrePrefix("depletedNuclearFuel", -1L, null,
            TGMaterialIconTypes.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION,
            mat -> mat.hasFlag(TGMaterialFlags.GENERATE_NUCLEAR_FUELS));
    public static OrePrefix catalystBed = new OrePrefix("catalystBed", -1L, null,
            TGMaterialIconTypes.catalystBed, OrePrefix.Flags.ENABLE_UNIFICATION,
            mat -> mat.hasFlag(TGMaterialFlags.GENERATE_CATALYST_BED));
}
