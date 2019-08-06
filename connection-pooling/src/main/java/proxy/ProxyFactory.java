package proxy;

import pool.IConnectionPool;

import java.sql.*;

public class ProxyFactory {

    public static ProxyConnection getProxyConnection(final Connection connection, final IConnectionPool iConnectionPool) {
        return new ProxyConnection(connection, iConnectionPool);
    }

    public static ProxyStatement getProxyStatement(final ProxyConnection connection, final Statement statement) {
        return null;
    }

    public static ProxyCallableStatement getProxyCallableStatement(final Connection connection, final CallableStatement statement) {
        return null;
    }

    public static ProxyPreparedStatement getProxyPreparedStatement(final ProxyConnection connection, final PreparedStatement statement) {
        return null;
    }

    public static ProxyResultSet getProxyResultSet(final ProxyConnection connection, final ProxyStatement statement, final ResultSet resultSet) {
        return null;
    }
}
