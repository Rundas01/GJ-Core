package thegreggening.api.metatileentity.part;

import gregtech.api.capability.IFilter;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.items.itemhandlers.GTItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.ItemStackHashStrategy;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FilteredNotifiableItemStackHandler extends NotifiableItemStackHandler implements IFilter<ItemStack> {

    private IItemCondition condition;

    public FilteredNotifiableItemStackHandler(MetaTileEntity metaTileEntity, int slots, MetaTileEntity entityToNotify, boolean isExport, IItemCondition condition) {
        super(metaTileEntity, slots, entityToNotify, isExport);
        this.condition = condition;
    }

    public FilteredNotifiableItemStackHandler(MetaTileEntity metaTileEntity, int slots, MetaTileEntity entityToNotify, boolean isExport) {
        this(metaTileEntity, slots, entityToNotify, isExport, is -> false);
    }

    public void changeCondition(IItemCondition newCondition) {
        this.condition = newCondition;
    }

    @Override
    public boolean test(@NotNull ItemStack stack) {
        return this.condition.test(stack);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        // If the item was not valid, nothing from the stack can be inserted
        if (!isItemValid(slot, stack)) {
            return stack;
        }

        // Return Empty if passed Empty
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // If the stacks do not match, nothing can be inserted
        if (!ItemStackHashStrategy.comparingAllButCount().equals(stack, this.getStackInSlot(slot)) &&
                !this.getStackInSlot(slot).isEmpty()) {
            return stack;
        }

        int amountInSlot = this.getStackInSlot(slot).getCount();
        int slotLimit = getSlotLimit(slot);

        // If the current stack size in the slot is greater than the limit of the Multiblock, nothing can be
        // inserted
        if (amountInSlot >= slotLimit) {
            return stack;
        }

        // This will always be positive and greater than zero if reached
        int spaceAvailable = slotLimit - amountInSlot;

        // Insert the minimum amount between the amount of space available and the amount being inserted
        int amountToInsert = Math.min(spaceAvailable, stack.getCount());

        // The remainder that was not inserted
        int remainderAmount = stack.getCount() - amountToInsert;

        // Handle any remainder
        ItemStack remainder = ItemStack.EMPTY;

        if (remainderAmount > 0) {
            remainder = stack.copy();
            remainder.setCount(remainderAmount);
        }

        if (!simulate) {
            // Perform the actual insertion
            ItemStack temp = stack.copy();
            temp.setCount(amountInSlot + amountToInsert);
            this.setStackInSlot(slot, temp);
        }

        return remainder;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        boolean slotMatches = this.getStackInSlot(slot).isEmpty() ||
                ItemStackHashStrategy.comparingAllButCount().equals(this.getStackInSlot(slot), stack);

        return slotMatches && test(stack);
    }
}
