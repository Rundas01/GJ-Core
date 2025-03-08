package thegreggening.api.metatileentity.part;

import net.minecraftforge.fluids.FluidStack;

@FunctionalInterface
public interface IFluidCondition {

    boolean test(FluidStack stack);
}
