package com.api.gestiondetache.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@Table(name="project")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 150)
    private String name;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

}
