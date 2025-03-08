package thegreggening.api.metatileentity.part;

import gregtech.api.metatileentity.MetaTileEntity;
import thegreggening.api.unification.material.TGMiscMaterials;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import static thegreggening.utils.TGMaterialUtil.getFromFluidStack;

public class NeutronEmitterFluidTank extends FilteredFluidTank{

    private MetaTileEntityNuclearReactor controller;

    public NeutronEmitterFluidTank(MetaTileEntity entityToNotify, MetaTileEntityNuclearReactor controller) {
        super(1000, entityToNotify, false, fs -> getFromFluidStack(fs) == TGMiscMaterials.FreeNeutronGas);
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
