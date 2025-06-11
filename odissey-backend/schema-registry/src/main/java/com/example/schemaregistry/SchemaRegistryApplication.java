package com.example.schemaregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.schemaregistry")
public class SchemaRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchemaRegistryApplication.class, args);
    }
}
