package gregicality.legacy.api.unification;

import gregtech.api.unification.material.Material;

public class GCYLRMaterials {

    /*
     * Isotopes 3060 - X
     */
    public static Material Thallium205;
    public static Material Thallium206;
    public static Material Thallium207;
    public static Material Thallium208;
    public static  Material Thallium209;
    public static  Material Thallium210;
    public static  Material Lead204;
    public static  Material Lead205;
    public static  Material Lead206;
    public static  Material Lead207;
    public static  Material Lead208;
    public static  Material Lead209;
    public static  Material Lead210;
    public static  Material Lead211;
    public static  Material Lead212;
    public static  Material Lead213;
    public static  Material Lead214;
    public static  Material Bismuth205;
    public static  Material Bismuth209;
    public static  Material Bismuth210;
    public static  Material Bismuth211;
    public static  Material Bismuth212;
    public static  Material Bismuth213;
    public static  Material Bismuth214;
    public static  Material Bismuth215;
    public static  Material Polonium209;
    public static  Material Polonium210;
    public static  Material Polonium211;
    public static  Material Polonium212;
    public static  Material Polonium213;
    public static  Material Polonium214;
    public static  Material Polonium215;
    public static  Material Polonium216;
    public static  Material Polonium218;
    public static  Material Astatine215;
    public static  Material Astatine217;
    public static  Material Astatine218;
    public static  Material Astatine219;
    public static  Material Radon217;
    public static  Material Radon218;
    public static  Material Radon219;
    public static  Material Radon222;
    public static  Material Francium223;
    public static  Material Radium221;
    public static  Material Radium223;
    public static  Material Radium226;
    public static  Material Radium227;
    public static Material Radium228;
    public static  Material Actinium227;
    public static  Material Actinium228;
    public static  Material Thorium227;
    public static  Material Thorium228;
    public static  Material Thorium231;

    private GCYLRMaterials() {}

    public static void init() {
        //Isotopes 3040 - X
        GCYLRIsotopes.init();
        // First Degree X - Y
        GCYLRFirstDegreeMaterials.init();
        // Flags
        GCYLRMaterialFlagAddition.init();
    }
}
