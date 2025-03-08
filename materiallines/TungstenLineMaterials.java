package thegreggening.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static thegreggening.api.unification.material.GJOreMaterials.Ferberite;
import static thegreggening.api.unification.material.GJOreMaterials.Hubnerite;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class TungstenLineMaterials {

    private TungstenLineMaterials() {}

    public static Material TungstenSlag;
    public static Material TungstenWaste;
    public static Material FerberiteSlag;
    public static Material HubneriteSlag;
    public static Material ScheeliteSlag;
    public static Material SodiumTungstate;
    public static Material AmmoniumParatungstate;
    public static Material TungstenTrioxide;
    public static Material HighPurityTungsten;

    public static void init() {
        TungstenSlag = new Material.Builder(16000, gjId("tungsten_slag"))
                .fluid()
                .color(avgColor(Tungsten, Steel))
                .build()
                .setFormula("W?", true);

        TungstenWaste = new Material.Builder(16001, gjId("tungsten_waste"))
                .fluid()
                .color(TungstenSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("W?", true);

        FerberiteSlag = new Material.Builder(16002, gjId("ferberite_slag"))
                .fluid()
                .color(avgColor(Ferberite, Steel))
                .build()
                .setFormula("?", true);

        HubneriteSlag = new Material.Builder(16003, gjId("hubnerite_slag"))
                .fluid()
                .color(avgColor(Hubnerite, Steel))
                .build()
                .setFormula("?", true);

        ScheeliteSlag = new Material.Builder(16004, gjId("scheelite_slag"))
                .fluid()
                .color(avgColor(Scheelite, Steel))
                .build()
                .setFormula("?", true);

        SodiumTungstate = new Material.Builder(16005, gjId("sodium_tungstate"))
                .dust()
                .components(Sodium, 2, Tungsten, 1, Oxygen, 5)
                .colorAverage()
                .build();

        AmmoniumParatungstate = new Material.Builder(16006, gjId("ammonium_paratungstate"))
                .dust()
                .components(Nitrogen, 10, Tungsten, 12, Hydrogen, 42, Oxygen, 42)
                .colorAverage()
                .build()
                .setFormula("(NH4)10H2W12O42",true);

        TungstenTrioxide = new Material.Builder(16007, gjId("tungsten_trioxide"))
                .dust()
                .components(Tungsten, 1, Oxygen, 3)
                .colorAverage()
                .build();

        HighPurityTungsten = new Material.Builder(16050, gjId("high_purity_tungsten"))
                .dust()
                .color(Tungsten.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("W*", true);
    }
}
