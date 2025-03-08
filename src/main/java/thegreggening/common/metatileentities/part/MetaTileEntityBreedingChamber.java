package thegreggening.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.items.itemhandlers.GTItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.metatileentity.part.BreedingChamberItemStackHandler;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import java.util.Arrays;
import java.util.List;

public class MetaTileEntityBreedingChamber extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IItemHandlerModifiable> {

    private BreedingChamberItemStackHandler input;
    private GTItemStackHandler output;
    private MetaTileEntityNuclearReactor controller;

    public MetaTileEntityBreedingChamber(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityBreedingChamber(metaTileEntityId);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        input = new BreedingChamberItemStackHandler(this, this.controller);
        output = new GTItemStackHandler(this, 1);
        itemInventory = new ItemHandlerProxy(input, output);
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return true;
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return input;
    }

    @Override
    public IItemHandlerModifiable getExportItems() {
        return output;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 133);
        builder.widget(new LabelWidget(6, 6, getMetaFullName()));
        builder.widget(new ImageWidget(61, 20, 18, 18, GuiTextures.SLOT));
        builder.widget(new SlotWidget(input, 0, 61, 20, true, true));
        builder.widget(new ImageWidget(97, 20, 18, 18, GuiTextures.SLOT));
        builder.widget(new SlotWidget(output, 0, 97, 20, true, false));
        return builder.bindPlayerInventory(entityPlayer.inventory, 50).build(getHolder(), entityPlayer);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.VOLTAGE_CASINGS[1].render(renderState, translation, ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()))));
        for (EnumFacing facing : EnumFacing.VALUES) {
            Textures.BUFFER_OVERLAY.renderSided(facing, renderState, translation, pipeline);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(Textures.VOLTAGE_CASINGS[1].getParticleSprite(), this.getPaintingColorForRendering());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setTag("Input Inventory", input.serializeNBT());
        tag.setTag("Output Inventory", output.serializeNBT());
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        output.deserializeNBT(tag.getCompoundTag("Output Inventory"));
        input.deserializeNBT(tag.getCompoundTag("Input Inventory"));
    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        clearInventory(itemBuffer, input);
        clearInventory(itemBuffer, output);
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return TGMultiblockAbility.BREEDER_CELL;
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> list) {
        list.addAll(Arrays.asList(input,output));
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
        this.input.setController(controller);
    }
}
