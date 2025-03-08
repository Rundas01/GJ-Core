package thegreggening;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import org.jetbrains.annotations.NotNull;

import gregtech.GTInternalTags;

import thegreggening.common.CommonProxy;
import thegreggening.common.block.GJMetaBlocks;
import thegreggening.common.metatileentities.TGMetaTileEntities;
import thegreggening.common.recipe.TGRecipeMaps;
import thegreggening.integration.forestry.ForestryModule;
import thegreggening.utils.TGLog;

@Mod(modid = TheGreggening.MODID,
     name = TheGreggening.NAME,
     version = TheGreggening.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + ";required-after:gcym" + ";required-after:forestry")
public class TheGreggening {

    public static final String MODID = "tg";
    public static final String NAME = "The Greggening";
    public static final String VERSION = "1.0";

    @SidedProxy(modId = MODID,
                clientSide = "thegreggening.client.ClientProxy",
                serverSide = "thegreggening.common.CommonProxy")
    public static CommonProxy serverProxy;

    @Mod.EventHandler
    public void preInit(@NotNull FMLPreInitializationEvent event) {
        TGLog.init(event.getModLog());
        GJMetaBlocks.init();
        TGRecipeMaps.init();
        TGMetaTileEntities.init();
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
