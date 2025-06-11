package com.example.userauthservice.resolvers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.userauthservice.model.User;
import com.example.userauthservice.service.UserService;
import com.example.userauthservice.util.JwtUtil;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
@Component
public class UserResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @DgsQuery
    public String hello() {
        return "Hello, world!";
    }

    @DgsQuery
    public User user(@InputArgument String id) {
        Long userId = Long.parseLong(id);
        Optional<User> userOptional = userService.findUserById(userId);
        return userOptional.orElse(null);
    }

    @DgsQuery
    public List<User> users() {
        return userService.findAllUsers();
    }

    @DgsQuery
    public Map<String, Object> login(@InputArgument String email, @InputArgument String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userOptional = userService.login(email, password);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = jwtUtil.generateToken(user.getEmail());
            response.put("token", token);
            response.put("user", user);
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return response;
    }

    @DgsMutation
    public Map<String, Object> register(@InputArgument Map<String, Object> input) {
        String firstName = (String) input.get("firstName");
        String lastName = (String) input.get("lastName");
        String email = (String) input.get("email");
        String password = (String) input.get("password");

        User user = userService.registerUser(firstName, lastName, email, password);
        String token = jwtUtil.generateToken(email);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return response;
    }

    @DgsMutation
    public User updateUser(@InputArgument String id, @InputArgument Map<String, Object> input) {
        Long userId = Long.parseLong(id);
        String firstName = (String) input.get("firstName");
        String lastName = (String) input.get("lastName");
        String email = (String) input.get("email");
        String password = (String) input.get("password");

        return userService.updateUser(userId, firstName, lastName, email, password);
    }

    @DgsMutation
    public Boolean deleteUser(@InputArgument String id) {
        Long userId = Long.parseLong(id);
        return userService.deleteUser(userId);
    }
}
