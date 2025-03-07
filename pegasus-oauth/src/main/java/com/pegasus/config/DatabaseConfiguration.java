package com.pegasus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by enHui.Chen on 2019/8/30.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource myDataSource) {
        return new DataSourceTransactionManager(myDataSource);
    }
}
