package com.h5education.demo.config.duird;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DuridConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource(){
        return new DruidDataSource();
    }
}
