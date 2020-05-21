package com.lees.springdatajdbc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@ComponentScan("com.lees")
public class DatabaseConfig extends AbstractJdbcConfiguration { 
    // extending AbstractJdbcConfiguration adds some beans to the ApplicationContext that are required for Spring Data JDBC.
	
    @Bean
    public NamedParameterJdbcOperations operations() { 
    	// Spring Data JDBC uses an NamedParameterJdbcOperations instance internally to submit SQL statements to the database.
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() { 
    	// Create a PlatformTransactionManager implementation instance for the single H2 JDBC DataSource.
        return new DataSourceTransactionManager(dataSource());
	}

    @Bean
    public DataSource dataSource(){ 
    	// Create an in-memory H2 database and add a Customer table to it by running create-customer-schema.sql.
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-customer-schema.sql")
                .build();
    }
}