package custom.logger.layout;

import custom.logger.LogRecord;

public interface ILayout {

    String format(LogRecord logRecord);

}
