package uz.genesis.trello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import uz.genesis.trello.property.FileStorageProperties;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class TrelloApplication extends SpringBootServletInitializer {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(TrelloApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TrelloApplication.class);
    }

    // todo Liquibase ishlashi uchun
//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:/db/liquibase/changelog-master.xml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }
}
