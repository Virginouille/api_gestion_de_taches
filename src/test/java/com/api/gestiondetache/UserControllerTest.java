package com.api.gestiondetache;


import com.api.gestiondetache.controller.UserController;
import com.api.gestiondetache.dto.CreateUserRequest;
import com.api.gestiondetache.model.Project;
import com.api.gestiondetache.model.Task;
import com.api.gestiondetache.model.User;
import com.api.gestiondetache.repository.UserRepository;

import com.api.gestiondetache.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        userService = mock(UserService.class);
        userController = new UserController(userService, userRepository);
    }

    //Test si user est créé
    @Test
    void testUserCreateSuccess() throws Exception {

        //Création de l'utilisateur test
        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("userTest1");

        when(userService.createUser(anyString())).thenReturn(testUser);

        //Création de la requête test

        CreateUserRequest request = new CreateUserRequest();

        request.setUsername("testUser1");

        //Simulation du post via le controller

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser1"));
    }

    //Test de récupération user par id
    @Test
    void testGetUserById_UserExists_ReturnsUser() throws Exception {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    //Test projets utilisteur
    @Test
    void testGetUserProjects() {
        Long userId = 1L;

        List<Project> mockProjects = List.of(
                new Project() {{
                    setId(1L);
                    setName("Projet A");
                }},
                new Project() {{
                    setId(2L);
                    setName("Projet B");
                }}
        );

        when(userService.getUserProjects(userId)).thenReturn(mockProjects);

        ResponseEntity<List<Project>> response = userController.getUserProjects(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockProjects, response.getBody());
        Mockito.verify(userService).getUserProjects(userId);

    }

    //test listes de taches pour user
    @Test
    void testGetUserTasks() {
        Long userId = 1L;

        List<Task> mockTasks = List.of(
                new Task() {{
                    setId(1L);
                    setTitle("Tâche 1");
                }},
                new Task() {{
                    setId(2L);
                    setTitle("Tâche 2");
                }}
        );

        when(userService.getUserTasks(userId)).thenReturn(mockTasks);

        ResponseEntity<List<Task>> response = userController.getUserTasks(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockTasks, response.getBody());
        Mockito.verify(userService).getUserProjects(userId);
    }

}
