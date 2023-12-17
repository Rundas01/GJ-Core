package gregicality.legacy;

import gregicality.legacy.api.recipe.GCYLRRecipeMaps;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.jetbrains.annotations.NotNull;

import gregtech.GTInternalTags;

import gregicality.legacy.api.utils.GCYLRLog;
import gregicality.legacy.common.CommonProxy;
import gregicality.legacy.common.block.GCYLRMetaBlocks;
import gregicality.legacy.common.metatileentities.GCYLRMetaTileEntities;

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
    public void onPreInit(@NotNull FMLPreInitializationEvent event) {
        GCYLRLog.init(event.getModLog());

        GCYLRMetaBlocks.init();
        GCYLRRecipeMaps.init();
        GCYLRMetaTileEntities.init();

        proxy.preLoad();
    }
}
