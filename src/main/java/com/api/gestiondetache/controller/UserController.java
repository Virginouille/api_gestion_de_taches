package com.api.gestiondetache.controller;

import com.api.gestiondetache.dto.CreateUserRequest;
import com.api.gestiondetache.model.Project;
import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.UserRepository;
import com.api.gestiondetache.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    //Injection
    private UserService userService;
    private UserRepository userRepository;

    /**Créer nouvel utilisateur*/
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User createdUser = userService.createUser(request.getUsername());
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**Méthode récupération par id*/
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**Retrouver les projects par id des utilisateurs*/
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProjects(id));
    }

    /**Retrouver les tâches attribuées à un utilisteur*/
    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTasks(id));
    }
}
