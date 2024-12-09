package gregsjourney.api.unification.property;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

import java.util.List;
import java.util.Objects;

public @Desugar record OreTailingProperty(List<Material> outputs, int tier) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.DUST, true);
    }
}
