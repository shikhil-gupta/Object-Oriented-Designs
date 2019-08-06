package custom.logger.appenders;


import custom.logger.ConsoleQueueImp;
import custom.logger.LogLevels;
import custom.logger.LogQueue;
import custom.logger.LogRecord;
import custom.logger.filter.Filter;
import custom.logger.filter.IFilterLog;
import custom.logger.layout.ILayout;
import custom.logger.layout.PatternLayout;
import custom.logger.writer.ConsoleLogWriter;
import custom.logger.writer.ILogWritter;

/**
 * This class should be singleton because console appender should be one
 */

public class ConsoleAppender implements IAppender {

    private static LogQueue consoleAppenderQueue = ConsoleQueueImp.getInstance();

    private LogLevels thresholdLevel;

    private IFilterLog filterLog;

    private static ILogWritter logWritter = ConsoleLogWriter.getInstance();

    private ILayout patternLayout;

    public void poll() {
        consoleAppenderQueue.poll();
    }

    public void flush() {

        LogRecord logRecord = consoleAppenderQueue.poll();
        if(logRecord != null && filterLog.filter(logRecord, thresholdLevel)) {
            String messageformat =patternLayout.format(logRecord);
            logWritter.write(messageformat);
        }
    }

    private ConsoleAppender() {
        this.filterLog = new Filter();
        this.patternLayout = new PatternLayout();
        this.thresholdLevel = LogLevels.DEBUG;
    }

    private static class ConsoleAppenderInstanceHelper {
        private static final ConsoleAppender INSTANCE = new ConsoleAppender();
    }

    public static ConsoleAppender getInstance() {
        return ConsoleAppenderInstanceHelper.INSTANCE;
    }
}

