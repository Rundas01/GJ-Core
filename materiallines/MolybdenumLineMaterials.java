package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.Ferrimolybdite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import gregtech.api.unification.material.Material;

public class MolybdenumLineMaterials {

    private MolybdenumLineMaterials() {}

    public static Material MolybdenumSlag;
    public static Material MolybdenumWaste;
    public static Material MolybdeniteSlag;
    public static Material PowelliteSlag;
    public static Material WulfeniteSlag;
    public static Material FerrimolybditeSlag;
    public static Material MolybdenumHexanitrate;
    public static Material SodiumMolybdate;
    public static Material MolybdenumIINitride;
    public static Material AmmoniumHeptamolybdate;
    public static Material MolybdenumTrioxide;
    public static Material HighPurityMolybdenum;

    public static void init() {
        MolybdenumSlag = new Material.Builder(11000, gjId("molybdenum_slag"))
                .fluid()
                .color(avgColor(Molybdenum, Steel))
                .build()
                .setFormula("Mo?");

        MolybdenumWaste = new Material.Builder(11001, gjId("molybdenum_waste"))
                .fluid()
                .color(MolybdenumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Mo?");

        MolybdeniteSlag = new Material.Builder(11002, gjId("molybdenite_slag"))
                .fluid()
                .color(avgColor(Molybdenite, Steel))
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("?", true);

        PowelliteSlag = new Material.Builder(11003, gjId("powellite_slag"))
                .fluid()
                .color(avgColor(Powellite, Steel))
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("?", true);

        WulfeniteSlag = new Material.Builder(11004, gjId("wulfenite_slag"))
                .fluid()
                .color(avgColor(Wulfenite, Steel))
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("?", true);

        FerrimolybditeSlag = new Material.Builder(11005, gjId("ferrimolybdite_slag"))
                .fluid()
                .color(avgColor(Ferrimolybdite, Steel))
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("?", true);

        MolybdenumHexanitrate = new Material.Builder(11006, gjId("molybdenum_hexanitrate"))
                .dust()
                .components(Molybdenum, 1, Nitrogen, 6, Oxygen, 18)
                .colorAverage()
                .build()
                .setFormula("Mo(NO3)6", true);

        SodiumMolybdate = new Material.Builder(11007, gjId("sodium_molybdate"))
                .dust()
                .components(Sodium, 2, Molybdenum, 1, Oxygen, 4)
                .colorAverage()
                .build();

        MolybdenumIINitride = new Material.Builder(11008, gjId("molybdenum_ii_nitride"))
                .dust()
                .components(Molybdenum, 3, Nitrogen, 2)
                .colorAverage()
                .build();

        AmmoniumHeptamolybdate = new Material.Builder(11009, gjId("ammonium_heptamolybdate"))
                .dust()
                .components(Molybdenum, 7, Nitrogen, 6, Hydrogen, 24, Oxygen, 24)
                .colorAverage()
                .build()
                .setFormula("(NH4)6Mo7O24", true);

        MolybdenumTrioxide = new Material.Builder(11010, gjId("molybdenum_trioxide"))
                .dust()
                .components(Molybdenum, 1, Oxygen, 3)
                .colorAverage()
                .build();

        HighPurityMolybdenum = new Material.Builder(11050, gjId("high_purity_molybdenum"))
                .dust()
                .color(Molybdenum.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Mo*", true);
    }
}
