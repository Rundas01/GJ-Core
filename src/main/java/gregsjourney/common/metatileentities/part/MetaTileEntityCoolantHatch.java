package gregsjourney.common.metatileentities.part;

import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.metatileentity.part.FilteredFluidInputHatch;
import gregsjourney.api.unification.property.GJPropertyKeys;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static gregsjourney.utils.GJMaterialUtil.getFromFluidStack;

public class MetaTileEntityCoolantHatch extends FilteredFluidInputHatch {

    private final int numSlots;

    public MetaTileEntityCoolantHatch(ResourceLocation metaTileEntityId, int tier, int numSlots) {
        super(metaTileEntityId, tier, numSlots, fs -> getFromFluidStack(fs).hasProperty(GJPropertyKeys.COOLANT_PROPERTY));
        this.numSlots = numSlots;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCoolantHatch(metaTileEntityId, getTier(), this.numSlots);
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("gj.machine.coolant_hatch.info", Integer.toString(getTier() * 1000)));
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return GJMultiblockAbility.COOLANT_HATCH;
    }
}
