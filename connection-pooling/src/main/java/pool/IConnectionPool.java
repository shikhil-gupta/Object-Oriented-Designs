package pool;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

//https://codereview.stackexchange.com/questions/40005/connection-pool-implementation
//https://github.com/brettwooldridge/HikariCP


public interface IConnectionPool {

    Connection getConnection();

    Connection getConnection(final long timeOut, final TimeUnit timeUnit);


    void releaseConnection(final Connection connection);

}
