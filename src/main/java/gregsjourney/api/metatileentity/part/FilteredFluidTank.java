package gregsjourney.api.metatileentity.part;

import gregsjourney.utils.GJLog;
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
        GJLog.logger.debug("In test");
        return this.condition.test(fluidStack);
    }

    @Override
    public int fillInternal(FluidStack resource, boolean doFill) {
        if (!test(resource)) {
            GJLog.logger.debug("Fill internal, test false");
            new Throwable().printStackTrace();
            return 0;
        }
        GJLog.logger.debug("Fill internal, test true");
        new Throwable().printStackTrace();
        return super.fillInternal(resource, doFill);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluidStack) {
        GJLog.logger.debug("In canFillFluidType");
        return test(fluidStack);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (!test(resource)) {
            return 0;
        }
        return super.fill(resource, doFill);
    }
}
