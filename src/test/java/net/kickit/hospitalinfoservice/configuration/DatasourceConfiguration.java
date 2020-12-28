package net.kickit.hospitalinfoservice.configuration;

import liquibase.Liquibase;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties({DataSourceProperties.class, LiquibaseProperties.class})
public class DatasourceConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    JdbcDatabaseContainer dbContainer(final DataSourceProperties properties) {
        return new PostgreSQLContainer("postgres:11.1")
                .withUsername(properties.getUsername())
                .withPassword(properties.getPassword())
                .withDatabaseName(properties.getDatabase());
    }

    @Bean
    @DependsOn("dbContainer")
    public DataSource dataSource(JdbcDatabaseContainer container) {
        return DataSourceBuilder.create()
                .driverClassName(container.getDriverClassName())
                .url(container.getJdbcUrl())
                .username(container.getUsername())
                .password(container.getPassword())
                .build();
    }

    @Bean
    SpringLiquibase liquibase(final LiquibaseProperties properties, final DataSource dataSource) {

        final SpringLiquibase springLiquibase = new SpringLiquibase() {
            @Override
            public void afterPropertiesSet() throws LiquibaseException {
                super.afterPropertiesSet();
                try (final Connection connection = getDataSource().getConnection()) {
                    final Liquibase liquibase = createLiquibase(connection);
                    liquibase.rollback(liquibase.getDatabaseChangeLog().getChangeSets().size(), contexts);
                    performUpdate(liquibase);
                } catch (SQLException e) {
                    throw new DatabaseException(e);
                }
            }
        };
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(properties.getChangeLog());
        springLiquibase.setContexts(properties.getContexts());
        springLiquibase.setDefaultSchema(properties.getDefaultSchema());
        springLiquibase.setDropFirst(properties.isDropFirst());
        springLiquibase.setShouldRun(properties.isEnabled());
        springLiquibase.setLabels(properties.getLabels());
        springLiquibase.setChangeLogParameters(properties.getParameters());
        springLiquibase.setRollbackFile(properties.getRollbackFile());
        return springLiquibase;
    }
}
