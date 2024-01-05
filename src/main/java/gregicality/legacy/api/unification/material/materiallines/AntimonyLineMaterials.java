package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public class AntimonyLineMaterials {
    private AntimonyLineMaterials(){}

    public static Material AntimonySulfide;
    public static Material AntimonyDross;

    public static void init(){
        AntimonySulfide = new Material.Builder(3618,gcylrId("antimony_sulfide"))
                .dust()
                .components(Antimony, 2, Sulfur, 3)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        AntimonyDross = new Material.Builder(3619, gcylrId("antimony_dross"))
                .dust().liquid(new FluidBuilder().temperature(600))
                .components(Calcium, 1, Magnesium, 1, Antimony, 2)
                .color(0x3c3a45)
                .build();
    }
}
