package gregicality.legacy.api.unification.material.materiallines;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.convertRGB2Hex;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class SiliconLineMaterials {
    private SiliconLineMaterials(){}

    public static Material TrichloroSilane;
    public static Material Silane;
    public static Material SiliconTetrachloride;
    public static Material HighPuritySilicon;

    public static void init(){
        TrichloroSilane = new Material.Builder(3726,gcylrId("trichloro_silane"))
                .fluid()
                .color(convertRGB2Hex(255,255,255))
                .components(Hydrogen,1,Silicon,1,Chlorine,3)
                .build();

        Silane = new Material.Builder(3727,gcylrId("silane"))
                .fluid()
                .color(convertRGB2Hex(255,255,255))
                .components(Silicon,1,Hydrogen,4)
                .build();

        SiliconTetrachloride = new Material.Builder(3728,gcylrId("silicon_tetrachloride"))
                .fluid()
                .color(convertRGB2Hex(220,220,220))
                .components(Silicon,1,Chlorine,4)
                .build();

        HighPuritySilicon = new Material.Builder(3729,gcylrId("high_purity_silicon"))
                .dust()
                .iconSet(SHINY)
                .color(Silicon.getMaterialRGB())
                .components(Silicon,1,Hydrogen,4)
                .build()
                .setFormula("Si*",true);
    }
}
