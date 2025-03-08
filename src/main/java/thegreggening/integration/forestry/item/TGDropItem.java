package thegreggening.integration.forestry.item;

import javax.annotation.Nullable;

import gregtech.api.util.Mods;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.integration.forestry.bees.GTDropType;

import forestry.api.apiculture.BeeManager;
import forestry.api.arboriculture.TreeManager;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.ISpeciesRoot;
import forestry.api.lepidopterology.ButterflyManager;
import forestry.core.items.IColoredItem;

public class TGDropItem extends Item implements IColoredItem, IItemModelRegister {

    public TGDropItem() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setTranslationKey("tg.honey_drop");
        setRegistryName(GTValues.MODID, "tg.honey_drop");
        setResearchSuitability(BeeManager.beeRoot);
        setResearchSuitability(TreeManager.treeRoot);
        setResearchSuitability(ButterflyManager.butterflyRoot);
        setResearchSuitability(AlleleManager.alleleRegistry.getSpeciesRoot("rootFlowers"));
    }

    private void setResearchSuitability(@Nullable ISpeciesRoot speciesRoot) {
        if (speciesRoot != null) {
            speciesRoot.setResearchSuitability(new ItemStack(this, 1, GTValues.W), 0.5f);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void registerModel(@NotNull Item item, @NotNull IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < TGDropType.VALUES.length; i++) {
            manager.registerItemModel(item, i, Mods.Forestry.name(), "tg.honey_drop");
        }
    }

    @NotNull
    @Override
    public String getTranslationKey(@NotNull ItemStack stack) {
        TGDropType type = TGDropType.getDrop(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (TGDropType type : TGDropType.VALUES) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public int getColorFromItemstack(@NotNull ItemStack stack, int i) {
        GTDropType type = GTDropType.getDrop(stack.getItemDamage());
        return type.color[i == 0 ? 0 : 1];
    }
}
