package thegreggening.common.metatileentities.multiblock.primitive;

import static thegreggening.api.render.TGTextures.CRUCIBLE_OVERLAY;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandler;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;

import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.common.metatileentities.part.MetaTileEntityHeatHatch;

public class MetaTileEntityCrucible extends RecipeMapMultiblockController {

    public int stoneAmount = 0, smeltingModifier = 0;

    public MetaTileEntityCrucible(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, null);
        this.recipeMapWorkable = new CrucibleRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityCrucible(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), stoneAmount < 64000)
                .addCustom(t -> {
                    ITextComponent stoneText = TextComponentUtil.translationWithColor(TextFormatting.WHITE,
                            "tg.machine.crucible.stone_amount", Integer.toString(stoneAmount));
                    t.add(TextComponentUtil.setHover(stoneText));
                    ITextComponent modifierText = TextComponentUtil.translationWithColor(TextFormatting.WHITE,
                            "tg.machine.crucible.modifier", Integer.toString(smeltingModifier));
                    t.add(TextComponentUtil.setHover(modifierText));
                    if (isStructureFormed()) {
                        int total = 0;
                        IMultipleTankHandler output = this.getOutputFluidInventory();
                        for (IMultipleTankHandler.MultiFluidTankEntry tank : output.getFluidTanks()) {
                            total += tank.getFluidAmount();
                        }
                        ITextComponent lavaText = TextComponentUtil.translationWithColor(TextFormatting.WHITE,
                                "tg.machine.crucible.lava_amount", Integer.toString(total));
                        t.add(TextComponentUtil.setHover(lavaText));
                    }
                })
                .addWorkPausedLine(recipeMapWorkable.isWorkingEnabled());
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("AHA", "AAA", "AAA")
                .aisle("AAA", "ALA", "A#A")
                .aisle("ISO", "AAA", "AAA")
                .where('S', selfPredicate())
                .where('H', abilities(TGMultiblockAbility.HEAT_HATCH))
                .where('L', blocks(Blocks.LAVA))
                .where('A', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS)))
                .where('I', abilities(MultiblockAbility.IMPORT_ITEMS))
                .where('O', abilities(MultiblockAbility.EXPORT_FLUIDS))
                .where('#', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.PRIMITIVE_BRICKS;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return CRUCIBLE_OVERLAY;
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_MECHANICAL;
    }

    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.FURNACE;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("stoneAmount", stoneAmount);
        data.setInteger("modifier", smeltingModifier);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        stoneAmount = data.getInteger("stoneAmount");
        smeltingModifier = data.getInteger("modifier");
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        List<IMultiblockPart> parts = getMultiblockParts();
        for (IMultiblockPart part : parts) {
            if (part instanceof MetaTileEntityHeatHatch) {
                ((MetaTileEntityHeatHatch) part).setController(this);
            }
        }
        setSmeltingModifier(this);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (isStructureFormed()) {
            List<IMultiblockPart> parts = getMultiblockParts();
            for (IMultiblockPart part : parts) {
                if (part instanceof MetaTileEntityHeatHatch) {
                    ((MetaTileEntityHeatHatch) part).setController(this);
                }
            }
        }
        setSmeltingModifier(this);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        List<IMultiblockPart> parts = getMultiblockParts();
        for (IMultiblockPart part : parts) {
            if (part instanceof MetaTileEntityHeatHatch) {
                ((MetaTileEntityHeatHatch) part).setController(null);
            }
        }
        setSmeltingModifier(null);
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    public void setSmeltingModifier(RecipeMapMultiblockController controller) {
        if (isStructureFormed()) {
            IFluidTank heatProvider = controller.getAbilities(TGMultiblockAbility.HEAT_HATCH).get(0);
            if (heatProvider.getFluidAmount() == 0) {
                smeltingModifier = 0;
                return;
            }
            smeltingModifier = Objects.requireNonNull(heatProvider.getFluid()).getFluid().getTemperature() -
                    Materials.Lava.getFluid().getTemperature() + 1;
        }
    }

    protected class CrucibleRecipeLogic extends MultiblockRecipeLogic {

        public CrucibleRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        private final List<String> validTags = Arrays.asList("stoneCobble", "stone", "stoneSmooth");

        @Override
        public void update() {
            super.update();
            RecipeMapMultiblockController controller = (RecipeMapMultiblockController) this.metaTileEntity;
            if (isStructureFormed() && !getWorld().isRemote && isWorkingEnabled()) {
                convertStone(controller);
                if ((Math.floorMod(getOffsetTimer(), 20) == 0) && stoneAmount > 0) {
                    outputLava(controller);
                }
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            stoneAmount = 0;
            smeltingModifier = 0;
        }

        private void convertStone(RecipeMapMultiblockController controller) {
            IItemHandler inputs = controller.getInputInventory();
            for (int i = 0; i < inputs.getSlots(); i++) {
                ItemStack stack = inputs.getStackInSlot(i);
                if (!stack.isEmpty() && isValidToConsume(stack) && stoneAmount <= 63000) {
                    inputs.extractItem(i, 1, false);
                    stoneAmount += 1000;
                    return;
                }
            }
        }

        private void outputLava(RecipeMapMultiblockController controller) {
            IMultipleTankHandler output = controller.getOutputFluidInventory();
            for (int i = 0; i < output.getTanks(); i++) {
                IMultipleTankHandler.MultiFluidTankEntry tank = output.getTankAt(i);
                if (tank.getFluidAmount() < tank.getCapacity() &&
                        (tank.getFluidAmount() == 0 ||
                                Objects.requireNonNull(tank.getFluid()).getFluid() == Materials.Lava.getFluid()) &&
                        smeltingModifier > 0) {
                    int amount = Math.min(smeltingModifier, stoneAmount);
                    stoneAmount -= amount;
                    tank.fill(Materials.Lava.getFluid(amount), true);
                    return;
                }
            }
        }

        private boolean isValidToConsume(ItemStack stack) {
            for (String tag : validTags) {
                if (OreDictUnifier.hasOreDictionary(stack, tag)) {
                    return true;
                }
            }
            return false;
        }
    }
}
