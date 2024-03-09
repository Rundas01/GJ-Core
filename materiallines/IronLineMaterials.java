package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class IronLineMaterials {

    private IronLineMaterials() {}

    public static Material IronSlag;
    public static Material IronWaste;
    public static Material ChalcopyriteSlag;
    public static Material PyriteSlag;
    public static Material OlivineSlag;
    public static Material MagnetiteSlag;
    public static Material IronIISulfate;
    public static Material IronCarbonate;
    public static Material SodiumHypoferriteII;
    public static Material SodiumHypoferriteIII;
    public static Material IronDifluoride;
    public static Material IronIIOxide;
    public static Material IronIIIOxide;
    public static Material IronTrinitrate;
    public static Material IronIINitride;
    public static Material Ferrosilicon;
    public static Material HighPurityIron;

    public static void init() {
        IronSlag = new Material.Builder(4851, gjId("iron_slag"))
                .fluid()
                .color(avgColor(Iron, Steel))
                .build()
                .setFormula("Fe?", true);

        IronWaste = new Material.Builder(4852, gjId("iron_waste"))
                .fluid()
                .color(IronSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Fe?", true);

        ChalcopyriteSlag = new Material.Builder(4853, gjId("chalcopyrite_slag"))
                .fluid()
                .color(avgColor(Chalcopyrite, Steel))
                .build()
                .setFormula("?", true);

        PyriteSlag = new Material.Builder(4854, gjId("pyrite_slag"))
                .fluid()
                .color(avgColor(Pyrite, Steel))
                .build()
                .setFormula("?", true);

        OlivineSlag = new Material.Builder(4855, gjId("olivine_slag"))
                .fluid()
                .color(avgColor(Olivine, Steel))
                .build()
                .setFormula("?", true);

        MagnetiteSlag = new Material.Builder(4856, gjId("magnetite_slag"))
                .fluid()
                .color(avgColor(Magnetite, Steel))
                .build()
                .setFormula("?", true);

        IronIISulfate = new Material.Builder(4857, gjId("iron_ii_sulfate"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Sulfur, 1, Oxygen, 4)
                .build();

        IronCarbonate = new Material.Builder(4858, gjId("iron_carbonate"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Carbon, 1, Oxygen, 3)
                .build();

        SodiumHypoferriteII = new Material.Builder(4859, gjId("sodium_hypoferrite_ii"))
                .dust()
                .colorAverage()
                .components(Sodium, 2, Iron, 1, Oxygen, 2)
                .build();

        IronDifluoride = new Material.Builder(4860, gjId("iron_difluoride"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Fluorine, 2)
                .build();

        IronIIIOxide = new Material.Builder(4861, gjId("iron_iii_oxide"))
                .dust()
                .colorAverage()
                .components(Iron, 2, Oxygen, 3)
                .build();

        IronTrinitrate = new Material.Builder(4862, gjId("iron_trinitrate"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Fe(NO3)3", true);

        IronIINitride = new Material.Builder(4863, gjId("iron_ii_nitride"))
                .dust()
                .colorAverage()
                .components(Iron, 3, Nitrogen, 2)
                .build();

        Ferrosilicon = new Material.Builder(4864, gjId("ferrosilicon"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Silicon, 1)
                .build();

        SodiumHypoferriteIII = new Material.Builder(4865, gjId("sodium_hypoferrite_iii"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Iron, 1, Oxygen, 2)
                .build();

        HighPurityIron = new Material.Builder(4900, gjId("high_purity_iron"))
                .dust()
                .color(Iron.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Fe*", true);
    }
}
