package gregsjourney.api.recipe;

import javax.annotation.Nonnull;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;

public class NoEnergyMultiblockRecipeLogic extends MultiblockRecipeLogic {

    public NoEnergyMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    protected long getEnergyInputPerSecond() {
        return 2147483647L;
    }

    protected long getEnergyStored() {
        return 0L;
    }

    protected long getEnergyCapacity() {
        return 2147483647L;
    }

    protected boolean drawEnergy(int recipeEUt, boolean simulate) {
        return true;
    }

    protected int[] runOverclockingLogic(@Nonnull IRecipePropertyStorage propertyStorage, int recipeEUt,
                                         long maxVoltage, int recipeDuration, int amountOC) {
        return OverclockingLogic.standardOverclockingLogic(1, 1, recipeDuration, amountOC,
                this.getOverclockingDurationDivisor(), this.getOverclockingVoltageMultiplier());
    }

    public long getMaximumOverclockVoltage() {
        return GTValues.V[1];
    }

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
