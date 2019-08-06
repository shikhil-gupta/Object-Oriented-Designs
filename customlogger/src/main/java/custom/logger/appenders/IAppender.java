package custom.logger.appenders;

public interface IAppender {

    void poll();

    void flush();
}
