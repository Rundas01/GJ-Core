package thegreggening.api.recipe;

import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import net.minecraft.item.Item;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;
import thegreggening.common.metatileentities.part.MetaTileEntityFissionChamber;
import thegreggening.common.metatileentities.part.MetaTileEntityNeutronEmitter;
import thegreggening.utils.TGLog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NuclearReactorRecipeLogic extends NoEnergyMultiblockRecipeLogic {

    MetaTileEntityNuclearReactor controller;

    public NuclearReactorRecipeLogic(MetaTileEntityNuclearReactor controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public long getMaxVoltage() {
        List<ITieredMetaTileEntity> list = controller.getAbilities(GCYMMultiblockAbility.TIERED_HATCH);

        if (list.isEmpty())
            return super.getMaxVoltage();

        return list.get(0).getTier();
    }

    @Override
    public IItemHandlerModifiable getInputInventory() {
        List<IItemHandlerModifiable> inputs = new ArrayList<>();
        List<MetaTileEntityFissionChamber> validChambers, properlyDirectedChambers;
        MetaTileEntityFissionChamber primedChamber = null;
        Item currentPellet;
        MetaTileEntityNeutronEmitter neutronEmitter = controller.getNeutronEmitter();
        inputs.add(controller.getCircuitBus().getImportItems());
        for (MetaTileEntityFissionChamber fc : controller.getFissionChambers()) {
            if (neutronEmitter.isDirectedAtFissionChamber(fc)) {
                primedChamber = fc;
                break;
            }
        }
        if (primedChamber == null) {
            return new ItemHandlerList(inputs);
        }
        currentPellet = primedChamber.getImportItems().getStackInSlot(0).getItem();
        properlyDirectedChambers = getProperlyDirectedValidChambers(primedChamber);
        validChambers = properlyDirectedChambers.stream().filter(fc -> fc.getImportItems().getStackInSlot(0).getItem() == currentPellet).filter(fc -> fc.getExportItems().getStackInSlot(0).isEmpty()).collect(Collectors.toList());
        for (MetaTileEntityFissionChamber fc : validChambers) {
            inputs.add(fc.getImportItems());
        }
        return new ItemHandlerList(inputs);
    }

    @Override
    public IItemHandlerModifiable getOutputInventory() {
        List<IItemHandlerModifiable> outputs = new ArrayList<>();
        List<MetaTileEntityFissionChamber> validChambers, properlyDirectedChambers;
        MetaTileEntityFissionChamber primedChamber = null;
        Item currentPellet;
        MetaTileEntityNeutronEmitter neutronEmitter = controller.getNeutronEmitter();
        for (MetaTileEntityFissionChamber fc : controller.getFissionChambers()) {
            if (neutronEmitter.isDirectedAtFissionChamber(fc)) {
                primedChamber = fc;
                break;
            }
        }
        if (primedChamber == null) {
            return new ItemHandlerList(outputs);
        }
        currentPellet = primedChamber.getImportItems().getStackInSlot(0).getItem();
        properlyDirectedChambers = getProperlyDirectedValidChambers(primedChamber);
        validChambers = properlyDirectedChambers.stream().filter(fc -> fc.getImportItems().getStackInSlot(0).getItem() == currentPellet).filter(fc -> fc.getExportItems().getStackInSlot(0).isEmpty()).collect(Collectors.toList());
        for (MetaTileEntityFissionChamber fc : validChambers) {
            outputs.add(fc.getExportItems());
        }
        return new ItemHandlerList(outputs);
    }

    private List<MetaTileEntityFissionChamber> getProperlyDirectedValidChambers(MetaTileEntityFissionChamber primedChamber) {
        List<MetaTileEntityFissionChamber> allChambers = new ArrayList<>();
        MetaTileEntityFissionChamber currentChamber = primedChamber, tempChamber;
        allChambers.add(currentChamber);
        while (currentChamber.getDirectedAtFissionChamber(controller.size - 2, allChambers) != null) {
            tempChamber = currentChamber.getDirectedAtFissionChamber(controller.size - 2, allChambers);
            allChambers.add(tempChamber);
            currentChamber = tempChamber;
        }
        return allChambers;
    }

    @Override
    protected boolean setupAndConsumeRecipeInputs(@NotNull Recipe recipe, @NotNull IItemHandlerModifiable importInventory, @NotNull IMultipleTankHandler importFluids) {
        MetaTileEntityNeutronEmitter neutronEmitter = controller.getNeutronEmitter();
        if (neutronEmitter.getImportFluids().getTankAt(0).getFluidAmount() == 0) {
            return false;
        }
        if (!neutronEmitter.isDirectedAtFissionChamber(null)) {
            return false;
        }
        neutronEmitter.getImportFluids().getTankAt(0).drain(1, true);
        controller.currentEnergy = recipe.getProperty(FissionEnergyProperty.getInstance(), 0);
        for (MetaTileEntityFissionChamber fc : controller.getFissionChambers()) {
            fc.emitNeutrons(recipe.getProperty(FissionNeutronProperty.getInstance(), 0));
        }
        return super.setupAndConsumeRecipeInputs(recipe, controller.getInputInventory(), importFluids);
    }

    @Override
    protected boolean setupAndConsumeRecipeInputs(@NotNull Recipe recipe, @NotNull IItemHandlerModifiable importInventory) {
        MetaTileEntityNeutronEmitter neutronEmitter = controller.getNeutronEmitter();
        if (neutronEmitter.getImportFluids().getTankAt(0).getFluidAmount() == 0) {
            return false;
        }
        TGLog.logger.debug("Bruder Hilarius");
        if (!neutronEmitter.isDirectedAtFissionChamber(null)) {
            return false;
        }
        TGLog.logger.debug("Bruder Hilarius 2");
        neutronEmitter.getImportFluids().getTankAt(0).drain(1, true);
        controller.currentEnergy = recipe.getProperty(FissionEnergyProperty.getInstance(), 0);
        for (MetaTileEntityFissionChamber fc : controller.getFissionChambers()) {
            fc.emitNeutrons(recipe.getProperty(FissionNeutronProperty.getInstance(), 0));
        }
        TGLog.logger.debug("Bruder Hilarius 3");
        return super.setupAndConsumeRecipeInputs(recipe, controller.getInputInventory());
    }

    @Override
    public @NotNull ParallelLogicType getParallelLogicType() {
        return ParallelLogicType.APPEND_ITEMS;
    }

    @Override
    protected void modifyOverclockPre(int @NotNull [] values, @NotNull IRecipePropertyStorage storage) {
        super.modifyOverclockPre(values, storage);
        values[1] *= getMaxVoltage() + 1;
        values[1] /= controller.isBreeder() ? 2 : 1;
    }
}
