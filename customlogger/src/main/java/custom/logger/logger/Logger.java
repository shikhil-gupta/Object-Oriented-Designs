package custom.logger.logger;

import custom.logger.ConsoleQueueImp;
import custom.logger.LogLevels;
import custom.logger.LogQueue;
import custom.logger.LogRecord;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private String loggername;

   private static List<LogQueue> logQueues;

   static {
       logQueues = new ArrayList<LogQueue>();
       logQueues.add(ConsoleQueueImp.getInstance());
   }

   public Logger(String loggername) {
       this.loggername = loggername;
   }

   public void info(String message, Throwable throwable) {

       LogRecord logRecord = new LogRecord(this, LogLevels.INFO, message, throwable);

       for (LogQueue logqueue: logQueues
            ) {
           logqueue.log(logRecord);
       }

   }

   public void debug(String message, Throwable throwable) {

       LogRecord logRecord = new LogRecord(this, LogLevels.DEBUG, message, throwable);

       for (LogQueue logqueue: logQueues
               ) {
           logqueue.log(logRecord);
       }
   }

   public void error(String message, Throwable throwable) {


       LogRecord logRecord = new LogRecord(this, LogLevels.ERROR, message, throwable);

       for (LogQueue logqueue: logQueues
               ) {
           logqueue.log(logRecord);
       }
   }


}
