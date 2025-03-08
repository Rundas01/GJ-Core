package thegreggening.integration.forestry.bees.mutation;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.core.utils.Translator;
import org.jetbrains.annotations.NotNull;
import thegreggening.utils.TGUtil;

public class DimensionMutationCondition implements IMutationCondition {

    final int dimID;
    final String dimName;

    public DimensionMutationCondition(TGUtil.Dimensions dim) {
        dimID = dim.getDimension();
        dimName = dim.getName();
    }

    @Override
    public float getChance(World world, @NotNull BlockPos blockPos, @NotNull IAllele iAllele, @NotNull IAllele iAllele1, @NotNull IGenome iGenome,
                           @NotNull IGenome iGenome1, @NotNull IClimateProvider iClimateProvider) {
        if (world.provider.getDimension() == dimID) return 1;
        return 0;
    }

    @Override
    public @NotNull String getDescription() {
        return Translator.translateToLocalFormatted("gj.mutation.condition.dim", dimName);
    }
}
