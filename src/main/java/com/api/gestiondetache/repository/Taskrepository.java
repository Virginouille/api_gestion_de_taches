package com.api.gestiondetache.repository;

import com.api.gestiondetache.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Taskrepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    boolean existsByTitle(String title);
}
