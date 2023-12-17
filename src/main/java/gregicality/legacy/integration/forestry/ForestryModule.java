package gregicality.legacy.integration.forestry;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import gregicality.legacy.integration.forestry.recipe.GCYLRCombRecipes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.integration.IntegrationModule;
import gregtech.integration.forestry.ForestryConfig;
import gregtech.integration.forestry.ForestryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ForestryModule {

    public static GCYLRDropItem drops;
    public static GCYLRCombItem combs;

    public static void preInit(FMLPreInitializationEvent event) {
        // GT Bees
        if (ForestryConfig.enableGTBees) {
            if (ForestryUtil.apicultureEnabled()) {
                drops = new GCYLRDropItem();
                combs = new GCYLRCombItem();
            } else {
                IntegrationModule.logger.warn("GregTech Bees are enabled, but Forestry Apiculture module is disabled. Skipping...");
            }
        }
    }

    public static void init(FMLInitializationEvent event) {
        if (ForestryUtil.apicultureEnabled()) {
            if (ForestryConfig.enableGTBees) {
                GTBeeDefinition.initBees();
            }
        }
        if (event.getSide() == Side.CLIENT) {
            if (ForestryUtil.apicultureEnabled()) {
                if (ForestryConfig.enableGTBees) {
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                        if (stack.getItem() instanceof IColoredItem coloredItem) {
                            return coloredItem.getColorFromItemstack(stack, tintIndex);
                        }
                        return 0xFFFFFF;
                    }, drops, combs);
                }
            }
        }
    }

    public static void postInit(FMLPostInitializationEvent event) {
        if (ForestryUtil.apicultureEnabled()) {
            IntegrationModule.logger.info("Copying Forestry Centrifuge recipes to GT Centrifuge");
            GCYLRCombRecipes.initForestryCombs();
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        // GT Drops
        if (ForestryUtil.apicultureEnabled()) {
            if (ForestryConfig.enableGTBees) {
                registry.register(drops);
                registry.register(combs);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        if (ForestryUtil.apicultureEnabled()) {
            if (ForestryConfig.enableGTBees) {
                drops.registerModel(drops, ForestryAPI.modelManager);
                combs.registerModel(combs, ForestryAPI.modelManager);
            }
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (ForestryUtil.apicultureEnabled()) {
            // GT Frames

            // GT Combs
            if (ForestryConfig.enableGTBees) {
                GCYLRCombRecipes.initGTCombs();
            }
        }
    }

    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        if (ForestryUtil.apicultureEnabled()) {
            if (ForestryConfig.enableGTBees) {
                // Blocks for Bee Breeding
                //Materials.Arsenic.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
                //createOreProperty(Materials.NetherStar);
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
