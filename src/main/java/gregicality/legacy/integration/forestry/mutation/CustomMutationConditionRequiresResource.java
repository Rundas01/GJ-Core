package gregicality.legacy.integration.forestry.mutation;

import forestry.api.apiculture.IBeeHousing;
import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.core.tiles.TileUtil;
import forestry.core.utils.Translator;
import gregtech.api.unification.material.Material;
import gregtech.common.blocks.BlockCompressed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static gregtech.api.unification.ore.OrePrefix.block;

public class CustomMutationConditionRequiresResource implements IMutationCondition {
    private final Set<IBlockState> acceptedBlockStates = new HashSet();
    private final String displayName;

    public CustomMutationConditionRequiresResource(Material material) {
        Collections.addAll(this.acceptedBlockStates, BlockCompressed.create(new Material[]{material}).getBlock(material));
        this.displayName = block.getLocalNameForItem(material);
    }

    public float getChance(World world, BlockPos pos, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1, IClimateProvider climate) {
        TileEntity tile;
        do {
            pos = pos.down();
            tile = TileUtil.getTile(world, pos);
        } while(tile instanceof IBeeHousing);

        IBlockState blockState = world.getBlockState(pos);
        return this.acceptedBlockStates.contains(blockState) ? 1.0F : 0.0F;
    }

    public String getDescription() {
        return Translator.translateToLocalFormatted("for.mutation.condition.resource", this.displayName);
    }
}
