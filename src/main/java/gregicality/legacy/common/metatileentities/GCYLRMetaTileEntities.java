package gregicality.legacy.common.metatileentities;

import gregicality.legacy.common.GCYLRConfigHolder;
import gregicality.legacy.common.metatileentities.multiblock.electric.*;
import gregicality.legacy.common.metatileentities.singleblock.generator.MetaTileEntityDecayGenerator;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.*;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;

public final class GCYLRMetaTileEntities {
    public static SimpleGeneratorMetaTileEntity[] DECAY_GENERATOR = new SimpleGeneratorMetaTileEntity[3];
    public static SimpleMachineMetaTileEntity[] ISOTOPIC_STABILIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_DEHYDRATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityBioReactor BIO_REACTOR;
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;
    public static MetaTileEntityAdvancedFusionReactor ADVANCED_FUSION_REACTOR;
    public static MetaTileEntityMetallurgicRefinery METALLURGIC_REACTION_SMELTER;
    public static MetaTileEntityFlotationCell FLOTATION_CELL;
    public static MetaTileEntityAdvancedArcFurnace ADVANCED_ARC_FURNACE;
    public static MetaTileEntityRoastingOven ROASTING_OVEN;

    private GCYLRMetaTileEntities() {}

    public static void init() {
        // Singleblocks
        DECAY_GENERATOR[0] = registerMetaTileEntity(2069, new MetaTileEntityDecayGenerator(gcylrId("decay_generator.lv"), DECAY_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 1, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[1] = registerMetaTileEntity(2070, new MetaTileEntityDecayGenerator(gcylrId("decay_generator.mv"), DECAY_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 2, GTUtility.genericGeneratorTankSizeFunction));
        DECAY_GENERATOR[2] = registerMetaTileEntity(2071, new MetaTileEntityDecayGenerator(gcylrId("decay_generator.hv"), DECAY_GENERATOR_FUELS, Textures.COMBUSTION_GENERATOR_OVERLAY, 3, GTUtility.genericGeneratorTankSizeFunction));
        registerSimpleMetaTileEntity(ISOTOPIC_STABILIZER,2072,"isotopic_stabilizer",ISOTOPIC_STABILIZER_RECIPES,Textures.CHEMICAL_REACTOR_OVERLAY,true,GTUtility::gregtechId,GTUtility.defaultTankSizeFunction);
        registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR,2087,"chemical_dehydrator",CHEMICAL_DEHYDRATOR_RECIPES,Textures.CHEMICAL_REACTOR_OVERLAY,true,GTUtility::gregtechId,GTUtility.defaultTankSizeFunction);
        // Multiblocks
        BIO_REACTOR = registerMetaTileEntity(2102, new MetaTileEntityBioReactor(gcylrId("bio_reactor")));
        NUCLEAR_REACTOR = registerMetaTileEntity(2103, new MetaTileEntityNuclearReactor(gcylrId("nuclear_reactor"),9));
        ADVANCED_FUSION_REACTOR = registerMetaTileEntity(2104, new MetaTileEntityAdvancedFusionReactor(gcylrId("advanced_fusion_reactor"),GTValues.UHV));
        if(GCYLRConfigHolder.recipes.realisticRecipes){
            METALLURGIC_REACTION_SMELTER = registerMetaTileEntity(2105, new MetaTileEntityMetallurgicRefinery(gcylrId("metallurgic_reaction_smelter")));
            FLOTATION_CELL = registerMetaTileEntity(2106, new MetaTileEntityFlotationCell(gcylrId("flotation_cell")));
            ADVANCED_ARC_FURNACE = registerMetaTileEntity(2107, new MetaTileEntityAdvancedArcFurnace(gcylrId("advanced_arc_furnace")));
            ROASTING_OVEN = registerMetaTileEntity(2108, new MetaTileEntityRoastingOven(gcylrId("roasting_oven")));
        }
    }
}
