package gregsjourney.client;

import gregsjourney.GregsJourney;
import gregsjourney.common.CommonProxy;
import gregsjourney.common.block.GJMetaBlocks;
import gregsjourney.integration.forestry.ForestryModule;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GregsJourney.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void onLoad() {
        super.onLoad();
        GJTooltipLoader.registerTooltips();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GJMetaBlocks.registerItemModels();
        ForestryModule.registerModels(event);
    }
}
