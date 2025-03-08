package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.unification.material.GJOreMaterials.Kieserite;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class MagnesiumLineMaterials {

    private MagnesiumLineMaterials() {}

    public static Material MagnesiumSlag;
    public static Material MagnesiumWaste;
    public static Material AsbestosSlag;
    public static Material GlauconiteSandSlag;
    public static Material TalcSlag;
    public static Material SoapstoneSlag;
    public static Material BentoniteSlag;
    public static Material FullersEarthSlag;
    public static Material KieseriteSlag;
    public static Material MagnesiumSulfate;
    public static Material MagnesiumDifluoride;
    public static Material MagnesiumDinitrate;
    public static Material MagnesiumDichloride;
    public static Material MagnesiumSulfide;
    public static Material HighPurityMagnesium;

    public static void init() {
        MagnesiumSlag = new Material.Builder(5051, gjId("magnesium_slag"))
                .fluid()
                .color(avgColor(Magnesium, Steel))
                .build()
                .setFormula("Mg?", true);

        MagnesiumWaste = new Material.Builder(5052, gjId("magnesium_waste"))
                .fluid()
                .color(MagnesiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Mg?", true);

        AsbestosSlag = new Material.Builder(5053, gjId("asbestos_slag"))
                .fluid()
                .color(avgColor(Asbestos, Steel))
                .build()
                .setFormula("?", true);

        GlauconiteSandSlag = new Material.Builder(5054, gjId("glauconite_sand_slag"))
                .fluid()
                .color(avgColor(GlauconiteSand, Steel))
                .build()
                .setFormula("?", true);

        TalcSlag = new Material.Builder(5055, gjId("talc_slag"))
                .fluid()
                .color(avgColor(Talc, Steel))
                .build()
                .setFormula("?", true);

        SoapstoneSlag = new Material.Builder(5056, gjId("soapstone_slag"))
                .fluid()
                .color(avgColor(Soapstone, Steel))
                .build()
                .setFormula("?", true);

        BentoniteSlag = new Material.Builder(5057, gjId("bentonite_slag"))
                .fluid()
                .color(avgColor(Bentonite, Steel))
                .build()
                .setFormula("?", true);

        FullersEarthSlag = new Material.Builder(5058, gjId("fullers_earth_slag"))
                .fluid()
                .color(avgColor(FullersEarth, Steel))
                .build()
                .setFormula("?", true);

        KieseriteSlag = new Material.Builder(5059, gjId("kieserite_slag"))
                .fluid()
                .color(avgColor(Kieserite, Steel))
                .build()
                .setFormula("?", true);

        MagnesiumSulfate = new Material.Builder(5060, gjId("magnesium_sulfate"))
                .dust()
                .colorAverage()
                .components(Magnesium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        MagnesiumDifluoride = new Material.Builder(5061, gjId("magnesium_difluoride"))
                .dust()
                .components(Magnesium, 1, Fluorine, 2)
                .colorAverage()
                .build();

        MagnesiumDinitrate = new Material.Builder(5062, gjId("magnesium_dinitrate"))
                .dust()
                .components(Magnesium, 1, Nitrogen, 2, Oxygen, 6)
                .colorAverage()
                .build()
                .setFormula("Mg(NO3)2", true);

        MagnesiumSulfide = new Material.Builder(5063, gjId("magnesium_sulfide"))
                .dust()
                .colorAverage()
                .components(Magnesium, 1, Sulfur, 1)
                .build();

        MagnesiumDichloride = new Material.Builder(5064, gjId("magnesium_dichloride"))
                .dust()
                .components(Magnesium, 1, Chlorine, 2)
                .colorAverage()
                .build();

        HighPurityMagnesium = new Material.Builder(5100, gjId("high_purity_magnesium"))
                .dust()
                .color(Magnesium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Mg*", true);
    }
}
