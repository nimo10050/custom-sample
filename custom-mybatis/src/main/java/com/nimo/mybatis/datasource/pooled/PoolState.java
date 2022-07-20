package com.nimo.mybatis.datasource.pooled;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/20
 */
@Getter
@Setter
public class PoolState {

    private List<PoolConnection> activeConnection = new ArrayList<>();

    private List<PoolConnection> idleConnection = new ArrayList<>();


}
