package thegreggening.api.unification.property;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;

public @Desugar record SHCProperty(int shc) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {}
}
