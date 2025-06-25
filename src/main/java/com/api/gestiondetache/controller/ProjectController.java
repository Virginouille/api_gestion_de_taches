package com.api.gestiondetache.controller;

import com.api.gestiondetache.dto.CreateProjectRequest;
import com.api.gestiondetache.model.Project;
import com.api.gestiondetache.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectRequest request) {
        Project createdProject = projectService.createProject(request.getIdUser(), request.getProjectName());
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping("/{id}") //Get d'un project avec ses dates
    public ResponseEntity<Project> getProjectWithTasks(@PathVariable Long id) {
        Project project = projectService.getProjectWithTasks(id);
        return ResponseEntity.ok(project);
    }
}
