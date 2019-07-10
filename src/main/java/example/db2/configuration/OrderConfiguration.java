package example.db2.configuration;

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

import example.db2.model.Orders;
import example.db2.repository.OrderRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackageClasses = OrderRepository.class,
		entityManagerFactoryRef = "orderEntityManager",
		transactionManagerRef = "orderTransactionManager")

public class OrderConfiguration {

	@Bean(name = "orderDBProperties")
    @ConfigurationProperties(prefix = "db2.spring.datasource")
    public DataSourceProperties orderDBProperties() {
        return new DataSourceProperties();
    }
	
	@Bean(name = "orderDB")
    public DataSource orderDB(@Qualifier("orderDBProperties") DataSourceProperties orderDBProperties) {
        return orderDBProperties.initializeDataSourceBuilder().build();
    }
	
	@Bean(name = "orderEntityManager")
    public LocalContainerEntityManagerFactoryBean orderEntityManager(@Qualifier("orderDB") DataSource orderDB,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(orderDB).packages(Orders.class).persistenceUnit("db2").build();
    }
	
	@Bean(name = "orderTransactionManager")
    public PlatformTransactionManager orderTransactionManager(
            @Qualifier("orderEntityManager") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
	
}
