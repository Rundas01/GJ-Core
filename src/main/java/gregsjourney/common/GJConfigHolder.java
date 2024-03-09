package gregsjourney.common;

import net.minecraftforge.common.config.Config;

import gregtech.api.GTValues;

import gregsjourney.GregsJourney;

@Config(modid = GregsJourney.MODID, name = GregsJourney.NAME + '/' + GTValues.MODID)
public class GJConfigHolder {

    @Config.Comment("Config options for Greg's Journey")
    @Config.Name("GJ Options")
    @Config.RequiresMcRestart
    public static GJOptions options = new GJOptions();

    public static class GJOptions {

        @Config.Comment({ "Za Handozawarudo", "Default: true" })
        public boolean test = true;
    }
}
