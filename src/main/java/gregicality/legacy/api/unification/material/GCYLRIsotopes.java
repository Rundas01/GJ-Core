package gregicality.legacy.api.unification.material;

import gregicality.legacy.api.unification.properties.NuclearProperty;
import gregicality.legacy.common.GCYLRConfigHolder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.core.unification.material.internal.MaterialRegistryManager;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregicality.legacy.api.unification.material.GCYLRElements.*;
import static gregicality.legacy.api.unification.ore.GCYLRMaterialFlags.GENERATE_NUCLEAR;
import static gregicality.legacy.api.unification.properties.GCYLRPropertyKeys.NUCLEAR;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.METALLIC;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class GCYLRIsotopes {

    private GCYLRIsotopes() {}

    public static Material Thallium205;
    public static Material Thallium206;
    public static Material Thallium207;
    public static Material Thallium208;
    public static Material Thallium209;
    public static Material Thallium210;
    public static Material Lead204;
    public static Material Lead205;
    public static Material Lead206;
    public static Material Lead207;
    public static Material Lead208;
    public static Material Lead209;
    public static Material Lead210;
    public static Material Lead211;
    public static Material Lead212;
    public static Material Lead213;
    public static Material Lead214;
    public static Material Bismuth205;
    public static Material Bismuth209;
    public static Material Bismuth210;
    public static Material Bismuth211;
    public static Material Bismuth212;
    public static Material Bismuth213;
    public static Material Bismuth214;
    public static Material Bismuth215;
    public static Material Polonium209;
    public static Material Polonium210;
    public static Material Polonium211;
    public static Material Polonium212;
    public static Material Polonium213;
    public static Material Polonium214;
    public static Material Polonium215;
    public static Material Polonium216;
    public static Material Polonium218;
    public static Material Astatine215;
    public static Material Astatine217;
    public static Material Astatine218;
    public static Material Astatine219;
    public static Material Radon217;
    public static Material Radon218;
    public static Material Radon219;
    public static Material Radon222;
    public static Material Francium223;
    public static Material Radium221;
    public static Material Radium223;
    public static Material Radium226;
    public static Material Radium227;
    public static Material Radium228;
    public static Material Actinium227;
    public static Material Actinium228;
    public static Material Thorium227;
    public static Material Thorium228;
    public static Material Thorium231;

    public static List<Material> nuclearMaterials = new ArrayList<>();

    public static void init() {
        initIsotopes();
        addProperties();
        buildList();
        assignProperties();
        addFlags();
    }

    private static void addProperties() {
        Actinium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Polonium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Radium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Astatine.setProperty(PropertyKey.INGOT, new IngotProperty());
        Francium.setProperty(PropertyKey.INGOT, new IngotProperty());
    }

    private static void initIsotopes() {
        Thallium205 = new Material.Builder(3040,gcylrId("thallium_205"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl205)
                .build();

        Thallium206 = new Material.Builder(3041,gcylrId("thallium_206"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl206)
                .build();

        Thallium207 = new Material.Builder(3042,gcylrId("thallium_207"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl207)
                .build();

        Thallium208 = new Material.Builder(3043,gcylrId("thallium_208"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl208)
                .build();

        Thallium209 = new Material.Builder(3044,gcylrId("thallium_209"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl209)
                .build();

        Thallium210 = new Material.Builder(3045,gcylrId("thallium_210"))
                .ingot()
                .color(0xC1C1DE)
                .iconSet(SHINY)
                .element(Tl210)
                .build();

        Lead204 = new Material.Builder(3046,gcylrId("lead_204"))
                .ingot()
                .color(0x8C648C)
                .element(Pb204)
                .build();

        Lead205 = new Material.Builder(3047,gcylrId("lead_205"))
                .ingot()
                .color(0x8C648C)
                .element(Pb205)
                .build();

        Lead206 = new Material.Builder(3048,gcylrId("lead_206"))
                .ingot()
                .color(0x8C648C)
                .element(Pb206)
                .build();

        Lead207 = new Material.Builder(3049,gcylrId("lead_207"))
                .ingot()
                .color(0x8C648C)
                .element(Pb207)
                .build();

        Lead208 = new Material.Builder(3050,gcylrId("lead_208"))
                .ingot()
                .color(0x8C648C)
                .element(Pb208)
                .build();

        Lead209 = new Material.Builder(3051,gcylrId("lead_209"))
                .ingot()
                .color(0x8C648C)
                .element(Pb209)
                .build();

        Lead210 = new Material.Builder(3052,gcylrId("lead_210"))
                .ingot()
                .color(0x8C648C)
                .element(Pb210)
                .build();

        Lead211 = new Material.Builder(3053,gcylrId("lead_211"))
                .ingot()
                .color(0x8C648C)
                .element(Pb211)
                .build();

        Lead212 = new Material.Builder(3054,gcylrId("lead_212"))
                .ingot()
                .color(0x8C648C)
                .element(Pb212)
                .build();

        Lead213 = new Material.Builder(3055,gcylrId("lead_213"))
                .ingot()
                .color(0x8C648C)
                .element(Pb213)
                .build();

        Lead214 = new Material.Builder(3056,gcylrId("lead_214"))
                .ingot()
                .color(0x8C648C)
                .element(Pb214)
                .build();

        Bismuth205 = new Material.Builder(3057,gcylrId("bismuth_205"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi205)
                .build();

        Bismuth209 = new Material.Builder(3058,gcylrId("bismuth_209"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi209)
                .build();

        Bismuth210 = new Material.Builder(3059,gcylrId("bismuth_210"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi210)
                .build();

        Bismuth211 = new Material.Builder(3060,gcylrId("bismuth_211"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi211)
                .build();

        Bismuth212 = new Material.Builder(3061,gcylrId("bismuth_212"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi212)
                .build();

        Bismuth213 = new Material.Builder(3062,gcylrId("bismuth_213"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi213)
                .build();

        Bismuth214 = new Material.Builder(3063,gcylrId("bismuth_214"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi214)
                .build();

        Bismuth215 = new Material.Builder(3064,gcylrId("bismuth_215"))
                .ingot()
                .color(0x64A0A0)
                .iconSet(METALLIC)
                .element(Bi215)
                .build();

        Polonium209 = new Material.Builder(3065,gcylrId("polonium_209"))
                .ingot()
                .color(0xC9D47E)
                .element(Po209)
                .build();

        Polonium210 = new Material.Builder(3066,gcylrId("polonium_210"))
                .ingot()
                .color(0xC9D47E)
                .element(Po210)
                .build();

        Polonium211 = new Material.Builder(3067,gcylrId("polonium_211"))
                .ingot()
                .color(0xC9D47E)
                .element(Po211)
                .build();

        Polonium212 = new Material.Builder(3068,gcylrId("polonium_212"))
                .ingot()
                .color(0xC9D47E)
                .element(Po212)
                .build();

        Polonium213 = new Material.Builder(3069,gcylrId("polonium_213"))
                .ingot()
                .color(0xC9D47E)
                .element(Po213)
                .build();

        Polonium214 = new Material.Builder(3070,gcylrId("polonium_214"))
                .ingot()
                .color(0xC9D47E)
                .element(Po214)
                .build();

        Polonium215 = new Material.Builder(3071,gcylrId("polonium_215"))
                .ingot()
                .color(0xC9D47E)
                .element(Po215)
                .build();

        Polonium216 = new Material.Builder(3072,gcylrId("polonium_216"))
                .ingot()
                .color(0xC9D47E)
                .element(Po216)
                .build();

        Polonium218 = new Material.Builder(3073,gcylrId("polonium_218"))
                .ingot()
                .color(0xC9D47E)
                .element(Po218)
                .build();

        Astatine215 = new Material.Builder(3074,gcylrId("astatine_215"))
                .ingot()
                .color(0x241A24)
                .element(At215)
                .build();

        Astatine217 = new Material.Builder(3075,gcylrId("astatine_217"))
                .ingot()
                .color(0x241A24)
                .element(At217)
                .build();

        Astatine218 = new Material.Builder(3076,gcylrId("astatine_218"))
                .ingot()
                .color(0x241A24)
                .element(At218)
                .build();

        Astatine219 = new Material.Builder(3077,gcylrId("astatine_219"))
                .ingot()
                .color(0x241A24)
                .element(At219)
                .build();

        Radon217 = new Material.Builder(3078,gcylrId("radon_217"))
                .gas()
                .color(0xFF39FF)
                .element(Rn217)
                .build();

        Radon218 = new Material.Builder(3079,gcylrId("radon_218"))
                .gas()
                .color(0xFF39FF)
                .element(Rn218)
                .build();

        Radon219 = new Material.Builder(3080,gcylrId("radon_219"))
                .gas()
                .color(0xFF39FF)
                .element(Rn219)
                .build();

        Radon222 = new Material.Builder(3081,gcylrId("radon_222"))
                .gas()
                .color(0xFF39FF)
                .element(Rn222)
                .build();

        Francium223 = new Material.Builder(3082,gcylrId("francium_223"))
                .color(0xAAAAAA)
                .ingot()
                .iconSet(SHINY)
                .element(Fr223)
                .build();

        Radium221 = new Material.Builder(3083,gcylrId("radium_221"))
                .ingot()
                .color(0xFFFFCD).iconSet(SHINY)
                .element(Ra221)
                .build();

        Radium223 = new Material.Builder(3084,gcylrId("radium_223"))
                .ingot()
                .color(0xFFFFCD).iconSet(SHINY)
                .element(Ra223)
                .build();

        Radium226 = new Material.Builder(3085,gcylrId("radium_226"))
                .ingot()
                .color(0xFFFFCD).iconSet(SHINY)
                .element(Ra226)
                .build();

        Radium227 = new Material.Builder(3086,gcylrId("radium_227"))
                .ingot()
                .color(0xFFFFCD).iconSet(SHINY)
                .element(Ra227)
                .build();

        Radium228 = new Material.Builder(3087,gcylrId("radium_228"))
                .ingot()
                .color(0xFFFFCD).iconSet(SHINY)
                .element(Ra228)
                .build();

        Actinium227 = new Material.Builder(3088,gcylrId("actinium_227"))
                .ingot()
                .color(0xC3D1FF).iconSet(METALLIC)
                .element(Ac227)
                .build();

        Actinium228 = new Material.Builder(3089,gcylrId("actinium_228"))
                .ingot()
                .color(0xC3D1FF).iconSet(METALLIC)
                .element(Ac228)
                .build();

        Thorium227 = new Material.Builder(3090,gcylrId("thorium_227"))
                .ingot()
                .color(0x001E00).iconSet(SHINY)
                .element(Th227)
                .build();

        Thorium228 = new Material.Builder(3091,gcylrId("thorium_228"))
                .ingot()
                .color(0x001E00).iconSet(SHINY)
                .element(Th228)
                .build();

        Thorium231 = new Material.Builder(3092,gcylrId("thorium_231"))
                .ingot()
                .color(0x001E00).iconSet(SHINY)
                .element(Th231)
                .build();
    }

    private static void assignProperties() {
        Thallium206.setProperty(NUCLEAR,new NuclearProperty(Thallium206,Thallium,0,0,1.533,true));
        Thallium207.setProperty(NUCLEAR,new NuclearProperty(Thallium207,Thallium,0,0,1.423,true));
        Thallium208.setProperty(NUCLEAR,new NuclearProperty(Thallium208,Thallium,0,0,5.001,true));
        Thallium209.setProperty(NUCLEAR,new NuclearProperty(Thallium209,Thallium,0,0,3.98,true));
        Thallium210.setProperty(NUCLEAR,new NuclearProperty(Thallium210,Thallium,0,0,5.484,true));
        Lead204.setProperty(NUCLEAR,new NuclearProperty(Lead204,Lead,2.186,0,0,true));
        Lead205.setProperty(NUCLEAR,new NuclearProperty(Lead205,Lead,0,0.051,0,true));
        Lead209.setProperty(NUCLEAR,new NuclearProperty(Lead209,Lead,0,0,0.644,true));
        Lead210.setProperty(NUCLEAR,new NuclearProperty(Lead210,Lead,3.792,0,0.064,true));
        Lead211.setProperty(NUCLEAR,new NuclearProperty(Lead211,Lead,0,0,1.367,true));
        Lead212.setProperty(NUCLEAR,new NuclearProperty(Lead212,Lead,0,0,0.574,true));
        Lead213.setProperty(NUCLEAR,new NuclearProperty(Lead213,Lead,0,0,2.07,true));
        Lead214.setProperty(NUCLEAR,new NuclearProperty(Lead214,Lead,0,0,1.024,true));
        Bismuth205.setProperty(NUCLEAR,new NuclearProperty(Bismuth205,Bismuth,0,2.708,0,true));
        Bismuth209.setProperty(NUCLEAR,new NuclearProperty(Bismuth209,Bismuth,3.137,0,0,true));
        Bismuth210.setProperty(NUCLEAR,new NuclearProperty(Bismuth210,Bismuth,5.037,0,1.163,true));
        Bismuth211.setProperty(NUCLEAR,new NuclearProperty(Bismuth211,Bismuth,6.751,0,0.579,true));
        Bismuth212.setProperty(NUCLEAR,new NuclearProperty(Bismuth212,Bismuth,6.027,0,2.245,true));
        Bismuth213.setProperty(NUCLEAR,new NuclearProperty(Bismuth213,Bismuth,5.982,0,1.464,true));
        Bismuth214.setProperty(NUCLEAR,new NuclearProperty(Bismuth214,Bismuth,5.671,0,3.272,true));
        Bismuth215.setProperty(NUCLEAR,new NuclearProperty(Bismuth215,Bismuth,0,0,2.25,true));
        Polonium.setProperty(NUCLEAR,new NuclearProperty(Polonium,null,7.526,1.401, 0.721,true));
        Polonium209.setProperty(NUCLEAR,new NuclearProperty(Polonium209,Polonium,4.879,1.839, 0,true));
        Polonium210.setProperty(NUCLEAR,new NuclearProperty(Polonium210,Polonium,5.307,0, 0,true));
        Polonium211.setProperty(NUCLEAR,new NuclearProperty(Polonium211,Polonium,7.595,0,0,true));
        Polonium212.setProperty(NUCLEAR,new NuclearProperty(Polonium212,Polonium,7.526,0, 0.721,true));
        Polonium213.setProperty(NUCLEAR,new NuclearProperty(Polonium213,Polonium,8.5,0, 0,true));
        Polonium214.setProperty(NUCLEAR,new NuclearProperty(Polonium214,Polonium,7.69,0, 0,true));
        Polonium215.setProperty(NUCLEAR,new NuclearProperty(Polonium215,Polonium,7.526,0, 0.721,true));
        Polonium216.setProperty(NUCLEAR,new NuclearProperty(Polonium216,Polonium,6.78,0, 0,true));
        Polonium218.setProperty(NUCLEAR,new NuclearProperty(Polonium218,Polonium,6.115,0, 0.26,true));
        Astatine.setProperty(NUCLEAR,new NuclearProperty(Astatine,null,5.757,3.486, 0,true));
        Astatine215.setProperty(NUCLEAR,new NuclearProperty(Astatine215,Astatine,8.178,0, 0,true));
        Astatine217.setProperty(NUCLEAR,new NuclearProperty(Astatine217,Astatine,5.982,0, 0.786,true));
        Astatine218.setProperty(NUCLEAR,new NuclearProperty(Astatine218,Astatine,6.874,0, 0,true));
        Astatine219.setProperty(NUCLEAR,new NuclearProperty(Astatine219,Astatine,6.39,0, 1.7,true));
        Radon217.setProperty(NUCLEAR,new NuclearProperty(Radon217,Radon,7.889,0, 0,false));
        Radon218.setProperty(NUCLEAR,new NuclearProperty(Radon218,Radon,7.263,0, 0,false));
        Radon219.setProperty(NUCLEAR,new NuclearProperty(Radon219,Radon,6.946,0, 0,false));
        Radon.setProperty(NUCLEAR,new NuclearProperty(Radon,null,6.405,0, 0,false));
        Radon222.setProperty(NUCLEAR,new NuclearProperty(Radon222,Radon,5.59,0, 0,false));
        Francium.setProperty(NUCLEAR,new NuclearProperty(Francium,null,6.458,0,2.033,true));
        Francium223.setProperty(NUCLEAR,new NuclearProperty(Francium223,Francium,5.43,0,1.149,true));
        Radium221.setProperty(NUCLEAR,new NuclearProperty(Radium221,Radium,6.886,0, 0,true));
        Radium223.setProperty(NUCLEAR,new NuclearProperty(Radium223,Radium,5.979,0, 0,true));
        Radium.setProperty(NUCLEAR,new NuclearProperty(Radium,null,5.789,0, 0,true));
        Radium226.setProperty(NUCLEAR,new NuclearProperty(Radium226,Radium,4.871,0, 0,true));
        Radium227.setProperty(NUCLEAR,new NuclearProperty(Radium227,Radium,0,0, 1.325,true));
        Radium228.setProperty(NUCLEAR,new NuclearProperty(Radium228,Radium,0,0, 0.389,true));
        Actinium.setProperty(NUCLEAR,new NuclearProperty(Actinium,null,5.935,0, 0,true));
        Actinium227.setProperty(NUCLEAR,new NuclearProperty(Actinium227,Actinium,5.042,0, 0.045,true));
        Actinium228.setProperty(NUCLEAR,new NuclearProperty(Actinium228,Actinium,0,0, 2.127,true));
        Thorium227.setProperty(NUCLEAR,new NuclearProperty(Thorium227,Thorium,6.146,0, 0,true));
        Thorium228.setProperty(NUCLEAR,new NuclearProperty(Thorium228,Thorium,5.52,0,0,true));
        Thorium.setProperty(NUCLEAR,new NuclearProperty(Thorium,null,4.083,0, 0,true));
        Thorium231.setProperty(NUCLEAR,new NuclearProperty(Thorium231,Thorium,4.231,0, 0.389,true));

    }

    private static void buildList() {
        nuclearMaterials.add(Mercury);
        nuclearMaterials.add(Thallium205); //stable
        nuclearMaterials.add(Thallium206);
        nuclearMaterials.add(Thallium207);
        nuclearMaterials.add(Thallium208);
        nuclearMaterials.add(Thallium209);
        nuclearMaterials.add(Thallium210);
        nuclearMaterials.add(Lead204);
        nuclearMaterials.add(Lead205);
        nuclearMaterials.add(Lead206); //stable
        nuclearMaterials.add(Lead); //207, stable
        nuclearMaterials.add(Lead208); //stable
        nuclearMaterials.add(Lead209);
        nuclearMaterials.add(Lead210);
        nuclearMaterials.add(Lead211);
        nuclearMaterials.add(Lead212);
        nuclearMaterials.add(Lead213);
        nuclearMaterials.add(Lead214);
        nuclearMaterials.add(Bismuth205);
        nuclearMaterials.add(Bismuth); //208, stable
        nuclearMaterials.add(Bismuth209);
        nuclearMaterials.add(Bismuth210);
        nuclearMaterials.add(Bismuth211);
        nuclearMaterials.add(Bismuth212);
        nuclearMaterials.add(Bismuth213);
        nuclearMaterials.add(Bismuth214);
        nuclearMaterials.add(Bismuth215);
        nuclearMaterials.add(Polonium); //208
        nuclearMaterials.add(Polonium209);
        nuclearMaterials.add(Polonium210);
        nuclearMaterials.add(Polonium211);
        nuclearMaterials.add(Polonium212);
        nuclearMaterials.add(Polonium213);
        nuclearMaterials.add(Polonium214);
        nuclearMaterials.add(Polonium215);
        nuclearMaterials.add(Polonium216);
        nuclearMaterials.add(Polonium218);
        nuclearMaterials.add(Astatine); //209
        nuclearMaterials.add(Astatine215);
        nuclearMaterials.add(Astatine217);
        nuclearMaterials.add(Astatine218);
        nuclearMaterials.add(Astatine219);
        nuclearMaterials.add(Radon217);
        nuclearMaterials.add(Radon218);
        nuclearMaterials.add(Radon219);
        nuclearMaterials.add(Radon); //220
        nuclearMaterials.add(Radon222);
        nuclearMaterials.add(Francium); //221
        nuclearMaterials.add(Francium223);
        nuclearMaterials.add(Radium221);
        nuclearMaterials.add(Radium223);
        nuclearMaterials.add(Radium); //224
        nuclearMaterials.add(Radium226);
        nuclearMaterials.add(Radium227);
        nuclearMaterials.add(Radium228);
        nuclearMaterials.add(Actinium); //225
        nuclearMaterials.add(Actinium227);
        nuclearMaterials.add(Actinium228);
        nuclearMaterials.add(Thorium227);
        nuclearMaterials.add(Thorium228);
        nuclearMaterials.add(Thorium);//230
        nuclearMaterials.add(Thorium231);
    }

    private static void addFlags(){
        for(Material material : nuclearMaterials){
            NuclearProperty prop = material.getProperty(NUCLEAR);
            if(prop == null) continue;
            if(prop.canBeFuel()){
                material.addFlags(GENERATE_NUCLEAR);
            }
        }
    }

    public static Material getFromProtonsAndNeutrons(long protons, long neutrons, boolean justNuclear){
        if (justNuclear){
            for (Material material : nuclearMaterials) {
                if(material.getProtons() == protons && material.getNeutrons() == neutrons){
                    return material;
                }
            }
        }else{
            for (Material material : MaterialRegistryManager.getInstance().getRegisteredMaterials()) {
                if(material.getProtons() == protons && material.getNeutrons() == neutrons){
                    return material;
                }
            }
        }
        return Materials.Iron;
    }

    public static Material getFromProtons(long protons, boolean justNuclear){
        if (justNuclear){
            for (Material material : nuclearMaterials) {
                if(material.getProtons() == protons){
                    return material;
                }
            }
        }else{
            for (Material material : MaterialRegistryManager.getInstance().getRegisteredMaterials()) {
                if(material.getProtons() == protons){
                    return material;
                }
            }
        }
        return Materials.Iron;
    }

    public static Material getAlphaDecayMaterial(Material material){
        return getFromProtonsAndNeutrons(material.getProtons() - 2, material.getNeutrons() - 2, true);
    }

    public static Material getBetaPlusDecayMaterial(Material material){
        return getFromProtonsAndNeutrons(material.getProtons() - 1, material.getNeutrons() + 1, true);
    }

    public static Material getBetaMinusDecayMaterial(Material material){
        return getFromProtonsAndNeutrons(material.getProtons() + 1, material.getNeutrons() - 1, true);
    }

    public static List<Tuple<List<Material>,Long>> getFissionProducts(Material material) {
        List<Tuple<List<Material>,Long>> result = new ArrayList<>();
        long protons = material.getProtons(), neutrons = material.getNeutrons();
        if(protons == 1){
            return null;
        }
        long protons1, protons2;
        Material mat1, mat2;
        long freeNeutrons;
        if(protons % 2 == 0){
            for (int i = 0; i < GCYLRConfigHolder.machines.fuelSplitOffset; i++) {
                protons1 = protons/2 + i;
                protons2 = protons/2 - i;
                if(protons2 <= 0){
                    continue;
                }
                mat1 = getFromProtons(protons1,false);
                mat2 = getFromProtons(protons2,false);
                freeNeutrons = neutrons - mat1.getNeutrons() - mat2.getNeutrons();
                if(freeNeutrons > 0){
                    result.add(new Tuple<>(Arrays.asList(mat1,mat2),freeNeutrons));
                }
            }
        }else{
            for (int i = 0; i < GCYLRConfigHolder.machines.fuelSplitOffset; i++) {
                protons1 = (protons + 1)/2 + i;
                protons2 = (protons - 1)/2 - i;
                if(protons2 <= 0){
                    continue;
                }
                mat1 = getFromProtons(protons1,false);
                mat2 = getFromProtons(protons2,false);
                freeNeutrons = neutrons - mat1.getNeutrons() - mat2.getNeutrons();
                if(freeNeutrons > 0){
                    result.add(new Tuple<>(Arrays.asList(mat1, mat2),freeNeutrons));
                }
            }
        }
        return result;
    }

    private static void calculateFissionProducts(long remainingProtons, List<Integer> currentWay, List<List<Integer>> result) {
        if (remainingProtons == 0) {
            result.add(new ArrayList<>(currentWay));
            return;
        }

        for (int i = 1; i <= remainingProtons; i++) {
            currentWay.add(i);
            calculateFissionProducts(remainingProtons - i, currentWay, result);
            currentWay.remove(currentWay.size() - 1);
        }
    }
}
