package thegreggening.integration.thaumcraft;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ThaumcraftModule {

    public static void preInit(FMLPreInitializationEvent event) {}

    public static void init(FMLInitializationEvent event) {}

    public static void postInit(FMLPostInitializationEvent event) {}

    public static void registerRecipes() {
        ThaumcraftOreHandler.init();
    }
}
