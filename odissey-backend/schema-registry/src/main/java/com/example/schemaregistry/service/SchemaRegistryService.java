package com.example.schemaregistry.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SchemaRegistryService {

    private final Map<String, String> schemaFragments = new ConcurrentHashMap<>();

    public void registerSchemaFragment(String serviceName, String schemaFragment) {
        schemaFragments.put(serviceName, schemaFragment);
    }

    public String getSchemaFragment(String serviceName) {
        return schemaFragments.get(serviceName);
    }

    public String getAllSchemaFragments() {
        StringBuilder combinedSchema = new StringBuilder();
        schemaFragments.forEach((serviceName, schemaFragment) -> {
            combinedSchema.append(schemaFragment).append("\n");
        });
        return combinedSchema.toString();
    }
}
