package com.example.schemaregistry.controller;

import com.example.schemaregistry.service.SchemaRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/schema")
public class SchemaRegistryController {

    @Autowired
    private SchemaRegistryService schemaRegistryService;

    @PostMapping("/register")
    public ResponseEntity<String> registerSchemaFragment(@RequestParam String serviceName, @RequestBody String schemaFragment) {
        schemaRegistryService.registerSchemaFragment(serviceName, schemaFragment);
        return ResponseEntity.ok("Schema fragment registered successfully");
    }

    @GetMapping("/get/{serviceName}")
    public ResponseEntity<String> getSchemaFragment(@PathVariable String serviceName) {
        String schemaFragment = schemaRegistryService.getSchemaFragment(serviceName);
        if (schemaFragment != null) {
            return ResponseEntity.ok(schemaFragment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllSchemaFragments() {
        String combinedSchema = schemaRegistryService.getAllSchemaFragments();
        return ResponseEntity.ok(combinedSchema);
    }
}
