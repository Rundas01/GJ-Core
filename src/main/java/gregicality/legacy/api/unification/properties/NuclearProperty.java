package gregicality.legacy.api.unification.properties;

import com.github.bsideup.jabel.Desugar;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;

@Desugar
public record NuclearProperty(Material mat, Material baseMat, double alphaEnergy, double betaPlusEnergy,
                              double betaMinusEnergy, boolean canBeFuel) implements IMaterialProperty {

    @Override
    public void verifyProperty(MaterialProperties properties) {}

    public int calculateHalfLifeTier(){
        long hls = mat.getElement().halfLifeSeconds;
        if(hls == -1) return 1;
        if(hls == 1) return 8;
        if(hls <= 60) return 7;
        if(hls <= 3600) return 6;
        if(hls <= 86400) return 5;
        if(hls <= 604800) return 4;
        if(hls <= 2419200) return 3;
        if(hls <= 29030400) return 2;
        return 1;
    }

    public int calculateDurationTier(){
        return 9 - calculateHalfLifeTier();
    }
}
