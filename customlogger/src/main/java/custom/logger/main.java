package custom.logger;


import custom.logger.appenders.ConsoleAppenderRunnable;
import custom.logger.logger.LogFactory;
import custom.logger.logger.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class main {

    private static Logger logger = LogFactory.getLogger(main.class.toString());
    public static void main(String[] args) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new ConsoleAppenderRunnable());

        logger.info("hello", null);

        for (int i = 0; i <100 ; i++) {

            logger.info("hello" + i, null);
        }

    }
}
