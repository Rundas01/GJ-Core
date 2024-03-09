package gregsjourney.api.render;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;

public final class GJTextures {
    private GJTextures() {}
    // Singleblocks
    public static final OrientedOverlayRenderer BATCH_OVERLAY = new OrientedOverlayRenderer("singleblock/batch_reactor");
    public static final OrientedOverlayRenderer BUBBLE_OVERLAY = new OrientedOverlayRenderer("singleblock/bubble_column_reactor");
    public static final OrientedOverlayRenderer MIXING_OVERLAY = new OrientedOverlayRenderer("singleblock/mixing_reactor");
    public static final OrientedOverlayRenderer CATALYTIC_OVERLAY = new OrientedOverlayRenderer("singleblock/catalytic_reactor");
    public static final OrientedOverlayRenderer ROASTER_OVERLAY = new OrientedOverlayRenderer("singleblock/roaster");
    public static final OrientedOverlayRenderer POLYMERIZER_OVERLAY = new OrientedOverlayRenderer("singleblock/polymerizer");
    // Multiblock Controllers
    public static final OrientedOverlayRenderer FLOTATION_OVERLAY = new OrientedOverlayRenderer("multiblock/flotation_cell");
    public static final OrientedOverlayRenderer HEAT_EXCHANGER_OVERLAY = new OrientedOverlayRenderer("multiblock/heat_exchanger");
    public static final OrientedOverlayRenderer ADVANCED_ELECTROLYZER_OVERLAY = new OrientedOverlayRenderer("multiblock/advanced_electrolyzer");
    public static final OrientedOverlayRenderer ADVANCED_COMBUSTION_TURBINE_OVERLAY = new OrientedOverlayRenderer("multiblock/advanced_combustion_turbine");
    public static final OrientedOverlayRenderer ADVANCED_STEAM_TURBINE_OVERLAY = new OrientedOverlayRenderer("multiblock/advanced_steam_turbine");
    public static final OrientedOverlayRenderer CRUCIBLE_OVERLAY = new OrientedOverlayRenderer("multiblock/crucible");
}
