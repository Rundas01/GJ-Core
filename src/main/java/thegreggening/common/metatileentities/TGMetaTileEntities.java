package thegreggening.common.metatileentities;

import static thegreggening.utils.TGUtil.tgId;
import static gregtech.api.util.GTUtility.defaultTankSizeFunction;
import static gregtech.api.util.GTUtility.largeTankSizeFunction;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import java.util.function.Function;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;

import thegreggening.api.metatileentity.singleblock.MetaTileEntityGeneratorWithOutput;
import thegreggening.api.render.TGTextures;
import thegreggening.common.metatileentities.multiblock.electric.*;
import thegreggening.common.metatileentities.multiblock.primitive.MetaTileEntityCrucible;
import thegreggening.common.metatileentities.multiblock.primitive.MetaTileEntityHeatExchanger;
import thegreggening.common.metatileentities.part.*;
import thegreggening.common.recipe.TGRecipeMaps;
import thegreggening.utils.TGUtil;

public final class TGMetaTileEntities {

    private TGMetaTileEntities() {}

    public static SimpleMachineMetaTileEntity[] ROASTER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] BATCH_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] GAS_BUBBLE_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleMachineMetaTileEntity[] DISSOLUTION_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleMachineMetaTileEntity[] MIXING_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CATALYTIC_REACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleMachineMetaTileEntity[] POLYMERIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CRYSTALLIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_DEHYDRATOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleGeneratorMetaTileEntity[] DECAY_GENERATOR = new SimpleGeneratorMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] DECAY_HASTENER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] ISOTOPIC_STABILIZER = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleMachineMetaTileEntity[] MATTER_FABRICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length -
            1];
    public static MetaTileEntityBeeHatch[] BEE_HATCH = new MetaTileEntityBeeHatch[6];
    public static MetaTileEntityHeatHatch HEAT_HATCH;
    public static MetaTileEntityReactorCooler REACTOR_COOLER;
    public static MetaTileEntityFissionChamber FISSION_CHAMBER;
    public static MetaTileEntityBreedingChamber BREEDING_CHAMBER;
    public static SimpleGeneratorMetaTileEntity[] ROCKET_ENGINE = new SimpleGeneratorMetaTileEntity[3];
    public static MetaTileEntityAdvancedRotorHolder[] ADVANCED_ROTOR_HOLDERS = new MetaTileEntityAdvancedRotorHolder[6];
    public static MetaTileEntityCircuitBus CIRCUIT_BUS;
    public static MetaTileEntityNeutronEmitter NEUTRON_EMITTER;

    // Multiblock
    public static MetaTileEntityCrucible CRUCIBLE;
    public static MetaTileEntityAdvancedArcFurnace ADVANCED_ARC_FURNACE;
    public static MetaTileEntityFlotationCell FLOTATION_CELL;
    public static MetaTileEntityHeatExchanger HEAT_EXCHANGER;
    public static MetaTileEntityAdvancedTurbine ADVANCED_STEAM_TURBINE;
    public static MetaTileEntityAdvancedTurbine ADVANCED_COMBUSTION_TURBINE;
    public static MetaTileEntityAdvancedTurbine ADVANCED_ROCKET_TURBINE;
    public static MetaTileEntityAdvancedTurbine ADVANCED_PLASMA_TURBINE;
    public static MetaTileEntityAdvancedElectrolyzer ADVANCED_ELECTROLYZER;
    public static MetaTileEntityIndustrialAlveary MEGA_ALVEARY;
    public static MetaTileEntityCombProcessor COMB_PROCESSOR;
    public static MetaTileEntityClarificationPlant CLARIFICATION_PLANT;
    public static MetaTileEntityReactionFurnace REACTION_FURNACE;
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;
    public static MetaTileEntityIsotopicEnricher ISOTOPIC_ENRICHER;
    public static MetaTileEntityFuelReprocessor FUEL_REPROCESSOR;

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name,
                                                     RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing,
                                                     Function<Integer, Integer> tankScalingFunction) {
        gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity(machines, startId, name, map,
                texture, hasFrontFacing, TGUtil::tgId, tankScalingFunction);
    }

    public static void init() {
        // Singleblocks
        registerSimpleMetaTileEntity(ROASTER, 2200, "roaster", TGRecipeMaps.ROASTING_RECIPES,
                TGTextures.ROASTER_OVERLAY, true, largeTankSizeFunction);
        registerSimpleMetaTileEntity(BATCH_REACTOR, 2215, "batch_reactor", TGRecipeMaps.BATCH_REACTOR_RECIPES,
                TGTextures.BATCH_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(GAS_BUBBLE_REACTOR, 2230, "gas_bubble_reactor", TGRecipeMaps.BUBBLE_RECIPES,
                TGTextures.BUBBLE_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(DISSOLUTION_REACTOR, 2245, "dissolution_reactor",
                TGRecipeMaps.DISSOLUTION_REACTOR_RECIPES, TGTextures.MIXING_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(MIXING_REACTOR, 2260, "mixing_reactor", TGRecipeMaps.MIXING_REACTOR_RECIPES,
                TGTextures.MIXING_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CATALYTIC_REACTOR, 2275, "catalytic_reactor",
                TGRecipeMaps.CATALYTIC_REACTOR_RECIPES, TGTextures.CATALYTIC_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(POLYMERIZER, 2290, "polymerizer", TGRecipeMaps.POLYMERIZATION_RECIPES,
                TGTextures.POLYMERIZER_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CRYSTALLIZER, 2305, "crystallizer", TGRecipeMaps.CRYSTALLIZATION_RECIPES,
                TGTextures.CRYSTALLIZER_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR, 2320, "chemical_dehydrator",
                TGRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                TGTextures.DEHYDRATOR_OVERLAY, true, defaultTankSizeFunction);
        BEE_HATCH[0] = registerMetaTileEntity(2335, new MetaTileEntityBeeHatch(tgId("bee_hatch.hv"), 3));
        BEE_HATCH[1] = registerMetaTileEntity(2336, new MetaTileEntityBeeHatch(tgId("bee_hatch.ev"), 4));
        BEE_HATCH[2] = registerMetaTileEntity(2337, new MetaTileEntityBeeHatch(tgId("bee_hatch.iv"), 5));
        BEE_HATCH[3] = registerMetaTileEntity(2338, new MetaTileEntityBeeHatch(tgId("bee_hatch.luv"), 6));
        BEE_HATCH[4] = registerMetaTileEntity(2339, new MetaTileEntityBeeHatch(tgId("bee_hatch.zpm"), 7));
        BEE_HATCH[5] = registerMetaTileEntity(2340, new MetaTileEntityBeeHatch(tgId("bee_hatch.uv"), 8));
        HEAT_HATCH = registerMetaTileEntity(2342, new MetaTileEntityHeatHatch(tgId("heat_hatch")));
        // Multiblocks
        CRUCIBLE = registerMetaTileEntity(2343, new MetaTileEntityCrucible(tgId("crucible")));
        FLOTATION_CELL = registerMetaTileEntity(2344, new MetaTileEntityFlotationCell(tgId("flotation_cell")));
        ADVANCED_ARC_FURNACE = registerMetaTileEntity(2345,
                new MetaTileEntityAdvancedArcFurnace(tgId("advanced_arc_furnace")));
        HEAT_EXCHANGER = registerMetaTileEntity(2346,
                new MetaTileEntityHeatExchanger(tgId("heat_exchanger")));
        ADVANCED_STEAM_TURBINE = registerMetaTileEntity(2347,
                new MetaTileEntityAdvancedTurbine(tgId("advanced_large_steam_turbine"),
                        RecipeMaps.STEAM_TURBINE_FUELS, 1,
                        MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                        Textures.SOLID_STEEL_CASING, TGTextures.ADVANCED_COMBUSTION_TURBINE_OVERLAY));
        ADVANCED_COMBUSTION_TURBINE = registerMetaTileEntity(2348,
                new MetaTileEntityAdvancedTurbine(tgId("advanced_large_combustion_turbine"),
                        TGRecipeMaps.ADVANCED_COMBUSTION_FUELS, 2,
                        MetaBlocks.TURBINE_CASING
                                .getState(BlockTurbineCasing.TurbineCasingType.STAINLESS_TURBINE_CASING),
                        Textures.CLEAN_STAINLESS_STEEL_CASING, TGTextures.ADVANCED_STEAM_TURBINE_OVERLAY));
        ADVANCED_ELECTROLYZER = registerMetaTileEntity(2349,
                new MetaTileEntityAdvancedElectrolyzer(tgId("advanced_electrolyzer")));
        MEGA_ALVEARY = registerMetaTileEntity(2350, new MetaTileEntityIndustrialAlveary(tgId("mega_alveary")));
        COMB_PROCESSOR = registerMetaTileEntity(2351, new MetaTileEntityCombProcessor(tgId("comb_processor")));
        CLARIFICATION_PLANT = registerMetaTileEntity(2352,
                new MetaTileEntityClarificationPlant(tgId("clarification_plant")));
        REACTION_FURNACE = registerMetaTileEntity(2353,
                new MetaTileEntityReactionFurnace(tgId("reaction_furnace")));
        NUCLEAR_REACTOR = registerMetaTileEntity(2354,
                new MetaTileEntityNuclearReactor(tgId("nuclear_reactor")));
        // zeugs, dass noch einsortiert werden muss
        DECAY_GENERATOR[0] = registerMetaTileEntity(2355,
                new MetaTileEntityGeneratorWithOutput(tgId("decay_generator.lv"), TGRecipeMaps.DECAY_GENERATOR_RECIPES,
                        Textures.STEAM_TURBINE_OVERLAY, 1, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[1] = registerMetaTileEntity(2356,
                new MetaTileEntityGeneratorWithOutput(tgId("decay_generator.mv"), TGRecipeMaps.DECAY_GENERATOR_RECIPES,
                        Textures.STEAM_TURBINE_OVERLAY, 2, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[2] = registerMetaTileEntity(2357,
                new MetaTileEntityGeneratorWithOutput(tgId("decay_generator.hv"), TGRecipeMaps.DECAY_GENERATOR_RECIPES,
                        Textures.STEAM_TURBINE_OVERLAY, 3, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[3] = registerMetaTileEntity(2358,
                new MetaTileEntityGeneratorWithOutput(tgId("decay_generator.ev"), TGRecipeMaps.DECAY_GENERATOR_RECIPES,
                        Textures.STEAM_TURBINE_OVERLAY, 4, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[4] = registerMetaTileEntity(2359,
                new MetaTileEntityGeneratorWithOutput(tgId("decay_generator.iv"), TGRecipeMaps.DECAY_GENERATOR_RECIPES,
                        Textures.STEAM_TURBINE_OVERLAY, 5, GTUtility.genericGeneratorTankSizeFunction));

        registerSimpleMetaTileEntity(DECAY_HASTENER, 2360, "decay_hastener", TGRecipeMaps.DECAY_HASTENER_RECIPES,
                TGTextures.DECAY_HASTENER_OVERLAY, true, defaultTankSizeFunction);

        registerSimpleMetaTileEntity(ISOTOPIC_STABILIZER, 2375, "isotopic_stabilizer",
                TGRecipeMaps.ISOTOPIC_STABILIZER_RECIPES,
                TGTextures.ISOTOPIC_STABILIZER_OVERLAY, true, defaultTankSizeFunction);

        REACTOR_COOLER = registerMetaTileEntity(2390,
                new MetaTileEntityReactorCooler(tgId("reactor_cooler")));
        FISSION_CHAMBER = registerMetaTileEntity(2391,
                new MetaTileEntityFissionChamber(tgId("fission_chamber")));
        BREEDING_CHAMBER = registerMetaTileEntity(2392,
                new MetaTileEntityBreedingChamber(tgId("breeding_chamber")));
        ROCKET_ENGINE[0] = registerMetaTileEntity(2402,
                new SimpleGeneratorMetaTileEntity(tgId("rocket_engine.hv"), TGRecipeMaps.ROCKET_ENGINE_FUELS,
                        Textures.STEAM_TURBINE_OVERLAY, 3, GTUtility.genericGeneratorTankSizeFunction));
        ROCKET_ENGINE[1] = registerMetaTileEntity(2403,
                new SimpleGeneratorMetaTileEntity(tgId("rocket_engine.ev"), TGRecipeMaps.ROCKET_ENGINE_FUELS,
                        Textures.STEAM_TURBINE_OVERLAY, 4, GTUtility.genericGeneratorTankSizeFunction));
        ROCKET_ENGINE[2] = registerMetaTileEntity(2404,
                new SimpleGeneratorMetaTileEntity(tgId("rocket_engine.iv"), TGRecipeMaps.ROCKET_ENGINE_FUELS,
                        Textures.STEAM_TURBINE_OVERLAY, 5, GTUtility.genericGeneratorTankSizeFunction));
        ADVANCED_ROCKET_TURBINE = registerMetaTileEntity(2405,
                new MetaTileEntityAdvancedTurbine(tgId("advanced_large_rocket_turbine"),
                        TGRecipeMaps.ROCKET_ENGINE_FUELS, 3,
                        MetaBlocks.TURBINE_CASING
                                .getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_TURBINE_CASING),
                        Textures.STABLE_TITANIUM_CASING, TGTextures.ADVANCED_STEAM_TURBINE_OVERLAY));
        ADVANCED_PLASMA_TURBINE = registerMetaTileEntity(2406,
                new MetaTileEntityAdvancedTurbine(tgId("advanced_large_plasma_turbine"), TGRecipeMaps.PLASMA_FUELS, 4,
                        MetaBlocks.TURBINE_CASING
                                .getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_TURBINE_CASING),
                        Textures.ROBUST_TUNGSTENSTEEL_CASING, TGTextures.ADVANCED_STEAM_TURBINE_OVERLAY));
        ADVANCED_ROTOR_HOLDERS[0] = registerMetaTileEntity(2407,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.hv"), GTValues.HV));
        ADVANCED_ROTOR_HOLDERS[1] = registerMetaTileEntity(2408,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.ev"), GTValues.EV));
        ADVANCED_ROTOR_HOLDERS[2] = registerMetaTileEntity(2409,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.iv"), GTValues.IV));
        ADVANCED_ROTOR_HOLDERS[3] = registerMetaTileEntity(2410,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.luv"), GTValues.LuV));
        ADVANCED_ROTOR_HOLDERS[4] = registerMetaTileEntity(2411,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.zpm"), GTValues.ZPM));
        ADVANCED_ROTOR_HOLDERS[5] = registerMetaTileEntity(2412,
                new MetaTileEntityAdvancedRotorHolder(tgId("advanced_rotor_holder.uv"), GTValues.UV));
        ISOTOPIC_ENRICHER = registerMetaTileEntity(2413, new MetaTileEntityIsotopicEnricher(tgId("isotopic_enricher")));
        FUEL_REPROCESSOR = registerMetaTileEntity(2414, new MetaTileEntityFuelReprocessor(tgId("fuel_reprocessor")));
        CIRCUIT_BUS = registerMetaTileEntity(2415, new MetaTileEntityCircuitBus(tgId("circuit_bus")));
        registerSimpleMetaTileEntity(MATTER_FABRICATOR, 2416, "matter_fabricator",
                TGRecipeMaps.MATTER_FABRICATOR_RECIPES,
                TGTextures.MATTER_FABRICATOR_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(REPLICATOR, 2431, "replicator",
                TGRecipeMaps.REPLICATOR_RECIPES,
                TGTextures.REPLICATOR_OVERLAY, true, defaultTankSizeFunction);
        NEUTRON_EMITTER = registerMetaTileEntity(2447, new MetaTileEntityNeutronEmitter(tgId("neutron_emitter")));
    }
}
