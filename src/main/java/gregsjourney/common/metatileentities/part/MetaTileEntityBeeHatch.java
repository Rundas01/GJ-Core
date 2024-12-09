package gregsjourney.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.metatileentity.part.FilteredItemInputBus;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetaTileEntityBeeHatch extends FilteredItemInputBus {

    public MetaTileEntityBeeHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier, MetaTileEntityBeeHatch::isQueen);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBeeHatch(metaTileEntityId, getTier());
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return GJMultiblockAbility.BEE_HATCH;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.PIPE_IN_OVERLAY.renderSided(getFrontFacing(), renderState, translation, pipeline);
    }

    @Override
    public boolean canPartShare() {
        return false;
    }

    public int getBeeLimit() {
        return getTier() * getTier();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gj.machine.bee_hatch.info", Integer.toString(getBeeLimit())));
    }

    @Override
    public void addToolUsages(ItemStack stack, @Nullable World world, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"));
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"));
        super.addToolUsages(stack, world, tooltip, advanced);
    }

    private static boolean isQueen(ItemStack stack) {
        EnumBeeType beeType = BeeManager.beeRoot.getType(stack);
        return beeType == EnumBeeType.QUEEN;
    }
}
