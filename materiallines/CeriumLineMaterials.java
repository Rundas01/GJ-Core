package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class CeriumLineMaterials {

    private CeriumLineMaterials() {}

    public static Material CeriumSlag;
    public static Material CeriumWaste;
    public static Material BastnasiteSlag;
    public static Material SodiumCerateIII;
    public static Material CeriumPhosphate;
    public static Material CeriumDisulfate;
    public static Material CeriumIIISulfide;
    public static Material CeriumDioxide;
    public static Material CeriumTetraiodate;
    public static Material CeriumIIIOxalate;
    public static Material CeriumTrifluoride;
    public static Material CeriumTrifluorideSolution;
    public static Material CeriumTrihydroxide;
    public static Material HighPurityCerium;

    public static void init() {
        CeriumSlag = new Material.Builder(4601, gjId("cerium_slag"))
                .fluid()
                .color(avgColor(Cerium, Steel))
                .build()
                .setFormula("Ce?", true);

        CeriumWaste = new Material.Builder(4602, gjId("cerium_waste"))
                .fluid()
                .color(CeriumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Ce?", true);

        BastnasiteSlag = new Material.Builder(4603, gjId("bastnasite_slag"))
                .fluid()
                .color(avgColor(Bastnasite, Steel))
                .build()
                .setFormula("?");

        SodiumCerateIII = new Material.Builder(4604, gjId("sodium_cerate_iii"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Cerium, 1, Oxygen, 2)
                .build();

        CeriumPhosphate = new Material.Builder(4605, gjId("cerium_phosphate"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Phosphorus, 1, Oxygen, 4)
                .build();

        CeriumDisulfate = new Material.Builder(4606, gjId("cerium_disulfate"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Sulfur, 2, Oxygen, 8)
                .build()
                .setFormula("Ce(SO4)2");

        CeriumIIISulfide = new Material.Builder(4607, gjId("cerium_iii_sulfide"))
                .dust()
                .colorAverage()
                .components(Cerium, 2, Sulfur, 3)
                .build();

        CeriumDioxide = new Material.Builder(4608, gjId("cerium_dioxide"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Oxygen, 2)
                .build();

        CeriumTetraiodate = new Material.Builder(4609, gjId("cerium_tetraiodate"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Iodine, 4, Oxygen, 12)
                .build()
                .setFormula("Ce(IO3)4");

        CeriumIIIOxalate = new Material.Builder(4610, gjId("cerium_iii_oxalate"))
                .dust()
                .colorAverage()
                .components(Cerium, 2, Carbon, 6, Oxygen, 12)
                .build()
                .setFormula("Ce2(C2O4)3");

        CeriumTrifluoride = new Material.Builder(4611, gjId("cerium_trifluoride"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Fluorine, 3)
                .build();

        CeriumTrifluorideSolution = new Material.Builder(4612, gjId("cerium_trifluoride_solution"))
                .fluid()
                .colorAverage()
                .components(CeriumTrifluoride, 1, Water, 1)
                .build();

        CeriumTrihydroxide = new Material.Builder(4613, gjId("cerium_trihydroxide"))
                .dust()
                .colorAverage()
                .components(Cerium, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("Ce(OH)3", true);

        HighPurityCerium = new Material.Builder(4650, gjId("high_purity_cerium"))
                .dust()
                .color(Cerium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Ce*", true);
    }
}
