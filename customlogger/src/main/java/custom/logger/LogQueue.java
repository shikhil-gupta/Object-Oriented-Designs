package custom.logger;


public interface LogQueue {

    void log(LogRecord logRecord);

    LogRecord poll();
}


