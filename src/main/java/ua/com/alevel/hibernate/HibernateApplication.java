package ua.com.alevel.hibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import ua.com.alevel.hibernate.service.system.InitDbService;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
public class HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Bean
    CommandLineRunner init(InitDbService initDbService) {
        return args -> initDbService.init();
    }
}
