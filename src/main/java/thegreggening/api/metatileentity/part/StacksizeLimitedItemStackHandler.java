package thegreggening.api.metatileentity.part;

import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StacksizeLimitedItemStackHandler extends NotifiableItemStackHandler {

    private final int capacity;

    public StacksizeLimitedItemStackHandler(MetaTileEntity metaTileEntity, int slots, MetaTileEntity entityToNotify, boolean isExport, int capacity) {
        super(metaTileEntity, slots, entityToNotify, isExport);
        this.capacity = capacity;
    }

    @Override
    public int getSlotLimit(int slot) {
        return this.capacity;
    }

    @Override
    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return getSlotLimit(slot);
    }
}
