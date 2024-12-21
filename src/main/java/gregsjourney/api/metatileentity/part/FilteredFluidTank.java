package gregsjourney.api.metatileentity.part;

import gregtech.api.capability.IFilter;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class FilteredFluidTank extends NotifiableFluidTank implements IFilter<FluidStack> {

    private final IFluidCondition condition;

    public FilteredFluidTank(int capacity, MetaTileEntity entityToNotify, boolean isExport, IFluidCondition condition) {
        super(capacity, entityToNotify, isExport);
        this.condition = condition;
    }

    @Override
    public boolean test(@NotNull FluidStack fluidStack) {
        return this.condition.test(fluidStack);
    }

    @Override
    public int fillInternal(FluidStack resource, boolean doFill) {
        if (!test(resource)) {
            return 0;
        }
        return super.fillInternal(resource, doFill);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluidStack) {
        return test(fluidStack);
    }
}
