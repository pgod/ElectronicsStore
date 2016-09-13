package godziszewski.patryk.ElectronicsStore.config.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Profile("InMemoryDatabaseDatabase")
@Configuration
public class HibernateTestConfig {
	@Autowired
	Environment environment;
	
	@Bean
	@Primary
	public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
       factoryBean.setDataSource(dataSource());
       factoryBean.setPackagesToScan("godziszewski.patryk.ElectronicsStore.model");
       factoryBean.setHibernateProperties(hibernateProperties());
       return factoryBean;
       }
	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("org.hsqldb.jdbcDriver");
		dm.setUrl("jdbc:hsqldb:mem:ElectronicsStoreTestDatabase");
		dm.setUsername("sa");
		dm.setPassword("");
		return dm;
		}
	private Properties hibernateProperties() {
		Properties properties = new Properties();
	    properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.hbm2ddl.auto", "create");
	    return properties;
	    } 
}
