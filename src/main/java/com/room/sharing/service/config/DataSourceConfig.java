package com.room.sharing.service.config;

import static com.room.sharing.service.config.DatabaseConfig.DATASOURCE_BEAN_ID;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

  @Primary
  @Bean(DATASOURCE_BEAN_ID)
  @ConfigurationProperties("spring.datasource.hikari")
  DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

}
