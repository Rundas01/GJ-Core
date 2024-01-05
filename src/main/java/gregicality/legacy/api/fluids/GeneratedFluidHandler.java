package gregicality.legacy.api.fluids;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import net.minecraft.util.Tuple;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static gregicality.legacy.api.unification.material.GCYLRFusionModeratorMaterials.*;
import static gregtech.api.unification.material.Materials.SodiumPotassium;

@ApiStatus.Internal
public final class GeneratedFluidHandler {

    private GeneratedFluidHandler() {}

    public static void init() {
        List<Tuple<Material,Integer>> supercriticalMaterials = new ArrayList<>();
        supercriticalMaterials.add(new Tuple<>(LeadBismuthEutectic,1943));
        supercriticalMaterials.add(new Tuple<>(SodiumPotassium,1105));
        supercriticalMaterials.add(new Tuple<>(FLiBe,1020));
        supercriticalMaterials.add(new Tuple<>(FLiNaK,1017));
        for (Tuple<Material,Integer> tuple : supercriticalMaterials) {
            createSupercriticalFluid(tuple.getFirst(), tuple.getSecond());
        }
    }

    public static void createSupercriticalFluid(@NotNull Material material, int temp) {
        FluidProperty fluidProperty = material.getProperty(PropertyKey.FLUID);
        if (fluidProperty == null) return;
        fluidProperty.getStorage().enqueueRegistration(GCYLRFluidStorageKeys.SUPERCRITICAL, new FluidBuilder().temperature(temp));
    }
}
