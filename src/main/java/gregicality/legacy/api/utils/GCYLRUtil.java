package gregicality.legacy.api.utils;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregicality.legacy.GregicalityLegacyReimagined;

public final class GCYLRUtil {

    public static @NotNull ResourceLocation gcylrId(@NotNull String path) {
        return new ResourceLocation(GregicalityLegacyReimagined.MODID, path);
    }

    private GCYLRUtil() {}
}
