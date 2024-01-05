package gregicality.legacy.api.unification.material;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.convertRGB2Hex;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;

public class GCYLRPolymerizationMaterials {
    private GCYLRPolymerizationMaterials() {}

    public static Material BenzoicAcid;
    public static Material BenzoTrichloride;
    public static Material BenzoylChloride;
    public static Material DibenzoylPeroxide;
    public static Material Dibenzene;

    public static void init(){
        BenzoicAcid = new Material.Builder(3995,gcylrId("benzoic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(convertRGB2Hex(240,240,240))
                .components(Carbon,7,Hydrogen,6,Oxygen,2)
                .build()
                .setFormula("C6H5COOH",true);

        BenzoTrichloride = new Material.Builder(3996,gcylrId("benzotrichloride"))
                .fluid()
                .color(convertRGB2Hex(220,220,220))
                .components(Carbon,7,Hydrogen,7,Chlorine,3)
                .build()
                .setFormula("C6H5CCl3",true);

        BenzoylChloride = new Material.Builder(3997,gcylrId("benzoyl_chloride"))
                .fluid()
                .color(convertRGB2Hex(200,200,200))
                .components(Carbon,7,Hydrogen,5,Chlorine,1,Oxygen,1)
                .build()
                .setFormula("C6H5CClO",true);

        DibenzoylPeroxide = new Material.Builder(3998,gcylrId("dibenzoyl_peroxide"))
                .dust()
                .color(convertRGB2Hex(255,255,255))
                .components(Carbon,14,Hydrogen,4,Oxygen,10)
                .build()
                .setFormula("(C6H5CO2)2",true);

        Dibenzene = new Material.Builder(3999,gcylrId("dibenzene"))
                .dust()
                .color(convertRGB2Hex(150,150,0))
                .components(Carbon,12,Hydrogen,10)
                .build()
                .setFormula("(C6H5)2",true);
    }
}
