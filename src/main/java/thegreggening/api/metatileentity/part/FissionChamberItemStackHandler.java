package thegreggening.api.metatileentity.part;

import gregtech.api.metatileentity.MetaTileEntity;
import thegreggening.api.unification.ore.TGOrePrefixes;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import static thegreggening.utils.TGUtil.hasOrePrefix;

public class FissionChamberItemStackHandler extends FilteredNotifiableItemStackHandler {

    private MetaTileEntityNuclearReactor controller;

    public FissionChamberItemStackHandler(MetaTileEntity metaTileEntity, MetaTileEntityNuclearReactor controller, boolean isExport) {
        super(metaTileEntity, 1, controller, isExport, is -> hasOrePrefix(is, TGOrePrefixes.nuclearFuel));
        this.controller = controller;
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
    }

    @Override
    public void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        if (this.controller != null && controller.isStructureFormed()) {
            this.controller.updatePellets();
        }
    }
}
