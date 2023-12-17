package gregicality.legacy.loaders.recipe.singleblock;

import gregicality.legacy.api.unification.properties.NuclearProperty;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import net.minecraft.item.ItemStack;

import static gregicality.legacy.api.recipe.GCYLRRecipeMaps.ISOTOPIC_STABILIZER_RECIPES;
import static gregicality.legacy.api.unification.GCYLRIsotopes.nuclearMaterials;
import static gregicality.legacy.api.unification.properties.GCYLRPropertyKey.NUCLEAR;
import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.V;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public final class IsotopicStabilizerRecipes {
    private IsotopicStabilizerRecipes() {}

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
            ItemStack output = OreDictUnifier.get(ingot,prop.baseMat());
            if(output.isEmpty()){
                continue;
            }
            long neutrons = Math.abs(mat.getNeutrons() - prop.baseMat().getNeutrons());
            ISOTOPIC_STABILIZER_RECIPES.recipeBuilder()
                .inputs(input)
                .outputs(output)
                .duration((int) (20 * neutrons))
                .EUt((int) V[LV])
                .buildAndRegister();
        }
    }
}
