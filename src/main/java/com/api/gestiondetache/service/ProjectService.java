package com.api.gestiondetache.service;

import com.api.gestiondetache.model.Project;
import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.ProjectRepository;
import com.api.gestiondetache.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

    //Injection
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    /**Méthode création d'un project avec id de l'user*/
    public Project createProject(Long idUser, String projectName) {

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur avec l'id " + idUser + " non trouvé"));

        Project project = new Project();
        project.setName(projectName);
        project.setCreator(user);

        return projectRepository.save(project);
    }

    /**Récupérer un project avec ses tâches*/
    public Project getProjectWithTasks(Long id) {
        return projectRepository.findByIdWithTasks(id)
                .orElseThrow(() -> new EntityNotFoundException("Projet avec l'id " + id + " non trouvé"));
    }
}
