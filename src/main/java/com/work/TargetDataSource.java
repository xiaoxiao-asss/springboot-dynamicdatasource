package com.work;

import com.work.enums.DataSource;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DataSource value() default  DataSource.MASTER;
}
