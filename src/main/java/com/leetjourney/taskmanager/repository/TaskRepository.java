package com.leetjourney.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leetjourney.taskmanager.entity.Task;

// @Repository // Spring Boot annotation to mark the interface as a Spring Data JPA repository. and it provides many CRUD operations for the entity. and the annotation is not needed because Spring Boot will automatically inject the dependency.
//? This is called a bean and it's automatically injected into the TaskController.
public interface TaskRepository extends JpaRepository<Task, Long> {
    //? Spring Data JPA will automatically generate the implementation of this interface.
    //? This is a good practice to avoid writing the implementation of the interface.
}
