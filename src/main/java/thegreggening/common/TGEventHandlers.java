package thegreggening.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.ApiStatus;

import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import gregtech.common.items.MetaItems;

import thegreggening.TheGreggening;
import thegreggening.api.unification.material.TGElements;
import thegreggening.api.unification.material.TGMaterialFlagAddition;
import thegreggening.api.unification.material.TGMaterials;
import thegreggening.api.unification.ore.TGOrePrefixes;

@ApiStatus.Internal
@Mod.EventBusSubscriber(modid = TheGreggening.MODID)
public final class TGEventHandlers {

    private TGEventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerElements(MaterialEvent event) {
        TGElements.init();
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        TGMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        TGMaterialFlagAddition.initLate();
        registerOrePrefixes();
    }

    private static void registerOrePrefixes() {
        MetaItems.addOrePrefix(TGOrePrefixes.nuclearFuel);
        MetaItems.addOrePrefix(TGOrePrefixes.depletedNuclearFuel);
        MetaItems.addOrePrefix(TGOrePrefixes.catalystBed);
    }
}
