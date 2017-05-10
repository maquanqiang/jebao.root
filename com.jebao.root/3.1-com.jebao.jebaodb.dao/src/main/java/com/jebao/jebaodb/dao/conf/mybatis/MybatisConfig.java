package com.jebao.jebaodb.dao.conf.mybatis;

/**
 * Created by Administrator on 2016/10/11.
 */
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jebao.jebaodb.dao.conf.mybatis.dataSource.DatabaseType;
import com.jebao.jebaodb.dao.conf.mybatis.dataSource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 */
@Configuration    //该注解类似于spring配置文件
@MapperScan(basePackages = "com.jebao.jebaodb.dao.mapper")
public class MybatisConfig {

    @Autowired
    private Environment env;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource sqlDbMasterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.masterDb.driverClassName"));
        props.put("url", env.getProperty("jdbc.masterDb.url"));
        props.put("username", env.getProperty("jdbc.masterDb.username"));
        props.put("password", env.getProperty("jdbc.masterDb.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource sqlDbSlaveDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.slaveDb.driverClassName"));
        props.put("url", env.getProperty("jdbc.slaveDb.url"));
        props.put("username", env.getProperty("jdbc.slaveDb.username"));
        props.put("password", env.getProperty("jdbc.slaveDb.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 创建数据源
     *
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    @Primary
    public DataSource getDataSource(@Qualifier("sqlDbMasterDataSource") DataSource sqlDbMasterDataSource,
                                    @Qualifier("sqlDbSlaveDataSource") DataSource sqlDbSlaveDataSource) throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DatabaseType.DbMaster, sqlDbMasterDataSource);
        targetDataSources.put(DatabaseType.DbSlave, sqlDbSlaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(sqlDbMasterDataSource);// 默认的datasource设置为myDbMasterDataSource

        return dataSource;
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置

        return fb.getObject();
    }
}
