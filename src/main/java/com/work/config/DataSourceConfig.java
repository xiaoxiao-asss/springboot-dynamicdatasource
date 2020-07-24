package com.work.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 */
@Configuration
@MapperScan(basePackages = "com.work.mapper",sqlSessionFactoryRef ="sqlSessionFactoryBean" )
public class DataSourceConfig {

    /**
     * 主数据源
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master(){
       return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从数据源
     * @return
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置动态数据源
     * @Primary 表示当前数据源优选
     * @return
     */
    @Primary
    @Bean(name="dynamicDataSource")
    public DataSource dynamicDataSource(){
        //DynamicRoutingDataSource 自定义动态数据源管理类
        DynamicRoutingDataSource dynamicRoutingDataSource=new DynamicRoutingDataSource();
        //设置默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());

        //需要动态切换的数据源集合
        Map<Object,Object> map=new HashMap<>(10);
        //添加数据源
        map.put(com.work.enums.DataSource.MASTER.getName(),master());
        map.put(com.work.enums.DataSource.SLAVE.getName(),slave());
        dynamicRoutingDataSource.setTargetDataSources(map);

        //将已加入的数据源key保存，用来判断用户选择的数据源是否存在
        DynamicDataSourceKeyContextHolder.dataSourceKey.addAll(map.keySet());

        return dynamicRoutingDataSource;
    }

    /**
     * 配置mybatis  sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    /**
     * 配置事务管理
     * @return
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
