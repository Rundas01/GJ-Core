package thegreggening.common.block.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantBlock;

import thegreggening.api.block.IBlockOrientable;

public class BlockAlternatorCoil extends VariantBlock<BlockAlternatorCoil.AlternatorCoilType>
                                 implements IBlockOrientable {

    public BlockAlternatorCoil() {
        super(Material.IRON);
        setTranslationKey("alternator_coil");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(AlternatorCoilType.COPPER).withProperty(FACING, EnumFacing.NORTH));
    }

    public @NotNull IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX,
                                                     float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING,
                placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int i = meta / 4;
        int j = meta % 4 + 2;

        EnumFacing enumfacing = EnumFacing.byHorizontalIndex(j);

        return this.getDefaultState()
                .withProperty(FACING, enumfacing)
                .withProperty(this.VARIANT, this.VALUES[i % this.VALUES.length]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(this.VARIANT).ordinal();
        int j = state.getValue(FACING).getIndex();
        return j - 2 + i * 4;
    }

    @Nonnull
    @Override
    public BlockStateContainer createBlockState() {
        super.createBlockState();

        return new BlockStateContainer(this, this.VARIANT, this.FACING);
    }

    public ItemStack getItemVariant(BlockTurbineRotor.BlockTurbineRotorType variant, int amount) {
        return new ItemStack(this, amount, variant.ordinal() * 4);
    }

    public int damageDropped(@Nonnull IBlockState state) {
        return this.getMetaFromState(state) - state.getValue(FACING).getIndex() + 2;
    }

    public enum AlternatorCoilType implements IStringSerializable, IStateHarvestLevel {

        COPPER("copper", 1);

        private final String name;
        private final int harvestLevel;

        AlternatorCoilType(String name, int harvestLevel) {
            this.name = name;
            this.harvestLevel = harvestLevel;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        public int getHarvestLevel(IBlockState state) {
            return this.harvestLevel;
        }

        public String getHarvestTool(IBlockState state) {
            return "wrench";
        }
    }
}
