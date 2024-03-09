package gregsjourney.common.integration.forestry;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import gregsjourney.api.utils.GJLog;
import gregsjourney.common.integration.forestry.bees.GJBeeDefinition;
import gregsjourney.common.integration.forestry.frames.GJFrameType;
import gregsjourney.common.integration.forestry.frames.GJItemFrame;
import gregsjourney.common.integration.forestry.item.GJCombItem;
import gregsjourney.common.integration.forestry.item.GJDropItem;
import gregtech.api.recipes.machines.RecipeMapScanner;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.util.Mods;
import gregtech.integration.IntegrationModule;
import gregtech.integration.forestry.ForestryConfig;
import gregtech.integration.forestry.ForestryUtil;
import gregtech.integration.forestry.bees.BeeRemovals;
import gregtech.integration.forestry.bees.ForestryScannerLogic;
import gregtech.integration.forestry.recipes.CombRecipes;
import gregtech.integration.forestry.recipes.ForestryExtractorRecipes;
import gregtech.integration.forestry.recipes.ForestryFrameRecipes;
import gregtech.integration.forestry.recipes.ForestryMiscRecipes;
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


public class ForestryModule {
    public static GJItemFrame FRAME_TEST;
    public static GJDropItem DROPS;
    public static GJCombItem COMBS;

    public static void preInit(FMLPreInitializationEvent event) {
        // GT Frames
        if (ForestryConfig.enableGTFrames) {
            if (Mods.ForestryApiculture.isModLoaded()) {
                FRAME_TEST = new GJItemFrame(GJFrameType.TEST);
            } else {
                GJLog.logger.warn("GregTech Frames are enabled, but Forestry Apiculture module is disabled. Skipping...");
            }
        }
        // GT Bees
        if (ForestryConfig.enableGTBees) {
            if (Mods.ForestryApiculture.isModLoaded()) {
                DROPS = new GJDropItem();
                COMBS = new GJCombItem();
            } else {
                GJLog.logger.warn("GregTech Bees are enabled, but Forestry Apiculture module is disabled. Skipping...");
            }
        }

        // Remove duplicate/conflicting bees from other Forestry addons.
        // Done in init to have our changes applied before their registration,
        // since we load after other Forestry addons purposefully.
        if (ForestryConfig.disableConflictingBees && Mods.ForestryApiculture.isModLoaded()) {
            BeeRemovals.init();
        }

        // Custom scanner logic for scanning Forestry bees, saplings, etc
        RecipeMapScanner.registerCustomScannerLogic(new ForestryScannerLogic());
    }

    public static void init(FMLInitializationEvent event) {
        // Yes, this recipe stuff has to be done in init. Because Forestry refuses to move their recipes
        // to the event, causing removals to need to be done in init instead of registry event.
        // See https://github.com/ForestryMC/ForestryMC/issues/2599

        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTBees) {
                GJBeeDefinition.initBees();
            }
        }
        if (event.getSide() == Side.CLIENT) {
            if (Mods.ForestryApiculture.isModLoaded()) {
                if (ForestryConfig.enableGTBees) {
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                        if (stack.getItem() instanceof IColoredItem coloredItem) {
                            return coloredItem.getColorFromItemstack(stack, tintIndex);
                        }
                        return 0xFFFFFF;
                    }, DROPS, COMBS);
                }
            }
        }
    }

    public static void postInit(FMLPostInitializationEvent event) {
        if (Mods.ForestryApiculture.isModLoaded()) {
            //CombRecipes.initForestryCombs();
        }
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        // GT Frames
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {
                registry.register(FRAME_TEST);
            }
        }
        // GT Drops
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTBees) {
                registry.register(DROPS);
                registry.register(COMBS);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {
                FRAME_TEST.registerModel(FRAME_TEST, ForestryAPI.modelManager);
            }
            if (ForestryConfig.enableGTBees) {
                DROPS.registerModel(DROPS, ForestryAPI.modelManager);
                COMBS.registerModel(COMBS, ForestryAPI.modelManager);
            }
        }
    }

    public static void registerRecipes() {
        if (Mods.ForestryApiculture.isModLoaded()) {
            // GT Frames
            if (ForestryConfig.enableGTFrames) {
                ForestryFrameRecipes.init();
            }

            // GT Combs
            if (ForestryConfig.enableGTBees) {
                CombRecipes.initGTCombs();
            }
        }

        // Random other recipes
        ForestryMiscRecipes.init();
        ForestryExtractorRecipes.init();
    }

    public static void registerMaterialFlags() {
        if (Mods.ForestryApiculture.isModLoaded()) {
            if (ForestryConfig.enableGTFrames) {
                Materials.TreatedWood.addFlags(MaterialFlags.GENERATE_LONG_ROD);
                Materials.Uranium235.addFlags(MaterialFlags.GENERATE_LONG_ROD);
                Materials.Plutonium241.addFlags(MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_FOIL);
                Materials.BlueSteel.addFlags(MaterialFlags.GENERATE_LONG_ROD);
            }
            if (ForestryConfig.enableGTBees) {
                // Blocks for Bee Breeding

                // Ores for Comb Processing, does not generate Ore Blocks
                //createOreProperty(Materials.Chrome, Materials.Iron, Materials.Magnesium);
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
