package com.leetjourney.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leetjourney.taskmanager.entity.Task;

@Repository // Spring Boot annotation to mark the interface as a Spring Data JPA repository. and it provides many CRUD operations for the entity.
public interface TaskRepository extends JpaRepository<Task, Long> {
    //? Spring Data JPA will automatically generate the implementation of this interface.
    //? This is a good practice to avoid writing the implementation of the interface.
}
