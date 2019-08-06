package custom.logger.writer;

/**
 * This instance should be singleton omly. since their should be
 * only one com.github.shikhil.writer.ConsoleLogWriter
 */
public class ConsoleLogWriter implements ILogWritter {

    public void write(String logMessage) {
        System.out.println(logMessage);
    }

    private ConsoleLogWriter(){}

    public static ConsoleLogWriter getInstance() {
        return ConsoleLogWriterInstanceHelper.INSTANCE;
    }

    public static class ConsoleLogWriterInstanceHelper{
        public static final ConsoleLogWriter INSTANCE = new ConsoleLogWriter();
    }
}
