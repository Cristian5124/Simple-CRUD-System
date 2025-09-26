package edu.escuelaing.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class.
 * Entry point for the Property CRUD System.
 */
@SpringBootApplication
public class PropertyCrudSystemApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PropertyCrudSystemApplication.class, args);
    }

}