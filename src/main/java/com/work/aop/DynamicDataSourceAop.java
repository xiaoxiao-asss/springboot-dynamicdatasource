/*
package com.work.aop;

import com.work.config.DynamicDataSourceKeyContextHolder;
import com.work.entity.DataSourceDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

*/
/**
 *  管理切换数据源（通过修改application.yml 中的 use.datasource=XX切换）
 *//*

@Aspect
@Slf4j
public class DynamicDataSourceAop {

    @Autowired
    private DataSourceDto  dataSourceDto;

    @Pointcut("execution(* com.work.controller.*(..))")
    public void pointCut(){

    }

    */
/**
     *//*

    @Before("pointCut()")
    public void before(){
        */
/**
         *  判断选择的数据源是否存在
         *//*

        if(!DynamicDataSourceKeyContextHolder.IsContainsDataSourceKey(dataSourceDto.getDatasource())){
            log.info("该数据源不存在");
        }

        //存在指定的数据源key,修改为当前使用数据源
        DynamicDataSourceKeyContextHolder.setDataSourceKey(dataSourceDto.getDatasource());
        log.info("当前数据源key:{}",dataSourceDto.getDatasource());


    }

    */
/**
     * 访问之后调用
     *//*

    @After("pointCut()")
    public void after(){
        */
/**
         * 清除当前使用数据源
         *//*

        DynamicDataSourceKeyContextHolder.clearDataSourceKey();
    }


}
*/
