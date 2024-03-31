package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan("java")
public class ConfigHibernate {
    @Autowired
    private Environment ev;

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManager () {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(getDataSource());
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getProperties());
        return entityManager;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ev.getProperty("db.hibernate.Driver"));
        dataSource.setUrl(ev.getProperty("db.hidernate.url"));
        dataSource.setUsername(ev.getProperty("db.hibernate.username"));
        dataSource.setPassword(ev.getProperty("db.hibernate.password"));

        return dataSource;
    }

    private Properties getProperties() {
        Properties properties;
        try {
            properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find \"hibernate.properties\" in classpath " + e);
        }
        return properties;
    }

}
