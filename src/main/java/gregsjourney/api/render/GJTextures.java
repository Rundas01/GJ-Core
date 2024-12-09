package gregsjourney.api.render;

import gregtech.api.gui.resources.SteamTexture;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;

public final class GJTextures {

    private GJTextures() {}

    // Singleblocks
    public static final OrientedOverlayRenderer STEAM_MIXER_OVERLAY = new OrientedOverlayRenderer("singleblock/steam/mixer");
    public static final OrientedOverlayRenderer BATCH_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/batch_reactor");
    public static final OrientedOverlayRenderer BUBBLE_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/gas_bubble_reactor");
    public static final OrientedOverlayRenderer MIXING_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/mixing_reactor");
    public static final OrientedOverlayRenderer CATALYTIC_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/catalytic_reactor");
    public static final OrientedOverlayRenderer ROASTER_OVERLAY = new OrientedOverlayRenderer("singleblock/roaster");
    public static final OrientedOverlayRenderer POLYMERIZER_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/polymerizer");
    public static final OrientedOverlayRenderer CRYSTALLIZER_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/crystallizer");
    public static final OrientedOverlayRenderer DEHYDRATOR_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/chemical_dehydrator");

    public static final OrientedOverlayRenderer DECAY_HASTENER_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/decay_hastener");

    public static final OrientedOverlayRenderer ISOTOPIC_STABILIZER_OVERLAY = new OrientedOverlayRenderer(
            "singleblock/isotopic_stabilizer");

    // Multiblock Controllers
    public static final OrientedOverlayRenderer FLOTATION_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/flotation_cell");
    public static final OrientedOverlayRenderer HEAT_EXCHANGER_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/heat_exchanger");
    public static final OrientedOverlayRenderer ADVANCED_ELECTROLYZER_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/advanced_electrolyzer");
    public static final OrientedOverlayRenderer ADVANCED_COMBUSTION_TURBINE_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/advanced_combustion_turbine");
    public static final OrientedOverlayRenderer ADVANCED_STEAM_TURBINE_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/advanced_steam_turbine");
    public static final OrientedOverlayRenderer CRUCIBLE_OVERLAY = new OrientedOverlayRenderer("multiblock/crucible");
    public static final OrientedOverlayRenderer COMB_OVERLAY = new OrientedOverlayRenderer("multiblock/comb_processor");
    public static final OrientedOverlayRenderer CLARIFICATION_OVERLAY = new OrientedOverlayRenderer(
            "multiblock/clarification_plant");

    //GUI
    public static final SteamTexture PROGRESS_BAR_MIXER_STEAM = SteamTexture.fullImage("textures/gui/progress_bar/progress_bar_mixer_%s.png");
    public static final SteamTexture INT_CIRCUIT_OVERLAY_STEAM = SteamTexture.fullImage("textures/gui/progress_bar/int_circuit_overlay_%s.png");
    public static final SteamTexture BUTTON_INT_CIRCUIT_PLUS_STEAM = SteamTexture.fullImage("textures/gui/widget/button_circuit_plus_%s.png");
    public static final SteamTexture BUTTON_INT_CIRCUIT_MINUS_STEAM = SteamTexture.fullImage("textures/gui/widget/button_circuit_minus_%s.png");
    public static final SteamTexture FLUID_SLOT_STEAM = SteamTexture.fullImage("textures/gui/slot/fluid_slot_%s.png");
}
