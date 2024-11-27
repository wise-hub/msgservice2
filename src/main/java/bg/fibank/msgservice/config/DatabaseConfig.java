package bg.fibank.msgservice.config;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        pds.setURL("jdbc:oracle:thin:@//localhost:1521/ORCL");
        pds.setUser("db_user");
        pds.setPassword("db_password");
        pds.setInitialPoolSize(5);
        pds.setMaxPoolSize(20);
        return pds;
    }
}
