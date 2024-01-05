package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class GalliumLineMaterials {
    private GalliumLineMaterials(){}

    public static Material SodiumMercurate;
    public static Material GalliumMercurate;
    public static Material AluminiumMercurate;
    public static Material BauxiteSlurry;
    public static Material AmalgamatedBauxiteSlurry;
    public static Material CleanAmalgamatedBauxiteSlurry;

    public static void init(){
        SodiumMercurate = new Material.Builder(3643,gcylrId("sodium_mercurate"))
                .dust()
                .iconSet(SHINY)
                .color((Sodium.getMaterialRGB()+Mercury.getMaterialRGB())/2)
                .components(Sodium,1,Mercury,1)
                .build();

        GalliumMercurate = new Material.Builder(3644,gcylrId("gallium_mercurate"))
                .dust()
                .iconSet(SHINY)
                .color((Gallium.getMaterialRGB()+Mercury.getMaterialRGB())/2)
                .components(Gallium,1,Mercury,1)
                .build();

        AluminiumMercurate = new Material.Builder(3645,gcylrId("aluminium_mercurate"))
                .dust()
                .iconSet(SHINY)
                .color((Aluminium.getMaterialRGB()+Mercury.getMaterialRGB())/2)
                .components(Aluminium,1,Mercury,1)
                .build();

        BauxiteSlurry = new Material.Builder(3646,gcylrId("bauxite_slurry"))
                .fluid()
                .color((Bauxite.getMaterialRGB()+SodiumHydroxide.getMaterialRGB())/2)
                .build()
                .setFormula("?",true);

        AmalgamatedBauxiteSlurry = new Material.Builder(3647,gcylrId("amalgamated_bauxite_slurry"))
                .fluid()
                .color((Bauxite.getMaterialRGB()+Mercury.getMaterialRGB())/2)
                .build()
                .setFormula("Hg?",true);

        CleanAmalgamatedBauxiteSlurry = new Material.Builder(3648,gcylrId("clean_amalgamated_bauxite_slurry"))
                .fluid()
                .color(AmalgamatedBauxiteSlurry.getMaterialRGB()-20)
                .build()
                .setFormula("Hg?",true);
    }
}
