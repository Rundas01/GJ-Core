package gregsjourney.api.unification.material;

import gregsjourney.api.unification.property.GJMaterialFlags;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;

import static gregsjourney.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;

public class GJNuclearMaterials {

    private GJNuclearMaterials() {}

    //Krypton
    public static Material Krypton88;
    //Barium
    public static Material Barium145;
    //Thallium
    public static Material Thallium207;
    public static Material Thallium208;
    //Lead
    public static Material Lead207;
    public static Material Lead208;
    public static Material Lead211;
    public static Material Lead212;
    //Bismuth
    public static Material Bismuth211;
    public static Material Bismuth212;
    public static Material Bismuth215;
    //Polonium
    public static Material Polonium211;
    public static Material Polonium212;
    public static Material Polonium215;
    public static Material Polonium216;
    //Astatine
    public static Material Astatine215;
    public static Material Astatine219;
    //Radon
    public static Material Radon219;
    public static Material Radon220;
    //Francium
    public static Material Francium223;
    //Radium
    public static Material Radium223;
    public static Material Radium224;
    public static Material Radium227;
    public static Material Radium228;
    //Actinium
    public static Material Actinium227;
    public static Material Actinium228;
    //Thorium
    public static Material Thorium227;
    public static Material Thorium228;
    public static Material Thorium231;
    public static Material Thorium232;
    //Protactinium
    public static Material Protactinium231;
    public static Material Protactinium232;
    //Uranium
    public static Material MetastableUranium;
    public static Material Uranium232;
    public static Material Uranium236;
    public static Material Uranium237;
    //Neptunium
    public static Material Neptunium236;
    public static Material Neptunium237;
    public static Material Neptunium239;
    //Plutonium
    public static Material MetastablePlutonium;
    public static Material Plutonium236;
    public static Material Plutonium243;
    //Americium
    public static Material Americium241;
    public static Material Americium243;
    //Curium
    public static Material Curium245;
    public static Material Curium247;

    public static void init() {
        materialInit();
    }

    private static void materialInit() {
        //Krypton
        Krypton88 = isotopicGasMaterial(12000, Krypton, 88);
        //Barium
        Barium145 = isotopicIngotMaterial(12001, Barium, 145);
        //Thallium
        Thallium207 = isotopicIngotMaterial(12002, Thallium, 207);
        Thallium208 = isotopicIngotMaterial(12003, Thallium, 208);
        //Lead
        Lead207 = isotopicIngotMaterial(12004, Lead, 207);
        Lead208 = isotopicIngotMaterial(12005, Lead, 208);
        Lead211 = isotopicIngotMaterial(12006, Lead, 211);
        Lead212 = isotopicIngotMaterial(12007, Lead, 212);
        //Bismuth
        Bismuth211 = isotopicIngotMaterial(12008, Bismuth, 211);
        Bismuth212 = isotopicIngotMaterial(12009, Bismuth, 212);
        Bismuth215 = isotopicIngotMaterial(12010, Bismuth, 215);
        //Polonium
        Polonium211 = isotopicIngotMaterial(12011, Polonium, 211);
        Polonium212 = isotopicIngotMaterial(12012, Polonium, 212);
        Polonium215 = isotopicIngotMaterial(12013, Polonium, 215);
        Polonium216 = isotopicIngotMaterial(12014, Polonium, 216);
        //Astatine
        Astatine215 = isotopicIngotMaterial(12015, Astatine, 215);
        Astatine219 = isotopicIngotMaterial(12016, Astatine, 219);
        //Radon
        Radon219 = isotopicGasMaterial(12017, Radon, 219);
        Radon220 = isotopicGasMaterial(12018, Radon, 220);
        //Francium
        Francium223 = isotopicIngotMaterial(12019, Francium, 223);
        //Radium
        Radium223 = isotopicIngotMaterial(12020, Radium, 223);
        Radium224 = isotopicIngotMaterial(12021, Radium, 224);
        Radium227 = isotopicIngotMaterial(12022, Radium, 227);
        Radium228 = isotopicIngotMaterial(12023, Radium, 228);
        //Actinium
        Actinium227 = isotopicIngotMaterial(12024, Actinium, 227);
        Actinium228 = isotopicIngotMaterial(12025, Actinium, 228);
        //Thorium
        Thorium227 = isotopicIngotMaterial(12026, Thorium, 227, true);
        Thorium228 = isotopicIngotMaterial(12027, Thorium, 228, true);
        Thorium231 = isotopicIngotMaterial(12028, Thorium, 231, true);
        Thorium232 = isotopicIngotMaterial(12029, Thorium, 232, true);
        //Protactinium
        Protactinium231 = isotopicIngotMaterial(12030, Protactinium, 231);
        Protactinium232 = isotopicIngotMaterial(12031, Protactinium, 232);
        //Uranium
        MetastableUranium = new Material.Builder(12032, gjId("metastable_uranium")).ingot().element(Elements.U).color(Uranium238.getMaterialRGB()).build();
        Uranium232 = isotopicIngotMaterial(12033, MetastableUranium, 232, true);
        Uranium235.addFlags(GJMaterialFlags.FISSIONABLE);
        Uranium236 = isotopicIngotMaterial(12034, MetastableUranium, 236, true);
        Uranium237 = isotopicIngotMaterial(12035, MetastableUranium, 237, true);
        Uranium238.addFlags(GJMaterialFlags.FISSIONABLE);
        //Neptunium
        Neptunium236 = isotopicIngotMaterial(12036, Neptunium, 236);
        Neptunium237 = isotopicIngotMaterial(12037, Neptunium, 237);
        Neptunium239 = isotopicIngotMaterial(12038, Neptunium, 239);
        //Plutonium
        MetastablePlutonium = new Material.Builder(12039, gjId("metastable_plutonium")).ingot().element(Elements.Pu).color(Plutonium241.getMaterialRGB()).build();
        Plutonium236 = isotopicIngotMaterial(12040, MetastablePlutonium, 236, true);
        Plutonium243 = isotopicIngotMaterial(12041, MetastablePlutonium, 243, true);
        //Americium
        Americium241 = isotopicIngotMaterial(12042, Americium, 241, true);
        Americium243 = isotopicIngotMaterial(12043, Americium, 243, true);
        //Curium
        Curium245 = isotopicIngotMaterial(12044, Curium, 245, true);
        Curium247 = isotopicIngotMaterial(12045, Curium, 247, true);
    }

    private static Material isotopicIngotMaterial(int id, Material baseIsotope, int nucleons, boolean isFissionable) {
        Material material = new Material.Builder(id, gjId(baseIsotope.getName() + "_" + nucleons)).ingot().element(Elements.get(baseIsotope.getElement().getName() + "-" + nucleons)).color(baseIsotope.getMaterialRGB()).build();
        if (isFissionable) {
            material.addFlags(GJMaterialFlags.FISSIONABLE, GJMaterialFlags.GENERATE_NUCLEAR_FUELS);
        }
        return material;
    }

    private static Material isotopicIngotMaterial(int id, Material baseIsotope, int nucleons) {
        return isotopicIngotMaterial(id, baseIsotope, nucleons, false);
    }

    private static Material isotopicFluidMaterial(int id, Material baseIsotope, int nucleons) {
        return new Material.Builder(id, gjId(baseIsotope.getName() + "_" + nucleons)).fluid().element(Elements.get(baseIsotope.getElement().getName() + "-" + nucleons)).color(baseIsotope.getMaterialRGB()).build();
    }

    private static Material isotopicGasMaterial(int id, Material baseIsotope, int nucleons) {
        Material.Builder builder = new Material.Builder(id, gjId(baseIsotope.getName() + "_" + nucleons));
        //Material.Builder builder2 = builder;
        //GJLog.logger.debug("Initial: " + builder2.build().hasProperty(PropertyKey.INGOT));
        builder = builder.gas();
        //builder2 = builder;
        //GJLog.logger.debug("After Gas: " + builder2.build().hasProperty(PropertyKey.INGOT));
        builder = builder.element(Elements.get(baseIsotope.getElement().getName() + "-" + nucleons));
        //builder2 = builder;
        //GJLog.logger.debug("After Element: " + builder2.build().hasProperty(PropertyKey.INGOT));
        builder = builder.color(baseIsotope.getMaterialRGB());
        //builder2 = builder;
        //GJLog.logger.debug("After Color: " + builder2.build().hasProperty(PropertyKey.INGOT));
        return builder.build();
    }
}
