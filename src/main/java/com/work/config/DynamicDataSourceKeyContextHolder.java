package com.work.config;

import com.work.enums.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理数据源key操作
 */
public class DynamicDataSourceKeyContextHolder {
    /**
     * 设置当前使用默认数据源key
     */
    private static ThreadLocal<Object> threadLocal=ThreadLocal.withInitial(()-> DataSource.MASTER.getName());

    /**
     * 保存添加到动态数据源中的所有数据源key
     */
    public static List<Object> dataSourceKey=new ArrayList<>();

    /**
     * 设置当前使用数据源key
     * @param key
     */
    public static void setDataSourceKey(String key){
        threadLocal.set(key);
    }

    /**
     * 获取当前使用数据源key
     * @return
     */
    public static Object getDataSourceKey(){
        return threadLocal.get();
    }

    /**
     * 清除当前使用数据源key
     */
    public static  void clearDataSourceKey(){
         threadLocal.remove();
    }

    /**
     * 验证key是否在数据源集合key中存在
     * @param key
     * @return
     */
    public static  boolean IsContainsDataSourceKey(String key){
        return dataSourceKey.contains(key);
    }

}
