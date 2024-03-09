package gregsjourney.common.metatileentities.part;

import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.utils.GJLog;
import gregsjourney.common.metatileentities.multiblock.electric.MetaTileEntityCrucible;
import gregtech.api.capability.IFilter;
import gregtech.api.capability.impl.FilteredItemHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class MetaTileEntityHeatHatch extends MetaTileEntityMultiblockNotifiablePart implements IMultiblockAbilityPart<IFluidTank> {
    public MetaTileEntityCrucible controller;
    private final HeatHatchFluidTank fluidTank;

    public MetaTileEntityHeatHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier, false);
        fluidTank = new HeatHatchFluidTank(1000 * tier, this);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHeatHatch(metaTileEntityId, getTier());
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gj.machine.heat_hatch.info",Integer.toString(getTier() * 1000)));
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createTankUI(fluidTank, getMetaFullName(), entityPlayer).build(getHolder(), entityPlayer);
    }

    public ModularUI.Builder createTankUI(IFluidTank fluidTank, String title, EntityPlayer entityPlayer) {
        // Create base builder/widget references
        ModularUI.Builder builder = ModularUI.defaultBuilder();
        TankWidget tankWidget;

        // Add input/output-specific widgets
        tankWidget = new TankWidget(fluidTank, 69, 52, 18, 18)
                .setAlwaysShowFull(true).setDrawHoveringText(false).setContainerClicking(true, true);

        builder.image(7, 16, 81, 55, GuiTextures.DISPLAY)
                .widget(new ImageWidget(91, 36, 14, 15, GuiTextures.TANK_ICON))
                .widget(new SlotWidget(exportItems, 0, 90, 53, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY));

        // Add general widgets
        return builder.label(6, 6, title)
                .label(11, 20, "gregtech.gui.fluid_amount", 0xFFFFFF)
                .widget(new AdvancedTextWidget(11, 30, getFluidAmountText(tankWidget), 0xFFFFFF))
                .widget(new AdvancedTextWidget(11, 40, getFluidNameText(tankWidget), 0xFFFFFF))
                .widget(tankWidget)
                .widget(new FluidContainerSlotWidget(importItems, 0, 90, 16, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.IN_SLOT_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory);
    }

    private Consumer<List<ITextComponent>> getFluidNameText(TankWidget tankWidget) {
        return (list) -> {
            TextComponentTranslation translation = tankWidget.getFluidTextComponent();
            if (translation != null) {
                list.add(translation);
            }
        };
    }

    private Consumer<List<ITextComponent>> getFluidAmountText(TankWidget tankWidget) {
        return (list) -> {
            String fluidAmount = tankWidget.getFormattedFluidAmount();
            if (!fluidAmount.isEmpty()) {
                list.add(new TextComponentString(fluidAmount));
            }
        };
    }

    @Override
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.add(this.fluidTank);
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return GJMultiblockAbility.HEAT_HATCH;
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return new FluidTankList(false, fluidTank);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new FilteredItemHandler(this).setFillPredicate(
                FilteredItemHandler.getCapabilityFilter(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY));
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    public void setController(MetaTileEntityCrucible controller){
        this.controller = controller;
    }

    private class HeatHatchFluidTank extends NotifiableFluidTank implements IFilter<FluidStack> {

        public HeatHatchFluidTank(int capacity, MetaTileEntity tileEntity) {
            super(capacity, tileEntity, false);
        }

        @Override
        public int fillInternal(FluidStack resource, boolean doFill) {
            if(!test(resource)) return 0;
            return super.fillInternal(resource, doFill);
        }

        @Override
        public void onContentsChanged() {
            super.onContentsChanged();
            if(MetaTileEntityHeatHatch.this.controller == null){
                return;
            }
            MetaTileEntityHeatHatch.this.controller.setSmeltingModifier(MetaTileEntityHeatHatch.this.controller);
        }

        @Override
        public boolean test(@NotNull FluidStack fluidStack) {
            return fluidStack.getFluid().getTemperature() >= Materials.Lava.getFluid().getTemperature();
        }

        @Override
        public boolean canFillFluidType(FluidStack fluidStack) {
            return test(fluidStack);
        }
    }
}
