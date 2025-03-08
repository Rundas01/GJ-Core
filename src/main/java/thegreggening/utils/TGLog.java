package thegreggening.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TGLog {

    public static Logger logger;

    private TGLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}
