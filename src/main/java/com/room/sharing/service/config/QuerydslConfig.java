package com.room.sharing.service.config;

import static com.room.sharing.service.config.DatabaseConfig.DATASOURCE_BEAN_ID;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import java.sql.Connection;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class QuerydslConfig {

  private final EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }

  /**
   * querydslConfiguration.
   */
  @Bean
  public com.querydsl.sql.Configuration querydslConfiguration() {
    SQLTemplates templates = MySQLTemplates.builder().build(); //change to your Templates
    com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
    SpringExceptionTranslator springExceptionTranslator = new SpringExceptionTranslator();
    configuration.setExceptionTranslator(springExceptionTranslator);
    return configuration;
  }


  /**
   * sqlQueryFactory.
   */
  @Bean
  public SQLQueryFactory sqlQueryFactory(
      @Qualifier(DATASOURCE_BEAN_ID) DataSource datasource,
      com.querydsl.sql.Configuration querydslConfiguration) {

    Provider<Connection> provider = new SpringConnectionProvider(datasource);

    return new SQLQueryFactory(querydslConfiguration, provider);
  }
}
