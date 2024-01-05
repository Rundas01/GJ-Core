package gregicality.legacy.api.unification.material;

import gregicality.legacy.common.GCYLRConfigHolder;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import net.minecraft.util.Tuple;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.fluids.store.FluidStorageKeys.PLASMA;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD;

@ApiStatus.Internal
public final class GCYLRMaterialFlagAddition {

    private GCYLRMaterialFlagAddition() {}

    public static void init() {
        Sulfur.setProperty(PropertyKey.FLUID, new FluidProperty());
        Calcium.setProperty(PropertyKey.FLUID, new FluidProperty());
        Bromine.setProperty(PropertyKey.FLUID, new FluidProperty());
        ToolProperty prop = Flint.getProperty(PropertyKey.TOOL);
        prop.setShouldIgnoreCraftingTools(false);
        if(GCYLRConfigHolder.recipes.realisticRecipes){
            VanadiumMagnetite.addFlags(DISABLE_DECOMPOSITION);
            Cobaltite.addFlags(DISABLE_DECOMPOSITION);
            CobaltOxide.addFlags(DISABLE_DECOMPOSITION);
            ArsenicTrioxide.addFlags(DISABLE_DECOMPOSITION);
            Bauxite.addFlags(DISABLE_DECOMPOSITION);
            Barite.addFlags(DISABLE_DECOMPOSITION);
            BariumSulfide.addFlags(DISABLE_DECOMPOSITION);

            Barium.addFlags(GENERATE_ROD);
            Graphite.addFlags(GENERATE_ROD);
        }
    }

    public static void initLate() {
        List<Tuple<Material,Integer>> plasmas = new ArrayList<>();
        plasmas.add(new Tuple<>(Carbon,9200));
        plasmas.add(new Tuple<>(Neon,5000));
        plasmas.add(new Tuple<>(Magnesium,2766));
        plasmas.add(new Tuple<>(Silicon,7066));
        //plasmas.add(new Tuple<>(Sulfur,1436));
        //plasmas.add(new Tuple<>(Calcium,3520));
        plasmas.add(new Tuple<>(Titanium,7066));
        plasmas.add(new Tuple<>(Chrome,5510));
        for (Tuple<Material,Integer> materialIntegerTuple : plasmas) {
            FluidProperty fluidProperty = materialIntegerTuple.getFirst().getProperty(PropertyKey.FLUID);
            if (fluidProperty == null) continue;
            fluidProperty.getStorage().enqueueRegistration(PLASMA, new FluidBuilder().temperature(materialIntegerTuple.getSecond()));
        }
    }
}
