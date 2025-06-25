package com.api.gestiondetache.init;

import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.UserRepository;
import com.api.gestiondetache.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        try {
            userService.createUser("alice");
            userService.createUser("bob");
            userService.createUser("charlie");
            System.out.println("Utilisateurs initialisés");
        } catch (IllegalArgumentException e) {
            System.out.println("Initialisation ignorée : " + e.getMessage());
        }
    }
}
