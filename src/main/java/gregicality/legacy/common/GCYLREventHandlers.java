package gregicality.legacy.common;

import gregicality.legacy.GregicalityLegacyReimagined;
import gregicality.legacy.api.fluids.GeneratedFluidHandler;
import gregicality.legacy.api.unification.material.GCYLRElements;
import gregicality.legacy.api.unification.material.GCYLRMaterialFlagAddition;
import gregicality.legacy.api.unification.material.GCYLRMaterials;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Mod.EventBusSubscriber(modid = GregicalityLegacyReimagined.MODID)
public final class GCYLREventHandlers {

    private GCYLREventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerElements(MaterialEvent event) {
        GCYLRElements.init();
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        GCYLRMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        GeneratedFluidHandler.init();
        GCYLRMaterialFlagAddition.initLate();
    }
}
