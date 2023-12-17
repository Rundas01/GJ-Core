package gregicality.legacy.loaders.recipe.singleblock;

import gregicality.legacy.api.unification.properties.NuclearProperty;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import net.minecraft.item.ItemStack;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.DECAY_GENERATOR_FUELS;
import static gregicality.legacy.api.unification.GCYLRIsotopes.*;
import static gregicality.legacy.api.unification.properties.GCYLRPropertyKey.NUCLEAR;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public final class DecayGeneratorRecipes {
    private DecayGeneratorRecipes() {}

    public static void init() {
        for(Material mat : nuclearMaterials){
            if(mat == null){
                continue;
            }
            NuclearProperty prop = mat.getProperty(NUCLEAR);
            if(prop == null){
                continue;
            }
            ItemStack input = OreDictUnifier.get(ingot,mat);
            if(input.isEmpty()){
                continue;
            }
            ItemStack alphaOutput = OreDictUnifier.get(ingot,getAlphaDecayMaterial(mat));
            ItemStack betaPlusOutput = OreDictUnifier.get(ingot,getBetaPlusDecayMaterial(mat));
            ItemStack betaMinusOutput = OreDictUnifier.get(ingot,getBetaMinusDecayMaterial(mat));
            int hlt = prop.calculateHalfLifeTier();
            if((prop.alphaEnergy() > 0) && getAlphaDecayMaterial(mat) != null && (!alphaOutput.isEmpty())){
                DECAY_GENERATOR_FUELS.recipeBuilder()
                        .inputs(input)
                        .circuitMeta(1)
                        .outputs(alphaOutput)
                        .duration(20 * prop.calculateDurationTier())
                        .EUt((int) (V[LV] * hlt * prop.alphaEnergy()))
                        .buildAndRegister();
            }
            if((prop.betaPlusEnergy() > 0) && getBetaPlusDecayMaterial(mat) != null && (!betaPlusOutput.isEmpty())){
                DECAY_GENERATOR_FUELS.recipeBuilder()
                        .inputs(input)
                        .circuitMeta(2)
                        .outputs(betaPlusOutput)
                        .duration(20 * prop.calculateDurationTier())
                        .EUt((int) (V[LV] * hlt * prop.betaPlusEnergy()))
                        .buildAndRegister();
            }
            if((prop.betaMinusEnergy() > 0) && getBetaMinusDecayMaterial(mat) != null && (!betaMinusOutput.isEmpty())){
                DECAY_GENERATOR_FUELS.recipeBuilder()
                        .inputs(input)
                        .circuitMeta(3)
                        .outputs(betaMinusOutput)
                        .duration(20 * prop.calculateDurationTier())
                        .EUt((int) (V[LV] * hlt * prop.betaMinusEnergy()))
                        .buildAndRegister();
            }
        }
    }
}
