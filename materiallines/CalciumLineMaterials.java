package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import gregtech.api.unification.material.Material;

public class CalciumLineMaterials {

    private CalciumLineMaterials() {}

    public static Material CalciumSlag;
    public static Material CalciumWaste;
    public static Material ApatiteSlag;
    public static Material TricalciumPhosphateSlag;
    public static Material ZeoliteSlag;
    public static Material GypsumSlag;
    public static Material FluorapatiteSlag;
    public static Material HydroxyapatiteSlag;
    public static Material CalciumDinitrate;
    public static Material CalciumSulfate;
    public static Material CalciumIINitride;
    public static Material CalciumSulfide;
    public static Material CalciumOxide;
    public static Material CalciumDiydroxide;
    public static Material DicalciumSilicate;
    public static Material CalciumDihydroxide;
    public static Material SulfaticCalciumTailings;
    public static Material HighPurityCalcium;

    public static void init() {
        CalciumSlag = new Material.Builder(4551, gjId("calcium_slag"))
                .fluid()
                .color(avgColor(Calcium, Steel))
                .build()
                .setFormula("Ca?", true);

        CalciumWaste = new Material.Builder(4552, gjId("calcium_waste"))
                .fluid()
                .color(CalciumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Ca?", true);

        ApatiteSlag = new Material.Builder(4553, gjId("apatite_slag"))
                .fluid()
                .color(avgColor(Apatite, Steel))
                .build()
                .setFormula("?", true);

        TricalciumPhosphateSlag = new Material.Builder(4554, gjId("tricalcium_phosphate_slag"))
                .fluid()
                .color(avgColor(TricalciumPhosphate, Steel))
                .build()
                .setFormula("?", true);

        ZeoliteSlag = new Material.Builder(4555, gjId("zeolite_slag"))
                .fluid()
                .color(avgColor(Zeolite, Steel))
                .build()
                .setFormula("?", true);

        GypsumSlag = new Material.Builder(4556, gjId("gypsum_slag"))
                .fluid()
                .color(avgColor(Gypsum, Steel))
                .build()
                .setFormula("?", true);

        FluorapatiteSlag = new Material.Builder(4557, gjId("fluorapatie_slag"))
                .fluid()
                .color(avgColor(Fluorapatite, Steel))
                .build()
                .setFormula("?", true);

        HydroxyapatiteSlag = new Material.Builder(4559, gjId("hydroxyapatite_slag"))
                .fluid()
                .color(avgColor(Hydroxyapatite, Steel))
                .build()
                .setFormula("?", true);

        CalciumDinitrate = new Material.Builder(4560, gjId("calcium_dinitrate"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Ca(NO3)2", true);

        CalciumSulfate = new Material.Builder(4561, gjId("calcium_sulfate"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        CalciumIINitride = new Material.Builder(4562, gjId("calcium_ii_nitride"))
                .dust()
                .colorAverage()
                .components(Calcium, 3, Nitrogen, 2)
                .build();

        CalciumSulfide = new Material.Builder(4563, gjId("calcium_sulfide"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Sulfur, 1)
                .build();

        CalciumOxide = new Material.Builder(4564, gjId("calcium_oxide"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Calcium, 1, Oxygen, 1)
                .build();

        CalciumDiydroxide = new Material.Builder(4565, gjId("calcium_dihydroxide"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Oxygen, 2, Hydrogen, 2)
                .build()
                .setFormula("Ca(OH)2", true);

        DicalciumSilicate = new Material.Builder(4566, gjId("dicalcium_silicate"))
                .dust()
                .colorAverage()
                .components(Calcium, 2, Silicon, 1, Oxygen, 4)
                .build();

        CalciumDihydroxide = new Material.Builder(4567, gjId("calcium_dihydroxide"))
                .dust()
                .colorAverage()
                .components(Calcium, 1, Hydrogen, 2, Oxygen, 2)
                .build()
                .setFormula("Ca(OH)2", true);

        SulfaticCalciumTailings = new Material.Builder(4599, gjId("sulfatic_calcium_tailings"))
                .fluid()
                .color(avgColor(Calcium, Steel, SulfuricAcid))
                .build()
                .setFormula("SO4?", true);

        HighPurityCalcium = new Material.Builder(4600, gjId("high_purity_calcium"))
                .dust().fluid()
                .color(Calcium.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Ca*", true);
    }
}
