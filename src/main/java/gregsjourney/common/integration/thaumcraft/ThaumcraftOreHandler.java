package gregsjourney.common.integration.thaumcraft;

import gregtech.api.unification.material.Material;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

import java.util.Arrays;
import java.util.List;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static thaumcraft.api.ThaumcraftApiHelper.makeCrystal;

public class ThaumcraftOreHandler {
    private ThaumcraftOreHandler(){}

    public static void init(){
        /*List<Material> ores = Arrays.asList(AirInfused,EarthInfused,FireInfused,WaterInfused,OrderInfused,EntropyInfused);
        List<ItemStack> crystals = Arrays.asList(makeCrystal(Aspect.AIR),makeCrystal(Aspect.EARTH),makeCrystal(Aspect.FIRE),makeCrystal(Aspect.WATER),makeCrystal(Aspect.ORDER),makeCrystal(Aspect.ENTROPY));

        for (int i = 0; i < ores.size(); i++) {
            AUTOCLAVE_RECIPES.recipeBuilder()
                    .input(dust,ores.get(i))
                    .fluidInputs(Thaumium.getFluid(144))
                    .outputs(crystals.get(i))
                    .duration(120)
                    .EUt(VA[LV])
                    .buildAndRegister();
        }*/
    }
}
