package com.web.demo.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.web.demo.repos",
        entityManagerFactoryRef = "prodEntityManager",
        transactionManagerRef = "prodTransactionManager"
)
public class ProdDataSource {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean prodEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(prodDBDataSource());
        em.setPackagesToScan(
                new String[]{"com.web.demo.entities"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        //properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        properties.put("spring.jpa.show-sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("spring.jpa.format-sql", env.getProperty("spring.jpa.format-sql"));
        properties.put("spring.jpa.open-in-view", env.getProperty("spring.jpa.open-in-view"));
        properties.put("spring.jpa.properties.hibernate.enable_lazy_load_no_trans",
                env.getProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.prod.datasource")
    public DataSource prodDBDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager prodTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                prodEntityManager().getObject());
        return transactionManager;
    }
}
