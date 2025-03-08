package thegreggening.api.recipe;

import javax.annotation.Nonnull;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;

public class NoEnergyMultiblockRecipeLogic extends MultiblockRecipeLogic {

    public NoEnergyMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    protected long getEnergyInputPerSecond() {
        return 2147483647L;
    }

    @Override
    protected long getEnergyStored() {
        return 0L;
    }

    @Override
    protected long getEnergyCapacity() {
        return 2147483647L;
    }

    @Override
    protected boolean drawEnergy(int recipeEUt, boolean simulate) {
        return true;
    }

    @Override
    protected int[] runOverclockingLogic(@Nonnull IRecipePropertyStorage propertyStorage, int recipeEUt,
                                         long maxVoltage, int recipeDuration, int amountOC) {
        return OverclockingLogic.standardOverclockingLogic(1, 1, recipeDuration, amountOC,
                this.getOverclockingDurationDivisor(), this.getOverclockingVoltageMultiplier());
    }

    @Override
    public void invalidate() {
        this.previousRecipe = null;
        this.progressTime = 0;
        this.maxProgressTime = 0;
        this.recipeEUt = 0;
        this.fluidOutputs = null;
        this.itemOutputs = null;
        this.setActive(false);
    }
}
