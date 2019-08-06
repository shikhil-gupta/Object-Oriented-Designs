package connection.pooling;

import java.util.List;


public abstract class ObjectPool<T> {

    List<T> lockedConnections;
    List<T> unlockedConnections;
    CustomLock customLock;

    public T getConnection() throws Exception {

        customLock.lock();

        T connection = null;
        synchronized (this) {
            if(unlockedConnections.size() > 0) {
                connection = unlockedConnections.get(unlockedConnections.size()-1);
                unlockedConnections.remove(connection);
                lockedConnections.add(connection);
            }
        }

        if(connection == null) {
            System.out.println("connection is null for thread id " + Thread.currentThread().getId());
        }
        return connection;

    }

    public synchronized void releaseConnection(T connection) {

        lockedConnections.remove(connection);
        unlockedConnections.add(connection);
        customLock.customnotify();
    }


}
