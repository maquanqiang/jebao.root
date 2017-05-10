package com.jebao.jebaodb.dao.conf.mybatis.aspect;

import com.jebao.jebaodb.dao.conf.mybatis.dataSource.DatabaseContextHolder;
import com.jebao.jebaodb.dao.conf.mybatis.dataSource.DatabaseType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 实现主从读写分离描述切面类
 * Created by Administrator on 2016/10/12.
 */
@Aspect
@Configuration
public class DataSourceAspect {
    //只读数据库层切点
    @Pointcut("@annotation(com.jebao.jebaodb.dao.conf.mybatis.annotation.ReadOnlyDB)")
    public  void readOnlyDBAspect() {
    }
    /**
     * 前置通知 用于切换当前的数据源为从库-从库为只读数据库
     *
     * @param joinPoint 切点
     */
    @Before("readOnlyDBAspect()")
    public  void doBefore(JoinPoint joinPoint) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.DbSlave);
    }
    /*
    *   后置通知 用于切换当前的数据源为主库
    * */
    @After("readOnlyDBAspect()")
    public  void doAfter(JoinPoint joinPoint) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.DbMaster);
    }
}
