package gregsjourney;

import gregsjourney.utils.GJLog;
import gregsjourney.common.CommonProxy;
import gregsjourney.common.block.GJMetaBlocks;
import gregsjourney.common.metatileentities.GJMetaTileEntities;
import gregsjourney.common.recipe.GJRecipeMaps;
import gregsjourney.integration.forestry.ForestryModule;
import gregtech.GTInternalTags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.jetbrains.annotations.NotNull;

@Mod(modid = GregsJourney.MODID,
     name = GregsJourney.NAME,
     version = GregsJourney.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + ";required-after:gcym")
public class GregsJourney {

    public static final String MODID = "gj";
    public static final String NAME = "Greg's Journey";
    public static final String VERSION = GJInternalTags.VERSION;

    @SidedProxy(modId = MODID,
                clientSide = "gregsjourney.client.ClientProxy",
                serverSide = "gregsjourney.common.CommonProxy")
    public static CommonProxy serverProxy;

    @Mod.EventHandler
    public void preInit(@NotNull FMLPreInitializationEvent event) {
        GJLog.init(event.getModLog());
        GJMetaBlocks.init();
        GJRecipeMaps.init();
        GJMetaTileEntities.init();
        ForestryModule.preInit(event);

        serverProxy.preLoad();
    }

    @Mod.EventHandler
    public void init(@NotNull FMLInitializationEvent event) {
        ForestryModule.init(event);
        serverProxy.onLoad();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ForestryModule.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {}

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {}

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {}

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {}

    @Mod.EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {}
}
