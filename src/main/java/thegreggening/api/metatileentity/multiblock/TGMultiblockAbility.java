package thegreggening.api.metatileentity.multiblock;

import gregtech.api.capability.IRotorHolder;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

public class TGMultiblockAbility {

    public static final MultiblockAbility<IItemHandlerModifiable> BEE_HATCH = new MultiblockAbility<>("bee_hatch");
    public static final MultiblockAbility<IFluidTank> HEAT_HATCH = new MultiblockAbility<>("heat_hatch");
    public static final MultiblockAbility<IFluidTank> COOLANT_CELL = new MultiblockAbility<>("coolant_cell");
    public static final MultiblockAbility<IFluidTank> NEUTRON_EMITTER = new MultiblockAbility<>("neutron_emitter");
    public static final MultiblockAbility<IItemHandlerModifiable> FISSION_CELL = new MultiblockAbility<>("fission_cell");
    public static final MultiblockAbility<IItemHandlerModifiable> BREEDER_CELL = new MultiblockAbility<>("breeding_cell");
    public static final MultiblockAbility<IItemHandlerModifiable> GHOST_CIRCUIT = new MultiblockAbility<>("ghost_circuit");
    public static final MultiblockAbility<IRotorHolder> ADVANCED_ROTOR_HOLDER = new MultiblockAbility<>(
            "advanced_rotor_holder");
}
