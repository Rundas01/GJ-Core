package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ManganeseLineMaterials {

    private ManganeseLineMaterials() {}

    public static Material ManganeseSlag;
    public static Material ManganeseWaste;
    public static Material SpessartineSlag;
    public static Material PyrolusiteSlag;
    public static Material ManganeseSulfate;
    public static Material ManganeseDioxide;
    public static Material PotassiumPermanganate;
    public static Material PotassiumManganate;
    public static Material PotassiumManganite;
    public static Material ManganeseTetranitrate;
    public static Material ManganeseDisulfate;
    public static Material ManganeseIINitride;
    public static Material ManganeseDifluoride;
    public static Material SodiumHypomanganite;
    public static Material HighPurityManganese;

    public static void init() {
        ManganeseSlag = new Material.Builder(5101, gjId("manganese_slag"))
                .fluid()
                .color(avgColor(Manganese, Steel))
                .build()
                .setFormula("Mn?", true);

        ManganeseWaste = new Material.Builder(5102, gjId("manganese_waste"))
                .fluid()
                .color(ManganeseSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Mn?", true);

        SpessartineSlag = new Material.Builder(5103, gjId("spessartine_slag"))
                .fluid()
                .color(avgColor(Spessartine, Steel))
                .build()
                .setFormula("?", true);

        PyrolusiteSlag = new Material.Builder(5104, gjId("pyrolusite_slag"))
                .fluid()
                .color(avgColor(Pyrolusite, Steel))
                .build()
                .setFormula("?", true);

        ManganeseSulfate = new Material.Builder(5105, gjId("manganese_sulfate"))
                .dust()
                .colorAverage()
                .components(Magnesium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        ManganeseDioxide = new Material.Builder(5106, gjId("manganese_dioxide"))
                .dust()
                .components(Magnesium, 1, Oxygen, 2)
                .colorAverage()
                .build();

        PotassiumPermanganate = new Material.Builder(5107, gjId("potassium_permanganate"))
                .dust()
                .components(Potassium, 1, Manganese, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumManganate = new Material.Builder(5108, gjId("potassium_manganate"))
                .dust()
                .components(Potassium, 2, Manganese, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumManganite = new Material.Builder(5109, gjId("potassium_manganite"))
                .dust()
                .components(Potassium, 4, Manganese, 1, Oxygen, 4)
                .colorAverage()
                .build();

        ManganeseTetranitrate = new Material.Builder(5110, gjId("manganese_tetranitrate"))
                .dust()
                .components(Manganese, 1, Nitrogen, 4, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("Mn(NO3)4", true);

        ManganeseDisulfate = new Material.Builder(5111, gjId("manganese_disulfate"))
                .dust()
                .components(Manganese, 1, Sulfur, 2, Oxygen, 8)
                .colorAverage()
                .build()
                .setFormula("Mn(SO4)2", true);

        ManganeseIINitride = new Material.Builder(5112, gjId("manganese_ii_nitride"))
                .dust()
                .components(Manganese, 3, Nitrogen, 2)
                .colorAverage()
                .build();

        ManganeseDifluoride = new Material.Builder(5113, gjId("manganese_difluoride"))
                .dust()
                .components(Manganese, 1, Fluorine, 2)
                .colorAverage()
                .build();

        SodiumHypomanganite = new Material.Builder(5114, gjId("sodium_hypomanganite"))
                .dust()
                .components(Sodium, 2, Manganese, 1, Oxygen, 2)
                .colorAverage()
                .build();

        HighPurityManganese = new Material.Builder(5150, gjId("high_purity_manganese"))
                .dust()
                .color(Manganese.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Mn*", true);
    }
}
