package ru.askael.supertodolist.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Alikin E.A. on 2020-10-18.
 */
@Configuration
@EnableJpaRepositories(basePackages = "ru.askael.supertodolist.repositories")
@EnableTransactionManagement//todo
public class PersistenceConfig {

    @Value("${host}")
    private String host;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${databaseName}")
    private String databaseName;

    @Value("${portNumber}")
    private String portNumber;

    @Bean
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(100);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");

        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", password);
        properties.put("databaseName", databaseName);
        properties.put("serverName", host);
        properties.put("portNumber", portNumber);
        hikariDataSource.setDataSourceProperties(properties);

        return hikariDataSource;
    }

}
