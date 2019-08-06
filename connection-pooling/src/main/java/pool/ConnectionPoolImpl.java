package pool;

import proxy.ProxyFactory;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConnectionPoolImpl implements IConnectionPool {

    private int POOL_SIZE;
    private BlockingQueue<Connection> queue;
    private Semaphore semaphoreLock;

    public ConnectionPoolImpl(int pool_size) {
        POOL_SIZE = pool_size;
        queue = new LinkedBlockingQueue<>(POOL_SIZE);
        semaphoreLock = new Semaphore(POOL_SIZE, true);
    }

    @Override
    public Connection getConnection() {
        return getConnection(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    @Override
    public Connection getConnection(final long timeOut, final TimeUnit timeUnit) {

        try {
            semaphoreLock.acquire();
            Connection connection = queue.poll(timeOut, timeUnit);
            return ProxyFactory.getProxyConnection(connection,this);

        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            semaphoreLock.release();
        }
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) {
        queue.add(connection);
    }
}
