package com.api.gestiondetache.service;

import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    //Injection
    private final UserRepository userRepository;

    /**Méthode qui se charge de la creation d'un user*/
    public User createUser(String username) {

        if(username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être vide");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Le nom d'utilisateur est déjà pris");
        }
       User newUser = new User();
       newUser.setUsername(username);
       userRepository.save(newUser);
       return newUser;
    }
}
