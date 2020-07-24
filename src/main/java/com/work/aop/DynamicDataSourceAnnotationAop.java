package com.work.aop;

import com.work.TargetDataSource;
import com.work.config.DynamicDataSourceKeyContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 管理切换数据源 （通过注解的方式）
 */
@Aspect
@Slf4j
@Component
public class DynamicDataSourceAnnotationAop {

    @Before("@annotation(targetDataSource)")
    public void before(TargetDataSource targetDataSource){
        if(!DynamicDataSourceKeyContextHolder.IsContainsDataSourceKey(targetDataSource.value().getName())){
            log.info("该数据源key不存在");
        }
        DynamicDataSourceKeyContextHolder.setDataSourceKey(targetDataSource.value().getName());


    }

    @After("@annotation(targetDataSource)")
    public void After(TargetDataSource targetDataSource){
        DynamicDataSourceKeyContextHolder.clearDataSourceKey();
    }
}
