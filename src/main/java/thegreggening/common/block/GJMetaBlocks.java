package thegreggening.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.common.blocks.MetaBlocks;

import thegreggening.common.block.blocks.BlockAlternatorCoil;
import thegreggening.common.block.blocks.BlockTurbineRotor;
import thegreggening.common.block.blocks.GJLargeMultiblockCasings;

public class GJMetaBlocks {

    private GJMetaBlocks() {}

    public static GJLargeMultiblockCasings LARGE_MULTIBLOCK_CASING;
    public static BlockAlternatorCoil ALTERNATOR_COIL;
    public static BlockTurbineRotor TURBINE_ROTOR;

    public static void init() {
        LARGE_MULTIBLOCK_CASING = new GJLargeMultiblockCasings();
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
