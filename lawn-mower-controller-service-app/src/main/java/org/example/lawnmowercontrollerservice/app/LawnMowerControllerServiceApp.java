package org.example.lawnmowercontrollerservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = "org.example.lawnmowercontrollerservice")
@EnableWebFlux
public class LawnMowerControllerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(LawnMowerControllerServiceApp.class);
    }
}