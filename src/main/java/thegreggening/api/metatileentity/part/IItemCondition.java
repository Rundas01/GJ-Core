package thegreggening.api.metatileentity.part;

import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IItemCondition {

    boolean test(ItemStack stack);
}
