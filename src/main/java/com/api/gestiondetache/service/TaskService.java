package com.api.gestiondetache.service;

import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.TaskStatuts;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.Taskrepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    //Injection
    private final Taskrepository taskRepository;

    /**Méthode qui se charge de la creation d'une nouvelle task*/
    @Transactional
    public Task createTask(String title, TaskStatuts statut, User assignee, List<User> users) { //revoir avec les id

        if (taskRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("Nom de tâche déjà attribué");
            // Piste d'amélioration regex pour autoriser les tache-1 et limiter à deux chiffres par exemple
        }

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setStatuts(statut);
        newTask.setAssignee(assignee);
        newTask.setUsers(users);

        return taskRepository.save(newTask);
    }

    /**Méthode qui permet de modifier le statut d'une tâche*/
    @Transactional
    public Task updateTaskStatut(Long id, TaskStatuts statut) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tâche non trouvée avec l'id " + id));

        task.setStatuts(statut);
        return taskRepository.save(task);
    }
}
