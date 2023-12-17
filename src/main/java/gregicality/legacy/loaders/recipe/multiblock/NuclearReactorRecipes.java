package gregicality.legacy.loaders.recipe.multiblock;

import gregicality.legacy.api.unification.GCYLRIsotopes;
import gregicality.legacy.api.unification.properties.NuclearProperty;
import gregicality.legacy.common.GCYLRConfigHolder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import net.minecraft.util.Tuple;

import java.util.List;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.BREEDER_REACTOR_RECIPES;
import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.FISSION_REACTOR_RECIPES;
import static gregicality.legacy.api.unification.GCYLRIsotopes.getBetaMinusDecayMaterial;
import static gregicality.legacy.api.unification.GCYLRIsotopes.nuclearMaterials;
import static gregicality.legacy.api.unification.properties.GCYLRPropertyKey.NUCLEAR;
import static gregtech.api.unification.material.properties.PropertyKey.FLUID;
import static gregtech.api.unification.material.properties.PropertyKey.INGOT;
import static gregtech.api.unification.ore.OrePrefix.ingot;
import static gregtech.api.unification.ore.OrePrefix.nugget;

public class NuclearReactorRecipes {
    public static void init() {
        registerFissionRecipes();
        registerBreederRecipes();
    }

    private static void registerFissionRecipes() {
        for(Material mat : nuclearMaterials){
            NuclearProperty prop = mat.getProperty(NUCLEAR);
            if(prop == null){
                continue;
            }
            if(!prop.canBeFuel()){
                continue;
            }
            List<Material> materials;
            List<Tuple<List<Material>,Long>> fissionProducts = GCYLRIsotopes.getFissionProducts(mat);
            assert fissionProducts != null;
            for(Tuple<List<Material>,Long> fP : fissionProducts){
                materials = fP.getFirst();
                if(checkProperties(materials, GCYLRConfigHolder.machines.allowLiquidsInReprocessing)){
                    continue;
                }
                registerFissionRecipe(mat, prop.calculateDurationTier(), fissionProducts.indexOf(fP) + 1, materials, fP.getSecond(), GCYLRConfigHolder.machines.allowLiquidsInReprocessing);
            }
        }
    }

    private static void registerFissionRecipe(Material material, int dt, int cn, List<Material> materials, Long neutrons, boolean allowLiquids) {
        Material mat1 = materials.get(0);
        Material mat2 = materials.get(1);
        if(mat1.hasProperty(INGOT) && mat2.hasProperty(INGOT)){
            FISSION_REACTOR_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(ingot,material))
                    .circuitMeta(cn)
                    .outputs(OreDictUnifier.get(nugget,mat1,3),OreDictUnifier.get(nugget,mat2,3))
                    .duration(50 * dt)
                    .EUt((int) (100 * neutrons))
                    .buildAndRegister();
        }else if (mat1.hasProperty(INGOT) && allowLiquids) {
            FISSION_REACTOR_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(ingot,material))
                    .circuitMeta(cn)
                    .outputs(OreDictUnifier.get(nugget,mat1,3))
                    .fluidOutputs(mat2.getFluid(432))
                    .duration(50 * dt)
                    .EUt((int) (100 * neutrons))
                    .buildAndRegister();
        }else if (mat2.hasProperty(INGOT) && allowLiquids) {
            FISSION_REACTOR_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(ingot,material))
                    .circuitMeta(cn)
                    .outputs(OreDictUnifier.get(nugget,mat2,3))
                    .fluidOutputs(mat1.getFluid(432))
                    .duration(50 * dt)
                    .EUt((int) (100 * neutrons))
                    .buildAndRegister();
        }
    }

    private static void registerBreederRecipes() {
        for(Material mat : nuclearMaterials){
            for(Material mat2 : nuclearMaterials){
                NuclearProperty prop = mat.getProperty(NUCLEAR);
                NuclearProperty prop2 = mat2.getProperty(NUCLEAR);
                if(prop == null){
                    continue;
                }
                if(prop2 == null){
                    continue;
                }
                if(!prop.canBeFuel()){
                    continue;
                }
                if(!prop2.canBeFuel()){
                    continue;
                }
                if(prop2.betaMinusEnergy() <= 0){
                    continue;
                }
                List<Material> materials;
                long neutrons;
                List<Tuple<List<Material>,Long>> fissionProducts = GCYLRIsotopes.getFissionProducts(mat);
                assert fissionProducts != null;
                for(Tuple<List<Material>,Long> fP : fissionProducts){
                    materials = fP.getFirst();
                    neutrons = fP.getSecond();
                    if(checkProperties(materials, GCYLRConfigHolder.machines.allowLiquidsInReprocessing)){
                        continue;
                    }
                    if(neutrons <= 1){
                        continue;
                    }
                    registerBreederRecipe(mat, mat2, prop.calculateDurationTier(), fissionProducts.indexOf(fP) + 1, materials, neutrons, GCYLRConfigHolder.machines.allowLiquidsInReprocessing);
                }
            }
        }
    }

    private static void registerBreederRecipe(Material mat1, Material mat2, int dt, int cn, List<Material> materials, Long neutrons, boolean allowLiquids) {
        Material decayMat1 = materials.get(0);
        Material decayMat2 = materials.get(1);
        if (mat1 == mat2) {
            if (decayMat1.hasProperty(INGOT) && decayMat2.hasProperty(INGOT)) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1, 2))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, decayMat1, 3), OreDictUnifier.get(nugget, decayMat2, 3), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();
            }else if (decayMat1.hasProperty(INGOT) && allowLiquids) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1, 2))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, decayMat1, 3), OreDictUnifier.get(nugget, decayMat2, 3), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .fluidOutputs(decayMat2.getFluid(432))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();
            }else if (decayMat2.hasProperty(INGOT) && allowLiquids) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1, 2))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, decayMat1, 3), OreDictUnifier.get(nugget, decayMat2, 3), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .fluidOutputs(decayMat1.getFluid(432))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();

            }
        }
        else {
            if (decayMat1.hasProperty(INGOT) && decayMat2.hasProperty(INGOT)) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1), OreDictUnifier.get(ingot, mat2))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, mat1, 3), OreDictUnifier.get(nugget, mat2, 3), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();
            }else if (decayMat1.hasProperty(INGOT) && allowLiquids) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1), OreDictUnifier.get(ingot, mat2), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, decayMat1, 3))
                        .fluidOutputs(decayMat2.getFluid(432))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();
            }else if (decayMat2.hasProperty(INGOT) && allowLiquids) {
                BREEDER_REACTOR_RECIPES.recipeBuilder()
                        .inputs(OreDictUnifier.get(ingot, mat1), OreDictUnifier.get(ingot, mat2), OreDictUnifier.get(ingot, getBetaMinusDecayMaterial(mat2)))
                        .circuitMeta(cn)
                        .outputs(OreDictUnifier.get(nugget, decayMat2, 3))
                        .fluidOutputs(decayMat1.getFluid(432))
                        .duration(50 * dt)
                        .EUt((int) (100 * neutrons))
                        .buildAndRegister();
            }
        }
    }

    private static boolean checkProperties(List<Material> materials, boolean allowFluids) {
        if(allowFluids){
            for(Material material : materials){
                if(!material.hasProperty(INGOT) && !material.hasProperty(FLUID)){
                    return true;
                }
            }
        }else{
            for(Material material : materials){
                if(!material.hasProperty(INGOT)){
                    return true;
                }
            }
        }
        return false;
    }
}
