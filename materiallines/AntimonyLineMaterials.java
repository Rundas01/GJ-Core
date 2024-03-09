package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJMiscMaterials.HydrogenFluoride;
import static gregsjourney.api.unification.material.GJOreMaterials.Livingstonite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class AntimonyLineMaterials {

    private AntimonyLineMaterials() {}

    public static Material AntimonySlag;
    public static Material AntimonyWaste;
    public static Material StibniteSlag;
    public static Material LivingstoniteSlag;
    public static Material AntimonyVSulfide;
    public static Material SodiumAntimonate;
    public static Material AntimonyPentafluoride;
    public static Material HexafluoroAntimonicAcid;
    public static Material AntimonyVOxide;
    public static Material AntimonyPentanitrate;
    public static Material AntimonyNitride;
    public static Material AntimonyTrinitrate;
    public static Material ChloridicAntimonyTailings;
    public static Material HighPurityAntimony;

    public static void init() {
        AntimonySlag = new Material.Builder(4151, gjId("antimony_slag"))
                .fluid()
                .color(avgColor(Antimony, Steel))
                .build()
                .setFormula("Sb?", true);

        AntimonyWaste = new Material.Builder(4152, gjId("antimony_waste"))
                .fluid()
                .color(AntimonySlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Sb?", true);

        StibniteSlag = new Material.Builder(4153, gjId("stibnite_slag"))
                .fluid()
                .color(avgColor(Stibnite, Steel))
                .build()
                .setFormula("?");

        LivingstoniteSlag = new Material.Builder(4154, gjId("livingstonite_slag"))
                .fluid()
                .color(avgColor(Livingstonite, Steel))
                .build()
                .setFormula("?");

        AntimonySlag = new Material.Builder(4155, gjId("antimony_slag"))
                .fluid()
                .color(avgColor(Antimony, Steel))
                .build()
                .setFormula("Sb?", true);

        AntimonyVSulfide = new Material.Builder(4156, gjId("antimony_v_sulfide"))
                .dust()
                .components(Antimony, 2, Sulfur, 5)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .build();

        SodiumAntimonate = new Material.Builder(4157, gjId("sodium_antimonate"))
                .dust()
                .components(Sodium, 1, Antimony, 1, Oxygen, 2)
                .colorAverage()
                .build();

        AntimonyPentafluoride = new Material.Builder(4158, gjId("antimony_pentafluoride"))
                .fluid()
                .colorAverage()
                .components(Antimony, 1, Fluorine, 5)
                .build();

        HexafluoroAntimonicAcid = new Material.Builder(4159, gjId("hexafluoro_antimonic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(AntimonyPentafluoride, 1, HydrogenFluoride, 1)
                .build();

        AntimonyVOxide = new Material.Builder(4160, gjId("antimony_v_oxide"))
                .dust()
                .components(Antimony, 2, Oxygen, 5)
                .colorAverage()
                .build();

        AntimonyPentanitrate = new Material.Builder(4161, gjId("antimony_pentanitrate"))
                .dust()
                .components(Antimony, 1, Nitrogen, 5, Oxygen, 15)
                .colorAverage()
                .build()
                .setFormula("Sb(NO3)5", true);

        AntimonyNitride = new Material.Builder(4162, gjId("antimony_nitride"))
                .dust()
                .components(Antimony, 1, Nitrogen, 1)
                .colorAverage()
                .build();

        ChloridicAntimonyTailings = new Material.Builder(4163, gjId("chloridic_antimony_tailings"))
                .fluid()
                .colorAverage()
                .build()
                .setFormula("Cl?", true);

        HighPurityAntimony = new Material.Builder(4200, gjId("high_purity_antimony"))
                .dust().fluid()
                .color(Antimony.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Sb*", true);
    }
}
