package com.api.gestiondetache.controller;

import com.api.gestiondetache.dto.CreateTaskRequest;
import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.TaskStatuts;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.Taskrepository;
import com.api.gestiondetache.service.TaskService;
import com.api.gestiondetache.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    //Injection
    private final TaskService taskService;
    private final UserService userService;

    /**Post create task*/
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {

        User assignee = userService.findById(request.getAssigneeId())
                .orElseThrow(() -> new IllegalArgumentException("Assignee non trouvé"));

        List<User> collaborators = userService.findAllById(request.getUserIds());

        Task createdTask = taskService.createTask(
                request.getTitle(),
                request.getStatut(),
                assignee,
                collaborators
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**Patch modifier le statut de l'enum*/
    @PatchMapping("/{id}/statut")
    public ResponseEntity<Task> updateStatut(@PathVariable Long id,
                                             @RequestParam TaskStatuts statut) {
        Task updated = taskService.updateTaskStatut(id, statut);
        return ResponseEntity.ok(updated);
    }


}
