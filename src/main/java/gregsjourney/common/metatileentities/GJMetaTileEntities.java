package gregsjourney.common.metatileentities;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import gregsjourney.common.metatileentities.multiblock.electric.*;

public final class GJMetaTileEntities {

    private GJMetaTileEntities() {}

    public static MetaTileEntityAdvancedArcFurnace ADVANCED_ARC_FURNACE;
    public static MetaTileEntityRedoxFurnace REDOX_FURNACE;
    public static MetaTileEntityMetallurgicRefinery METALLURGIC_REFINERY;
    public static MetaTileEntityFlotationCell FLOTATION_CELL;
    public static MetaTileEntityOreCracker ORE_CRACKER;

    public static void init() {
        // Singleblocks
        // Multiblocks
        ORE_CRACKER = registerMetaTileEntity(2200, new MetaTileEntityOreCracker(gjId("ore_cracker")));
        FLOTATION_CELL = registerMetaTileEntity(2201, new MetaTileEntityFlotationCell(gjId("flotation_cell")));
        REDOX_FURNACE = registerMetaTileEntity(2202, new MetaTileEntityRedoxFurnace(gjId("redox_furnace")));
        METALLURGIC_REFINERY = registerMetaTileEntity(2203,
                new MetaTileEntityMetallurgicRefinery(gjId("metallurgic_refinery")));
        ADVANCED_ARC_FURNACE = registerMetaTileEntity(2204, new MetaTileEntityAdvancedArcFurnace(gjId("advanced_arc_furnace")));
    }
}
