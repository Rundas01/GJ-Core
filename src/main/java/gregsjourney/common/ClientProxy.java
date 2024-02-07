package gregsjourney.common;

import gregsjourney.common.block.GJMetaBlocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import gregsjourney.api.render.GJTextures;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preLoad() {
        super.preLoad();
        GJTextures.preInit();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        registerColors();
    }

    public static void registerColors() {}

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GJMetaBlocks.registerItemModels();
        /*
         * if(Loader.isModLoaded("forestry")) {
         * ForestryModule.registerModels(event);
         * }
         */
    }
}
