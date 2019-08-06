package DataSource;

import pool.ConnectionPoolImpl;
import pool.IConnectionPool;

import java.sql.Connection;

public class CustomDataSoruce {

    IConnectionPool connectionPool;

    public CustomDataSoruce(){
        connectionPool = new ConnectionPoolImpl(12);
    }

    public Connection getConnection() {
        return connectionPool.getConnection();
    }
}
