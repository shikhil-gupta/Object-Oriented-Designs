package custom.logger.filter;

import custom.logger.LogLevels;
import custom.logger.LogRecord;

public class Filter implements IFilterLog {

    public Boolean filter(LogRecord logrecord, LogLevels thresholdLevel) {
        return true;
    }
}
