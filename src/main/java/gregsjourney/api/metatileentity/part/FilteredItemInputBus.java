package gregsjourney.api.metatileentity.part;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.BlockableSlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.List;

public class FilteredItemInputBus extends MetaTileEntityMultiblockNotifiablePart implements IMultiblockAbilityPart<IItemHandlerModifiable> {

    private final IItemCondition condition;
    private final FilteredItemStackHandler handler;

    public FilteredItemInputBus(ResourceLocation metaTileEntityId, int tier, IItemCondition condition) {
        super(metaTileEntityId, tier, false);
        this.condition = condition;
        this.handler = new FilteredItemStackHandler(this, Math.min(tier * tier, 100), this, false, condition);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new FilteredItemInputBus(metaTileEntityId, getTier(), this.condition);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 112 + 18 * getTier()).label(10, 5,
                getMetaFullName());
        int halfwidth = 88, offset;
        for (int i = 0; i < getTier(); i++) {
            offset = -9 * getTier();
            for (int j = 0; j < getTier(); j++) {
                builder.widget(new BlockableSlotWidget(handler, i * getTier() + j, halfwidth + offset, 18 * (1 + i),
                        true, true)
                        .setBackgroundTexture(GuiTextures.SLOT));
                offset += 18;
            }
        }
        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 18 * getTier() + 30)
                .build(getHolder(), entityPlayer);
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() { return MultiblockAbility.IMPORT_ITEMS; }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> list) { list.add(handler); }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() { return handler; }
}
