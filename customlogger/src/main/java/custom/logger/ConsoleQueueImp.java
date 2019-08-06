package custom.logger;

import java.util.LinkedList;

public class ConsoleQueueImp implements LogQueue {
    private static ConsoleQueueImp ourInstance = new ConsoleQueueImp();

    private LinkedList<LogRecord> list;

    public synchronized void log(LogRecord logRecord) {
        list.add(logRecord);
    }

    public synchronized LogRecord poll() {
        return  list.poll();
    }

    public static ConsoleQueueImp getInstance() {
        return ourInstance;
    }

    private ConsoleQueueImp() {
        this.list = new LinkedList<LogRecord>();
    }

}
