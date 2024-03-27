package com.ll.traveler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TravelerApplication.class, args);
    }

}
