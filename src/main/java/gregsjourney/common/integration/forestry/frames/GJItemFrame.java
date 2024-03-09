package gregsjourney.common.integration.forestry.frames;

import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import gregtech.integration.forestry.frames.GTFrameType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GJItemFrame extends Item implements IHiveFrame, IItemModelRegister {
    private final GJFrameType type;

    public GJItemFrame(GJFrameType type) {
        this.type = type;
        this.setMaxDamage(this.type.maxDamage);
        this.setMaxStackSize(1);
        this.setCreativeTab(Tabs.tabApiculture);
        this.setRegistryName("gregtech", "gj.frame_" + type.getName());
        this.setTranslationKey("gj.frame_" + type.getName().toLowerCase());
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public @NotNull ItemStack frameUsed(@NotNull IBeeHousing housing, ItemStack frame, @NotNull IBee bee, int wear) {
        frame.setItemDamage(frame.getItemDamage() + wear);
        return frame.getItemDamage() >= frame.getMaxDamage() ? ItemStack.EMPTY : frame;
    }

    public @NotNull IBeeModifier getBeeModifier() {
        return this.type;
    }

    public void registerModel(@NotNull Item item, @NotNull IModelManager manager) {
        manager.registerItemModel(item, 0, "forestry", "gj.frame_" + this.type.getName().toLowerCase());
    }

    public ItemStack getItemStack() {
        return new ItemStack(this);
    }
}
