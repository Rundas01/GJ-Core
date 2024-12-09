package gregsjourney.common;

import gregsjourney.GregsJourney;
import gregsjourney.api.unification.material.GJElements;
import gregsjourney.api.unification.material.GJMaterialFlagAddition;
import gregsjourney.api.unification.material.GJMaterials;
import gregsjourney.api.unification.ore.GJOrePrefixes;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import gregtech.common.items.MetaItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Mod.EventBusSubscriber(modid = GregsJourney.MODID)
public final class GJEventHandlers {

    private GJEventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerElements(MaterialEvent event) {
        GJElements.init();
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        GJMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        GJMaterialFlagAddition.initLate();
        registerOrePrefixes();
    }

    private static void registerOrePrefixes() {
        MetaItems.addOrePrefix(GJOrePrefixes.nuclearFuels);
        MetaItems.addOrePrefix(GJOrePrefixes.depletedNuclearFuels);
    }
}
