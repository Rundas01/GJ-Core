package gregsjourney.common.metatileentities;

import gregsjourney.api.metatileentity.singleblock.MetaTileEntityGeneratorWithOutput;
import gregsjourney.api.render.GJTextures;
import gregsjourney.common.metatileentities.multiblock.electric.*;
import gregsjourney.common.metatileentities.multiblock.primitive.MetaTileEntityCrucible;
import gregsjourney.common.metatileentities.multiblock.primitive.MetaTileEntityHeatExchanger;
import gregsjourney.common.metatileentities.part.MetaTileEntityBeeHatch;
import gregsjourney.common.metatileentities.part.MetaTileEntityCoolantHatch;
import gregsjourney.common.metatileentities.part.MetaTileEntityHeatHatch;
import gregsjourney.common.metatileentities.part.OreDictItemBus;
import gregsjourney.common.metatileentities.singleblock.MetaTileEntityOredictFilteredStacksizeBuffer;
import gregsjourney.common.metatileentities.singleblock.MetaTileEntityStacksizeBuffer;
import gregsjourney.common.recipe.GJRecipeMaps;
import gregsjourney.utils.GJUtil;
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

import java.util.function.Function;

import static gregsjourney.utils.GJUtil.gjId;
import static gregtech.api.util.GTUtility.defaultTankSizeFunction;
import static gregtech.api.util.GTUtility.largeTankSizeFunction;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public final class GJMetaTileEntities {

    private GJMetaTileEntities() {}

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
    public static SimpleMachineMetaTileEntity[] CHEMICAL_DEHYDRATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleGeneratorMetaTileEntity[] DECAY_GENERATOR = new SimpleGeneratorMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] DECAY_HASTENER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] ISOTOPIC_STABILIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityBeeHatch[] BEE_HATCH = new MetaTileEntityBeeHatch[6];
    public static MetaTileEntityHeatHatch HEAT_HATCH;
    public static MetaTileEntityCoolantHatch[] COOLANT_HATCH = new MetaTileEntityCoolantHatch[3];
    public static OreDictItemBus[] OREDICT_INPUT_BUS = new OreDictItemBus[3];
    public static OreDictItemBus[] OREDICT_OUTPUT_BUS = new OreDictItemBus[3];
    public static MetaTileEntityStacksizeBuffer STACKSIZE_BUFFER;
    public static MetaTileEntityOredictFilteredStacksizeBuffer OREDICT_FILTERED_STACKSIZE_BUFFER;
    //Multiblock
    public static MetaTileEntityCrucible CRUCIBLE;
    public static MetaTileEntityAdvancedArcFurnace ADVANCED_ARC_FURNACE;
    public static MetaTileEntityFlotationCell FLOTATION_CELL;
    public static MetaTileEntityHeatExchanger HEAT_EXCHANGER;
    public static MetaTileEntityAdvancedTurbine ADVANCED_STEAM_TURBINE;
    public static MetaTileEntityAdvancedTurbine ADVANCED_COMBUSTION_TURBINE;
    public static MetaTileEntityAdvancedElectrolyzer ADVANCED_ELECTROLYZER;
    public static MetaTileEntityIndustrialAlveary MEGA_ALVEARY;
    public static MetaTileEntityCombProcessor COMB_PROCESSOR;
    public static MetaTileEntityClarificationPlant CLARIFICATION_PLANT;
    public static MetaTileEntityReactionFurnace REACTION_FURNACE;
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name,
                                                     RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing,
                                                     Function<Integer, Integer> tankScalingFunction) {
        gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity(machines, startId, name, map,
                texture, hasFrontFacing, GJUtil::gjId, tankScalingFunction);
    }

    public static void init() {
        // Singleblocks
        registerSimpleMetaTileEntity(ROASTER, 2200, "roaster", GJRecipeMaps.ROASTING_RECIPES,
                GJTextures.ROASTER_OVERLAY, true, largeTankSizeFunction);
        registerSimpleMetaTileEntity(BATCH_REACTOR, 2215, "batch_reactor", GJRecipeMaps.BATCH_REACTOR_RECIPES,
                GJTextures.BATCH_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(GAS_BUBBLE_REACTOR, 2230, "gas_bubble_reactor", GJRecipeMaps.BUBBLE_RECIPES,
                GJTextures.BUBBLE_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(DISSOLUTION_REACTOR, 2245, "dissolution_reactor",
                GJRecipeMaps.DISSOLUTION_REACTOR_RECIPES, GJTextures.MIXING_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(MIXING_REACTOR, 2260, "mixing_reactor", GJRecipeMaps.MIXING_REACTOR_RECIPES,
                GJTextures.MIXING_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CATALYTIC_REACTOR, 2275, "catalytic_reactor",
                GJRecipeMaps.CATALYTIC_REACTOR_RECIPES, GJTextures.CATALYTIC_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(POLYMERIZER, 2290, "polymerizer", GJRecipeMaps.POLYMERIZATION_RECIPES,
                GJTextures.POLYMERIZER_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CRYSTALLIZER, 2305, "crystallizer", GJRecipeMaps.CRYSTALLIZATION_RECIPES,
                GJTextures.CRYSTALLIZER_OVERLAY, true, defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR, 2320, "chemical_dehydrator", GJRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                GJTextures.DEHYDRATOR_OVERLAY, true, defaultTankSizeFunction);
        BEE_HATCH[0] = registerMetaTileEntity(2335, new MetaTileEntityBeeHatch(gjId("bee_hatch.hv"), 3));
        BEE_HATCH[1] = registerMetaTileEntity(2336, new MetaTileEntityBeeHatch(gjId("bee_hatch.ev"), 4));
        BEE_HATCH[2] = registerMetaTileEntity(2337, new MetaTileEntityBeeHatch(gjId("bee_hatch.iv"), 5));
        BEE_HATCH[3] = registerMetaTileEntity(2338, new MetaTileEntityBeeHatch(gjId("bee_hatch.luv"), 6));
        BEE_HATCH[4] = registerMetaTileEntity(2339, new MetaTileEntityBeeHatch(gjId("bee_hatch.zpm"), 7));
        BEE_HATCH[5] = registerMetaTileEntity(2340, new MetaTileEntityBeeHatch(gjId("bee_hatch.uv"), 8));
        HEAT_HATCH = registerMetaTileEntity(2342, new MetaTileEntityHeatHatch(gjId("heat_hatch")));
        // Multiblocks
        CRUCIBLE = registerMetaTileEntity(2343, new MetaTileEntityCrucible(gjId("crucible")));
        FLOTATION_CELL = registerMetaTileEntity(2344, new MetaTileEntityFlotationCell(gjId("flotation_cell")));
        ADVANCED_ARC_FURNACE = registerMetaTileEntity(2345,
                new MetaTileEntityAdvancedArcFurnace(gjId("advanced_arc_furnace")));
        HEAT_EXCHANGER = registerMetaTileEntity(2346,
                new MetaTileEntityHeatExchanger(gjId("heat_exchanger")));
        ADVANCED_STEAM_TURBINE = registerMetaTileEntity(2347,
                new MetaTileEntityAdvancedTurbine(gjId("advanced_large_steam_turbine"),
                        RecipeMaps.STEAM_TURBINE_FUELS, 1,
                        MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                        Textures.SOLID_STEEL_CASING, GJTextures.ADVANCED_COMBUSTION_TURBINE_OVERLAY));
        ADVANCED_COMBUSTION_TURBINE = registerMetaTileEntity(2348,
                new MetaTileEntityAdvancedTurbine(gjId("advanced_large_combustion_turbine"),
                        GJRecipeMaps.ADVANCED_COMBUSTION_FUELS, 2,
                        MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                        Textures.SOLID_STEEL_CASING, GJTextures.ADVANCED_STEAM_TURBINE_OVERLAY));
        ADVANCED_ELECTROLYZER = registerMetaTileEntity(2349,
                new MetaTileEntityAdvancedElectrolyzer(gjId("advanced_electrolyzer")));
        MEGA_ALVEARY = registerMetaTileEntity(2350, new MetaTileEntityIndustrialAlveary(gjId("mega_alveary")));
        COMB_PROCESSOR = registerMetaTileEntity(2351, new MetaTileEntityCombProcessor(gjId("comb_processor")));
        CLARIFICATION_PLANT = registerMetaTileEntity(2352,
                new MetaTileEntityClarificationPlant(gjId("clarification_plant")));
        REACTION_FURNACE = registerMetaTileEntity(2353,
                new MetaTileEntityReactionFurnace(gjId("reaction_furnace")));
        NUCLEAR_REACTOR = registerMetaTileEntity(2354,
                new MetaTileEntityNuclearReactor(gjId("nuclear_reactor")));
        //zeugs, dass noch einsortiert werden muss
        DECAY_GENERATOR[0] = registerMetaTileEntity(2355, new MetaTileEntityGeneratorWithOutput(gjId("decay_generator.lv"), GJRecipeMaps.DECAY_GENERATOR_RECIPES, Textures.STEAM_TURBINE_OVERLAY, 1, GTUtility.steamGeneratorTankSizeFunction));
        DECAY_GENERATOR[1] = registerMetaTileEntity(2356, new MetaTileEntityGeneratorWithOutput(gjId("decay_generator.mv"), GJRecipeMaps.DECAY_GENERATOR_RECIPES, Textures.STEAM_TURBINE_OVERLAY, 2, GTUtility.steamGeneratorTankSizeFunction));
        DECAY_GENERATOR[2] = registerMetaTileEntity(2357, new MetaTileEntityGeneratorWithOutput(gjId("decay_generator.hv"), GJRecipeMaps.DECAY_GENERATOR_RECIPES, Textures.STEAM_TURBINE_OVERLAY, 3, GTUtility.steamGeneratorTankSizeFunction));
        DECAY_GENERATOR[3] = registerMetaTileEntity(2358, new MetaTileEntityGeneratorWithOutput(gjId("decay_generator.ev"), GJRecipeMaps.DECAY_GENERATOR_RECIPES, Textures.STEAM_TURBINE_OVERLAY, 4, GTUtility.steamGeneratorTankSizeFunction));
        DECAY_GENERATOR[4] = registerMetaTileEntity(2359, new MetaTileEntityGeneratorWithOutput(gjId("decay_generator.iv"), GJRecipeMaps.DECAY_GENERATOR_RECIPES, Textures.STEAM_TURBINE_OVERLAY, 5, GTUtility.steamGeneratorTankSizeFunction));

        registerSimpleMetaTileEntity(DECAY_HASTENER, 2360, "decay_hastener", GJRecipeMaps.DECAY_HASTENER_RECIPES,
                GJTextures.DECAY_HASTENER_OVERLAY, true, defaultTankSizeFunction);

        registerSimpleMetaTileEntity(ISOTOPIC_STABILIZER, 2375, "isotopic_stabilizer", GJRecipeMaps.ISOTOPIC_STABILIZER_RECIPES,
                GJTextures.ISOTOPIC_STABILIZER_OVERLAY, true, defaultTankSizeFunction);

        COOLANT_HATCH[0] = registerMetaTileEntity(2390, new MetaTileEntityCoolantHatch(gjId("coolant_hatch.luv"), 6, 1));
        COOLANT_HATCH[1] = registerMetaTileEntity(2391, new MetaTileEntityCoolantHatch(gjId("coolant_hatch.zpm"), 7, 4));
        COOLANT_HATCH[2] = registerMetaTileEntity(2392, new MetaTileEntityCoolantHatch(gjId("coolant_hatch.uv"), 8, 9));
        OREDICT_INPUT_BUS[0] = registerMetaTileEntity(2393, new OreDictItemBus(gjId("oredict_input_bus.lv"), 1, false));
        OREDICT_INPUT_BUS[1] = registerMetaTileEntity(2394, new OreDictItemBus(gjId("oredict_input_bus.mv"), 2,false));
        OREDICT_INPUT_BUS[2] = registerMetaTileEntity(2395, new OreDictItemBus(gjId("oredict_input_bus.hv"), 3,false));
        OREDICT_OUTPUT_BUS[0] = registerMetaTileEntity(2396, new OreDictItemBus(gjId("oredict_output_bus.lv"), 1,true));
        OREDICT_OUTPUT_BUS[1] = registerMetaTileEntity(2397, new OreDictItemBus(gjId("oredict_output_bus.mv"), 2,true));
        OREDICT_OUTPUT_BUS[2] = registerMetaTileEntity(2398, new OreDictItemBus(gjId("oredict_output_bus.hv"), 3,true));
        STACKSIZE_BUFFER = registerMetaTileEntity(2399, new MetaTileEntityStacksizeBuffer(gjId("stacksize_buffer")));
        OREDICT_FILTERED_STACKSIZE_BUFFER = registerMetaTileEntity(2400, new MetaTileEntityOredictFilteredStacksizeBuffer(gjId("item_filtered_buffer")));
    }
}
