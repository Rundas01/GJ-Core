package thegreggening.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.metatileentity.part.FissionChamberItemStackHandler;
import thegreggening.api.metatileentity.part.StacksizeLimitedItemStackHandler;
import thegreggening.api.unification.property.TGPropertyKeys;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static appeng.util.BlockPosUtils.getDistance;
import static thegreggening.api.unification.ore.TGOrePrefixes.nuclearFuel;
import static thegreggening.utils.TGMaterialUtil.getFromItemStack;
import static thegreggening.utils.TGNuclearUtil.getBreedingResultMaterial;

public class MetaTileEntityFissionChamber extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IItemHandlerModifiable> {

    private FissionChamberItemStackHandler input;
    private StacksizeLimitedItemStackHandler output;
    private MetaTileEntityNuclearReactor controller;

    public MetaTileEntityFissionChamber(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityFissionChamber(metaTileEntityId);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        input = new FissionChamberItemStackHandler(this, this.controller, false);
        output = new StacksizeLimitedItemStackHandler(this, 1, this.controller, true, 1);
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
    public IItemHandlerModifiable getExportItems() { return output; }

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
            if (facing == getFrontFacing()) {
                Textures.VOLTAGE_CASINGS[1].renderSided(facing, renderState, translation, pipeline);
                Textures.PIPE_OUT_OVERLAY.renderSided(facing, renderState, translation, pipeline);
            } else {
                Textures.BUFFER_OVERLAY.renderSided(facing, renderState, translation, pipeline);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(Textures.VOLTAGE_CASINGS[1].getParticleSprite(), this.getPaintingColorForRendering());
    }

    @Override
    public boolean hasFrontFacing() {
        return true;
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
        return TGMultiblockAbility.FISSION_CELL;
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> list) {
        list.addAll(Arrays.asList(input,output));
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
        this.input.setController(controller);
    }

    @Override
    public MetaTileEntityNuclearReactor getController() {
        return controller;
    }

    private List<INotifiableHandler> getPartHandlers() {
        return Arrays.asList(input, output);
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
        List<INotifiableHandler> handlerList = getPartHandlers();
        handlerList.get(0).addNotifiableMetaTileEntity(controllerBase);
        handlerList.get(0).addToNotifiedList(this, handlerList.get(0), false);
        handlerList.get(1).addNotifiableMetaTileEntity(controllerBase);
        handlerList.get(1).addToNotifiedList(this, handlerList.get(1), true);
    }

    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase) {
        super.removeFromMultiBlock(controllerBase);
        List<INotifiableHandler> handlerList = getPartHandlers();
        for (INotifiableHandler handler : handlerList) {
            handler.removeNotifiableMetaTileEntity(controllerBase);
        }
    }

    public void emitNeutrons(int neutrons) {
        if (controller.getBreedingChambers().isEmpty()) {
            return;
        }
        for (int i = 0; i < neutrons; i++) {
            List<MetaTileEntityBreedingChamber> visitedChambers = new ArrayList<>();
            MetaTileEntityBreedingChamber nearestChamber = getNearestUnvisitedBreedingChamber(controller.getBreedingChambers(), visitedChambers);
            if (nearestChamber == null) {
                continue;
            }
            if (nearestChamber.getImportItems().getStackInSlot(0).isEmpty()) {
                continue;
            }
            Material material = getFromItemStack(nearestChamber.getImportItems().getStackInSlot(0));
            if (material == null) {
                continue;
            }
            if (!material.hasProperty(TGPropertyKeys.NUCLEAR_PROPERTY)) {
                continue;
            }
            Material breedingResultMaterial = getBreedingResultMaterial(material);
            if (breedingResultMaterial == null) {
                continue;
            }
            nearestChamber.getImportItems().extractItem(0, 1, false);
            nearestChamber.getExportItems().insertItem(0, OreDictUnifier.get(nuclearFuel, breedingResultMaterial), false);
            visitedChambers.add(nearestChamber);
        }
    }

    private MetaTileEntityBreedingChamber getNearestUnvisitedBreedingChamber(List<MetaTileEntityBreedingChamber> breedingChambers, List<MetaTileEntityBreedingChamber> visitedChambers) {
        MetaTileEntityBreedingChamber chamber = null;
        long dist = Long.MAX_VALUE;
        long d;
        for (MetaTileEntityBreedingChamber c : breedingChambers) {
            d = getDistance(this.getPos(), c.getPos());
            if (d < dist && !visitedChambers.contains(c)) {
                chamber = c;
                dist = d;
            }
        }
        return chamber;
    }

    public MetaTileEntityFissionChamber getDirectedAtFissionChamber(int maxDistance, List<MetaTileEntityFissionChamber> allChambers) {
        EnumFacing.AxisDirection direction = getFrontFacing().getAxisDirection();
        EnumFacing.Axis axis = getFrontFacing().getAxis();
        BlockPos pos = getPos();
        World world = getWorld();
        TileEntity te;
        MetaTileEntityFissionChamber fc;
        return switch (axis) {
            case X -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX() + i, pos.getY(), pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX() - i, pos.getY(), pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                }
                yield null;
            }
            case Y -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                }
                yield null;
            }
            case Z -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + i));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 1; i <= maxDistance; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - i));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                fc = (MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity();
                                if (fc.getController() != null && !allChambers.contains(fc)) {
                                    yield fc;
                                }
                            }
                        }
                    }
                }
                yield null;
            }
        };
    }
}
