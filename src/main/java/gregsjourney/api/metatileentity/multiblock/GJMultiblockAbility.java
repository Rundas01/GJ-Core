package gregsjourney.api.metatileentity.multiblock;

import gregtech.api.capability.IRotorHolder;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;

public class GJMultiblockAbility {

    public static final MultiblockAbility<IItemHandlerModifiable> BEE_HATCH = new MultiblockAbility<>("bee_hatch");
    public static final MultiblockAbility<IFluidTank> HEAT_HATCH = new MultiblockAbility<>("heat_hatch");
    public static final MultiblockAbility<IFluidTank> COOLANT_HATCH = new MultiblockAbility<>("coolant_hatch");
    public static final MultiblockAbility<IRotorHolder> ADVANCED_ROTOR_HOLDER = new MultiblockAbility<>("advanced_rotor_holder");
}
