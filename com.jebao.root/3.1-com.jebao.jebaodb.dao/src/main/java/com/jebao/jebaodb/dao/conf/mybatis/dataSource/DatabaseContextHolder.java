package com.jebao.jebaodb.dao.conf.mybatis.dataSource;

/**
 * Created by Administrator on 2016/9/29.
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        //spring boot 初始时-contextHolder.get()=null,所以这时输出contextHolder.get().name()就会报错
        //System.out.println(contextHolder.get().name());
        return contextHolder.get();
    }
}
