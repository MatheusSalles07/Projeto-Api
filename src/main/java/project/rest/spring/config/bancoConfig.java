package project.rest.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(entityManagerFactoryRef = "bancoEntityManagerFactory",
        basePackages = {"project.rest.spring.repository.User"}, transactionManagerRef = "bancoTransactionManager")

public class bancoConfig {
    @Value("jdbc:mysql://localhost:3307/Banco")
    private String url;

    @Value("root")
    private String username;

    @Value("teste")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "banco.datasource")
    public DataSource bancoDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bancoEntityManagerFactory() {
        System.out.println("loading config banco");
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("project.rest.spring.model.Usuario");
        em.setPersistenceUnitName("UsuarioModel");
        em.setDataSource(bancoDataSource());

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager bancoTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bancoEntityManagerFactory().getObject());
        return transactionManager;
    }

}
