package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.BRIGHT;
import static gregtech.api.unification.material.info.MaterialIconSet.SAND;

public class ArsenicLineMaterials {
    private ArsenicLineMaterials(){}

    public static Material ImpureArsenicSlag;
    public static Material ArsenicSlag;
    public static Material ArsenicWaste;
    public static Material RoastedSperrylite;
    public static Material CobaltiteLeachSolution;
    public static Material ArsenopyriteLeachSolution;
    public static Material AcidicArsenateSolution;
    public static Material ArsenicVOxide;
    public static Material ArsenicVSulfide;
    public static Material ArsenicTrichloride;
    public static Material Arsine;
    public static Material ProustiteAlkalineLeachSolution;
    public static Material HighPurityArsenic;
    public static Material EnargiteResidue;
    public static Material EnargiteSulfideLeachSolution;
    public static Material CrudeArsenicTrichloride;
    public static Material SodiumArsenide;

    public static void init(){

        ImpureArsenicSlag = new Material.Builder(3620,gcylrId("impure_arsenic_slag"))
                .fluid()
                .color(Steel.getMaterialRGB())
                .build()
                .setFormula("As?");

        ArsenicSlag = new Material.Builder(3621,gcylrId("arsenic_slag"))
                .fluid()
                .color((Arsenic.getMaterialRGB()+ImpureArsenicSlag.getMaterialRGB())/2)
                .build()
                .setFormula("As?");

        ArsenicWaste = new Material.Builder(3622,gcylrId("arsenic_waste"))
                .fluid()
                .color(ArsenicSlag.getMaterialRGB()-10)
                .build()
                .setFormula("As?");

        RoastedSperrylite = new Material.Builder(3623, gcylrId("roasted_sperrylite"))
                .dust()
                .components(Platinum, 1)
                .color(0x3b1754)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(SAND)
                .build();

        CobaltiteLeachSolution = new Material.Builder(3624, gcylrId("cobaltite_leach_solution"))
                .fluid()
                .components(Cobalt, 3, Nitrogen, 6, Oxygen, 30, Hydrogen, 9, Arsenic, 3, Water, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(Co(NO3)2)3(H3AsO4)3(H2O)3", true);

        ArsenopyriteLeachSolution = new Material.Builder(3625, gcylrId("arsenopyrite_leach_solution"))
                .fluid()
                .components(Iron, 3, Nitrogen, 6, Oxygen, 32, Hydrogen, 13, Arsenic, 3, Water, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(Fe(NO3)2)3(H3AsO4)3(H2O)3", true);

        AcidicArsenateSolution = new Material.Builder(3626, gcylrId("acidic_arsenate_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(NitricAcid, 6, Hydrogen, 9, Arsenic, 3, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(HNO3)6(H3AsO4)3", true);

        ArsenicVOxide = new Material.Builder(3627, gcylrId("arsenic_v_oxide"))
                .dust()
                .components(Arsenic, 2, Oxygen, 5)
                .colorAverage()
                .build();

        ProustiteAlkalineLeachSolution = new Material.Builder(3628, gcylrId("proustite_alkaline_leach_solution"))
                .fluid()
                .components(Sodium, 3, Arsenic, 1, Sulfur, 3, Water, 1)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(Na3AsS3)(H2O)", true);

        EnargiteResidue = new Material.Builder(3629, gcylrId("enargite_residue"))
                .dust()
                .components(Copper, 2, Sulfur, 1)
                .color(0x59183c)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(BRIGHT)
                .build()
                .setFormula("(Cu2S)?", true);

        EnargiteSulfideLeachSolution = new Material.Builder(3630, gcylrId("enargite_sulfide_leach_solution"))
                .fluid()
                .components(Sodium, 3, Arsenic, 1, Sulfur, 4, Water, 1)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(Na3AsS4)(H2O)", true);

        ArsenicVSulfide = new Material.Builder(3631, gcylrId("arsenic_v_sulfide"))
                .dust()
                .components(Arsenic, 2, Sulfur, 5)
                .colorAverage()
                .build();

        CrudeArsenicTrichloride = new Material.Builder(3632, gcylrId("crude_arsenic_trichloride"))
                .fluid()
                .components(Arsenic, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(AsCl3)?", true);

        ArsenicTrichloride = new Material.Builder(3633, gcylrId("arsenic_trichloride"))
                .fluid()
                .components(Arsenic, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        Arsine = new Material.Builder(3634, gcylrId("arsine"))
                .gas()
                .components(Arsenic, 1, Hydrogen, 3)
                .colorAverage()
                .build();

        HighPurityArsenic = new Material.Builder(3635, gcylrId("high_purity_arsenic"))
                .dust()
                .components(Arsenic,1)
                .color(Arsenic.getMaterialRGB())
                .build()
                .setFormula("As*",true);

        SodiumArsenide = new Material.Builder(3636, gcylrId("sodium_arsenide"))
                .dust()
                .components(Sodium, 3, Arsenic, 1)
                .colorAverage()
                .build();
    }
}
