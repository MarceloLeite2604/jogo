package org.marceloleite.jogo.servidor.configuracao;

import java.util.Properties;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.marceloleite.libs.crypt.Crypt;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "createEntityManagerFactory",
		basePackages = "org.marceloleite.jogo.servidor.dao.repository")
public class ConfiguracaoPersistence {

	private static final Logger LOGGER = LogManager.getLogger(ConfiguracaoPersistence.class);

	private static final String[] PACKAGES_TO_SCAN = { "org.marceloleite.jogo.servidor.modelo" };

	@Bean
	public LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource,
			@Named("jpaProperties") Properties jpaProperties) {
		LOGGER.debug("Creating EntityManagerFactory.");
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Primary
	@Bean
	@ConfigurationProperties("datasource")
	public DataSourceProperties criarDataSourceProperties() {
		LOGGER.debug("Criando DataSourcePropeties.");
		return new DataSourceProperties();
	}

	@Bean("jpaProperties")
	@ConfigurationProperties("jpa")
	public Properties criarHibernateProperties() {
		return new Properties();
	}

	@Bean
	public DataSource criarDataSource(DataSourceProperties dataSourceProperties, Crypt crypt) {
		LOGGER.debug("Criando DataSource.");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(crypt.decrypt(dataSourceProperties.getUsername()));
		dataSource.setPassword(crypt.decrypt(dataSourceProperties.getPassword()));
		dataSource.setUrl(crypt.decrypt(dataSourceProperties.getUrl()));
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		LOGGER.debug("Creating TransactionManager.");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor createPersistenceExceptionTranslationPostProcessor() {
		LOGGER.debug("Creating PersistenceExceptionTranslationPostProcessor.");
		return new PersistenceExceptionTranslationPostProcessor();
	}

//	private Properties getHibernateProperties() {
//		LOGGER.debug("Retrieving hibernate properties.");
//		return PropertiesUtil.getPropertiesStartingWith(getProperties(), "hibernate");
//	}

//	private Properties getProperties() {
//		LOGGER.debug("Persistence properties file: \"" + simulatorProperties.getPersistencePropertiesFile() + "\".");
//		if (properties == null) {
//			properties = PropertiesUtil.retrieveProperties(simulatorProperties.getPersistencePropertiesFile());
//		}
//		return properties;
//	}
}
