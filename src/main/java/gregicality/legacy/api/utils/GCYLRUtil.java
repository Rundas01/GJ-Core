package gregicality.legacy.api.utils;

import gregtech.api.unification.material.Material;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregicality.legacy.GregicalityLegacyReimagined;

public final class GCYLRUtil {

    public static @NotNull ResourceLocation gcylrId(@NotNull String path) {
        return new ResourceLocation(GregicalityLegacyReimagined.MODID, path);
    }

    public static int convertRGB2Hex(int r, int g, int b){
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));
        String hexColor = String.format("%02X%02X%02X", r, g, b);
        return Integer.parseInt(hexColor, 16);
    }

    public static int avgColor(Material... materials){
        int color = 0;
        for(Material material : materials){
            color += material.getMaterialRGB();
        }
        color /= materials.length;
        return color;
    }

    private GCYLRUtil() {}
}
