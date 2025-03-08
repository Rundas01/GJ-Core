package thegreggening.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.IGhostSlotConfigurable;
import gregtech.api.capability.impl.GhostCircuitItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.GhostCircuitSlotWidget;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandlerModifiable;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;

import java.util.List;

public class MetaTileEntityCircuitBus extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IItemHandlerModifiable>, IGhostSlotConfigurable {

    public MetaTileEntityCircuitBus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
    }

    private GhostCircuitItemStackHandler circuitInventory;

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityCircuitBus(metaTileEntityId);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        this.circuitInventory = new GhostCircuitItemStackHandler(this);
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return this.circuitInventory;
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
        this.circuitInventory.addNotifiableMetaTileEntity(controllerBase);
        this.circuitInventory.addToNotifiedList(this, this.circuitInventory, false);
    }

    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase) {
        super.removeFromMultiBlock(controllerBase);
        this.circuitInventory.removeNotifiableMetaTileEntity(controllerBase);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        SlotWidget gcsw = new GhostCircuitSlotWidget(circuitInventory, 0, 79, 18).setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY);
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 133);
        builder.widget(new LabelWidget(6, 6, getMetaFullName()));
        builder.widget(gcsw.setConsumer(w -> {
            String configString;
            if (circuitInventory == null ||
                    circuitInventory.getCircuitValue() == GhostCircuitItemStackHandler.NO_CONFIG) {
                configString = new TextComponentTranslation("gregtech.gui.configurator_slot.no_value")
                        .getFormattedText();
            } else {
                configString = String.valueOf(circuitInventory.getCircuitValue());
            }

            w.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString);
        }));
        return builder.bindPlayerInventory(entityPlayer.inventory, 50).build(getHolder(), entityPlayer);
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return TGMultiblockAbility.GHOST_CIRCUIT;
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> list) {
        list.add(this.circuitInventory);
    }

    @Override
    public boolean hasGhostCircuitInventory() {
        return true;
    }

    @Override
    public void setGhostCircuitConfig(int config) {
        if (this.circuitInventory.getCircuitValue() == config) {
            return;
        }
        this.circuitInventory.setCircuitValue(config);
        if (!getWorld().isRemote) {
            markDirty();
        }
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return true;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer = Textures.PIPE_IN_OVERLAY;
            renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
            SimpleOverlayRenderer overlay = Textures.ITEM_HATCH_INPUT_OVERLAY;
            overlay.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }
}
