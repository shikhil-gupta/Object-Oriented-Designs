package custom.logger.filter;

import custom.logger.LogLevels;
import custom.logger.LogRecord;

public interface IFilterLog<T extends LogRecord> {

    Boolean filter(T logrecord, LogLevels levels);

}
