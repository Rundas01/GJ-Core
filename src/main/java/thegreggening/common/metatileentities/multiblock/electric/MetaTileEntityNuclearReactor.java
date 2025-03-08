package thegreggening.common.metatileentities.multiblock.electric;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.recipe.NuclearReactorRecipeLogic;
import thegreggening.common.metatileentities.part.*;

import java.util.ArrayList;
import java.util.List;

import static gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController.tieredCasing;
import static thegreggening.common.recipe.TGRecipeMaps.FISSION_REACTOR_RECIPES;

public class MetaTileEntityNuclearReactor extends RecipeMapMultiblockController {

    private int amountPellets = 0, amountCoolant = 0;
    public int currentEnergy;
    public int size;

    public MetaTileEntityNuclearReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, FISSION_REACTOR_RECIPES);
        this.recipeMapWorkable = new NuclearReactorRecipeLogic(this);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityNuclearReactor(this.metaTileEntityId);
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XHGHX", "XHGHX", "XHGHX", "XXXXX")
                .aisle("XXXXX", "H###H", "H###H", "H###H", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXXXX", "H###H", "H###H", "H###H", "XXXXX")
                .aisle("XXXXX", "XHGHX", "XHSHX", "XHGHX", "XXXXX")
                .where('S', selfPredicate())
                .where('G', states(getGlassState()))
                .where('T', tieredCasing())
                .where('#', any().or(abilities(TGMultiblockAbility.COOLANT_CELL).setMinGlobalLimited(0)).or(abilities(TGMultiblockAbility.FISSION_CELL).setMinGlobalLimited(0)).or(abilities(TGMultiblockAbility.BREEDER_CELL).setMinGlobalLimited(0)).or(abilities(TGMultiblockAbility.NEUTRON_EMITTER).setExactLimit(1)))
                .where('H', abilities(MultiblockAbility.PASSTHROUGH_HATCH).setExactLimit(4).or(abilities(TGMultiblockAbility.GHOST_CIRCUIT).setExactLimit(1)).or(states(getCasingState())))
                .where('X', states(getCasingState()))
                .build();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addCustom(tl -> {
                    if (isStructureFormed()) {
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY,
                                "tg.multiblock.nuclear_reactor.amount_pellets",
                                amountPellets));
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY,
                                "tg.multiblock.nuclear_reactor.amount_coolant",
                                amountCoolant));
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY,
                                "tg.multiblock.nuclear_reactor.base_coolant_conversion",
                                recipeMapWorkable.getMaxVoltage()));
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY,
                                "tg.multiblock.nuclear_reactor.parallel_amount",
                                recipeMapWorkable.getParallelLimit())
                        );
                    }
                })
                .addWorkingStatusLine()
                .addProgressLine(recipeMapWorkable.getProgressPercent());
    }

    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GCYMTextures.ATOMIC_CASING;
    }

    protected IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.ATOMIC_CASING);
    }

    protected IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("tg.multiblock.nuclear_reactor.description") };
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return false;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        for (IMultiblockPart part : getMultiblockParts()) {
            if (part instanceof MetaTileEntityReactorCooler) {
                ((MetaTileEntityReactorCooler) part).setController(this);
            }
            if (part instanceof MetaTileEntityFissionChamber) {
                ((MetaTileEntityFissionChamber) part).setController(this);
            }
            if (part instanceof MetaTileEntityBreedingChamber) {
                ((MetaTileEntityBreedingChamber) part).setController(this);
            }
            if (part instanceof MetaTileEntityNeutronEmitter) {
                ((MetaTileEntityNeutronEmitter) part).setController(this);
            }
        }
        this.size = 5;
        this.recipeMapWorkable.setParallelLimit(getFissionChambers().size());
        updatePellets();
        updateCoolant();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (isStructureFormed()) {
            for (MetaTileEntityFissionChamber chamber : getFissionChambers()) {
                chamber.setController(this);
            }
            for (MetaTileEntityBreedingChamber chamber : getBreedingChambers()) {
                chamber.setController(this);
            }
            getNeutronEmitter().setController(this);
            this.size = 5;
            this.recipeMapWorkable.setParallelLimit(getFissionChambers().size());
            updatePellets();
            updateCoolant();
        }
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        for (IMultiblockPart part : getMultiblockParts()) {
            if (part instanceof MetaTileEntityReactorCooler) {
                ((MetaTileEntityReactorCooler) part).setController(null);
            }
            if (part instanceof MetaTileEntityFissionChamber) {
                ((MetaTileEntityFissionChamber) part).setController(null);
            }
            if (part instanceof MetaTileEntityBreedingChamber) {
                ((MetaTileEntityBreedingChamber) part).setController(null);
            }
            if (part instanceof MetaTileEntityNeutronEmitter) {
                ((MetaTileEntityNeutronEmitter) part).setController(null);
            }
        }
        this.size = 0;
        this.recipeMapWorkable.setParallelLimit(0);
        setAmountPellets(0);
        setAmountCoolant(0);
    }

    public <T> List<T> getParts(Class<T> c) {
        List<T> parts = new ArrayList<>();

        if (!isStructureFormed()) {
            return parts;
        }

        for (IMultiblockPart part : getMultiblockParts()) {
            if (c.isInstance(part)) {
                parts.add(c.cast(part));
            }
        }

        return parts;
    }

    public List<MetaTileEntityReactorCooler> getCoolers() {
        return getParts(MetaTileEntityReactorCooler.class);
    }
    public List<MetaTileEntityFissionChamber> getFissionChambers() { return getParts(MetaTileEntityFissionChamber.class); }
    public List<MetaTileEntityBreedingChamber> getBreedingChambers() { return getParts(MetaTileEntityBreedingChamber.class); }
    public MetaTileEntityCircuitBus getCircuitBus() { return getParts(MetaTileEntityCircuitBus.class).get(0); }
    public MetaTileEntityNeutronEmitter getNeutronEmitter() { return getParts(MetaTileEntityNeutronEmitter.class).get(0); }
    public void setAmountPellets(int amountPellets) {
        this.amountPellets = amountPellets;
    }
    public void setAmountCoolant(int amountCoolant) {
        this.amountCoolant = amountCoolant;
    }

    public void updatePellets() {
        int pellets = 0;
        for (MetaTileEntityFissionChamber part : getFissionChambers()) {
            pellets += part.getImportItems().getStackInSlot(0).getCount();
        }
        for (MetaTileEntityBreedingChamber part : getBreedingChambers()) {
            pellets += part.getImportItems().getStackInSlot(0).getCount();
        }
        setAmountPellets(pellets);
    }

    public void updateCoolant() {
        int coolant = 0;
        for (MetaTileEntityReactorCooler part : getCoolers()) {
            coolant += part.getImportFluids().getTankAt(0).getFluidAmount();
        }
        setAmountCoolant(coolant);
    }

    public boolean isBreeder() {
        return !getBreedingChambers().isEmpty();
    }

    /*@Override
    public void update() {
        super.update();
        if (isActive() && getOffsetTimer() % 20 == 0) {
            if (getCoolers().get(0).getImportFluids().getTankAt(0).getFluidAmount() == 0) {
                return;
            }
            TGLog.logger.debug("Amount Coolers: " + getCoolers().size());
            TGLog.logger.debug("Amount Input Tanks: " + getCoolers().get(0).getImportFluids().getTanks());
            Material coolant = getFromFluidStack(getCoolers().get(0).getImportFluids().getTankAt(0).getFluid());
            Material hotCoolant = getFromString("hot_" + coolant.getName());
            getCoolers().get(0).getImportFluids().drain(coolant.getFluid((int) recipeMapWorkable.getMaxVoltage()), true);
            getCoolers().get(0).getExportFluids().fill(hotCoolant.getFluid((int) recipeMapWorkable.getMaxVoltage()), true);
        }
    }*/
}
