package gregicality.legacy.common.block.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantActiveBlock;
import gregtech.client.utils.BloomEffectUtil;
import gregtech.common.ConfigHolder;

public class BlockUniqueCasing extends VariantActiveBlock<BlockUniqueCasing.UniqueCasingType> {

    public BlockUniqueCasing() {
        super(Material.IRON);
        setTranslationKey("unique_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(UniqueCasingType.TEST));
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public boolean canRenderInLayer(@NotNull IBlockState state, @NotNull BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT;
    }

    @Override
    protected boolean isBloomEnabled(UniqueCasingType value) {
        return false;
    }

    public enum UniqueCasingType implements IStringSerializable {

        TEST("test");

        private final String name;

        UniqueCasingType(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }
    }
}
