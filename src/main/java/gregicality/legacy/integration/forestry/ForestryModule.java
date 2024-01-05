package gregicality.legacy.integration.forestry;

import forestry.api.core.ForestryAPI;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.items.IColoredItem;
import gregicality.legacy.integration.forestry.recipe.GCYLRCombRecipes;
import gregtech.api.GTValues;
import gregtech.api.block.VariantBlock;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockCompressed;
import gregtech.integration.IntegrationModule;
import gregtech.integration.IntegrationUtil;
import gregtech.integration.forestry.ForestryConfig;
import gregtech.integration.forestry.ForestryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;

import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.unification.material.Materials.Cobalt;
import static gregtech.api.unification.material.Materials.Mica;
import static gregtech.api.unification.ore.OrePrefix.block;

public class ForestryModule {

    public static GTDropItem drops;
    public static GTCombItem combs;

    public static void preInit(FMLPreInitializationEvent event) {
        // GT Bees
        if (ForestryConfig.enableGTBees) {
            if (ForestryUtil.apicultureEnabled()) {
                drops = new GTDropItem();
                combs = new GTCombItem();
            } else {
                IntegrationModule.logger.warn("GregTech Bees are enabled, but Forestry Apiculture module is disabled. Skipping...");
            }
        }
    }

    public static void init(FMLInitializationEvent event) {
        if (ForestryUtil.apicultureEnabled()) {
            if (ForestryConfig.enableGTBees) {
                GTBeeDefinition.initBees();
                if(Loader.isModLoaded("tconstruct")){
                    gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition.initBees();
                }
                /*
                if(Loader.isModLoaded("galacticraft")){
                    gregicality.legacy.integration.forestry.integration.galacticraft.GTBeeDefinition.initBees();
                }
                if(Loader.isModLoaded("thaumcraft")){
                    gregicality.legacy.integration.forestry.integration.thaumcraft.GTBeeDefinition.initBees();
                }
                if(Loader.isModLoaded("astralsorcery")){
                    gregicality.legacy.integration.forestry.integration.astral.GTBeeDefinition.initBees();
                }
                */
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
                Mica.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
                Cobalt.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
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

    public static ItemStack getForestryComb(EnumHoneyComb type) {
        return ModuleApiculture.getItems().beeComb.get(type, 1);
    }

    @Optional.Method(modid = GTValues.MODID_EB)
    public static ItemStack getExtraBeesComb(int meta) {
        return IntegrationUtil.getModItem(GTValues.MODID_EB, "honey_comb", meta);
    }

    @Optional.Method(modid = GTValues.MODID_MB)
    public static ItemStack getMagicBeesComb(int meta) {
        return IntegrationUtil.getModItem(GTValues.MODID_MB, "beecomb", meta);
    }

    public static ItemStack getGCYLRComb(GTCombType type) {
        return new ItemStack(ForestryModule.combs, 1, type.ordinal());
    }

    public static ItemStack getGCYLRTinkersComb(gregicality.legacy.integration.forestry.integration.tinkers.GTCombType type) {
        return new ItemStack(ForestryModule.combs, 1, type.ordinal());
    }

    public static ItemStack getGTComb(gregtech.integration.forestry.bees.GTCombType type) {
        return new ItemStack(gregtech.integration.forestry.ForestryModule.COMBS, 1, type.ordinal());
    }
}
