package gregicality.legacy.common;

import gregicality.legacy.common.items.GCYLRMetaItems;
import gregicality.legacy.integration.forestry.ForestryModule;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import gregicality.legacy.api.render.GCYLRTextures;
import gregicality.legacy.common.block.GCYLRMetaBlocks;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preLoad() {
        super.preLoad();
        GCYLRTextures.preInit();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        registerColors();
    }

    public static void registerColors() {
        GCYLRMetaItems.registerColors();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GCYLRMetaBlocks.registerItemModels();
        GCYLRMetaItems.registerModels();
        if(Loader.isModLoaded("forestry")) {
            ForestryModule.registerModels(event);
        }
    }
}
