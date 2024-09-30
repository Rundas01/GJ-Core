package gregsjourney.api.recipe;

import net.minecraftforge.fluids.FluidStack;

import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.recipes.Recipe;

import gregsjourney.common.metatileentities.multiblock.electric.MetaTileEntityAdvancedTurbine;

public class AdvancedTurbineRecipeLogic extends MultiblockFuelRecipeLogic {

    public AdvancedTurbineRecipeLogic(MetaTileEntityAdvancedTurbine tileEntity) {
        super(tileEntity);
    }

    public FluidStack getInputFluidStack() {
        if (previousRecipe == null) {
            Recipe recipe = findRecipe(Integer.MAX_VALUE, getInputInventory(), getInputTank());
            return recipe == null ? null : getInputTank().drain(
                    new FluidStack(recipe.getFluidInputs().get(0).getInputFluidStack().getFluid(), Integer.MAX_VALUE),
                    false);
        }
        FluidStack fuelStack = previousRecipe.getFluidInputs().get(0).getInputFluidStack();
        return getInputTank().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
    }
}
