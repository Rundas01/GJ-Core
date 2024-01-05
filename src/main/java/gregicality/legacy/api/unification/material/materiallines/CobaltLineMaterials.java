package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;

public class CobaltLineMaterials {
    private CobaltLineMaterials(){}

    public static Material CobaltTrioxide;
    public static Material SperryliteWaste;

    public static void init(){
        CobaltTrioxide = new Material.Builder(3639,gcylrId("cobalt_trioxide"))
                .dust()
                .iconSet(DULL)
                .colorAverage()
                .components(Cobalt,3,Oxygen,4)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        SperryliteWaste = new Material.Builder(3640, gcylrId("sperrylite_waste"))
                .fluid()
                .components(Water, 3, Chlorine, 2, Cobalt, 1, Nickel, 1)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(H2O)3(Ni,Co)Cl2", true);
    }
}
