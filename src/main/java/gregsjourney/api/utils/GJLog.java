package gregsjourney.api.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class GJLog {

    public static Logger logger;

    private GJLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}
