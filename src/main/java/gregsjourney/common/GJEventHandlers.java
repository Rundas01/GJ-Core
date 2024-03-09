package gregsjourney.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.ApiStatus;

import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;

import gregsjourney.GregsJourney;
import gregsjourney.api.unification.material.GJMaterialFlagAddition;
import gregsjourney.api.unification.material.GJMaterials;

@ApiStatus.Internal
@Mod.EventBusSubscriber(modid = GregsJourney.MODID)
public final class GJEventHandlers {

    private GJEventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerElements(MaterialEvent event) {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        GJMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        GJMaterialFlagAddition.initLate();
    }
}
