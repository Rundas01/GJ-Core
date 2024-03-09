package gregsjourney.common.integration.forestry.bees.mutation;

import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.core.utils.Translator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DimensionMutationCondition implements IMutationCondition {

    final int dimID;
    final String dimName;

    public DimensionMutationCondition(int id, String name) {
        dimID = id;
        dimName = name;
    }

    @Override
    public float getChance(World world, BlockPos blockPos, IAllele iAllele, IAllele iAllele1, IGenome iGenome, IGenome iGenome1, IClimateProvider iClimateProvider) {
        if (world.provider.getDimension() == dimID) return 1;
        return 0;
    }

    @Override
    public String getDescription() {
        return Translator.translateToLocalFormatted("gj.mutation.condition.dim", dimName);
    }
}
