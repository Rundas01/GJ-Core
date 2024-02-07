package gregsjourney.api.unification.material.materiallines;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregsjourney.api.utils.GJUtil.convertRGB2Hex;
import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class SiliconLineMaterials {
    private SiliconLineMaterials(){}
    public static Material TrichloroSilane;
    public static Material Silane;
    public static Material SiliconTetrachloride;
    public static Material HexafluoroSilicicAcid;
    public static Material AluminoSilicaSulfate;
    public static Material SodiumFluorosilicate;
    public static Material HighPuritySilicon;

    public static void init(){
        TrichloroSilane = new Material.Builder(5451,gjId("trichloro_silane"))
                .fluid()
                .color(convertRGB2Hex(255,255,255))
                .components(Hydrogen,1,Silicon,1,Chlorine,3)
                .build();

        Silane = new Material.Builder(5452,gjId("silane"))
                .fluid()
                .color(convertRGB2Hex(255,255,255))
                .components(Silicon,1,Hydrogen,4)
                .build();

        SiliconTetrachloride = new Material.Builder(5453,gjId("silicon_tetrachloride"))
                .fluid()
                .color(convertRGB2Hex(220,220,220))
                .components(Silicon,1,Chlorine,4)
                .build();

        HexafluoroSilicicAcid = new Material.Builder(5454, gjId("hexafluoro_silicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .components(Hydrogen,2,Silicon,1,Fluorine,6)
                .colorAverage()
                .build();

        SodiumFluorosilicate = new Material.Builder(5455, gjId("sodium_fluorosilicate"))
                .dust()
                .components(Sodium,2,Silicon,1,Fluorine,6)
                .colorAverage()
                .build();

        AluminoSilicaSulfate = new Material.Builder(5456, gjId("alumino_silica_sulfate"))
                .dust()
                .components(Aluminium,2,Silicon,6,Sulfur,3,Oxygen,24)
                .colorAverage()
                .build()
                .setFormula("Al2Si6O12(SO4)3",true);

        HighPuritySilicon = new Material.Builder(5500,gjId("high_purity_silicon"))
                .dust()
                .iconSet(SHINY)
                .color(Silicon.getMaterialRGB())
                .build()
                .setFormula("Si*",true);
    }
}
