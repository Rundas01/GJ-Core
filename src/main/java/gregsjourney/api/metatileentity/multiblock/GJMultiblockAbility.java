package gregsjourney.api.metatileentity.multiblock;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GJMultiblockAbility {
    public static final MultiblockAbility<IItemHandlerModifiable> BEE_HATCH = new MultiblockAbility("bee_hatch");
    public static final MultiblockAbility<IFluidTank> HEAT_HATCH = new MultiblockAbility("heat_hatch");
}
