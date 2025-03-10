package thegreggening.integration.forestry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.util.Mods;
import gregtech.integration.IntegrationModule;
import gregtech.integration.forestry.ForestryConfig;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import thegreggening.integration.forestry.bees.TGBeeDefinition;
import thegreggening.integration.forestry.item.TGCombItem;
import thegreggening.integration.forestry.item.TGDropItem;
import thegreggening.utils.TGLog;

public class ForestryModule {

    // public static GJItemFrame FRAME_TEST;
    public static TGDropItem DROPS;
    public static TGCombItem COMBS;

    public static void preInit(FMLPreInitializationEvent event) {
        // GJ Frames
        if (ForestryConfig.enableGTFrames) {
            if (Mods.ForestryApiculture.isModLoaded()) {
                // FRAME_TEST = new GJItemFrame(GJFrameType.TEST);
            } else {
                TGLog.logger
                        .warn("GregTech Frames are enabled, but Forestry Apiculture module is disabled. Skipping...");
            }
        }
        // GJ Bees
        if (Mods.ForestryApiculture.isModLoaded()) {
            DROPS = new TGDropItem();
            COMBS = new TGCombItem();
        } else {
            TGLog.logger.warn("GregTech Bees are enabled, but Forestry Apiculture module is disabled. Skipping...");
        }
    }

    public static void init(FMLInitializationEvent event) {
        if (Mods.ForestryApiculture.isModLoaded()) {
            TGBeeDefinition.initBees();
            if (event.getSide() == Side.CLIENT) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                    if (stack.getItem() instanceof IColoredItem coloredItem) {
                        return coloredItem.getColorFromItemstack(stack, tintIndex);
                    }
                    return 0xFFFFFF;
                }, DROPS, COMBS);
            }
        }
    }

    public static void postInit(FMLPostInitializationEvent event) {
        TGBeeDefinition.removeMutations();
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        // GT Frames
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {
                // registry.register(FRAME_TEST);
            }
        }
        // GT Drops
        if (Mods.ForestryApiculture.isModLoaded()) {
            registry.register(DROPS);
            registry.register(COMBS);
        }
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {}

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {
                // FRAME_TEST.registerModel(FRAME_TEST, ForestryAPI.modelManager);
            }
            if (ForestryConfig.enableGTBees) {
                DROPS.registerModel(DROPS, ForestryAPI.modelManager);
                COMBS.registerModel(COMBS, ForestryAPI.modelManager);
            }
        }
    }

    public static void registerRecipes() {}

    public static void registerMaterialFlags() {
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {

            }
            if (ForestryConfig.enableGTBees) {
                // Blocks for Bee Breeding
                // createOreProperty(Materials.Chrome, Materials.Iron, Materials.Magnesium);
            }
        }
    }

    private static void createOreProperty(Material material, Material... byproducts) {
        if (material.hasProperty(PropertyKey.ORE)) {
            IntegrationModule.logger.debug("Material {} already has an ore property, skipping...", material);
            return;
        }

        OreProperty property = new OreProperty();
        if (byproducts != null && byproducts.length != 0) {
            property.setOreByProducts(byproducts);
        }
        material.setProperty(PropertyKey.ORE, property);
        material.addFlags(MaterialFlags.DISABLE_ORE_BLOCK);
    }
}
