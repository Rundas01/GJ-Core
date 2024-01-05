package gregicality.legacy.integration.forestry.mutation;

import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.IAllele;

public interface ICustomBeeMutationFactory {
    ICustomBeeMutationBuilder createMutation(IAlleleBeeSpecies parentBee0, IAlleleBeeSpecies parentBee1, IAllele[] result, int chance);
}
