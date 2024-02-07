package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.avgColor;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class ArsenicLineMaterials {
    private ArsenicLineMaterials(){}
    public static Material ArsenicSlag;
    public static Material ArsenicWaste;
    public static Material AcidicArsenateSolution;
    public static Material ArsenicVOxide;
    public static Material ArsenicVSulfide;
    public static Material ArsenicTrichloride;
    public static Material Arsine;
    public static Material HighPurityArsenic;
    public static Material EnargiteResidue;
    public static Material CrudeArsenicTrichloride;
    public static Material SodiumArsenide;
    public static Material SodiumArsenate;
    public static Material ArsenicSulfate;

    public static void init(){
        ArsenicSlag = new Material.Builder(3620,gcylrId("arsenic_slag"))
                .fluid()
                .color(avgColor(Arsenic,Steel))
                .build()
                .setFormula("As?");

        ArsenicWaste = new Material.Builder(3621,gcylrId("arsenic_waste"))
                .fluid()
                .color(ArsenicSlag.getMaterialRGB()-10)
                .build()
                .setFormula("As?");

        AcidicArsenateSolution = new Material.Builder(3622, gcylrId("acidic_arsenate_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(NitricAcid, 6, Hydrogen, 9, Arsenic, 3, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(HNO3)6(H3AsO4)3", true);

        ArsenicVOxide = new Material.Builder(3623, gcylrId("arsenic_v_oxide"))
                .dust()
                .components(Arsenic, 2, Oxygen, 5)
                .colorAverage()
                .build();

        ArsenicVSulfide = new Material.Builder(3624, gcylrId("arsenic_v_sulfide"))
                .dust()
                .components(Arsenic, 2, Sulfur, 5)
                .colorAverage()
                .build();

        CrudeArsenicTrichloride = new Material.Builder(3625, gcylrId("crude_arsenic_trichloride"))
                .fluid()
                .components(Arsenic, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(AsCl3)?", true);

        ArsenicTrichloride = new Material.Builder(3626, gcylrId("arsenic_trichloride"))
                .fluid()
                .components(Arsenic, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        Arsine = new Material.Builder(3627, gcylrId("arsine"))
                .gas()
                .components(Arsenic, 1, Hydrogen, 3)
                .colorAverage()
                .build();

        HighPurityArsenic = new Material.Builder(3628, gcylrId("high_purity_arsenic"))
                .dust()
                .components(Arsenic,1)
                .color(Arsenic.getMaterialRGB())
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("As*",true);

        SodiumArsenide = new Material.Builder(3629, gcylrId("sodium_arsenide"))
                .dust()
                .components(Sodium, 3, Arsenic, 1)
                .colorAverage()
                .build();

        SodiumArsenate = new Material.Builder(3630, gcylrId("sodium_arsenate"))
                .dust()
                .components(Sodium, 1, Arsenic, 1, Oxygen, 3)
                .colorAverage()
                .build();

        ArsenicSulfate = new Material.Builder(3631, gcylrId("arsenic_sulfate"))
                .dust()
                .components(Arsenic, 2, Sulfur, 3, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("As2(SO4)3",true);
    }
}
