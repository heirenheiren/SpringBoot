/*package com.boot.SpringBoot.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfiguration {
 
    @Resource DataSourceProperties properties;
 
    @Bean
    public DataSource dataSource() {
    	//可以在此处调用相关接口获取数据库的配置信息进行 DataSource 的配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());
        return dataSource;
    }
}*/