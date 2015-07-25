package org.springframework.boot.error.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "org.springframework.boot.error.repository", entityManagerFactoryRef = "errorEntityManagerFactory", transactionManagerRef = "errorTransactionManager")
public class JpaConfig {
	@Autowired
	private DataSource datasource;

	@Autowired
	Environment env;

	@Bean(name = "errorEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean errorEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(datasource);
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("org.springframework.boot.error");
		entityManagerFactoryBean.setJpaProperties(jpaProperties());

		return entityManagerFactoryBean;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();

		String ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto");
		if (ddlAuto != null) {
			properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
		}

		return properties;
	}

	@Bean(name = "errorTransactionManager")
	public JpaTransactionManager errorTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(errorEntityManagerFactory().getObject());
		return transactionManager;
	}
}
