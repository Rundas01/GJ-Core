package gregsjourney.common.metatileentities;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.util.GTUtility.defaultTankSizeFunction;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import java.util.function.Function;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;

import gregsjourney.api.render.GJTextures;
import gregsjourney.api.utils.GJUtil;
import gregsjourney.common.metatileentities.multiblock.electric.*;
import gregsjourney.common.metatileentities.multiblock.electric.MetaTileEntityCrucible;
import gregsjourney.common.metatileentities.part.MetaTileEntityBeeHatch;
import gregsjourney.common.metatileentities.part.MetaTileEntityHeatHatch;
import gregsjourney.common.recipe.GJRecipeMaps;

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
    public static MetaTileEntityBeeHatch[] BEE_HATCH = new MetaTileEntityBeeHatch[7];
    public static MetaTileEntityHeatHatch HEAT_HATCH;
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

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name,
                                                     RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing,
                                                     Function<Integer, Integer> tankScalingFunction) {
        gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity(machines, startId, name, map,
                texture, hasFrontFacing, GJUtil::gjId, tankScalingFunction);
    }

    public static void init() {
        // Singleblocks
        registerSimpleMetaTileEntity(ROASTER, 2200, "roaster", GJRecipeMaps.ROASTING_RECIPES,
                GJTextures.ROASTER_OVERLAY, true, defaultTankSizeFunction);
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
        registerSimpleMetaTileEntity(CRYSTALLIZER, 2305, "crystallizer", GJRecipeMaps.POLYMERIZATION_RECIPES,
                GJTextures.POLYMERIZER_OVERLAY, true, defaultTankSizeFunction);
        BEE_HATCH[0] = registerMetaTileEntity(2320, new MetaTileEntityBeeHatch(gjId("bee_hatch_hv"), 3));
        BEE_HATCH[1] = registerMetaTileEntity(2321, new MetaTileEntityBeeHatch(gjId("bee_hatch_ev"), 4));
        BEE_HATCH[2] = registerMetaTileEntity(2322, new MetaTileEntityBeeHatch(gjId("bee_hatch_iv"), 5));
        BEE_HATCH[3] = registerMetaTileEntity(2323, new MetaTileEntityBeeHatch(gjId("bee_hatch_luv"), 6));
        BEE_HATCH[4] = registerMetaTileEntity(2324, new MetaTileEntityBeeHatch(gjId("bee_hatch_zpm"), 7));
        BEE_HATCH[5] = registerMetaTileEntity(2325, new MetaTileEntityBeeHatch(gjId("bee_hatch_uv"), 8));
        BEE_HATCH[6] = registerMetaTileEntity(2326, new MetaTileEntityBeeHatch(gjId("bee_hatch_uhv"), 9));
        HEAT_HATCH = registerMetaTileEntity(2327, new MetaTileEntityHeatHatch(gjId("heat_hatch"), 1));
        // Multiblocks
        CRUCIBLE = registerMetaTileEntity(2328, new MetaTileEntityCrucible(gjId("crucible")));
        FLOTATION_CELL = registerMetaTileEntity(2329, new MetaTileEntityFlotationCell(gjId("flotation_cell")));
        ADVANCED_ARC_FURNACE = registerMetaTileEntity(2330,
                new MetaTileEntityAdvancedArcFurnace(gjId("advanced_arc_furnace")));
        HEAT_EXCHANGER = registerMetaTileEntity(2331,
                new MetaTileEntityHeatExchanger(gjId("heat_exchanger")));
        ADVANCED_STEAM_TURBINE = registerMetaTileEntity(2332,
                new MetaTileEntityAdvancedTurbine(gjId("advanced_large_steam_turbine"),
                        GJRecipeMaps.ADVANCED_STEAM_FUELS, 1,
                        MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                        Textures.SOLID_STEEL_CASING, GJTextures.ADVANCED_COMBUSTION_TURBINE_OVERLAY));
        ADVANCED_COMBUSTION_TURBINE = registerMetaTileEntity(2333,
                new MetaTileEntityAdvancedTurbine(gjId("advanced_large_combustion_turbine"),
                        GJRecipeMaps.ADVANCED_COMBUSTION_FUELS, 2,
                        MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                        Textures.SOLID_STEEL_CASING, GJTextures.ADVANCED_STEAM_TURBINE_OVERLAY));
        ADVANCED_ELECTROLYZER = registerMetaTileEntity(2334,
                new MetaTileEntityAdvancedElectrolyzer(gjId("advanced_electrolyzer")));
        MEGA_ALVEARY = registerMetaTileEntity(2335, new MetaTileEntityIndustrialAlveary(gjId("mega_alveary")));
        COMB_PROCESSOR = registerMetaTileEntity(2336, new MetaTileEntityCombProcessor(gjId("comb_processor")));
        CLARIFICATION_PLANT = registerMetaTileEntity(2337,
                new MetaTileEntityClarificationPlant(gjId("clarification_plant")));
        REACTION_FURNACE = registerMetaTileEntity(2338,
                new MetaTileEntityReactionFurnace(gjId("reaction_furnace")));
    }
}
