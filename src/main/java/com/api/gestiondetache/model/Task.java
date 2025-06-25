package com.api.gestiondetache.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false, length = 70)
    private String title;

    @Enumerated
    private TaskStatuts statuts;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToMany(mappedBy="tasks", fetch=FetchType.LAZY)
    private List<User> users;
}
