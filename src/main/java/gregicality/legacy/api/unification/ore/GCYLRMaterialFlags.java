package gregicality.legacy.api.unification.ore;

import gregicality.legacy.api.unification.properties.GCYLRPropertyKeys;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public class GCYLRMaterialFlags {
    public static MaterialFlag GENERATE_NUCLEAR = (new MaterialFlag.Builder("generate_nuclear"))
            .requireProps(new PropertyKey[]{GCYLRPropertyKeys.NUCLEAR})
            .build();
}
