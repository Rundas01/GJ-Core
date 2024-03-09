package gregsjourney.common.metatileentities.multiblock.electric;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.apiculture.blocks.BlockAlvearyType;
import forestry.apiculture.blocks.BlockRegistryApiculture;
import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.render.GJTextures;
import gregsjourney.common.metatileentities.part.MetaTileEntityBeeHatch;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.IMachineHatchMultiblock;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static forestry.apiculture.ModuleApiculture.getBlocks;

public class MetaTileEntityIndustrialAlveary extends RecipeMapMultiblockController implements IMachineHatchMultiblock {

    private int queenCount = 0;
    private boolean queenChanged = false;
    private int consumption = 0;
    private int productionCycle = 0, currentCycle = 0;
    BlockRegistryApiculture blocks = getBlocks();
    public MetaTileEntityIndustrialAlveary(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, null);
        this.recipeMapWorkable = new MegaAlvearyRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityIndustrialAlveary(metaTileEntityId);
    }

    @Override
    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XEX", "XMX", "XXX")
                .aisle("XXX", "XAX", "XXX")
                .aisle("XXX", "ISO", "XXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()))
                .where('M', abilities(GJMultiblockAbility.BEE_HATCH).setExactLimit(1))
                .where('I', abilities(MultiblockAbility.IMPORT_ITEMS))
                .where('O', abilities(MultiblockAbility.EXPORT_ITEMS))
                .where('E', abilities(MultiblockAbility.INPUT_ENERGY))
                .where('A', blocks(blocks.getAlvearyBlock(BlockAlvearyType.PLAIN)))
                .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GJTextures.HEAT_EXCHANGER_OVERLAY;
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gj.multiblock.mega_alveary.description") };
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        int maxAmount = 0;
        if(isStructureFormed()) {
            List<IMultiblockPart> parts = getMultiblockParts();
            for (IMultiblockPart part : parts) {
                if (part instanceof MetaTileEntityBeeHatch) {
                    maxAmount = ((MetaTileEntityBeeHatch) part).getBeeLimit();
                }
            }
        }
        int finalMaxAmount = maxAmount;
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), queenCount > 0)
                .addCustom(t -> {
                    ITextComponent queensText = TextComponentUtil.translationWithColor(TextFormatting.GOLD, "gj.machine.tooltip.ia_qc", Integer.toString(queenCount));
                    t.add(TextComponentUtil.setHover(queensText));
                    ITextComponent maxQueensText = TextComponentUtil.translationWithColor(TextFormatting.GOLD, "gj.machine.tooltip.ia_mqc", Integer.toString(finalMaxAmount));
                    t.add(TextComponentUtil.setHover(maxQueensText));
                    ITextComponent consumptionText = TextComponentUtil.translationWithColor(TextFormatting.WHITE, "gj.machine.tooltip.ia_cc", Long.toString(consumption));
                    t.add(TextComponentUtil.setHover(consumptionText));
                    ITextComponent modifierText = TextComponentUtil.translationWithColor(TextFormatting.WHITE, "gj.machine.tooltip.ia_mod", Long.toString(Math.floorDiv(getEnergyContainer().getInputVoltage(), 32768) * 5));
                    t.add(TextComponentUtil.setHover(modifierText));
                    ITextComponent cycleText = TextComponentUtil.translationWithColor(TextFormatting.WHITE, "gj.machine.tooltip.ia_cycle", Integer.toString(productionCycle));
                    t.add(TextComponentUtil.setHover(cycleText));
                })
                .addWorkPausedLine(recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    public void notifyMachineChanged() {
        queenChanged = true;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        ((MegaAlvearyRecipeLogic) this.recipeMapWorkable).updateQueen(this);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_MECHANICAL;
    }

    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.ARC;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("consumption",consumption);
        data.setInteger("currentCycle",currentCycle);
        data.setInteger("productionCycle",productionCycle);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.consumption = data.getInteger("consumption");
        this.currentCycle = data.getInteger("currentCycle");
        this.productionCycle = data.getInteger("productionCycle");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.recipeMapWorkable.updateWorkable();
    }

    protected class MegaAlvearyRecipeLogic extends MultiblockRecipeLogic {

        public MegaAlvearyRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public void invalidate() {
            super.invalidate();
            queenChanged = true;
        }

        public void updateQueen(RecipeMapMultiblockController controller) {
            queenCount = 0;
            if(isStructureFormed()){
                ItemStack bee;
                for (int i = 0; i < controller.getAbilities(GJMultiblockAbility.BEE_HATCH).get(0).getSlots(); i++) {
                    bee = controller.getAbilities(GJMultiblockAbility.BEE_HATCH).get(0).getStackInSlot(i);
                    if (!bee.isEmpty()) {
                        queenCount += 1;
                    }
                }
                updateConsumption();
                updateProductionCycle(controller);
            }
        }

        public void updateConsumption() {
            consumption = 8192 * queenCount;
        }

        @Override
        public void updateWorkable() {
            super.updateWorkable();
            updateConsumption();
            updateProductionCycle((RecipeMapMultiblockController) this.getMetaTileEntity());
        }

        @Override
        public void update() {
            super.update();
            RecipeMapMultiblockController controller = (RecipeMapMultiblockController) this.metaTileEntity;
            if(queenChanged){
                updateQueen(controller);
                queenChanged = false;
            }
            if(isWorkingEnabled() && isStructureFormed() && drawEnergy(consumption, true) && !getWorld().isRemote){
                drawEnergy(consumption, false);
                currentCycle += 1;
                if(currentCycle == productionCycle){
                    currentCycle = 0;
                    produce(controller);
                    //frame logic will go here
                }
            }
        }
    }

    private void updateProductionCycle(RecipeMapMultiblockController controller) {
        productionCycle = 0;
        int lifespan;
        if(queenCount == 0) return;
        if(isStructureFormed()){
            ItemStack beeStack;
            for (int i = 0; i < controller.getAbilities(GJMultiblockAbility.BEE_HATCH).get(0).getSlots(); i++) {
                beeStack = controller.getAbilities(GJMultiblockAbility.BEE_HATCH).get(0).getStackInSlot(i);
                if (!beeStack.isEmpty()) {
                    assert BeeManager.beeRoot != null;
                    lifespan = Objects.requireNonNull(BeeManager.beeRoot.getMember(beeStack)).getGenome().getLifespan();
                    productionCycle += lifespan;
                }
            }
            productionCycle /= queenCount;
        }
    }

    private void produce(RecipeMapMultiblockController controller) {
        ItemStack beeStack;
        IBee bee;
        IItemHandlerModifiable beeHatch = controller.getAbilities(GJMultiblockAbility.BEE_HATCH).get(0);
        List<ItemStack> products = new ArrayList<>();
        for (int i = 0; i < beeHatch.getSlots(); i++) {
            beeStack = beeHatch.getStackInSlot(i);
            if (!beeStack.isEmpty()) {
                assert BeeManager.beeRoot != null;
                bee = BeeManager.beeRoot.getMember(beeStack);
                assert bee != null;
                Map<ItemStack, Float> possibleProducts = bee.getGenome().getPrimary().getProductChances();
                Map<ItemStack, Float> possibleSpecialties = bee.getGenome().getPrimary().getSpecialtyChances();
                IBee finalBee = bee;
                possibleProducts.forEach((product, chance) -> calculateShouldProduce(product, chance, products, controller, finalBee));
                possibleSpecialties.forEach((product, chance) -> calculateShouldProduce(product, chance, products, controller, finalBee));
                if(GTTransferUtils.addItemsToItemHandler(this.getOutputInventory(),true,products)){
                    GTTransferUtils.addItemsToItemHandler(this.getOutputInventory(),false,products);
                }
            }
        }
    }

    private void calculateShouldProduce(ItemStack product, Float chance, List<ItemStack> products, RecipeMapMultiblockController controller, IBee bee) {
        if(Math.random() < chance + computeModifier(controller, bee)){
            products.add(new ItemStack(product.getItem(),1));
        }
    }

    private float computeModifier(RecipeMapMultiblockController controller, IBee bee) {
        IBeeGenome genome = bee.getGenome();
        float purebreadModifier = genome.getPrimary() == genome.getSecondary() ? 0.1f : 0;
        float speedModifier = genome.getSpeed() * 2;
        return Math.floorDiv(controller.getEnergyContainer().getInputVoltage(), 32768) * 0.05f + purebreadModifier + speedModifier;
    }
}
