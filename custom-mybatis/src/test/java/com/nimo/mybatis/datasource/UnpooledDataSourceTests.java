package com.nimo.mybatis.datasource;

import com.nimo.mybatis.datasource.unpooled.UnpooledDataSource;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/20
 */
public class UnpooledDataSourceTests {

    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    /**
     * 直接根据 user、 password 连接数据库
     * @throws SQLException
     */
    @Test
    public void testGetConnectionWithoutProperties() throws SQLException {
        UnpooledDataSource unpooledDataSource = new UnpooledDataSource(URL);
        Connection connection = unpooledDataSource.getConnection(USER, PASSWORD);
        PreparedStatement pdst = connection.prepareStatement("select * from my_blog");
        ResultSet resultSet = pdst.executeQuery();
        Assert.assertTrue(resultSet.first());
    }

    /**
     * 把连接信息放在 Properties 中，连接数据库
     * @throws SQLException
     */
    @Test
    public void testGetConnectionWithProperties() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        UnpooledDataSource unpooledDataSource = new UnpooledDataSource(URL, properties);
        Connection connection = unpooledDataSource.getConnection();
        PreparedStatement pdst = connection.prepareStatement("select * from my_blog");
        ResultSet resultSet = pdst.executeQuery();
        Assert.assertTrue(resultSet.first());
    }

    /**
     * 测试连接属性覆盖，用一个错误的连接属性覆盖原有的正确的属性， 期望抛出 SQLException
     * @throws SQLException
     */
    @Test
    public void testGetConnectionWithOverrideProperties() throws SQLException {

        Assert.assertThrows(SQLException.class, ()-> {
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            UnpooledDataSource unpooledDataSource = new UnpooledDataSource(URL, properties);
            Connection connection = unpooledDataSource.getConnection("root", "root");
            PreparedStatement pdst = connection.prepareStatement("select * from my_blog");
            ResultSet resultSet = pdst.executeQuery();
        });
    }
}
