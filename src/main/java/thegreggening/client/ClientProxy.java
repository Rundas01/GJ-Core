package thegreggening.client;

import com.google.common.collect.Lists;
import gregtech.api.fluids.FluidState;
import gregtech.api.fluids.GTFluid;
import gregtech.api.fluids.attribute.AttributedFluid;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.client.utils.TooltipHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;
import thegreggening.TheGreggening;
import thegreggening.api.unification.ore.TGOrePrefixes;
import thegreggening.api.unification.property.CoolantProperty;
import thegreggening.api.unification.property.TGMaterialFlags;
import thegreggening.api.unification.property.TGPropertyKeys;
import thegreggening.common.CommonProxy;
import thegreggening.common.block.GJMetaBlocks;
import thegreggening.integration.forestry.ForestryModule;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static gregtech.api.util.FluidTooltipUtil.registerTooltip;
import static net.minecraft.util.text.TextFormatting.GOLD;
import static thegreggening.utils.TGNuclearUtil.getBreedingResultMaterial;
import static thegreggening.utils.TGNuclearUtil.isRadioactive;

@Mod.EventBusSubscriber(modid = TheGreggening.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GJMetaBlocks.registerItemModels();
        ForestryModule.registerModels(event);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        registerTooltips();
    }

    public static Supplier<List<String>> liquidVoidableTooltip = () -> Lists
            .newArrayList(TextFormatting.YELLOW + I18n.format("tg.fluid_voiding_liquid.tooltip"));
    public static Supplier<List<String>> gasVoidableTooltip = () -> Lists
            .newArrayList(TextFormatting.YELLOW + I18n.format("tg.fluid_voiding_gas.tooltip"));
    public static Supplier<List<String>> radioactivityTooltip = () -> Lists
            .newArrayList(TooltipHelper.BLINKING_RED + I18n.format("tg.radioactivity.tooltip"));

    @SubscribeEvent
    public static void addRadioactivityTooltip(@NotNull ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        UnificationEntry unificationEntry = OreDictUnifier.getUnificationEntry(stack);
        if (unificationEntry != null) {
            Material material = unificationEntry.material;
            if (material != null) {
                if (isRadioactive(material)) {
                    event.getToolTip().add(TooltipHelper.BLINKING_RED + I18n.format("tg.radioactivity.tooltip"));
                    if (unificationEntry.orePrefix == TGOrePrefixes.nuclearFuel) {
                        if (material.hasFlag(TGMaterialFlags.FISSIONABLE)) {
                            event.getToolTip().add(GOLD + I18n.format("tg.fissionable.tooltip"));
                        }
                        if (getBreedingResultMaterial(material) != null){
                            event.getToolTip().add(GOLD + I18n.format("tg.breedable.tooltip", getBreedingResultMaterial(material).getLocalizedName()));
                        }
                    }
                }
            }
        }
    }

    public static void registerTooltips() {
        for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            if (Objects.equals(fluid.getName(), "water") || Objects.equals(fluid.getName(), "lava")) {
                registerTooltip(fluid, liquidVoidableTooltip);
            } else {
                if (fluid instanceof AttributedFluid aFluid) {
                    registerVoidingTooltip(fluid, aFluid);
                }
                if (fluid instanceof GTFluid.GTMaterialFluid gtFluid &&
                        gtFluid.getMaterial().hasProperty(TGPropertyKeys.COOLANT_PROPERTY)) {
                    registerCoolantTooltip(fluid, gtFluid);
                }
                if (fluid instanceof GTFluid.GTMaterialFluid gtFluid && gtFluid.getMaterial().isElement() &&
                        gtFluid.getMaterial().getElement().isIsotope) {
                    registerRadioactivityTooltip(fluid);
                }
            }
        }
    }

    private static void registerVoidingTooltip(Fluid fluid, AttributedFluid aFluid) {
        FluidState state = aFluid.getState();
        if (state == FluidState.LIQUID) {
            registerTooltip(fluid, liquidVoidableTooltip);
        }
        if (state == FluidState.GAS) {
            registerTooltip(fluid, gasVoidableTooltip);
        }
    }

    private static void registerCoolantTooltip(Fluid fluid, GTFluid.GTMaterialFluid gtFluid) {
        Material mat = gtFluid.getMaterial();
        CoolantProperty property = mat.getProperty(TGPropertyKeys.COOLANT_PROPERTY);
        registerTooltip(fluid,
                () -> Lists.newArrayList(I18n.format("tg.coolant_conversion_factor.tooltip", property.conversion())));
    }

    private static void registerRadioactivityTooltip(Fluid fluid) {
        registerTooltip(fluid, radioactivityTooltip);
    }
}
