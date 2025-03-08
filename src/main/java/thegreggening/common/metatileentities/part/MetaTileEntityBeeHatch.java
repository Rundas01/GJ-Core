package thegreggening.common.metatileentities.part;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;

public class MetaTileEntityBeeHatch extends FilteredItemBus {

    public MetaTileEntityBeeHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier, MetaTileEntityBeeHatch::isQueen, false);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBeeHatch(metaTileEntityId, getTier());
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return TGMultiblockAbility.BEE_HATCH;
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gj.machine.bee_hatch.info", Integer.toString(getTier() * getTier())));
    }

    @Override
    public void addToolUsages(ItemStack stack, @Nullable World world, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"));
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"));
        super.addToolUsages(stack, world, tooltip, advanced);
    }

    @Override
    public boolean hasGhostCircuitInventory() {
        return false;
    }

    private static boolean isQueen(ItemStack stack) {
        EnumBeeType beeType = BeeManager.beeRoot.getType(stack);
        return beeType == EnumBeeType.QUEEN;
    }
}
