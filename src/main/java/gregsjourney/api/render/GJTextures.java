package gregsjourney.api.render;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import gregsjourney.GregsJourney;

@Mod.EventBusSubscriber(modid = GregsJourney.MODID, value = Side.CLIENT)
public final class GJTextures {

    // Multiblock Controllers
    public static OrientedOverlayRenderer BIO_REACTOR_OVERLAY;
    public static SimpleOverlayRenderer BIO_CASING;
    public static OrientedOverlayRenderer FLOTATION_OVERLAY;
    public static OrientedOverlayRenderer REDOX_FURNACE_OVERLAY;

    private GJTextures() {}

    public static void preInit() {
        // Multiblock Controllers
        BIO_REACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/bio_reactor");
        FLOTATION_OVERLAY = new OrientedOverlayRenderer("multiblock/flotation_cell");
        REDOX_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/redox_furnace");
        // Casings
        BIO_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/bio_casing");
    }
}
