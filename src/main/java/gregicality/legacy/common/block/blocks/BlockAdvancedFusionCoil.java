package gregicality.legacy.common.block.blocks;

import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class BlockAdvancedFusionCoil extends VariantActiveBlock<BlockAdvancedFusionCoil.AdvancedFusionCoilType> {
    public BlockAdvancedFusionCoil() {
        super(Material.IRON);
        this.setTranslationKey("advanced_fusion_coil");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(AdvancedFusionCoilType.ADVANCED_FUSION_COIL_MK1));
    }

    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityLiving.@NotNull SpawnPlacementType type) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack itemStack, @Nullable World worldIn, @NotNull List<String> lines,
                               @NotNull ITooltipFlag tooltipFlag) {
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);

        // noinspection rawtypes, unchecked
        VariantItemBlock itemBlock = (VariantItemBlock<AdvancedFusionCoilType, BlockAdvancedFusionCoil>) itemStack.getItem();
        IBlockState stackState = itemBlock.getBlockState(itemStack);
        AdvancedFusionCoilType coilType = getState(stackState);
        lines.add(I18n.format("gcylr.tile.advanced_fusion_coil.tooltip_tier", coilType.getTier()));
    }

    public enum AdvancedFusionCoilType implements IStringSerializable {
        ADVANCED_FUSION_COIL_MK1("advanced_fusion_coil_mk1", 2, 1);
        //ADVANCED_FUSION_COIL_MK2("advanced_fusion_coil_mk2", 2, 2),
        //ADVANCED_FUSION_COIL_MK3("advanced_fusion_coil_mk3", 3, 3),
        //ADVANCED_FUSION_COIL_MK4("advanced_fusion_coil_mk4", 3, 3),
        //ADVANCED_FUSION_COIL_MK5("advanced_fusion_coil_mk5", 4, 5);

        private final String name;
        private final int harvestLevel;
        private final int coilTier;

        AdvancedFusionCoilType(String name, int harvestLevel, int coilTier) {
            this.name = name;
            this.harvestLevel = harvestLevel;
            this.coilTier = coilTier;
        }

        public @NotNull String getName() {
            return this.name;
        }

        public int getTier() {return this.coilTier;}

        public int getHarvestLevel(IBlockState state) {
            return this.harvestLevel;
        }

        public String getHarvestTool(IBlockState state) {
            return "wrench";
        }
    }
}
