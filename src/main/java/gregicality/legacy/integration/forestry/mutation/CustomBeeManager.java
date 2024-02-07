package gregicality.legacy.integration.forestry.mutation;

import forestry.api.apiculture.*;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomBeeManager {
    public static List<IBeeGenome> commonVillageBees;
    public static List<IBeeGenome> uncommonVillageBees;
    public static final Map<ItemStack, Integer> inducers = new HashMap();
    @Nullable
    public static IBeeRoot beeRoot;
    @Nullable
    public static IBeeFactory beeFactory;
    @Nullable
    public static ICustomBeeMutationFactory beeMutationFactory;
    @Nullable
    public static IJubilanceFactory jubilanceFactory;
    @Nullable
    public static IArmorApiaristHelper armorApiaristHelper;

    public CustomBeeManager() {}
}
