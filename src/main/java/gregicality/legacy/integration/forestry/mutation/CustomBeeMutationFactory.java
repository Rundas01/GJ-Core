package gregicality.legacy.integration.forestry.mutation;

import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeMutationBuilder;
import forestry.api.apiculture.IBeeMutationFactory;
import forestry.api.genetics.IAllele;
import forestry.apiculture.genetics.BeeMutation;
import org.jetbrains.annotations.NotNull;

public class CustomBeeMutationFactory implements IBeeMutationFactory {
    public CustomBeeMutationFactory() {}
    public @NotNull IBeeMutationBuilder createMutation(IAlleleBeeSpecies parentBee0, IAlleleBeeSpecies parentBee1, IAllele[] result, int chance) {
        BeeMutation beeMutation = new BeeMutation(parentBee0, parentBee1, result, chance);
        CustomBeeManager.beeRoot.registerMutation(beeMutation);
        return beeMutation;
    }
}
