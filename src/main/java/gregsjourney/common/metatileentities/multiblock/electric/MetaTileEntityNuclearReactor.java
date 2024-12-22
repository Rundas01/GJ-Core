package gregsjourney.common.metatileentities.multiblock.electric;

import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregsjourney.api.metatileentity.multiblock.GJMultiblockAbility;
import gregsjourney.api.recipe.NoEnergyMultiblockRecipeLogic;
import gregsjourney.api.unification.property.CoolantProperty;
import gregsjourney.api.unification.property.GJPropertyKeys;
import gregsjourney.common.metatileentities.part.MetaTileEntityCoolantHatch;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.Material;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController.tieredCasing;
import static gregsjourney.common.recipe.GJRecipeMaps.BREEDER_REACTOR_RECIPES;
import static gregsjourney.common.recipe.GJRecipeMaps.FISSION_REACTOR_RECIPES;
import static gregsjourney.utils.GJMaterialUtil.getFromFluidStack;
import static gregsjourney.utils.GJUtil.getTotalFluidAmount;

public class MetaTileEntityNuclearReactor extends MultiMapMultiblockController {

    public MetaTileEntityNuclearReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[]{FISSION_REACTOR_RECIPES, BREEDER_REACTOR_RECIPES});
        this.recipeMapWorkable = new NuclearReactorRecipeLogic(this);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityNuclearReactor(this.metaTileEntityId);
    }

    @NotNull
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XGX", "XGX", "XGX", "XXX")
                .aisle("XXX", "GPG", "GTG", "GPG", "XXX")
                .aisle("XSX", "XGX", "XGX", "XGX", "XXX")
                .where('S', this.selfPredicate())
                .where('P', states(getPipeCasingState()))
                .where('G', states(getGlassState()))
                .where('T', tieredCasing())
                .where('X', states(getCasingState())
                        .or(autoAbilities(false, true, true, true, false, false, false))
                        .or(abilities(GJMultiblockAbility.COOLANT_HATCH).setExactLimit(1))
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS).setExactLimit(1))
                )
                .build();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addCustom(tl -> {
                    tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gj.multiblock.active_recipe_map", getCurrentRecipeMap().getLocalizedName()));
                    if (isStructureFormed()) {
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gj.multiblock.nuclear_reactor.base_coolant_conversion", recipeMapWorkable.getMaxVoltage()));
                        //TODO: Aktiver Coolant muss nicht in slot 1 sein
                        if (getTotalFluidAmount(getCoolantInput()) > 0) {
                            IMultipleTankHandler.MultiFluidTankEntry inputTank = getCoolantInput().getTankAt(0);
                            IMultipleTankHandler.MultiFluidTankEntry outputTank = getOutputFluidInventory().getTankAt(0);
                            CoolantProperty property = getFromFluidStack(inputTank.getFluid()).getProperty(GJPropertyKeys.COOLANT_PROPERTY);
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gj.multiblock.nuclear_reactor.actual_coolant_conversion", calculateActualCoolantConversion(recipeMapWorkable, inputTank, outputTank, property)));
                        }
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

    protected IBlockState getPipeCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    protected IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gj.multiblock.nuclear_reactor.description") };
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    public int calculateActualCoolantConversion(MultiblockRecipeLogic recipeLogic, IMultipleTankHandler.MultiFluidTankEntry inputTank, IMultipleTankHandler.MultiFluidTankEntry outputTank, CoolantProperty property) {
        return (int) Math.min(recipeLogic.getMaxVoltage() * property.conversion(), Math.min(inputTank.getFluidAmount(), outputTank.getCapacity() - outputTank.getFluidAmount()));
    }

    protected FluidTankList getCoolantInput() {
        List<IMultiblockPart> parts = getMultiblockParts();
        for (IMultiblockPart part : parts) {
            if (part instanceof MetaTileEntityCoolantHatch) {
                return ((MetaTileEntityCoolantHatch) part).getImportFluids();
            }
        }
        return null;
    }

    protected class NuclearReactorRecipeLogic extends NoEnergyMultiblockRecipeLogic {
        RecipeMapMultiblockController controller;
        public NuclearReactorRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.controller = tileEntity;
        }

        @Override
        public long getMaxVoltage() {
            List<ITieredMetaTileEntity> list = controller.getAbilities(GCYMMultiblockAbility.TIERED_HATCH);

            if (list.isEmpty())
                return super.getMaxVoltage();

            return list.get(0).getTier();
        }

        @Override
        public void update() {
            super.update();
            if (getWorld().isRemote) {
                return;
            }
            if (!isStructureFormed()) {
                return;
            }
            setWorkingEnabled(getTotalFluidAmount(MetaTileEntityNuclearReactor.this.getCoolantInput()) != 0);
            if ((Math.floorMod(getOffsetTimer(), 20) == 0) && isWorkingEnabled() && isActive()) {
                convertCoolant();
            }
        }

        private IMultipleTankHandler.MultiFluidTankEntry getValidOutputTank(IMultipleTankHandler inventory, CoolantProperty property) {
            Material hotCoolant = property.hotCoolant();
            for (IMultipleTankHandler.MultiFluidTankEntry tank : inventory) {
                if (tank.getFluidAmount() == 0) {
                    return tank;
                }
                if (tank.getFluid().getFluid() == hotCoolant.getFluid()){
                    return tank;
                }
            }
            return null;
        }

        private void convertCoolant() {
            FluidTankList coolantTanks = MetaTileEntityNuclearReactor.this.getCoolantInput();
            int coolantAmount = 0;
            int activeIndex = -1;
            int amount;
            for (int i = 0; i < coolantTanks.getTanks(); i++) {
                amount = coolantTanks.getTankAt(i).getFluidAmount();
                coolantAmount += amount;
                if (amount > 0 && activeIndex == -1){
                    activeIndex = i;
                }
            }
            if (coolantAmount == 0) {
                return;
            }
            IMultipleTankHandler.MultiFluidTankEntry activeInputTank = coolantTanks.getTankAt(activeIndex);
            Material coolant = getFromFluidStack(activeInputTank.getFluid());
            CoolantProperty property = coolant.getProperty(GJPropertyKeys.COOLANT_PROPERTY);
            IMultipleTankHandler.MultiFluidTankEntry activeOutputTank = getValidOutputTank(MetaTileEntityNuclearReactor.this.getOutputFluidInventory(), property);
            if (activeOutputTank == null) {
                return;
            }
            int actualConversion = calculateActualCoolantConversion(this, activeInputTank, activeOutputTank, property);
            if (actualConversion == 0) {
                return;
            }
            if (activeInputTank.drain(coolant.getFluid(actualConversion), false).amount == 0) {
                return;
            }
            if (activeOutputTank.fill(property.hotCoolant().getFluid(actualConversion), false) == 0) {
                return;
            }
            activeInputTank.drain(coolant.getFluid(actualConversion), true);
            activeOutputTank.fill(property.hotCoolant().getFluid(actualConversion), true);
        }
    }
}
