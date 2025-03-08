package thegreggening.api.unification.material;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.FluidUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.*;
import gregtech.api.util.Mods;
import net.minecraft.util.Tuple;
import org.jetbrains.annotations.ApiStatus;
import thegreggening.api.unification.property.NuclearProperty;
import thegreggening.api.unification.property.TGMaterialFlags;
import thegreggening.api.unification.property.TGPropertyKeys;
import thegreggening.integration.forestry.ForestryModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD;
import static net.minecraftforge.fml.common.Loader.isModLoaded;
import static thegreggening.api.unification.material.TGNuclearMaterials.*;
import static thegreggening.api.unification.property.TGMaterialFlags.GENERATE_CATALYST_BED;
import static thegreggening.utils.TGNuclearUtil.nuclearMaterials;

public class TGMaterialFlagAddition {

    private TGMaterialFlagAddition() {}

    public static void init() {
        FluidUnifier.registerFluid(Water.getFluid(), Water);
        FluidUnifier.registerFluid(Lava.getFluid(), Lava);
        if (isModLoaded(Mods.Forestry.name())) {
            ForestryModule.registerMaterialFlags();
        }
        setupNuclearMaterials();
    }

    public static void initLate() {
        Water.addFlags(TGMaterialFlags.SOLVENT);
        ToolProperty prop = Flint.getProperty(PropertyKey.TOOL);
        prop.setShouldIgnoreCraftingTools(false);
        Copper.addFlags(GENERATE_CATALYST_BED);
        Barium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Strontium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Rubidium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Praseodymium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Selenium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Scandium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Thallium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Polonium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Astatine.setProperty(PropertyKey.INGOT, new IngotProperty());
        Francium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Actinium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Curium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Berkelium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Californium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Einsteinium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Fermium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Mendelevium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Nobelium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Lawrencium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Rutherfordium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Dubnium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Seaborgium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Bohrium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Hassium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Meitnerium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Roentgenium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Copernicium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Nihonium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Flerovium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Moscovium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Livermorium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Tennessine.setProperty(PropertyKey.INGOT, new IngotProperty());
        Oganesson.setProperty(PropertyKey.INGOT, new IngotProperty());
        Graphite.addFlags(GENERATE_ROD);
        Nickel.addFlags(GENERATE_ROD);
        changeOreMultiplier();
    }

    private static void changeOreMultiplier() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE)) {
                OreProperty property = material.getProperty(PropertyKey.ORE);
                property.setOreMultiplier(2);
            }
        }
    }

    private static void setupNuclearMaterials() {
        List<Tuple<Tuple<Tuple<Material, Material>, Tuple<List<Material>, List<Double>>>, Integer>> nuclearMaterialsPre = new ArrayList<>();
        // Krypton
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Krypton88, Krypton),
                new Tuple<>(Arrays.asList(null, null, Rubidium88), Arrays.asList(0.0, 0.0, 2.918))), 5));

        // Rubidium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rubidium88, Rubidium),
                new Tuple<>(Arrays.asList(null, null, Strontium), Arrays.asList(0.0, 0.0, 5.316))), 5));

        // Barium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Barium145, Barium),
                new Tuple<>(Arrays.asList(null, null, Lanthanum145), Arrays.asList(0.0, 0.0, 4.93))), 5));

        // Lanthanum
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lanthanum145, Lanthanum),
                new Tuple<>(Arrays.asList(null, null, Cerium145), Arrays.asList(0.0, 0.0, 4.12))), 5));

        // Cerium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Cerium145, Cerium),
                new Tuple<>(Arrays.asList(null, null, Praseodymium145), Arrays.asList(0.0, 0.0, 2.54))), 5));

        // Praseodymium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Praseodymium145, Praseodymium),
                new Tuple<>(Arrays.asList(null, null, Neodymium145), Arrays.asList(0.0, 0.0, 1.805))), 5));

        // Neodymium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neodymium145, Neodymium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 5)); //Stable

        // Mercury
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mercury206, Mercury),
                new Tuple<>(Arrays.asList(null, null, Thallium206), Arrays.asList(0.0, 0.0, 1.308))), 5));

        // Thallium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium206, Thallium),
                new Tuple<>(Arrays.asList(null, null, Lead206), Arrays.asList(0.0, 0.0, 1.533))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium207, Thallium),
                new Tuple<>(Arrays.asList(null, null, Lead), Arrays.asList(0.0, 0.0, 1.423))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium208, Thallium),
                new Tuple<>(Arrays.asList(null, null, Lead208), Arrays.asList(0.0, 0.0, 5.001))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium210, Thallium),
                new Tuple<>(Arrays.asList(null, null, Lead210), Arrays.asList(0.0, 0.0, 5.484))), 5));

        // Lead
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead206, Lead),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 0)); // Stable
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead208, Lead),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 0)); // Stable
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead209, Lead),
                new Tuple<>(Arrays.asList(null, null, Bismuth209), Arrays.asList(0.0, 0.0, 0.644))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead210, Lead),
                new Tuple<>(Arrays.asList(Mercury206, null, Bismuth210), Arrays.asList(3.792, 0.0, 0.064))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead211, Lead),
                new Tuple<>(Arrays.asList(null, null, Bismuth211), Arrays.asList(0.0, 0.0, 1.367))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead212, Lead),
                new Tuple<>(Arrays.asList(null, null, Bismuth212), Arrays.asList(0.0, 0.0, 0.574))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead214, Lead),
                new Tuple<>(Arrays.asList(null, null, Bismuth214), Arrays.asList(0.0, 0.0, 1.024))), 5));

        // Bismuth
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth209, Bismuth),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 0)); // Stable
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth211, Bismuth),
                new Tuple<>(Arrays.asList(Thallium207, null, Polonium211), Arrays.asList(6.751, 0.0, 0.597))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth212, Bismuth),
                new Tuple<>(Arrays.asList(Thallium208, null, Polonium212), Arrays.asList(6.027, 0.0, 2.245))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth214, Bismuth),
                new Tuple<>(Arrays.asList(Thallium210, null, Polonium214), Arrays.asList(5.617, 0.0, 3.272))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth215, Bismuth),
                new Tuple<>(Arrays.asList(null, null, Polonium215), Arrays.asList(0.0, 0.0, 2.25))), 5));

        // Polonium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium211, Polonium),
                new Tuple<>(Arrays.asList(Lead, null, null), Arrays.asList(7.595, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium212, Polonium),
                new Tuple<>(Arrays.asList(Lead208, null, null), Arrays.asList(8.78, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium213, Polonium),
                new Tuple<>(Arrays.asList(Lead209, null, null), Arrays.asList(8.537, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium214, Polonium),
                new Tuple<>(Arrays.asList(Lead210, null, null), Arrays.asList(7.69, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium215, Polonium),
                new Tuple<>(Arrays.asList(Lead211, null, Astatine215), Arrays.asList(7.526, 0.0, 0.721))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium216, Polonium),
                new Tuple<>(Arrays.asList(Lead212, null, null), Arrays.asList(6.78, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium218, Polonium),
                new Tuple<>(Arrays.asList(Lead214, null, Astatine218), Arrays.asList(6.115, 0.0, 0.26))), 5));

        // Astatine
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Astatine215, Astatine),
                new Tuple<>(Arrays.asList(Bismuth211, null, null), Arrays.asList(8.178, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Astatine218, Astatine),
                new Tuple<>(Arrays.asList(Bismuth214, null, Radon218), Arrays.asList(6.874, 0.0, 2.883))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Astatine219, Astatine),
                new Tuple<>(Arrays.asList(Bismuth215, null, Radon219), Arrays.asList(6.39, 0.0, 1.7))), 5));

        // Radon
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon217, Radon),
                new Tuple<>(Arrays.asList(Polonium213, null, null), Arrays.asList(7.889, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon218, Radon),
                new Tuple<>(Arrays.asList(Polonium213, null, null), Arrays.asList(7.889, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon219, Radon),
                new Tuple<>(Arrays.asList(Polonium215, null, null), Arrays.asList(6.946, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon220, Radon),
                new Tuple<>(Arrays.asList(Polonium216, null, null), Arrays.asList(6.405, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon, null),
                new Tuple<>(Arrays.asList(Polonium214, null, null), Arrays.asList(7.263, 0.0, 0.0))), 5));

        // Francium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Francium221, Francium),
                new Tuple<>(Arrays.asList(Astatine217, null, Radium221), Arrays.asList(6.458, 0.0, 2.033))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Francium223, Francium),
                new Tuple<>(Arrays.asList(Astatine219, null, Radium223), Arrays.asList(5.43, 0.0, 1.149))), 5));

        // Radium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium221, Radium),
                new Tuple<>(Arrays.asList(Radon217, null, null), Arrays.asList(6.886, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium223, Radium),
                new Tuple<>(Arrays.asList(Radon219, null, null), Arrays.asList(5.979, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium224, Radium),
                new Tuple<>(Arrays.asList(Radon220, null, null), Arrays.asList(5.789, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium225, Radium),
                new Tuple<>(Arrays.asList(null, null, Actinium225), Arrays.asList(0.0, 0.0, 0.357))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium, null),
                new Tuple<>(Arrays.asList(Radon, null, null), Arrays.asList(4.871, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium227, Radium),
                new Tuple<>(Arrays.asList(null, null, Actinium227), Arrays.asList(0.0, 0.0, 1.325))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium228, Radium),
                new Tuple<>(Arrays.asList(null, null, Actinium228), Arrays.asList(0.0, 0.0, 0.046))), 5));

        // Actinium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Actinium225, Actinium),
                new Tuple<>(Arrays.asList(Francium221, null, null), Arrays.asList(5.935, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Actinium227, Actinium),
                        new Tuple<>(Arrays.asList(Francium223, null, Thorium227), Arrays.asList(5.563, 0.0, 0.045))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Actinium228, Actinium),
                new Tuple<>(Arrays.asList(null, null, Thorium228), Arrays.asList(0.0, 0.0, 2.127))), 5));

        // Thorium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium227, Thorium),
                new Tuple<>(Arrays.asList(Radium223, null, null), Arrays.asList(6.146, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium228, Thorium),
                new Tuple<>(Arrays.asList(Radium224, null, null), Arrays.asList(5.52, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium229, Thorium),
                new Tuple<>(Arrays.asList(Radium225, null, null), Arrays.asList(5.168, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium231, Thorium),
                new Tuple<>(Arrays.asList(Radium227, null, Protactinium), Arrays.asList(4.231, 0.0, 0.398))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium230, Thorium),
                new Tuple<>(Arrays.asList(Radium226, null, null), Arrays.asList(4.77, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium234, Thorium),
                new Tuple<>(Arrays.asList(null, null, Protactinium234), Arrays.asList(0.0, 0.0, 0.273))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium, null),
                new Tuple<>(Arrays.asList(Radium228, null, null), Arrays.asList(4.083, 0.0, 0.0))), 5));


        // Protactinium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium229, Protactinium),
                new Tuple<>(Arrays.asList(Actinium225, Thorium229, null), Arrays.asList(5.841, 0.316, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium, null),
                new Tuple<>(Arrays.asList(Actinium227, null, null), Arrays.asList(5.149, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium232, Protactinium),
                new Tuple<>(Arrays.asList(null, Thorium, Uranium232), Arrays.asList(0.0, 0.495, 1.337))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium234, Protactinium),
                new Tuple<>(Arrays.asList(null, null, Uranium234), Arrays.asList(0.0, 0.0, 2.197))), 5));

        // Uranium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium232, Uranium238),
                new Tuple<>(Arrays.asList(Thorium228, null, null), Arrays.asList(5.414, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium233, Uranium238),
                new Tuple<>(Arrays.asList(Thorium229, null, null), Arrays.asList(4.824, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium234, Uranium238),
                new Tuple<>(Arrays.asList(Thorium230, null, null), Arrays.asList(4.774, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium235, Uranium238),
                new Tuple<>(Arrays.asList(Thorium231, null, null), Arrays.asList(4.398, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium236, Uranium238),
                new Tuple<>(Arrays.asList(Thorium, null, null), Arrays.asList(4.494, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium237, Uranium238),
                new Tuple<>(Arrays.asList(null, null, Neptunium), Arrays.asList(0.0, 0.0, 0.519))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium238, null),
                new Tuple<>(Arrays.asList(Thorium234, null, null), Arrays.asList(4.27, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium240, Uranium238),
                new Tuple<>(Arrays.asList(null, null, Neptunium240), Arrays.asList(0.0, 0.0, 0.338))), 6));

        // Neptunium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium233, Neptunium), new Tuple<>(
                Arrays.asList(Protactinium229, Uranium233, null), Arrays.asList(5.83, 1.23, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium236, Neptunium), new Tuple<>(
                        Arrays.asList(Protactinium232, Uranium236, Plutonium236), Arrays.asList(5.02, 0.94, 0.49))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium, null),
                new Tuple<>(Arrays.asList(Thorium231, null, null), Arrays.asList(4.398, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium239, Neptunium),
                new Tuple<>(Arrays.asList(null, null, Plutonium239), Arrays.asList(0.0, 0.0, 0.6))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium240, Neptunium),
                new Tuple<>(Arrays.asList(null, null, Plutonium240), Arrays.asList(0.0, 0.0, 2.2))), 6));

        // Plutonium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium236, Plutonium244),
                new Tuple<>(Arrays.asList(Uranium232, null, null), Arrays.asList(5.867, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium237, Plutonium244),
                new Tuple<>(Arrays.asList(Uranium233, Neptunium, null), Arrays.asList(5.75, 0.22, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium239, Plutonium244),
                new Tuple<>(Arrays.asList(Uranium235, null, null), Arrays.asList(5.245, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium240, Plutonium244),
                new Tuple<>(Arrays.asList(Uranium236, null, null), Arrays.asList(5.256, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium241, Plutonium244),
                        new Tuple<>(Arrays.asList(Uranium237, null, Americium241), Arrays.asList(5.41, 0.0, 0.021))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium242, Plutonium244),
                new Tuple<>(Arrays.asList(Uranium238, null, null), Arrays.asList(4.984, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium243, Plutonium244),
                new Tuple<>(Arrays.asList(null, null, Americium243), Arrays.asList(0.0, 0.0, 0.579))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium244, null),
                new Tuple<>(Arrays.asList(Uranium240, null, null), Arrays.asList(4.666, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium246, Plutonium244),
                new Tuple<>(Arrays.asList(null, null, Americium246), Arrays.asList(0.0, 0.0, 0.401))), 6));

        // Americium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium237, Americium),
                new Tuple<>(Arrays.asList(Neptunium233, Plutonium237, null), Arrays.asList(6.25, 1.73, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium241, Americium),
                new Tuple<>(Arrays.asList(Neptunium, null, null), Arrays.asList(5.486, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium243, Americium),
                new Tuple<>(Arrays.asList(Neptunium239, null, null), Arrays.asList(5.438, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium244, Americium),
                new Tuple<>(Arrays.asList(null, null, Curium244), Arrays.asList(0.0, 0.0, 1.428))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium245, Americium),
                new Tuple<>(Arrays.asList(null, null, Curium245), Arrays.asList(0.0, 0.0, 0.894))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium246, Americium),
                new Tuple<>(Arrays.asList(null, null, Curium246), Arrays.asList(0.0, 0.0, 2.3))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium247, Americium),
                new Tuple<>(Arrays.asList(null, null, Curium247), Arrays.asList(0.0, 0.0, 1.7))), 6));

        // Curium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium241, Curium),
                new Tuple<>(Arrays.asList(Plutonium237, Americium241, null), Arrays.asList(6.185, 0.767, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium243, Curium),
                new Tuple<>(Arrays.asList(Plutonium239, Americium243, null), Arrays.asList(6.169, 0.009, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium244, Curium),
                new Tuple<>(Arrays.asList(Plutonium240, null, null), Arrays.asList(5.902, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium245, Curium),
                new Tuple<>(Arrays.asList(Plutonium241, null, null), Arrays.asList(5.623, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium246, Curium),
                new Tuple<>(Arrays.asList(Plutonium242, null, null), Arrays.asList(5.745, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium247, Curium),
                new Tuple<>(Arrays.asList(Plutonium243, null, null), Arrays.asList(5.353, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium248, Curium),
                new Tuple<>(Arrays.asList(Plutonium244, null, null), Arrays.asList(5.126, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium249, Curium),
                new Tuple<>(Arrays.asList(null, null, Berkelium249), Arrays.asList(0.0, 0.0, 0.9))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium250, Curium),
                new Tuple<>(Arrays.asList(Plutonium246, null, Berkelium250), Arrays.asList(5.169, 0.0, 0.9))), 6));

        // Berkelium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium241, Berkelium),
                new Tuple<>(Arrays.asList(Americium237, Curium241, null), Arrays.asList(6.724, 0.969, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium245, Berkelium),
                new Tuple<>(Arrays.asList(Americium241, Curium245, null), Arrays.asList(6.455, 0.81, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium247, Berkelium),
                new Tuple<>(Arrays.asList(Americium243, null, null), Arrays.asList(5.889, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium248, Berkelium),
                new Tuple<>(Arrays.asList(Americium244, Curium248, Californium248), Arrays.asList(5.803, 0.717, 0.87))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium249, Berkelium),
                new Tuple<>(Arrays.asList(Americium245, null, Californium249), Arrays.asList(5.526, 0.0, 0.125))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium250, Berkelium),
                new Tuple<>(Arrays.asList(null, null, Californium250), Arrays.asList(0.0, 0.0, 1.78))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Berkelium251, Berkelium),
                new Tuple<>(Arrays.asList(Americium247, null, Californium251), Arrays.asList(5.57, 0.0, 1.093))), 6));

        // Californium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium245, Californium),
                new Tuple<>(Arrays.asList(Curium241, Berkelium245, null), Arrays.asList(7.256, 1.569, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium247, Californium),
                new Tuple<>(Arrays.asList(Curium243, Berkelium247, null), Arrays.asList(6.527, 0.646, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium248, Californium),
                new Tuple<>(Arrays.asList(Curium244, null, null), Arrays.asList(6.361, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium249, Californium),
                new Tuple<>(Arrays.asList(Curium245, null, null), Arrays.asList(6.295, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium250, Californium),
                new Tuple<>(Arrays.asList(Curium246, null, null), Arrays.asList(6.128, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium251, Californium),
                new Tuple<>(Arrays.asList(Curium247, null, null), Arrays.asList(6.176, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium252, Californium),
                new Tuple<>(Arrays.asList(Curium248, null, null), Arrays.asList(6.217, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium253, Californium),
                new Tuple<>(Arrays.asList(Curium249, null, Einsteinium253), Arrays.asList(6.124, 0.0, 0.285))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Californium254, Californium),
                new Tuple<>(Arrays.asList(Curium250, null, null), Arrays.asList(5.926, 0.0, 0.0))), 6));

        // Einsteinium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium245, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium241, Californium245, null), Arrays.asList(7.909, 3.05, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium249, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium245, Californium249, null), Arrays.asList(6.94, 1.45, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium251, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium247, Californium251, null), Arrays.asList(6.597, 0.367, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium252, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium248, Californium252, null), Arrays.asList(6.76, 1.26, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium253, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium249, null, null), Arrays.asList(6.739, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium254, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium250, Californium254, Mendelevium254), Arrays.asList(6.618, 0.654, 1.09))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium255, Einsteinium),
                new Tuple<>(Arrays.asList(Berkelium251, null, Fermium255), Arrays.asList(6.436, 0.0, 0.288))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Einsteinium256, Einsteinium),
                new Tuple<>(Arrays.asList(null, null, Fermium256), Arrays.asList(0.0, 0.0, 1.67))), 6));

        // Fermium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium249, Fermium),
                new Tuple<>(Arrays.asList(Californium245, Einsteinium249, null), Arrays.asList(7.81, 2.44, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium251, Fermium),
                new Tuple<>(Arrays.asList(Californium247, Einsteinium251, null), Arrays.asList(7.425, 1.474, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium252, Fermium),
                new Tuple<>(Arrays.asList(Californium248, null, null), Arrays.asList(7.153, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium253, Fermium),
                new Tuple<>(Arrays.asList(Californium249, Einsteinium253, null), Arrays.asList(7.197, 0.333, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium254, Fermium),
                new Tuple<>(Arrays.asList(Californium250, null, null), Arrays.asList(7.037, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium255, Fermium),
                new Tuple<>(Arrays.asList(Californium251, null, null), Arrays.asList(7.451, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium256, Fermium),
                new Tuple<>(Arrays.asList(Californium252, null, null), Arrays.asList(7.027, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium257, Fermium),
                new Tuple<>(Arrays.asList(Californium253, null, null), Arrays.asList(6.864, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Fermium260, Fermium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Mendelevium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium249, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium245, Fermium249, null), Arrays.asList(8.46, 3.7, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium253, Mendelevium),
                new Tuple<>(Arrays.asList(null, Fermium253, null), Arrays.asList(0.0, 1.96, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium254, Mendelevium),
                new Tuple<>(Arrays.asList(null, Fermium254, null), Arrays.asList(0.0, 2.68, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium255, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium251, Fermium255, null), Arrays.asList(7.907, 1.043, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium256, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium252, Fermium256, null), Arrays.asList(7.897, 2.13, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium258, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium254, null, null), Arrays.asList(7.241, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium259, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium255, null, null), Arrays.asList(7.1, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Mendelevium260, Mendelevium),
                new Tuple<>(Arrays.asList(Einsteinium256, Fermium260, Nobelium260), Arrays.asList(7.0, 2.0, 1.0))), 6));

        //Nobelium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium253, Nobelium),
                new Tuple<>(Arrays.asList(Fermium249, Mendelevium253, null), Arrays.asList(8.44, 3.2, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium255, Nobelium),
                new Tuple<>(Arrays.asList(Fermium251, Mendelevium255, null), Arrays.asList(8.445, 2.012, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium256, Nobelium),
                new Tuple<>(Arrays.asList(Fermium252, null, null), Arrays.asList(8.581, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium257, Nobelium),
                new Tuple<>(Arrays.asList(Fermium253, null, null), Arrays.asList(8.45, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium258, Nobelium),
                new Tuple<>(Arrays.asList(Fermium254, null, null), Arrays.asList(8.2, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium259, Nobelium),
                new Tuple<>(Arrays.asList(Fermium255, Mendelevium259, null), Arrays.asList(7.91, 0.5, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium260, Nobelium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nobelium262, Nobelium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Lawrencium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium253, Lawrencium),
                new Tuple<>(Arrays.asList(Mendelevium249, null, null), Arrays.asList(8.99, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium257, Lawrencium),
                new Tuple<>(Arrays.asList(Mendelevium253, null, null), Arrays.asList(9.01, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium258, Lawrencium),
                new Tuple<>(Arrays.asList(Mendelevium254, Nobelium258, null), Arrays.asList(8.9, 3.38, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium259, Lawrencium),
                new Tuple<>(Arrays.asList(Mendelevium255, Nobelium259, null), Arrays.asList(8.67, 1.81, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium260, Lawrencium),
                new Tuple<>(Arrays.asList(Mendelevium256, Nobelium260, null), Arrays.asList(8.31, 2.47, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium261, Lawrencium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lawrencium262, Lawrencium),
                new Tuple<>(Arrays.asList(null, Nobelium262, null), Arrays.asList(0.0, 2.1, 0.0))), 6));

        // Rutherfordium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium257, Rutherfordium),
                new Tuple<>(Arrays.asList(Nobelium253, Lawrencium257, null), Arrays.asList(9.25, 3.4, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium259, Rutherfordium),
                new Tuple<>(Arrays.asList(Nobelium255, null, null), Arrays.asList(9.11, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium260, Rutherfordium),
                new Tuple<>(Arrays.asList(Nobelium256, Lawrencium260, null), Arrays.asList(9.0, 2.45, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium261, Rutherfordium),
                new Tuple<>(Arrays.asList(Nobelium257, Lawrencium261, null), Arrays.asList(8.81, 1.8, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium262, Rutherfordium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6)); // SF
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium266, Rutherfordium),
                new Tuple<>(Arrays.asList(Nobelium262, null, null), Arrays.asList(7.26, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium267, Rutherfordium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6)); // SF
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Rutherfordium269, Rutherfordium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6)); // SF

        // Dubnium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium257, Dubnium),
                new Tuple<>(Arrays.asList(Lawrencium253, Rutherfordium257, null), Arrays.asList(9.31, 4.3, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium260, Dubnium),
                new Tuple<>(Arrays.asList(Lawrencium256, Rutherfordium260, null), Arrays.asList(9.37, 4.6, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium261, Dubnium),
                new Tuple<>(Arrays.asList(Lawrencium257, null, null), Arrays.asList(9.27, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium262, Dubnium),
                new Tuple<>(Arrays.asList(Lawrencium258, Rutherfordium262, null), Arrays.asList(9.21, 4.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium263, Dubnium),
                new Tuple<>(Arrays.asList(Lawrencium259, null, null), Arrays.asList(9.03, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium266, Dubnium),
                new Tuple<>(Arrays.asList(null, Rutherfordium266, null), Arrays.asList(0.0, 3.4, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium268, Dubnium),
                new Tuple<>(Arrays.asList(null, Rutherfordium266, null), Arrays.asList(0.0, 3.4, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium270, Dubnium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Dubnium274, Dubnium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Seaborgium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Seaborgium264, Seaborgium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6)); // SF
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Seaborgium265, Seaborgium),
                new Tuple<>(Arrays.asList(Rutherfordium261, null, null), Arrays.asList(9.3, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Seaborgium266, Seaborgium),
                new Tuple<>(Arrays.asList(Rutherfordium262, null, null), Arrays.asList(9.2, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Seaborgium271, Seaborgium),
                new Tuple<>(Arrays.asList(Rutherfordium267, null, null), Arrays.asList(9.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Seaborgium273, Seaborgium),
                new Tuple<>(Arrays.asList(Rutherfordium269, null, null), Arrays.asList(8.9, 0.0, 0.0))), 6));

        // Bohrium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium261, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium257, null, null), Arrays.asList(10.56, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium264, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium260, null, null), Arrays.asList(9.88, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium267, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium263, null, null), Arrays.asList(9.18, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium270, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium266, null, null), Arrays.asList(8.42, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium272, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium268, null, null), Arrays.asList(8.1, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium274, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium270, null, null), Arrays.asList(7.88, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bohrium278, Bohrium),
                new Tuple<>(Arrays.asList(Dubnium274, null, null), Arrays.asList(7.4, 0.0, 0.0))), 6));

        // Hassium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Hassium268, Hassium),
                new Tuple<>(Arrays.asList(Seaborgium264, null, null), Arrays.asList(9.897, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Hassium269, Hassium),
                new Tuple<>(Arrays.asList(Seaborgium265, null, null), Arrays.asList(9.34, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Hassium270, Hassium),
                new Tuple<>(Arrays.asList(Seaborgium266, null, null), Arrays.asList(9.07, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Hassium275, Hassium),
                new Tuple<>(Arrays.asList(Seaborgium271, null, null), Arrays.asList(9.44, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Hassium277, Hassium),
                new Tuple<>(Arrays.asList(Seaborgium273, null, null), Arrays.asList(9.05, 0.0, 0.0))), 6));

        // Meitnerium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Meitnerium274, Meitnerium),
                new Tuple<>(Arrays.asList(Bohrium270, null, null), Arrays.asList(10.5, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Meitnerium276, Meitnerium),
                new Tuple<>(Arrays.asList(Bohrium272, null, null), Arrays.asList(9.8, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Meitnerium278, Meitnerium),
                new Tuple<>(Arrays.asList(Bohrium274, null, null), Arrays.asList(9.1, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Meitnerium282, Meitnerium),
                new Tuple<>(Arrays.asList(Bohrium278, null, null), Arrays.asList(8.5, 0.0, 0.0))), 6));

        // Darmstadtium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Darmstadtium279, Darmstadtium),
                new Tuple<>(Arrays.asList(Hassium275, null, null), Arrays.asList(9.6, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Darmstadtium281, Darmstadtium),
                new Tuple<>(Arrays.asList(Hassium277, null, null), Arrays.asList(9.0, 0.0, 0.0))), 6));

        // Roentgenium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Roentgenium280, Roentgenium),
                new Tuple<>(Arrays.asList(Meitnerium276, null, null), Arrays.asList(10.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Roentgenium281, Roentgenium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6)); // SF
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Roentgenium282, Roentgenium),
                new Tuple<>(Arrays.asList(Meitnerium278, null, null), Arrays.asList(10.4, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Roentgenium286, Roentgenium),
                new Tuple<>(Arrays.asList(Meitnerium282, null, null), Arrays.asList(10.7, 0.0, 0.0))), 6));

        // Copernicium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Copernicium282, Copernicium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Copernicium283, Copernicium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Copernicium285, Copernicium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Nihonium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nihonium284, Nihonium),
                new Tuple<>(Arrays.asList(Roentgenium280, null, null), Arrays.asList(10.3, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nihonium285, Nihonium),
                new Tuple<>(Arrays.asList(Roentgenium281, null, null), Arrays.asList(10.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nihonium286, Nihonium),
                new Tuple<>(Arrays.asList(Roentgenium282, null, null), Arrays.asList(9.7, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Nihonium287, Nihonium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Flerovium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Flerovium286, Flerovium),
                new Tuple<>(Arrays.asList(Copernicium282, null, null), Arrays.asList(10.7, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Flerovium288, Flerovium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Flerovium289, Flerovium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Flerovium290, Flerovium),
                new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 6));

        // Moscovium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Moscovium288, Moscovium),
                new Tuple<>(Arrays.asList(Nihonium284, null, null), Arrays.asList(11.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Moscovium289, Moscovium),
                new Tuple<>(Arrays.asList(Nihonium285, null, null), Arrays.asList(10.6, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Moscovium290, Moscovium),
                new Tuple<>(Arrays.asList(Nihonium286, null, null), Arrays.asList(10.3, 0.0, 0.0))), 6));

        // Livermorium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Livermorium290, Livermorium),
                new Tuple<>(Arrays.asList(Flerovium286, null, null), Arrays.asList(11.0, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Livermorium291, Livermorium),
                new Tuple<>(Arrays.asList(Flerovium287, null, null), Arrays.asList(10.89, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Livermorium292, Livermorium),
                new Tuple<>(Arrays.asList(Flerovium288, null, null), Arrays.asList(10.8, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Livermorium293, Livermorium),
                new Tuple<>(Arrays.asList(Flerovium289, null, null), Arrays.asList(10.67, 0.0, 0.0))), 6));

        // Tennessine
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Tennessine293, Tennessine),
                new Tuple<>(Arrays.asList(Moscovium289, null, null), Arrays.asList(11.03, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Tennessine294, Tennessine),
                new Tuple<>(Arrays.asList(Moscovium290, null, null), Arrays.asList(10.81, 0.0, 0.0))), 6));

        // Oganesson
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Oganesson294, Oganesson),
                new Tuple<>(Arrays.asList(Livermorium290, null, null), Arrays.asList(11.65, 0.0, 0.0))), 6));

        for (Tuple<Tuple<Tuple<Material, Material>, Tuple<List<Material>, List<Double>>>, Integer> isotopes : nuclearMaterialsPre) {
            Material isotope = isotopes.getFirst().getFirst().getFirst();
            Material stableIsotope = isotopes.getFirst().getFirst().getSecond();
            List<Material> products = isotopes.getFirst().getSecond().getFirst();
            List<Double> energies = isotopes.getFirst().getSecond().getSecond();
            isotope.setProperty(TGPropertyKeys.NUCLEAR_PROPERTY,
                    new NuclearProperty(stableIsotope, products.get(0), energies.get(0), products.get(1),
                            energies.get(1), products.get(2), energies.get(2), isotopes.getSecond()));
            nuclearMaterials.add(isotope);
        }
    }
}
