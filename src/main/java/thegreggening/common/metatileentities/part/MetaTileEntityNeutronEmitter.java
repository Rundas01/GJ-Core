package thegreggening.common.metatileentities.part;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.metatileentity.part.NeutronEmitterFluidTank;
import thegreggening.common.metatileentities.multiblock.electric.MetaTileEntityNuclearReactor;

import java.util.List;

public class MetaTileEntityNeutronEmitter extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IFluidTank> {

    private NeutronEmitterFluidTank inputTank;
    private MetaTileEntityNuclearReactor controller;
    private FluidTankList tanks;

    public MetaTileEntityNeutronEmitter(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityNeutronEmitter(metaTileEntityId);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        inputTank = new NeutronEmitterFluidTank(this, this.controller);
        fluidInventory = tanks = new FluidTankList(false, inputTank);
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return true;
    }

    @Override
    public FluidTankList getImportFluids() {
        return new FluidTankList(false, inputTank);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 133);
        builder.widget(new LabelWidget(6, 6, getMetaFullName()));
        builder.widget(new TankWidget(inputTank, 79, 18, 18, 18).setContainerClicking(true, true).setAlwaysShowFull(true).setBackgroundTexture(GuiTextures.FLUID_SLOT));
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
        tag.setTag("Fluid Inventory", tanks.serializeNBT());
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tanks.deserializeNBT(tag.getCompoundTag("Fluid Inventory"));
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return TGMultiblockAbility.NEUTRON_EMITTER;
    }

    @Override
    public void registerAbilities(List<IFluidTank> list) {
        list.add(inputTank);
    }

    public void setController(MetaTileEntityNuclearReactor controller) {
        this.controller = controller;
        this.inputTank.setController(controller);
    }

    public boolean isDirectedAtFissionChamber(@Nullable MetaTileEntityFissionChamber fc) {
        EnumFacing.AxisDirection direction = getFrontFacing().getAxisDirection();
        EnumFacing.Axis axis = getFrontFacing().getAxis();
        BlockPos pos = getPos();
        World world = getWorld();
        TileEntity te;
        return switch (axis) {
            case X -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX() + i, pos.getY(), pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                } else {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX() - i, pos.getY(), pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                }
                yield false;
            }
            case Y -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                } else {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                }
                yield false;
            }
            case Z -> {
                if (direction == EnumFacing.AxisDirection.POSITIVE) {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + i));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                } else {
                    for (int i = 1; i < 5; i++) {
                        te = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - i));
                        if (te instanceof IGregTechTileEntity) {
                            if (((IGregTechTileEntity) te).getMetaTileEntity() instanceof MetaTileEntityFissionChamber) {
                                yield ((MetaTileEntityFissionChamber) ((IGregTechTileEntity) te).getMetaTileEntity()).getController() != null && (fc == null || ((IGregTechTileEntity) te).getMetaTileEntity() == fc);
                            }
                        }
                    }
                }
                yield false;
            }
        };
    }
}
