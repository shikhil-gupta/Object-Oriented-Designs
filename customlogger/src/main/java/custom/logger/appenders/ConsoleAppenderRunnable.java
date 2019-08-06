package custom.logger.appenders;

public class ConsoleAppenderRunnable implements Runnable {


    private static ConsoleAppender appender = ConsoleAppender.getInstance();


    public void run() {
        System.out.println("started ConsoleAppenderRunnable");
        try {
        while (true) {
            appender.flush();
            Thread.sleep(1000);
        }
        } catch (InterruptedException ex) {

        }

    }
}