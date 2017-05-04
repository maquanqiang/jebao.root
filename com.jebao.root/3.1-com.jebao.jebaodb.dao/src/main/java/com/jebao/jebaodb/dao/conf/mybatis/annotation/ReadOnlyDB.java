package com.jebao.jebaodb.dao.conf.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 实现主从读写分离
 * ReadOnlyDB--表示从库读取数据
 * Created by Administrator on 2016/10/12.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface ReadOnlyDB {
    //表示从库中读取数据
}
