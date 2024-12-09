package gregsjourney.utils;

import gregsjourney.api.unification.material.GJNuclearMaterials;
import gregsjourney.common.GJConfigHolder;
import gregtech.api.unification.material.Material;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static gregsjourney.utils.GJUtil.generateCombinations;
import static gregsjourney.utils.MathUtil.findCombinations;

public class GJNuclearUtil {

    public static List<Material> nuclearMaterials = new ArrayList<>();
    protected static double massNeutron = 1.00866491595;
    protected static double massProton = 1.007276466812;
    protected static double massElectron = 5.48579909044e-4;
    protected static double mev_per_u = 931.5;

    public static double fissionCalculator(Material core, List<Material> products) {
        int totalProtonsProducts = 0;
        for (Material product : products) {
            totalProtonsProducts += product.getProtons();
        }
        GJLog.logger.debug("Products: " + products);
        GJLog.logger.debug("Amount Protons Core: " + core.getProtons());
        GJLog.logger.debug("Amount Protons Products: " + totalProtonsProducts);
        if (core.getProtons() != totalProtonsProducts) {
            return -1;
        }

        int totalNeutronsProducts = 0;
        for (Material product : products) {
            totalNeutronsProducts += product.getNeutrons();
        }

        long freeNeutrons = core.getNeutrons() + 1 - totalNeutronsProducts;

        double coreMass = nucleusMass(core.getProtons() + core.getNeutrons(), core.getProtons());
        double productsMass = 0.0;
        for (Material product : products) {
            productsMass += nucleusMass(product.getProtons() + product.getNeutrons(), product.getProtons());
        }

        double freeNeutronsMass = (freeNeutrons - 1) * massNeutron;

        double massDefect = coreMass - productsMass - freeNeutronsMass;

        return massDefect * mev_per_u;
    }

    private static double nucleusMass(long A, long Z){
        double B = semf(A, Z);
        long N = A - Z;
        return Z * (massProton + massElectron) + N * massNeutron - B / mev_per_u;
    }

    private static double semf(long A, long Z){
        double a_v = 15.75;
        double a_s = 17.8;
        double a_c = 0.711;
        double a_a = 23.7;
        double a_p = 11.2;
        long N = A - Z;

        double delta;

        if (A % 2 == 0){
            if (Z % 2 == 0){
                delta = a_p / Math.pow(A,0.5);
            } else {
                delta = -a_p / Math.pow(A,0.5);
            }
        } else {
            delta = 0;
        }

        return (a_v * A - a_s * Math.pow(A,2.0/3.0) - a_c * Z * (Z - 1) / Math.pow(A,1.0/3.0) - a_a * Math.pow(N - Z, 2) / A + delta);
    }

    public static Material getFromProtons(long protons){
        for (Material material : nuclearMaterials) {
            if (material.getProtons() == protons) {
                return material;
            }
        }
        return null;
    }

    public static Material getFromProtonsAndNeutrons(long protons, long neutrons){
        for (Material material : nuclearMaterials) {
            if (material.getProtons() == protons && material.getNeutrons() == neutrons) {
                return material;
            }
        }
        return null;
    }

    private static List<Material> getAllIsotopes(long protons) {
        List<Material> materials = new ArrayList<>();
        for (Material material : nuclearMaterials) {
            if (material.getProtons() == protons) {
                materials.add(material);
            }
        }
        return materials;
    }

    private static List<Material> getAllIsotopes(Material material){
        return getAllIsotopes(material.getProtons());
    }

    public static List<Tuple<List<Material>,Double>> getFissionReactions(Material material){
        List<Tuple<List<Material>,Double>> reactions = new ArrayList<>();
        List<List<Integer>> currentCombinations;
        List<List<List<Material>>> currentIsotopes;
        List<List<Material>> allIsotopes;
        double fissionEnergy;
        Material lightestIsotope = GJNuclearMaterials.Krypton88;
        for (int i = 2; i <= GJConfigHolder.options.maxFuelSplit; i++) {
            currentCombinations = findCombinations(material.getProtons(), i, lightestIsotope.getProtons());
            currentIsotopes = currentCombinations.stream().map(l -> l.stream().map(GJNuclearUtil::getAllIsotopes).collect(Collectors.toList())).collect(Collectors.toList());
            allIsotopes = generateCombinations(currentIsotopes);
            for (List<Material> l : allIsotopes) {
                fissionEnergy = fissionCalculator(material, l);
                if (fissionEnergy > 0) {
                    reactions.add(new Tuple<>(l, fissionEnergy));
                }
            }
            currentIsotopes.clear();
        }
        reactions.sort((r1, r2) -> Double.compare(r2.getSecond(), r1.getSecond()));
        return reactions.stream().limit(Math.min(GJConfigHolder.options.maxFissionReactions, 24)).collect(Collectors.toList());
    }

    public static Material getAlphaDecayMaterial(Material material) {
        return getFromProtonsAndNeutrons(material.getProtons() - 2, material.getNeutrons() - 2);
    }

    public static Material getBetaPlusDecayMaterial(Material material) {
        return getFromProtonsAndNeutrons(material.getProtons() - 1, material.getNeutrons() + 1);
    }

    public static Material getBetaMinusDecayMaterial(Material material) {
        return getFromProtonsAndNeutrons(material.getProtons() + 1, material.getNeutrons() - 1);
    }

    public static Material getBreedingResultMaterial(Material material) {
        Material mat = getFromProtonsAndNeutrons(material.getProtons(), material.getNeutrons() + 1);
        if (mat == null) {
            return null;
        }
        return getBetaMinusDecayMaterial(mat);
    }

}
