package org.sam.mines.address.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.sam.mines.address"})
@EnableJpaRepositories(basePackages = {"org.sam.mines.address.persistence"})
@EntityScan(basePackages = {"org.sam.mines.address.model"})
@EnableTransactionManagement
public class AddressApp
{
    public static void main(String[] args) {
        SpringApplication.run(AddressApp.class, args);
    }
}
