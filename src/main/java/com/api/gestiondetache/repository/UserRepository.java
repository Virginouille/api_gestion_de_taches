package com.api.gestiondetache.repository;

import com.api.gestiondetache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsername(String username);
   Optional<User> findById(Long id);

   boolean existsByUsername(String username);
}
