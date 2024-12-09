package gregsjourney.api.unification.property;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

import java.util.Objects;

public @Desugar
record LeachProperty(Material flotationAgent, int tier) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.FLUID, true);
    }
}
