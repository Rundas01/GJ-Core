package gregicality.legacy.api.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class GCYLRLog {

    public static Logger logger;

    private GCYLRLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}
