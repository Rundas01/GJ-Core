package gregsjourney.api.unification.material;

import gregsjourney.api.unification.property.GJMaterialFlags;
import gregsjourney.api.unification.property.GJPropertyKeys;
import gregsjourney.api.unification.property.NuclearProperty;
import gregsjourney.integration.forestry.ForestryModule;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.FluidUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import net.minecraft.util.Tuple;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregsjourney.api.unification.material.GJNuclearMaterials.*;
import static gregsjourney.utils.GJMaterialUtil.getTotalAtomAmounts;
import static gregsjourney.utils.GJNuclearUtil.nuclearMaterials;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD;

@ApiStatus.Internal
public class GJMaterialFlagAddition {

    private GJMaterialFlagAddition() {}
    public static void init() {
        FluidUnifier.registerFluid(Water.getFluid(), Water);
        FluidUnifier.registerFluid(Lava.getFluid(), Lava);
        ForestryModule.registerMaterialFlags();
        setupNuclearMaterials();
    }

    public static void initLate() {
        Water.addFlags(GJMaterialFlags.SOLVENT);
        ToolProperty prop = Flint.getProperty(PropertyKey.TOOL);
        prop.setShouldIgnoreCraftingTools(false);
        Barium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Selenium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Scandium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Thallium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Polonium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Astatine.setProperty(PropertyKey.INGOT, new IngotProperty());
        Francium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Radium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Actinium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Protactinium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Neptunium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Curium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Graphite.addFlags(GENERATE_ROD);
        Nickel.addFlags(GENERATE_ROD);
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE)) {
                changeOreMultiplier(material);
            }
        }
    }

    private static void setupNuclearMaterials() {
        List<Tuple<Tuple<Tuple<Material, Material>, Tuple<List<Material>, List<Double>>>, Integer>> nuclearMaterialsPre = new ArrayList<>();
        //Krypton
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Krypton88, Krypton), new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 5));

        //Barium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Barium145, Barium), new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 5));

        //Thallium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium207, Thallium), new Tuple<>(Arrays.asList(null, null, Lead207), Arrays.asList(0.0, 0.0, 1.423))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thallium208, Thallium), new Tuple<>(Arrays.asList(null, null, Lead208), Arrays.asList(0.0, 0.0, 5.001))), 5));

        //Lead
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead207, Lead), new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 0)); //Stable
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead208, Lead), new Tuple<>(Arrays.asList(null, null, null), Arrays.asList(0.0, 0.0, 0.0))), 0)); //Stable
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead211, Lead), new Tuple<>(Arrays.asList(null, null, Bismuth211), Arrays.asList(0.0, 0.0, 1.367))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Lead212, Lead), new Tuple<>(Arrays.asList(null, null, Bismuth212), Arrays.asList(0.0, 0.0, 0.574))), 5));

        //Bismuth
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth211, Bismuth), new Tuple<>(Arrays.asList(Thallium207, null, Polonium211), Arrays.asList(6.751, 0.0, 0.597))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth212, Bismuth), new Tuple<>(Arrays.asList(Thallium208, null, Polonium212), Arrays.asList(6.027, 0.0, 2.245))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Bismuth215, Bismuth), new Tuple<>(Arrays.asList(null, null, Polonium215), Arrays.asList(0.0, 0.0, 2.25))), 5));

        //Polonium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium211, Polonium), new Tuple<>(Arrays.asList(Lead207, null, null), Arrays.asList(7.595, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium212, Polonium), new Tuple<>(Arrays.asList(Lead208, null, null), Arrays.asList(8.78, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium215, Polonium), new Tuple<>(Arrays.asList(Lead211, null, Astatine215), Arrays.asList(7.526, 0.0, 0.721))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Polonium216, Polonium), new Tuple<>(Arrays.asList(Lead212, null, null), Arrays.asList(6.78, 0.0, 0.0))), 5));

        //Astatine
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Astatine215, Astatine), new Tuple<>(Arrays.asList(Bismuth211, null, null), Arrays.asList(8.178, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Astatine219, Astatine), new Tuple<>(Arrays.asList(Bismuth215, null, Radon219), Arrays.asList(6.39, 0.0, 1.7))), 5));

        //Radon
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon219, Radon), new Tuple<>(Arrays.asList(Polonium215, null, null), Arrays.asList(6.946, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radon220, Radon), new Tuple<>(Arrays.asList(Polonium216, null, null), Arrays.asList(6.405, 0.0, 0.0))), 5));

        //Francium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Francium223, Francium), new Tuple<>(Arrays.asList(Astatine219, null, Radium223), Arrays.asList(5.43, 0.0, 1.149))), 5));

        //Radium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium223, Radium), new Tuple<>(Arrays.asList(Radon219, null, null), Arrays.asList(5.979, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium224, Radium), new Tuple<>(Arrays.asList(Radon220, null, null), Arrays.asList(5.789, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium227, Radium), new Tuple<>(Arrays.asList(null, null, Actinium227), Arrays.asList(0.0, 0.0, 1.325))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Radium228, Radium), new Tuple<>(Arrays.asList(null, null, Actinium228), Arrays.asList(0.0, 0.0, 0.046))), 5));

        //Actinium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Actinium227, Actinium), new Tuple<>(Arrays.asList(Francium223, null, Thorium227), Arrays.asList(5.563, 0.0, 0.045))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Actinium228, Actinium), new Tuple<>(Arrays.asList(null, null, Thorium228), Arrays.asList(0.0, 0.0, 2.127))), 5));

        //Thorium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium227, Thorium), new Tuple<>(Arrays.asList(Radium223, null, null), Arrays.asList(6.146, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium228, Thorium), new Tuple<>(Arrays.asList(Radium224, null, null), Arrays.asList(5.52, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium232, Thorium), new Tuple<>(Arrays.asList(Radium228, null, null), Arrays.asList(4.083, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Thorium231, Thorium), new Tuple<>(Arrays.asList(Radium227, null, Protactinium231), Arrays.asList(4.231, 0.0, 0.398))), 5));

        //Protactinium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium231, Protactinium), new Tuple<>(Arrays.asList(Actinium227, null, null), Arrays.asList(5.149, 0.0, 0.0))), 5));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Protactinium232, Protactinium), new Tuple<>(Arrays.asList(null, Thorium232, Uranium232), Arrays.asList(0.0, 0.495, 1.337))), 5));

        //Uranium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium232, MetastableUranium), new Tuple<>(Arrays.asList(Thorium228, null, null), Arrays.asList(5.414, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium235, MetastableUranium), new Tuple<>(Arrays.asList(Thorium231, null, null), Arrays.asList(4.398, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium236, MetastableUranium), new Tuple<>(Arrays.asList(Thorium232, null, null), Arrays.asList(4.494, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Uranium237, MetastableUranium), new Tuple<>(Arrays.asList(null, null, Neptunium237), Arrays.asList(0.0, 0.0, 0.519))), 6));

        //Neptunium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium236, Neptunium), new Tuple<>(Arrays.asList(Protactinium232, Uranium236, Plutonium236), Arrays.asList(5.02, 0.94, 0.49))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium237, Neptunium), new Tuple<>(Arrays.asList(Thorium231, null, null), Arrays.asList(4.398, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Neptunium239, Neptunium), new Tuple<>(Arrays.asList(null, null, Plutonium239), Arrays.asList(0.0, 0.0, 0.6))), 6));

        //Plutonium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium236, MetastablePlutonium), new Tuple<>(Arrays.asList(Uranium232, null, null), Arrays.asList(5.867, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium239, MetastablePlutonium), new Tuple<>(Arrays.asList(Uranium235, null, null), Arrays.asList(5.245, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium241, MetastablePlutonium), new Tuple<>(Arrays.asList(Uranium237, null, Americium241), Arrays.asList(5.41, 0.0, 0.021))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Plutonium243, MetastablePlutonium), new Tuple<>(Arrays.asList(null, null, Americium243), Arrays.asList(0.0, 0.0, 0.579))), 6));

        //Americium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium241, Americium), new Tuple<>(Arrays.asList(Neptunium237, null, null), Arrays.asList(5.486, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Americium243, Americium), new Tuple<>(Arrays.asList(Neptunium239, null, null), Arrays.asList(5.438, 0.0, 0.0))), 6));

        //Curium
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium245, Curium), new Tuple<>(Arrays.asList(Plutonium241, null, null), Arrays.asList(5.623, 0.0, 0.0))), 6));
        nuclearMaterialsPre.add(new Tuple<>(new Tuple<>(new Tuple<>(Curium247, Curium), new Tuple<>(Arrays.asList(Plutonium243, null, null), Arrays.asList(5.353, 0.0, 0.0))), 6));

        //Berkelium

        //Californium

        //Einsteinium

        //Mendelevium

        //Fermium

        for (Tuple<Tuple<Tuple<Material, Material>, Tuple<List<Material>, List<Double>>>, Integer> isotopes : nuclearMaterialsPre) {
            Material isotope = isotopes.getFirst().getFirst().getFirst();
            Material stableIsotope = isotopes.getFirst().getFirst().getSecond();
            List<Material> products = isotopes.getFirst().getSecond().getFirst();
            List<Double> energies = isotopes.getFirst().getSecond().getSecond();
            isotope.setProperty(GJPropertyKeys.NUCLEAR_PROPERTY, new NuclearProperty(stableIsotope, products.get(0), energies.get(0), products.get(1), energies.get(1), products.get(2), energies.get(2), isotopes.getSecond()));
            nuclearMaterials.add(isotope);
        }
    }

    private static void changeOreMultiplier(Material material) {
        OreProperty property = material.getProperty(PropertyKey.ORE);
        property.setOreMultiplier(getTotalAtomAmounts(material));
    }
}
