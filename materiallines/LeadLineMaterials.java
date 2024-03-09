package gregsjourney.api.unification.material.materiallines;

import static gregsjourney.api.unification.material.GJOreMaterials.*;
import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class LeadLineMaterials {

    private LeadLineMaterials() {}

    public static Material LeadSlag;
    public static Material LeadWaste;
    public static Material GalenaSlag;
    public static Material MimetiteSlag;
    public static Material PyromorphiteSlag;
    public static Material DescloiziteSlag;
    public static Material MottramiteSlag;
    public static Material LeadOxide;
    public static Material LeadDinitrate;
    public static Material LeadDichloride;
    public static Material LeadSulfate;
    public static Material SodiumPlumbate;
    public static Material HighPurityLead;

    public static void init() {
        LeadSlag = new Material.Builder(4951, gjId("lead_slag"))
                .fluid()
                .color(avgColor(Lead, Steel))
                .build()
                .setFormula("Pb?", true);

        LeadWaste = new Material.Builder(4952, gjId("lead_waste"))
                .fluid()
                .color(LeadSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Pb?", true);

        GalenaSlag = new Material.Builder(4953, gjId("galena_slag"))
                .fluid()
                .color(avgColor(Galena, Steel))
                .build()
                .setFormula("?", true);

        MimetiteSlag = new Material.Builder(4954, gjId("mimetite_slag"))
                .fluid()
                .color(avgColor(Mimetite, Steel))
                .build()
                .setFormula("?", true);

        PyromorphiteSlag = new Material.Builder(4955, gjId("pyromorphite_slag"))
                .fluid()
                .color(avgColor(Pyromorphite, Steel))
                .build()
                .setFormula("?", true);

        DescloiziteSlag = new Material.Builder(4956, gjId("descloizite_slag"))
                .fluid()
                .color(avgColor(Descloizite, Steel))
                .build()
                .setFormula("?", true);

        MottramiteSlag = new Material.Builder(4957, gjId("mottramite_slag"))
                .fluid()
                .color(avgColor(Mottramite, Steel))
                .build()
                .setFormula("?", true);

        LeadOxide = new Material.Builder(4958, gjId("lead_oxide"))
                .dust()
                .components(Lead, 1, Oxygen, 1)
                .colorAverage()
                .build();

        LeadDinitrate = new Material.Builder(4959, gjId("lead_dinitrate"))
                .dust()
                .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
                .colorAverage()
                .build()
                .setFormula("Pb(NO3)2", true);

        LeadDichloride = new Material.Builder(4960, gjId("lead_dichloride"))
                .dust().fluid()
                .components(Lead, 1, Chlorine, 2)
                .colorAverage()
                .build();

        SodiumPlumbate = new Material.Builder(4961, gjId("sodium_plumbate"))
                .dust()
                .components(Sodium, 2, Lead, 1, Oxygen, 2)
                .colorAverage()
                .build();

        HighPurityLead = new Material.Builder(5000, gjId("high_purity_lead"))
                .dust()
                .color(Lead.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pb*", true);
    }
}
