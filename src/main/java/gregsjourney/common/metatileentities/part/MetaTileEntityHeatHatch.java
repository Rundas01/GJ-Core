package gregsjourney.common.metatileentities.part;

import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.utils.GJLog;
import gregsjourney.common.metatileentities.multiblock.electric.MetaTileEntityCrucible;
import gregtech.api.capability.IFilter;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetaTileEntityHeatHatch extends MetaTileEntityFluidHatch {
    public MetaTileEntityCrucible controller;
    private final HeatHatchFluidTank fluidTank;

    public MetaTileEntityHeatHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier, false);
        fluidTank = new HeatHatchFluidTank(1000 * tier);
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
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.add(this.fluidTank);
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return GJMultiblockAbility.HEAT_HATCH;
    }

    public void setController(MetaTileEntityCrucible controller){
        this.controller = controller;
    }

    private class HeatHatchFluidTank extends FluidTank implements IFilter<FluidStack> {

        public HeatHatchFluidTank(int capacity) {
            super(capacity);
            GJLog.logger.debug("constructor");
        }

        @Override
        public int fillInternal(FluidStack resource, boolean doFill) {
            if(!test(resource)) return 0;
            return super.fillInternal(resource, doFill);
        }

        @Override
        public void onContentsChanged() {
            GJLog.logger.debug("Za Hando");
            super.onContentsChanged();
            if(MetaTileEntityHeatHatch.this.controller == null){
                GJLog.logger.debug("Null");
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
