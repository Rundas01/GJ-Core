package gregicality.legacy.api.unification.material;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.unification.material.GCYLRMaterials.*;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public final class GCYLRFusionModeratorMaterials {

    private GCYLRFusionModeratorMaterials() {}

    public static Material LeadBismuthEutectic;
    public static Material FLiBe;
    public static Material FLiNaK;

    public static void init() {
        LeadBismuthEutectic = new Material.Builder(3200,gcylrId("lead_bismuth_eutectic"))
                .dust().fluid()
                .color(0x757575)
                .iconSet(SHINY)
                .components(Lead,3,Bismuth,7)
                .build();

        FLiBe = new Material.Builder(3201,gcylrId("flibe"))
                .fluid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine,3,Lithium,1,Beryllium,1)
                .build();

        FLiNaK = new Material.Builder(3202,gcylrId("flinak"))
                .fluid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine,3,Lithium,1,Sodium,1,Potassium,1)
                .build();
    }
}
