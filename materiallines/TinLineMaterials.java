package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class TinLineMaterials {

    private TinLineMaterials() {}

    public static Material TinSlag;
    public static Material TinWaste;
    public static Material TinTetrachloride;
    public static Material TinTetranitrate;
    public static Material HighPurityTin;

    public static void init() {
        TinSlag = new Material.Builder(5701, gjId("tin_slag"))
                .fluid()
                .color(avgColor(Tin, Steel))
                .build()
                .setFormula("Sn?", true);

        TinWaste = new Material.Builder(5702, gjId("tin_waste"))
                .fluid()
                .color(TinSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Sn?", true);

        TinTetrachloride = new Material.Builder(5703, gjId("tin_tetrachloride"))
                .dust().fluid()
                .colorAverage()
                .components(Tin, 1, Chlorine, 4)
                .build();

        TinTetranitrate = new Material.Builder(5704, gjId("tin_tetranitrate"))
                .dust()
                .colorAverage()
                .components(Tin, 1, Nitrogen, 4, Oxygen, 12)
                .build()
                .setFormula("Sn(NO3)4", true);

        HighPurityTin = new Material.Builder(5750, gjId("high_purity_tin"))
                .dust()
                .color(Tin.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sn*", true);
    }
}
