package gregicality.legacy.api.fluids;

import gregicality.multiblocks.api.unification.material.GCYMMaterialIconTypes;
import gregtech.api.fluids.store.FluidStorageKey;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;

public final class GCYLRFluidStorageKeys {

    public static final FluidStorageKey SUPERCRITICAL = new FluidStorageKey(gcylrId("supercritical"),
            GCYMMaterialIconTypes.molten,
            s -> "supercritical." + s,
            m -> "gcylr.fluid.supercritical");

    private GCYLRFluidStorageKeys() {}
}
