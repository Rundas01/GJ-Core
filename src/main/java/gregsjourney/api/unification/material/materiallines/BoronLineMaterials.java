package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.unification.material.GJOreMaterials.Colemanite;
import static gregsjourney.api.unification.material.GJOreMaterials.Kernite;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class BoronLineMaterials {
    private BoronLineMaterials(){}
    public static Material BoronSlag;
    public static Material BoronWaste;
    public static Material KerniteSlag;
    public static Material ColemaniteSlag;
    public static Material BoricAcid;
    public static Material DioxoboricAcid;
    public static Material BoronTrichloride;
    public static Material SodiumBorohydride;
    public static Material BoronTrinitrate;
    public static Material BoronNitride;
    public static Material BoronIIIOxide;
    public static Material HighPurityBoron;

    public static void init(){
        BoronSlag = new Material.Builder(4401, gjId("boron_slag"))
                .fluid()
                .color(avgColor(Boron,Steel))
                .build()
                .setFormula("B?",true);

        BoronWaste = new Material.Builder(4402, gjId("boron_waste"))
                .fluid()
                .color(BoronSlag.getMaterialRGB()-20)
                .build()
                .setFormula("B?",true);

        KerniteSlag = new Material.Builder(4403, gjId("kernite_slag"))
                .fluid()
                .color(avgColor(Kernite,Steel))
                .build()
                .setFormula("?");

        ColemaniteSlag = new Material.Builder(4404, gjId("colemanite_slag"))
                .fluid()
                .color(avgColor(Colemanite,Steel))
                .build()
                .setFormula("?");

        BoricAcid = new Material.Builder(4405, gjId("boric_acid"))
                .dust()
                .components(Hydrogen, 3, Boron, 1, Oxygen, 3)
                .colorAverage()
                .build();

        DioxoboricAcid = new Material.Builder(4406, gjId("dioxoboric_acid"))
                .dust()
                .components(Hydrogen, 3, Boron, 3, Oxygen, 6)
                .colorAverage()
                .build();

        BoronTrichloride = new Material.Builder(4407, gjId("boron_trichloride"))
                .gas()
                .components(Boron, 1, Chlorine, 3)
                .colorAverage()
                .build();

        SodiumBorohydride = new Material.Builder(4408, gjId("sodium_borohydride"))
                .dust()
                .components(Sodium, 1, Boron, 1, Hydrogen, 4)
                .colorAverage()
                .build();

        BoronTrinitrate = new Material.Builder(4409, gjId("boron_trinitrate"))
                .dust()
                .components(Boron, 1, Nitrogen, 3, Oxygen, 9)
                .colorAverage()
                .build()
                .setFormula("B(NO3)3",true);

        BoronNitride = new Material.Builder(4410, gjId("boron_nitride"))
                .dust()
                .components(Boron, 1, Nitrogen, 1)
                .colorAverage()
                .build();

        BoronIIIOxide = new Material.Builder(4411, gjId("boron_iii_oxide"))
                .dust()
                .components(Boron, 2, Oxygen, 3)
                .colorAverage()
                .build();

        HighPurityBoron = new Material.Builder(4450, gjId("high_purity_boron"))
                .dust()
                .color(Boron.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("B*",true);
    }
}
