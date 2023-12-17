package gregicality.legacy.integration.forestry;

import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.core.items.IColoredItem;
import forestry.core.utils.ItemTooltipUtil;
import gregtech.api.GTValues;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GCYLRCombItem extends Item implements IColoredItem, IItemModelRegister {

    public GCYLRCombItem() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setRegistryName(GTValues.MODID, "gt.comb");
        setTranslationKey("gt.comb");
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel(@Nonnull Item item, IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < GCYLRCombType.values().length; i++) {
            manager.registerItemModel(item, i, GTValues.MODID_FR, "gt.comb");
        }
    }

    @Nonnull
    @Override
    public String getTranslationKey(ItemStack stack) {
        GCYLRCombType type = GCYLRCombType.getComb(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (GCYLRCombType type : GCYLRCombType.VALUES) {
                if (!type.showInList) continue;
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        GCYLRCombType type = GCYLRCombType.getComb(stack.getItemDamage());
        return type.color[tintIndex >= 1 ? 1 : 0];
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemTooltipUtil.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

