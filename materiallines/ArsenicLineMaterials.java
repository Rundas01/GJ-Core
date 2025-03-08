package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.unification.material.GJOreMaterials.*;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ArsenicLineMaterials {

    private ArsenicLineMaterials() {}

    public static Material ArsenicSlag;
    public static Material ArsenicWaste;
    public static Material RealgarSlag;
    public static Material ArsenopyriteSlag;
    public static Material HutchinsoniteSlag;
    public static Material SkutteruditeSlag;
    public static Material ArsenicVOxide;
    public static Material ArsenicVSulfide;
    public static Material ArsenicTrichloride;
    public static Material Arsine;
    public static Material SodiumArsenate;
    public static Material ArsenicTrinitrate;
    public static Material ArsenicPentanitrate;
    public static Material ArsenicNitride;
    public static Material ArsenicSulfate;
    public static Material HighPurityArsenic;

    public static void init() {
        ArsenicSlag = new Material.Builder(4201, gjId("arsenic_slag"))
                .fluid()
                .color(avgColor(Arsenic, Steel))
                .build()
                .setFormula("As?", true);

        ArsenicWaste = new Material.Builder(4202, gjId("arsenic_waste"))
                .fluid()
                .color(ArsenicSlag.getMaterialRGB() - 10)
                .build()
                .setFormula("As?", true);

        RealgarSlag = new Material.Builder(4203, gjId("realgar_slag"))
                .fluid()
                .color(avgColor(Realgar, Steel))
                .build()
                .setFormula("?");

        ArsenopyriteSlag = new Material.Builder(4204, gjId("arsenopyrite_slag"))
                .fluid()
                .color(avgColor(Arsenopyrite, Steel))
                .build()
                .setFormula("?");

        HutchinsoniteSlag = new Material.Builder(4205, gjId("hutchinsonite_slag"))
                .fluid()
                .color(avgColor(Hutchinsonite, Steel))
                .build()
                .setFormula("?");

        SkutteruditeSlag = new Material.Builder(4206, gjId("skutterudite_slag"))
                .fluid()
                .color(avgColor(Skutterudite, Steel))
                .build()
                .setFormula("?");

        ArsenicVOxide = new Material.Builder(4207, gjId("arsenic_v_oxide"))
                .dust()
                .components(Arsenic, 2, Oxygen, 5)
                .colorAverage()
                .build();

        ArsenicVSulfide = new Material.Builder(4208, gjId("arsenic_v_sulfide"))
                .dust()
                .components(Arsenic, 2, Sulfur, 5)
                .colorAverage()
                .build();

        ArsenicTrichloride = new Material.Builder(4209, gjId("arsenic_trichloride"))
                .fluid()
                .components(Arsenic, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        Arsine = new Material.Builder(4210, gjId("arsine"))
                .gas()
                .components(Arsenic, 1, Hydrogen, 3)
                .colorAverage()
                .build();

        SodiumArsenate = new Material.Builder(4211, gjId("sodium_arsenate"))
                .dust()
                .components(Sodium, 1, Arsenic, 1, Oxygen, 3)
                .colorAverage()
                .build();

        ArsenicTrinitrate = new Material.Builder(4212, gjId("arsenic_trinitrate"))
                .dust()
                .components(Arsenic, 1, Nitrogen, 3, Oxygen, 9)
                .colorAverage()
                .build()
                .setFormula("As(NO3)3", true);

        ArsenicPentanitrate = new Material.Builder(4213, gjId("arsenic_pentanitrate"))
                .dust()
                .components(Arsenic, 1, Nitrogen, 5, Oxygen, 15)
                .colorAverage()
                .build()
                .setFormula("As(NO3)5", true);

        ArsenicNitride = new Material.Builder(4214, gjId("arsenic_nitride"))
                .dust()
                .components(Arsenic, 1, Nitrogen, 1)
                .colorAverage()
                .build();

        ArsenicSulfate = new Material.Builder(4215, gjId("arsenic_sulfate"))
                .dust()
                .components(Arsenic, 2, Sulfur, 3, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("As2(SO4)3", true);

        HighPurityArsenic = new Material.Builder(4250, gjId("high_purity_arsenic"))
                .dust()
                .iconSet(MaterialIconSet.SHINY)
                .color(Arsenic.getMaterialRGB())
                .build()
                .setFormula("As*", true);
    }
}
