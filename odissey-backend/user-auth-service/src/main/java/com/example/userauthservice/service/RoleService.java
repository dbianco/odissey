package com.example.userauthservice.service;

import com.example.userauthservice.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userauthservice.model.Role;
import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(String name) {
        Role role = new Role(name);
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public void addRoleToUser(User user, String roleName) {
        Role role = getRoleByName(roleName);
        if (role != null) {
            user.getRoles().add(role);
        }
    }

    public void removeRoleFromUser(User user, String roleName) {
        Role role = getRoleByName(roleName);
        if (role != null) {
            user.getRoles().remove(role);
        }
    }
}
