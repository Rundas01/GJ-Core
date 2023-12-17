package gregicality.legacy.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraftforge.fml.common.Loader;

import org.apache.commons.lang3.ArrayUtils;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gregicality.legacy.api.GCYLRValues;
import gregicality.legacy.common.block.GCYLRMetaBlocks;
import gregicality.legacy.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.legacy.common.metatileentities.GCYLRMetaTileEntities;

public final class GCYLRMetaTileEntityLoader {

    private GCYLRMetaTileEntityLoader() {}

    public static void init() {
        ModHandler.addShapedRecipe(true, "bio_reactor", GCYLRMetaTileEntities.BIO_REACTOR.getStackForm(),
                "TCT", "PSP", "MWM",
                'T', new UnificationEntry(plate, TungstenCarbide),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'S', MetaTileEntities.MACERATOR[IV].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Platinum));
    }
}
