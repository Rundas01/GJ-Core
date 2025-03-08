package thegreggening.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static thegreggening.api.unification.material.GJOreMaterials.Columbite;
import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;

public class NiobiumLineMaterials {

    private NiobiumLineMaterials() {}

    public static Material NiobiumSlag;
    public static Material NiobiumWaste;
    public static Material PyrochloreSlag;
    public static Material ColumbiteSlag;
    public static Material HeptafluoroNiobicAcid;
    public static Material PotassiumHeptafluoroNiobate;
    public static Material NiobiumPentafluoride;
    public static Material NiobiumVOxide;
    public static Material NiobiumCarbide;
    public static Material NiobiumDioxide;
    public static Material HighPurityNiobium;

    public static void init() {
        NiobiumSlag = new Material.Builder(13001, gjId("niobium_slag"))
                .fluid()
                .color(avgColor(Nickel, Steel))
                .build()
                .setFormula("Nb?", true);

        NiobiumWaste = new Material.Builder(13002, gjId("niobium_waste"))
                .fluid()
                .color(NiobiumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Nb?", true);

        PyrochloreSlag = new Material.Builder(13003, gjId("pyrochlore_slag"))
                .fluid()
                .color(avgColor(Pyrochlore, Steel))
                .build()
                .setFormula("?");

        ColumbiteSlag = new Material.Builder(13004, gjId("columbite_slag"))
                .fluid()
                .color(avgColor(Columbite,Steel))
                .build()
                .setFormula("?", true);

        HeptafluoroNiobicAcid = new Material.Builder(13005, gjId("heptafluoroniobic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen, 2, Niobium, 1, Fluorine, 7)
                .build();

        PotassiumHeptafluoroNiobate = new Material.Builder(13006, gjId("potassium_heptafluoroniobate"))
                .dust()
                .colorAverage()
                .components(Potassium, 2, Niobium, 1, Fluorine, 7)
                .build();

        NiobiumPentafluoride = new Material.Builder(13007, gjId("niobium_pentafluoride"))
                .dust()
                .colorAverage()
                .components(Niobium, 1, Fluorine, 4)
                .build();

        NiobiumVOxide = new Material.Builder(13008, gjId("niobium_v_oxide"))
                .dust()
                .colorAverage()
                .components(Niobium, 2, Oxygen, 5)
                .build();

        NiobiumCarbide = new Material.Builder(13009, gjId("niobium_carbide"))
                .dust()
                .colorAverage()
                .components(Niobium, 1, Carbon, 1)
                .build();

        NiobiumDioxide = new Material.Builder(13010, gjId("niobium_dioxide"))
                .dust()
                .colorAverage()
                .components(Niobium, 1, Oxygen, 2)
                .build();

        HighPurityNiobium = new Material.Builder(13050, gjId("high_purity_niobium"))
                .dust()
                .color(Niobium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Nb*", true);
    }
}
