package gregicality.legacy;

import gregicality.legacy.api.recipe.GCYLRRecipeMaps;
import gregicality.legacy.api.unification.ore.GCYLROrePrefix;
import gregicality.legacy.api.utils.GCYLRLog;
import gregicality.legacy.common.CommonProxy;
import gregicality.legacy.common.block.GCYLRMetaBlocks;
import gregicality.legacy.common.metatileentities.GCYLRMetaTileEntities;
import gregicality.legacy.integration.forestry.ForestryModule;
import gregtech.GTInternalTags;
import gregtech.api.GregTechAPI;
import gregtech.api.modules.ModuleContainerRegistryEvent;
import gregtech.api.util.oreglob.OreGlob;
import gregtech.common.covers.filter.oreglob.impl.OreGlobParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.jetbrains.annotations.NotNull;

@Mod(modid = GregicalityLegacyReimagined.MODID,
     name = GregicalityLegacyReimagined.NAME,
     version = GregicalityLegacyReimagined.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + ";required-after:gcym")
public class GregicalityLegacyReimagined {

    public static final String MODID = "gcylr";
    public static final String NAME = "Gregicality Legacy Reimagined";
    public static final String VERSION = GCYLRInternalTags.VERSION;

    @SidedProxy(modId = MODID,
                clientSide = "gregicality.legacy.common.ClientProxy",
                serverSide = "gregicality.legacy.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(@NotNull FMLPreInitializationEvent event) {
        GCYLRLog.init(event.getModLog());
        GCYLROrePrefix.init();
        GCYLRMetaBlocks.init();
        GCYLRRecipeMaps.init();
        GCYLRMetaTileEntities.init();

        ForestryModule.preInit(event);

        proxy.preLoad();
    }

    @Mod.EventHandler
    public void init(@NotNull FMLInitializationEvent event) {
        ForestryModule.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ForestryModule.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {

    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {

    }

    @Mod.EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {

    }
}
