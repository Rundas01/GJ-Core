package gregicality.legacy.api.render;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import gregicality.legacy.GregicalityLegacyReimagined;

@Mod.EventBusSubscriber(modid = GregicalityLegacyReimagined.MODID, value = Side.CLIENT)
public final class GCYLRTextures {

    // Multiblock Controllers
    public static OrientedOverlayRenderer BIO_REACTOR_OVERLAY;
    public static SimpleOverlayRenderer BIO_CASING;
    public static OrientedOverlayRenderer FLOTATION_OVERLAY;

    private GCYLRTextures() {}

    public static void preInit() {
        //Multiblock Controllers
        BIO_REACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/bio_reactor");
        FLOTATION_OVERLAY = new OrientedOverlayRenderer("multiblock/flotation_cell");
        //Casings
        BIO_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/bio_casing");
    }
}
