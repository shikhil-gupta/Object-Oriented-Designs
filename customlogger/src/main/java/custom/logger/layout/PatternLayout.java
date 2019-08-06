package custom.logger.layout;

import custom.logger.LogRecord;

public class PatternLayout implements ILayout {

    public String format(LogRecord logRecord) {
        return logRecord.getMessage();
    }
}
