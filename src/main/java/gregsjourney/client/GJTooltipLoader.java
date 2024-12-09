package gregsjourney.client;

import com.google.common.collect.Lists;
import gregsjourney.api.unification.property.CoolantProperty;
import gregsjourney.api.unification.property.GJPropertyKeys;
import gregtech.api.fluids.FluidState;
import gregtech.api.fluids.GTFluid;
import gregtech.api.fluids.attribute.AttributedFluid;
import gregtech.api.unification.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static gregtech.api.util.FluidTooltipUtil.registerTooltip;

public class GJTooltipLoader {

    static Supplier<List<String>> liquidVoidableTooltip = () -> Lists.newArrayList(TextFormatting.YELLOW + I18n.format("gj.fluid_voiding_liquid.tooltip"));
    static Supplier<List<String>> gasVoidableTooltip = () -> Lists.newArrayList(TextFormatting.YELLOW + I18n.format("gj.fluid_voiding_gas.tooltip"));

    public static void registerTooltips(){
        for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            if (Objects.equals(fluid.getName(), "water") || Objects.equals(fluid.getName(), "lava")) {
                registerTooltip(fluid, liquidVoidableTooltip);
            } else {
                if (fluid instanceof AttributedFluid aFluid) {
                    registerVoidingTooltip(fluid, aFluid);
                }
                if (fluid instanceof GTFluid.GTMaterialFluid gtFluid && gtFluid.getMaterial().hasProperty(GJPropertyKeys.COOLANT_PROPERTY)) {
                    registerCoolantTooltip(fluid, gtFluid);
                }
            }
        }
    }

    private static void registerVoidingTooltip(Fluid fluid, AttributedFluid aFluid){
        FluidState state = aFluid.getState();
        if (state == FluidState.LIQUID) {
            registerTooltip(fluid, liquidVoidableTooltip);
        }
        if (state == FluidState.GAS){
            registerTooltip(fluid, gasVoidableTooltip);
        }
    }

    private static void registerCoolantTooltip(Fluid fluid, GTFluid.GTMaterialFluid gtFluid){
        Material mat = gtFluid.getMaterial();
        CoolantProperty property = mat.getProperty(GJPropertyKeys.COOLANT_PROPERTY);
        registerTooltip(fluid, () -> Lists.newArrayList(I18n.format("gj.coolant_conversion_factor.tooltip", property.conversion())));
    }
}
