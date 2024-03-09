package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class NickelLineMaterials {

    private NickelLineMaterials() {}

    public static Material NickelSlag;
    public static Material NickelWaste;
    public static Material PentlanditeSlag;
    public static Material NickelDinitrate;
    public static Material NickelINitride;
    public static Material NickelSulfate;
    public static Material NickelSulfide;
    public static Material HighPurityNickel;

    public static void init() {
        NickelSlag = new Material.Builder(12001, gjId("nickel_slag"))
                .fluid()
                .color(avgColor(Nickel, Steel))
                .build()
                .setFormula("Ni?", true);

        NickelWaste = new Material.Builder(12002, gjId("nickel_waste"))
                .fluid()
                .color(NickelSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Ni?", true);

        PentlanditeSlag = new Material.Builder(12003, gjId("pentlandite_slag"))
                .fluid()
                .color(avgColor(Pentlandite, Steel))
                .build()
                .setFormula("?");

        NickelDinitrate = new Material.Builder(12004, gjId("nickel_dinitrate"))
                .dust()
                .colorAverage()
                .components(Nickel, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Ni(NO3)2", true);

        NickelINitride = new Material.Builder(12005, gjId("nickel_i_nitride"))
                .dust()
                .colorAverage()
                .components(Nickel, 1, Nitrogen, 3)
                .build();

        NickelSulfate = new Material.Builder(12006, gjId("nickel_sulfate"))
                .dust()
                .colorAverage()
                .components(Nickel, 1, Sulfur, 1, Oxygen, 4)
                .build();

        NickelSulfide = new Material.Builder(12007, gjId("nickel_sulfide"))
                .dust()
                .colorAverage()
                .components(Nickel, 1, Sulfur, 1)
                .build();

        HighPurityNickel = new Material.Builder(12050, gjId("high_purity_nickel"))
                .dust()
                .color(Nickel.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ni*", true);
    }
}
