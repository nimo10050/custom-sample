package com.nimo.mybatis.datasource.pooled;

import com.nimo.mybatis.datasource.unpooled.UnpooledDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/20
 */
public class PooledDataSource implements DataSource {

    private UnpooledDataSource dataSource;

    private PoolState poolState;

    private static final int MAX_ACTIVE_CONNECTION_COUNT = 10;

    private static final int MAX_BAD_CONNECTION_COUNT = 5;

    public PooledDataSource(String url) {
        this.dataSource = new UnpooledDataSource(url);
        poolState = new PoolState();
    }

    public PoolConnection popConnection() {
        PoolConnection poolConnection = null;
        int localBadConnectionCount = 0;
        while (poolConnection == null) {
            synchronized (poolState) {
                // 如果存在空闲连接， 直接取
                List<PoolConnection> idleConnections = poolState.getIdleConnection();
                if (!idleConnections.isEmpty()) {
                    poolConnection = idleConnections.get(0);
                } else {
                    List<PoolConnection> activeConnections = poolState.getActiveConnection();
                    if (activeConnections.size() < MAX_ACTIVE_CONNECTION_COUNT) {
                        poolConnection = new PoolConnection();
                    } else {
                        // 取出最早使用的连接
                        PoolConnection oldestActiveConnection = activeConnections.get(0);
                        if (!oldestActiveConnection.pingConnection()) {
                            localBadConnectionCount ++;
                            // 连接不可用, 剔除， 重新建立一个连接
                            activeConnections.remove(oldestActiveConnection);
                            poolConnection = new PoolConnection();
                            activeConnections.add(poolConnection);
                        }
                    }
                }
            }

            if (localBadConnectionCount > MAX_BAD_CONNECTION_COUNT) {
                throw new RuntimeException("无法获取到有效的连接");
            }

        }

        if (poolConnection == null) {
            throw new RuntimeException("无法获取到有效的连接");
        }

        return poolConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
