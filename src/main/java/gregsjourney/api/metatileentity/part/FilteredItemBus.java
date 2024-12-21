package gregsjourney.api.metatileentity.part;

import gregtech.api.capability.impl.GhostCircuitItemStackHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.GhostCircuitSlotWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Arrays;
import java.util.List;

public class FilteredItemBus extends MetaTileEntityItemBus {

    private final IItemCondition condition;
    protected FilteredItemStackHandler handler;
    private final IItemHandlerModifiable actualImportItems;

    public FilteredItemBus(ResourceLocation metaTileEntityId, int tier, IItemCondition condition, boolean isExport) {
        super(metaTileEntityId, tier, isExport);
        this.condition = condition;
        this.handler = new FilteredItemStackHandler(this, Math.min((int) Math.pow(tier, 2), 100), condition);
        if (!isExport) {
            this.circuitInventory = new GhostCircuitItemStackHandler(this);
            this.circuitInventory.addNotifiableMetaTileEntity(this);
            this.actualImportItems = new ItemHandlerList(Arrays.asList(this.getImportItems(), circuitInventory));
        } else {
            this.actualImportItems = null;
        }
    }

    public FilteredItemBus(ResourceLocation metaTileEntityId, int tier, boolean isExport) {
        this(metaTileEntityId, tier, is -> false, isExport);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new FilteredItemBus(metaTileEntityId, getTier(), this.condition, this.isExportHatch);
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> abilityList) {
        if (this.hasGhostCircuitInventory() && this.actualImportItems != null) {
            abilityList.add(this.isExportHatch ? this.exportItems : this.actualImportItems);
        } else {
            abilityList.add(this.isExportHatch ? this.exportItems : this.importItems);
        }
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return this.actualImportItems == null ? handler : this.actualImportItems;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        SlotWidget gsWidget = new GhostCircuitSlotWidget(circuitInventory, 0, 8, 100 + 18 * getTier()).setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY);
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 112 + 18 * getTier()).label(10, 5, getMetaFullName());
        int halfwidth = 88, offset;
        for (int i = 0; i < getTier(); i++) {
            offset = -9 * getTier();
            for (int j = 0; j < getTier(); j++) {
                builder.widget(new SlotWidget(handler, i * getTier() + j, halfwidth + offset, 18 * (1 + i), true, !isExportHatch)
                        .setBackgroundTexture(GuiTextures.SLOT));
                offset += 18;
            }
        }
        if (!isExportHatch) {
            builder.widget(gsWidget.setConsumer(this::getCircuitSlotTooltip));
        }
        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 18 * getTier() + 30)
                .build(getHolder(), entityPlayer);
    }
}
