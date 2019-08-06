package custom.logger.logger;

import java.util.concurrent.ConcurrentHashMap;

public class LogFactory {

    private static final ConcurrentHashMap<String, Logger> hashMap = new ConcurrentHashMap<String, Logger>();

    public static Logger getLogger(final String classname) {

        Logger logger = hashMap.get(classname);

        if(logger == null) {
            logger = new Logger(classname);
            hashMap.put(classname, logger);
        }

        return logger;
    }
}
