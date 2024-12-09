package gregsjourney.api.unification.property;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public @Desugar record NuclearProperty(Material stableIsotope, Material alphaMaterial, double alphaEnergy,
                                       Material betaPlusMaterial, double betaPlusEnergy, Material betaMinusMaterial,
                                       double betaMinusEnergy, int tier) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.INGOT, true);
    }
}
