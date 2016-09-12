package godziszewski.patryk.ElectronicsStore.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/database.properties")
public class DataBaseConfig {
	
	@Autowired
	Environment environment;
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("godziszewski.patryk.ElectronicsStore.domain");
		factoryBean.setHibernateProperties(hibernateProperties());
		return factoryBean;
		}
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dm.setUrl(environment.getRequiredProperty("jdbc.url"));
		dm.setUsername(environment.getRequiredProperty("jdbc.username"));
		dm.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dm;
		}
	private Properties hibernateProperties() {
		Properties properties = new Properties();
	    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	    return properties;
	    }
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
		}
	@Bean
	public BeanPostProcessor persistenceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
		}
}
