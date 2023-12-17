package gregicality.legacy.common;

import java.util.Objects;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import gregtech.api.block.VariantItemBlock;

import gregicality.legacy.GregicalityLegacyReimagined;
import gregicality.legacy.api.utils.GCYLRLog;
import gregicality.legacy.common.block.GCYLRMetaBlocks;
import gregicality.legacy.loaders.recipe.GCYLRRecipeLoader;

@Mod.EventBusSubscriber(modid = GregicalityLegacyReimagined.MODID)
public class CommonProxy {

    public void preLoad() {}

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GregicalityLegacyReimagined.MODID)) {
            ConfigManager.sync(GregicalityLegacyReimagined.MODID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        GCYLRLog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(GCYLRMetaBlocks.UNIQUE_CASING);
        registry.register(GCYLRMetaBlocks.LARGE_MULTIBLOCK_CASING);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GCYLRLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(GCYLRMetaBlocks.UNIQUE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GCYLRMetaBlocks.LARGE_MULTIBLOCK_CASING, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GCYLRLog.logger.info("Registering recipes...");

        // Main recipe registration
        // This is called AFTER GregTech registers recipes, so
        // anything here is safe to call removals in
        GCYLRRecipeLoader.init();
    }
}
