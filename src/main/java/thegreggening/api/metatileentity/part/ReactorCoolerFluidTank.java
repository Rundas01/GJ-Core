package thegreggening.api.metatileentity.part;

import gregtech.api.metatileentity.MetaTileEntity;
import thegreggening.api.unification.property.TGPropertyKeys;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import static thegreggening.utils.TGMaterialUtil.getFromFluidStack;

public class ReactorCoolerFluidTank extends FilteredFluidTank{

    private MetaTileEntityNuclearReactor controller;

    public ReactorCoolerFluidTank(MetaTileEntity entityToNotify, MetaTileEntityNuclearReactor controller) {
        super(1000, entityToNotify, false, fs -> getFromFluidStack(fs).hasProperty(TGPropertyKeys.COOLANT_PROPERTY));
        this.controller = controller;
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
    }

    @Override
    protected void onContentsChanged() {
        super.onContentsChanged();
        if (this.controller != null) {
            controller.updateCoolant();
        }
    }
}
