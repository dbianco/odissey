package com.example.userauthservice.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.userauthservice.model.Role;
import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.RoleRepository;
import com.example.userauthservice.repository.UserRepository;
import com.example.userauthservice.service.RoleService;
import com.example.userauthservice.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        
        roleRepository.saveAll(List.of(adminRole, userRole));

        // Initialize admin user
        String adminEmail = "admin@odissey.com";
        String adminPassword = "Admin123!";
        String adminFirstName = "Admin";
        String adminLastName = "User";
        
        // Check if admin user already exists
        if (!userRepository.existsByEmail(adminEmail)) {
            // Create admin user
            User adminUser = new User(
                adminFirstName,
                adminLastName,
                adminEmail,
                passwordEncoder.encode(adminPassword)
            );
            
            // Add admin role to user
            adminUser.getRoles().add(adminRole);
            
            // Save admin user
            userRepository.save(adminUser);
            
            System.out.println("Admin user created successfully with email: " + adminEmail);
            System.out.println("Default admin password: " + adminPassword);
        } else {
            System.out.println("Admin user already exists");
        }
    }
}
