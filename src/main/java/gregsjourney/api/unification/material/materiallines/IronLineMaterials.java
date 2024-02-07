package gregsjourney.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregsjourney.api.utils.GJUtil.avgColor;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class IronLineMaterials {
    private IronLineMaterials(){}
    public static Material IronSlag;
    public static Material IronWaste;
    public static Material IronIISulfate;
    public static Material IronCarbonate;
    public static Material SodiumFerrateII;
    public static Material IronDifluoride;
    public static Material IronIIIOxide;
    public static Material IronTrinitrate;
    public static Material HighPurityIron;

    public static void init(){
        IronSlag = new Material.Builder(4851,gjId("iron_slag"))
                .fluid()
                .color(avgColor(Iron,Steel))
                .build()
                .setFormula("Fe?",true);

        IronWaste = new Material.Builder(4852,gjId("iron_waste"))
                .fluid()
                .color(IronSlag.getMaterialRGB()-20)
                .build()
                .setFormula("Fe?",true);

        IronIISulfate = new Material.Builder(4853,gjId("iron_ii_sulfate"))
                .dust()
                .colorAverage()
                .components(Iron,1,Sulfur,1,Oxygen,4)
                .build();

        IronCarbonate = new Material.Builder(4854,gjId("iron_carbonate"))
                .dust()
                .colorAverage()
                .components(Iron,1,Carbon,1,Oxygen,3)
                .build();

        SodiumFerrateII = new Material.Builder(4855, gjId("sodium_ferrate_ii"))
                .dust()
                .colorAverage()
                .components(Sodium, 2, Iron, 1, Oxygen, 2)
                .build();

        IronDifluoride = new Material.Builder(4856, gjId("iron_difluoride"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Fluorine, 2)
                .build();

        IronIIIOxide = new Material.Builder(4857, gjId("iron_iii_oxide"))
                .dust()
                .colorAverage()
                .components(Iron, 2, Oxygen, 3)
                .build();

        IronTrinitrate = new Material.Builder(4858, gjId("iron_trinitrate"))
                .dust()
                .colorAverage()
                .components(Iron, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Fe(NO3)3",true);

        HighPurityIron = new Material.Builder(4900, gjId("high_purity_iron"))
                .dust()
                .color(Iron.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Fe*",true);
    }
}
