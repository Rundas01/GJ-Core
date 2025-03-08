package thegreggening.common;

import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import thegreggening.TheGreggening;
import thegreggening.common.block.GJMetaBlocks;
import thegreggening.integration.forestry.ForestryModule;
import thegreggening.common.recipe.TGRecipeLoader;
import thegreggening.utils.TGLog;

import java.util.Objects;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = TheGreggening.MODID)
public class CommonProxy {

    public void preLoad() {}

    public void onLoad() {}

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(TheGreggening.MODID)) {
            ConfigManager.sync(TheGreggening.MODID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        TGLog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(GJMetaBlocks.LARGE_MULTIBLOCK_CASING);
        registry.register(GJMetaBlocks.ALTERNATOR_COIL);
        registry.register(GJMetaBlocks.TURBINE_ROTOR);

        ForestryModule.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        TGLog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(GJMetaBlocks.LARGE_MULTIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GJMetaBlocks.ALTERNATOR_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GJMetaBlocks.TURBINE_ROTOR, VariantItemBlock::new));

        ForestryModule.registerItems(event);
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        TGLog.logger.info("Registering recipes...");
        TGRecipeLoader.init();
    }
}
