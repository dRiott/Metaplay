package com.thoughtriott.metaplay.config;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.thoughtriott.metaplay.data.repositories.jpa")
public class SpringDataJPAConfig {
  
  @Bean
  public BasicDataSource dataSource() {
     BasicDataSource dataSource = new BasicDataSource();
     
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://metaplay.c1nbtf7dwiku.us-west-2.rds.amazonaws.com:3306/metaplay");
       dataSource.setUsername("driott");
       dataSource.setPassword("B3nchl3y.Sch00led");
     return dataSource;
  }
  
  //Spring's exception translation, unifying all data-access exceptions under Spring's exception hierarchy. Easier to swap persistence mechanisms.
  @Bean
  public BeanPostProcessor persistenceTranslation() {
	  return new PersistenceExceptionTranslationPostProcessor();
	  }
  
  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	  JpaTransactionManager transactionManager = new  JpaTransactionManager();
	  transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

  @Bean
  public EclipseLinkJpaVendorAdapter jpaVendorAdapter() {
	  EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
    adapter.setDatabase(Database.MYSQL);
    adapter.setShowSql(false);
    adapter.setGenerateDdl(true);
    return adapter;
  }
  
  @Bean
  public EntityManagerFactory entityManagerFactory() throws SQLException {
	  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(jpaVendorAdapter());
      factory.setPackagesToScan("com.thoughtriott.metaplay.data.entities");
      factory.setDataSource(dataSource());
      
      Map<String, String> jpaProperties = new HashMap<String, String>();
      jpaProperties.put("eclipselink.weaving", "false");
      factory.setJpaPropertyMap(jpaProperties);
      factory.afterPropertiesSet();
     
      return factory.getObject();
  }
 
}
