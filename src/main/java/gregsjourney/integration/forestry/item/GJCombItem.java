package gregsjourney.integration.forestry.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;

import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.core.items.IColoredItem;
import forestry.core.utils.ItemTooltipUtil;

public class GJCombItem extends Item implements IColoredItem, IItemModelRegister {

    public GJCombItem() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setRegistryName(GTValues.MODID, "gj.comb");
        setTranslationKey("gj.comb");
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel(@NotNull Item item, IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < GJCombType.values().length; i++) {
            manager.registerItemModel(item, i, GTValues.MODID_FR, "gj.comb");
        }
    }

    @NotNull
    @Override
    public String getTranslationKey(ItemStack stack) {
        GJCombType type = GJCombType.getComb(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (GJCombType type : GJCombType.VALUES) {
                if (!type.showInList) continue;
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        GJCombType type = GJCombType.getComb(stack.getItemDamage());
        return type.color[tintIndex >= 1 ? 1 : 0];
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemTooltipUtil.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
