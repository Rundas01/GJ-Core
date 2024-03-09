package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class CopperLineMaterials {

    private CopperLineMaterials() {}

    public static Material CopperSlag;
    public static Material CopperWaste;
    public static Material TetrahedriteSlag;
    public static Material BorniteSlag;
    public static Material ChalcociteSlag;
    public static Material MalachiteSlag;
    public static Material EnargiteSlag;
    public static Material CrookesiteSlag;
    public static Material ChrysollaSlag;
    public static Material StanniteSlag;
    public static Material AcidicCopperSolution;
    public static Material CopperSulfate;
    public static Material CopperDinitrate;
    public static Material CopperTrinitrate;
    public static Material CopperIFluoride;
    public static Material CopperDichloride;
    public static Material HighPurityCopper;

    public static void init() {
        CopperSlag = new Material.Builder(4751, gjId("copper_slag"))
                .fluid()
                .color(avgColor(Copper, Steel))
                .build()
                .setFormula("Cu?", true);

        CopperWaste = new Material.Builder(4752, gjId("copper_waste"))
                .fluid()
                .color(CopperSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Cu?", true);

        TetrahedriteSlag = new Material.Builder(4753, gjId("tetrahedrite_slag"))
                .fluid()
                .color(avgColor(Tetrahedrite, Steel))
                .build()
                .setFormula("?", true);

        BorniteSlag = new Material.Builder(4754, gjId("bornite_slag"))
                .fluid()
                .color(avgColor(Bornite, Steel))
                .build()
                .setFormula("?", true);

        ChalcociteSlag = new Material.Builder(4755, gjId("chalcocite_slag"))
                .fluid()
                .color(avgColor(Chalcocite, Steel))
                .build()
                .setFormula("?", true);

        MalachiteSlag = new Material.Builder(4756, gjId("malachite_slag"))
                .fluid()
                .color(avgColor(Malachite, Steel))
                .build()
                .setFormula("?", true);

        EnargiteSlag = new Material.Builder(4757, gjId("enargite_slag"))
                .fluid()
                .color(avgColor(Enargite, Steel))
                .build()
                .setFormula("?", true);

        CrookesiteSlag = new Material.Builder(4758, gjId("crookesite_slag"))
                .fluid()
                .color(avgColor(Crookesite, Steel))
                .build()
                .setFormula("?", true);

        ChrysollaSlag = new Material.Builder(4759, gjId("chrysolla_slag"))
                .fluid()
                .color(avgColor(Chrysolla, Steel))
                .build()
                .setFormula("?", true);

        StanniteSlag = new Material.Builder(4760, gjId("stannite_slag"))
                .fluid()
                .color(avgColor(Stannite, Steel))
                .build()
                .setFormula("?", true);

        AcidicCopperSolution = new Material.Builder(4761, gjId("acidic_copper_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Water, 4, Copper, 2, Sulfur, 2, Oxygen, 8, NitricAcid, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build()
                .setFormula("(H2O)4(CuSO4)2(HNO3)2", true);

        CopperSulfate = new Material.Builder(4762, gjId("copper_sulfate"))
                .dust()
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        CopperIFluoride = new Material.Builder(4763, gjId("copper_i_fluoride"))
                .dust()
                .components(Copper, 1, Fluorine, 1)
                .colorAverage()
                .build();

        CopperDichloride = new Material.Builder(4764, gjId("copper_dichloride"))
                .dust()
                .components(Copper, 1, Chlorine, 2)
                .colorAverage()
                .build();

        HighPurityCopper = new Material.Builder(4800, gjId("high_purity_copper"))
                .dust()
                .color(Copper.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Cu*", true);
    }
}
