package com.nimo.mybatis.datasource.unpooled;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/20
 */
@Getter
@Setter
public class UnpooledDataSource implements DataSource {

    private String url;

    private Properties properties;

    private String username;

    private String password;

    public UnpooledDataSource(String url) {
        this.url = url;
    }

    public UnpooledDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public UnpooledDataSource(String url, Properties properties) {
        this.url = url;
        this.properties = properties;
    }

    public Connection getConnection() throws SQLException {
        return doGetConnection(this.username, this.password, this.properties);
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return doGetConnection(username, password, this.properties);
    }

    private Connection doGetConnection(String username, String password, Properties properties) throws SQLException {
        Properties newProps = new Properties();
        if (properties != null) {
            newProps.putAll(this.properties);
        }
        if (username != null) {
            newProps.setProperty("user", username);
        }
        if (password != null) {
            newProps.setProperty("password", password);
        }
        return DriverManager.getConnection(this.url, newProps);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    public void setLoginTimeout(int seconds) throws SQLException {

    }

    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
