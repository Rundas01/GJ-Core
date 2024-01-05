package gregicality.legacy.integration.forestry.mutation;

import forestry.api.apiculture.IBeeMutation;
import forestry.api.genetics.IMutationBuilder;

public interface ICustomBeeMutationBuilder extends ICustomMutationBuilder {
        @Override
        IBeeMutation build();
}
