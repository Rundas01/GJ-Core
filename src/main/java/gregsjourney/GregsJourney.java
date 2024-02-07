package gregsjourney;

import gregsjourney.common.block.GJMetaBlocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import org.jetbrains.annotations.NotNull;

import gregtech.GTInternalTags;

import gregsjourney.api.recipe.GJRecipeMaps;
import gregsjourney.api.utils.GJLog;
import gregsjourney.common.CommonProxy;
import gregsjourney.common.metatileentities.GJMetaTileEntities;

@Mod(modid = GregsJourney.MODID,
     name = GregsJourney.NAME,
     version = GregsJourney.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + ";required-after:gcym")
public class GregsJourney {

    public static final String MODID = "gj";
    public static final String NAME = "Greg's Journey";
    public static final String VERSION = GJInternalTags.VERSION;

    @SidedProxy(modId = MODID,
                clientSide = "gregsjourney.common.ClientProxy",
                serverSide = "gregsjourney.legacy.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(@NotNull FMLPreInitializationEvent event) {
        GJLog.init(event.getModLog());
        GJMetaBlocks.init();
        GJRecipeMaps.init();
        GJMetaTileEntities.init();

        /*
         * if(Loader.isModLoaded("forestry")){
         * ForestryModule.preInit(event);
         * }
         */

        proxy.preLoad();
    }

    @Mod.EventHandler
    public void init(@NotNull FMLInitializationEvent event) {
        /*
         * if(Loader.isModLoaded("forestry")){
         * ForestryModule.init(event);
         * }
         */
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        /*
         * if(Loader.isModLoaded("forestry")){
         * ForestryModule.postInit(event);
         * }
         */
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
