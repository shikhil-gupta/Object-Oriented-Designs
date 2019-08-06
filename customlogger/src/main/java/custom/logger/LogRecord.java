package custom.logger;

import custom.logger.logger.Logger;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class LogRecord {

    private Timestamp timestamp;

    private Logger logSource;

    private LogLevels logLevel;

    private String message;

    private Throwable throwable;

    public LogRecord(Logger logSource, LogLevels logLevel, String message, Throwable throwable) {
        this.logSource = logSource;
        this.logLevel = logLevel;
        this.message = message;
        this.throwable = throwable;
        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime());
    }
}
