package com.api.gestiondetache.service;

import com.api.gestiondetache.model.Project;
import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    //Injection
    private final UserRepository userRepository;

    /**Méthode qui se charge de la creation d'un user*/
    @Transactional
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

    /**Méthode retrouver project liés à l'utilisateur*/
    @Transactional
    public List<Project> getUserProjects(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec id " + id));
        return user.getProjects();
    }

    /**Méthode pour retrouver les taches assignées à un utilisateur*/
    @Transactional
    public List<Task> getUserTasks(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User non trouve avec cet id " + id));
        return user.getTasks();
    }

    /**Expo userrepo trouver par id */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**Expo userrepo trouver liste user par id */
    public List<User> findAllById(List<Long> ids) {
        return userRepository.findAllById(ids);

    }
}
