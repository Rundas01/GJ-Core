package thegreggening.common;

import net.minecraftforge.common.config.Config;

import gregtech.api.GTValues;

import thegreggening.TheGreggening;

@Config(modid = TheGreggening.MODID, name = TheGreggening.NAME + '/' + GTValues.MODID)
public class TGConfigHolder {

    @Config.Comment("Config options for Greg's Journey")
    @Config.Name("GJ Options")
    @Config.RequiresMcRestart
    public static GJOptions options = new GJOptions();

    public static class GJOptions {

        @Config.Comment({
                "Into up to how many smaller cores an isotope can be split in the Fission- and Breeder Reactors. Obviously > 1. Has a huge impact on startup time, so be careful!",
                "Default: 3" })
        public int maxFuelSplit = 3;
        @Config.Comment({
                "How many different fission reactions a material can have. Max 24. Has a large impact on the Breeder Reactor especially!",
                "Default: 5" })
        public int maxFissionReactions = 5;
    }
}
