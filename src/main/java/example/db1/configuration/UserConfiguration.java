package example.db1.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.db1.model.User;
import example.db1.repository.UserRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackageClasses = UserRepository.class,
		entityManagerFactoryRef = "userEntityManager",
		transactionManagerRef = "userTransactionManager")

public class UserConfiguration {

	@Bean(name = "userDBProperties")
    @ConfigurationProperties(prefix = "db1.spring.datasource")
    public DataSourceProperties userDBProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "userDB")
    public DataSource profileDB(@Qualifier("userDBProperties") DataSourceProperties userDBProperties) {
        return userDBProperties.initializeDataSourceBuilder().build();
    }

	@Bean(name = "userEntityManager")
    public LocalContainerEntityManagerFactoryBean userEntityManager(@Qualifier("userDB") DataSource userDB,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(userDB).packages(User.class).persistenceUnit("db1").build();
    }

	@Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManager") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}
