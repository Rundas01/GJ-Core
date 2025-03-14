package thegreggening.common.metatileentities.multiblock.electric;

import static thegreggening.api.block.IBlockOrientable.FACING;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.BlockInfo;
import gregtech.client.renderer.ICubeRenderer;

import thegreggening.api.metatileentity.multiblock.TGMultiblockAbility;
import thegreggening.api.recipe.AdvancedTurbineRecipeLogic;
import thegreggening.common.block.GJMetaBlocks;
import thegreggening.common.block.blocks.BlockAlternatorCoil;
import thegreggening.common.block.blocks.BlockTurbineRotor;

public class MetaTileEntityAdvancedTurbine extends FuelMultiblockController implements ITieredMetaTileEntity {

    public final int tier;
    public final IBlockState casingState;
    public final ICubeRenderer casingRenderer;
    public final ICubeRenderer frontOverlay;

    public MetaTileEntityAdvancedTurbine(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier,
                                         IBlockState casingState, ICubeRenderer casingRenderer,
                                         ICubeRenderer frontOverlay) {
        super(metaTileEntityId, recipeMap, tier);
        this.casingState = casingState;
        this.casingRenderer = casingRenderer;
        this.frontOverlay = frontOverlay;
        this.tier = tier;
        this.recipeMapWorkable = new AdvancedTurbineRecipeLogic(this);
        this.recipeMapWorkable.setMaximumOverclockVoltage(GTValues.V[tier]);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAdvancedTurbine(metaTileEntityId, recipeMap, tier, casingState, casingRenderer,
                frontOverlay);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack fuelStack = ((AdvancedTurbineRecipeLogic) recipeMapWorkable).getInputFluidStack();
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;
            ITextComponent fuelName = new TextComponentTranslation(
                    fuelAmount == 0 ? "gregtech.fluid.empty" : fuelStack.getUnlocalizedName());
            textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.fuel_amount", fuelAmount, fuelName));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("AAAAAAAAA", "OEEEEEEEE", "A   A   A")
                .aisle("AAAAAAAAA", "EDDDDCCCR", "AAAAAAAAA")
                .aisle("AAAAAAAAA", "OSEEEEEEE", "A   A   A")
                .where('S', selfPredicate())
                .where('A', states(casingState))
                .where('O',
                        states(casingState).or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .or(abilities(MultiblockAbility.EXPORT_FLUIDS))))
                .where('E', states(casingState).or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1)))
                .where('R',
                        metaTileEntities(
                                MultiblockAbility.REGISTRY.get(TGMultiblockAbility.ADVANCED_ROTOR_HOLDER).stream()
                                        .filter(mte -> (mte instanceof ITieredMetaTileEntity) &&
                                                (((ITieredMetaTileEntity) mte).getTier() >= tier))
                                        .toArray(MetaTileEntity[]::new))
                                                .addTooltips("gregtech.multiblock.pattern.clear_amount_3")
                                                .addTooltip("gregtech.multiblock.pattern.error.limited.1",
                                                        GTValues.VN[tier])
                                                .setExactLimit(1))
                .where('C', coilOrientation())
                .where('D', rotorOrientation())
                .where(' ', any())
                .build();
    }

    protected TraceabilityPredicate rotorOrientation() {
        Supplier<BlockInfo[]> supplier = () -> new BlockInfo[] {
                new BlockInfo(steelRotorState().withProperty(FACING, EnumFacing.WEST)) };
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            if (!(state.getBlock() instanceof BlockTurbineRotor)) return false;
            EnumFacing facing = MetaTileEntityAdvancedTurbine.this.getFrontFacing();
            EnumFacing rotorFacing = EnumFacing.byHorizontalIndex((facing.getHorizontalIndex() + 1) % 4).getOpposite();
            return state == steelRotorState().withProperty(FACING, rotorFacing) ||
                    state == steelRotorState().withProperty(FACING, rotorFacing.getOpposite());
        }, supplier);
    }

    protected TraceabilityPredicate coilOrientation() {
        Supplier<BlockInfo[]> supplier = () -> new BlockInfo[] {
                new BlockInfo(copperCoilState().withProperty(FACING, EnumFacing.WEST)) };
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            if (!(state.getBlock() instanceof BlockAlternatorCoil)) return false;
            EnumFacing facing = MetaTileEntityAdvancedTurbine.this.getFrontFacing();
            EnumFacing coilFacing = EnumFacing.byHorizontalIndex((facing.getHorizontalIndex() + 1) % 4);
            return state == copperCoilState().withProperty(FACING, coilFacing) ||
                    state == copperCoilState().withProperty(FACING, coilFacing.getOpposite());
        }, supplier);
    }

    protected IBlockState steelRotorState() {
        return GJMetaBlocks.TURBINE_ROTOR.getState(BlockTurbineRotor.BlockTurbineRotorType.STEEL);
    }

    protected IBlockState copperCoilState() {
        return GJMetaBlocks.ALTERNATOR_COIL.getState(BlockAlternatorCoil.AlternatorCoilType.COPPER);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return casingRenderer;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return frontOverlay;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return false;
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public boolean canVoidRecipeFluidOutputs() {
        return true;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return true;
    }
}
