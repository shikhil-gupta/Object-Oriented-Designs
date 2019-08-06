package connection.pooling;

import java.sql.Connection;
import java.util.ArrayList;

public class BasicConnectionPool extends ObjectPool<Integer> {

    public BasicConnectionPool() {
        super();
        lockedConnections = new ArrayList<Integer>();
        unlockedConnections= new ArrayList<Integer>();
        customLock = new CustomLock(unlockedConnections.size());
    }

}
