package gregsjourney.api.unification.property;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

public @Desugar record CoolantProperty(Material hotCoolant, int conversion) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.FLUID, true);
    }
}
