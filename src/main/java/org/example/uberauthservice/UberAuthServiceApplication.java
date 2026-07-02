package org.example.uberauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EntityScan("org.example.uberentityservice.models")
@EnableJpaAuditing
@SpringBootApplication
public class UberAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthServiceApplication.class, args);
    }

}
