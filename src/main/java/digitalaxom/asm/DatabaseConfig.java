package digitalaxom.asm;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.driverClassName}")
    private String databaseDriverClassName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.max-active}")
    private int maximumPoolSize;

    @Bean
    @Primary
    public DataSource datasource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(maximumPoolSize);
        ds.setDriverClassName(databaseDriverClassName);
        ds.setJdbcUrl(datasourceUrl);
        ds.setUsername(databaseUsername);
        ds.setPassword(databasePassword);
        return ds;
    }

}