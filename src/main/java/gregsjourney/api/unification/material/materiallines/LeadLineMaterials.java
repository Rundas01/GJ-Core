package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class LeadLineMaterials {
    private LeadLineMaterials(){}
    public static Material LeadSlag;
    public static Material LeadWaste;
    public static Material LeadOxide;
    public static Material LeadDinitrate;
    public static Material LeadDichloride;
    public static Material HighPurityLead;

    public static void init(){
        LeadSlag = new Material.Builder(4951,gjId("lead_slag"))
                .fluid()
                .color(avgColor(Lead,Steel))
                .build()
                .setFormula("Pb?",true);

        LeadWaste = new Material.Builder(4952,gjId("lead_waste"))
                .fluid()
                .color(LeadSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Pb?",true);

        LeadOxide = new Material.Builder(4953, gjId("lead_oxide"))
                .dust()
                .components(Lead,1,Oxygen,1)
                .colorAverage()
                .build();

        LeadDinitrate = new Material.Builder(4954,gjId("lead_dinitrate"))
                .dust()
                .components(Lead,1,Nitrogen,2,Oxygen,6)
                .colorAverage()
                .build()
                .setFormula("Pb(NO3)2",true);

        LeadDichloride = new Material.Builder(4955,gjId("lead_dichloride"))
                .dust().fluid()
                .components(Lead,1,Chlorine,2)
                .colorAverage()
                .build();

        HighPurityLead = new Material.Builder(5000,gjId("high_purity_lead"))
                .dust()
                .color(Lead.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Pb*",true);
    }
}
