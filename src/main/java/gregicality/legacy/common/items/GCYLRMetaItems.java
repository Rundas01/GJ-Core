package gregicality.legacy.common.items;

import gregtech.api.items.metaitem.MetaItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GCYLRMetaItems {
    private GCYLRMetaItems() {}

    public static final List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

    /*public static MetaItem<?>.MetaValueItem UVA_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem ULTRASONIC_HOMOGENIZER;
    public static MetaItem<?>.MetaValueItem YEAST;*/

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        MinecraftForge.EVENT_BUS.register(GCYLRMetaItems.class);
        for (MetaItem<?> item : ITEMS) {
            item.registerModels();
            item.registerTextureMesh();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        for (MetaItem<?> item : ITEMS) {
            item.registerColor();
        }
    }
}
