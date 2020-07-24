package com.work.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源管理类
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 设置当前使用数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        //在动态数据源中所配置的key对应的数据源
        return DynamicDataSourceKeyContextHolder.getDataSourceKey();
    }
}
