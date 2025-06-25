package com.api.gestiondetache;

import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.TaskStatuts;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.Taskrepository;
import com.api.gestiondetache.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private Taskrepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepository);
    }

    /**Test cretion de task*/
    @Test
    void createTask_shouldCreateNewTask_whenTitleIsUnique() {
        // Arrange
        String title = "Tâche 1";
        TaskStatuts statut = TaskStatuts.TODO;
        User assignee = new User();
        List<User> users = Arrays.asList(new User(), new User());

        when(taskRepository.existsByTitle(title)).thenReturn(false);
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task result = taskService.createTask(title, statut, assignee, users);

        assertEquals(title, result.getTitle());
        assertEquals(statut, result.getStatuts());
        assertEquals(assignee, result.getAssignee());
        assertEquals(users, result.getUsers());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    /**Test update task en changeant le statut*/

    @Test
    void updateTaskStatut_shouldUpdateStatut_whenTaskExists() {

        Long id = 1L;
        Task existingTask = new Task();
        existingTask.setId(id);
        existingTask.setStatuts(TaskStatuts.TODO);

        when(taskRepository.findById(id)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task updatedTask = taskService.updateTaskStatut(id, TaskStatuts.DONE);

        assertEquals(TaskStatuts.DONE, updatedTask.getStatuts());
        verify(taskRepository).save(existingTask);
    }
}
