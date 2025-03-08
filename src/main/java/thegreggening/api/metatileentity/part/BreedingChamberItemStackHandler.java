package thegreggening.api.metatileentity.part;

import gregtech.api.metatileentity.MetaTileEntity;
import thegreggening.api.unification.ore.TGOrePrefixes;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import static thegreggening.utils.TGUtil.hasOrePrefix;

public class BreedingChamberItemStackHandler extends FilteredItemStackHandler {

    private MetaTileEntityNuclearReactor controller;

    public BreedingChamberItemStackHandler(MetaTileEntity metaTileEntity, MetaTileEntityNuclearReactor controller) {
        super(metaTileEntity, 1, is -> hasOrePrefix(is, TGOrePrefixes.nuclearFuel));
        this.controller = controller;
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        if (this.controller != null && controller.isStructureFormed()) {
            this.controller.updatePellets();
        }
    }
}
