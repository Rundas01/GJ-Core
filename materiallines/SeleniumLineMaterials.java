package thegreggening.api.unification.material.materiallines;

import static thegreggening.api.utils.GJUtil.avgColor;
import static thegreggening.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class SeleniumLineMaterials {

    private SeleniumLineMaterials() {}

    public static Material SeleniumSlag;
    public static Material SeleniumWaste;
    public static Material SeleniumTetrachloride;
    public static Material HighPuritySelenium;

    public static void init() {
        SeleniumSlag = new Material.Builder(14000, gjId("selenium_slag"))
                .fluid()
                .color(avgColor(Samarium, Steel))
                .build()
                .setFormula("Se?", true);

        SeleniumWaste = new Material.Builder(14001, gjId("selenium_waste"))
                .fluid()
                .color(SeleniumSlag.getMaterialRGB() - 20)
                .build()
                .setFormula("Se?", true);

        SeleniumTetrachloride = new Material.Builder(14002, gjId("selenium_tetrachloride"))
                .dust()
                .components(Selenium, 1, Chlorine, 4)
                .colorAverage()
                .build();

        HighPuritySelenium = new Material.Builder(14050, gjId("high_purity_selenium"))
                .dust()
                .color(Selenium.getMaterialRGB())
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Se*", true);
    }
}
