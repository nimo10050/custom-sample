package com.nimo.mybatis.datasource.pooled;

import lombok.Getter;

import java.sql.Connection;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/20
 */
@Getter
public class PoolConnection {

    private Connection connection;

    public PoolConnection() {

    }

    public PoolConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean pingConnection() {
        return false;
    }

}
