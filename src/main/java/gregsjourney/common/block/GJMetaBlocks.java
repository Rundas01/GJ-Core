package gregsjourney.common.block;

import gregsjourney.common.block.blocks.BlockAlternatorCoil;
import gregsjourney.common.block.blocks.BlockTurbineRotor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.common.blocks.MetaBlocks;

import gregsjourney.common.block.blocks.BlockLargeMultiblockCasing;

public class GJMetaBlocks {

    private GJMetaBlocks() {}

    public static BlockLargeMultiblockCasing LARGE_MULTIBLOCK_CASING;
    public static BlockAlternatorCoil ALTERNATOR_COIL;
    public static BlockTurbineRotor TURBINE_ROTOR;

    public static void init() {
        LARGE_MULTIBLOCK_CASING = new BlockLargeMultiblockCasing();
        LARGE_MULTIBLOCK_CASING.setRegistryName("large_multiblock_casing");
        ALTERNATOR_COIL = new BlockAlternatorCoil();
        ALTERNATOR_COIL.setRegistryName("alternator_coil");
        TURBINE_ROTOR = new BlockTurbineRotor();
        TURBINE_ROTOR.setRegistryName("turbine_rotor");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(LARGE_MULTIBLOCK_CASING);
        registerItemModel(ALTERNATOR_COIL);
        registerItemModel(TURBINE_ROTOR);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(@NotNull Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            MetaBlocks.statePropertiesToString(state.getProperties())));
        }
    }
}
