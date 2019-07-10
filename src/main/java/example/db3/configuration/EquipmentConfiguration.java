package example.db3.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import example.db3.model.Equipment;
import example.db3.repository.EquipmentRepositoryImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackageClasses = EquipmentRepositoryImpl.class,
		entityManagerFactoryRef = "equipmentEntityManager",
		transactionManagerRef = "equipmentTransactionManager")

public class EquipmentConfiguration {

	@Primary
	@Bean(name = "equipmentDBProperties")
    @ConfigurationProperties(prefix = "db3.spring.datasource")
    public DataSourceProperties equipmentDBProperties() {
        return new DataSourceProperties();
    }
	
	@Primary
	@Bean(name = "equipmentDB")
    public DataSource equipmentDB(@Qualifier("equipmentDBProperties") DataSourceProperties equipmentDBProperties) {
        return equipmentDBProperties.initializeDataSourceBuilder().build();
    }
	
	@Primary
	@Bean(name = "equipmentEntityManager")
    public LocalContainerEntityManagerFactoryBean equipmentEntityManager(@Qualifier("equipmentDB") DataSource equipmentDB,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(equipmentDB).packages(Equipment.class).persistenceUnit("db3").build();
    }
	
	@Primary
	@Bean(name = "equipmentTransactionManager")
    public PlatformTransactionManager equipmentTransactionManager(
            @Qualifier("equipmentEntityManager") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
