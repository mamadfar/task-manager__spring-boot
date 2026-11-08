package com.leetjourney.taskmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leetjourney.taskmanager.entity.Task;

// @Repository // Spring Boot annotation to mark the interface as a Spring Data JPA repository. and it provides many CRUD operations for the entity. and the annotation is not needed because Spring Boot will automatically inject the dependency.
//? This is called a bean and it's automatically injected into the TaskController.
public interface TaskRepository extends JpaRepository<Task, Long> {
    //? Spring Data JPA will automatically generate the implementation of this interface.
    //? This is a good practice to avoid writing the implementation of the interface.

    // SELECT * FROM tasks WHERE completed = :completed
    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    //? JPQL (Java Persistence Query Language) mainly used for complex queries. Task is the entity name
    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") boolean completed);

    //? Pagination methods
    Page<Task> findByCompleted(boolean completed, Pageable pageable);
    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    Page<Task> findTasksByCompletionStatus(@Param("completed") boolean completed, Pageable pageable);
}
