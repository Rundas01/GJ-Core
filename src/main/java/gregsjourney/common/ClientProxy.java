package gregsjourney.common;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import gregsjourney.common.block.GJMetaBlocks;
import gregsjourney.integration.forestry.ForestryModule;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preLoad() {
        super.preLoad();
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
        ForestryModule.registerModels(event);
    }
}
