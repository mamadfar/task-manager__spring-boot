package com.leetjourney.taskmanager.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//? Without @Entity, this class is a POJO (Plain Old Java Object) and it's not a JPA entity.

@Entity // JPA annotation to mark the class as a JPA entity and it's used to map the class to a database table.
@Table(name = "tasks") // JPA annotation to map the class to a database table.
@Data // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods.
@NoArgsConstructor // Lombok annotation to generate a no-args constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields.
public class Task {

    @Id // JPA annotation to mark the field as a primary key.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // JPA annotation to generate a primary key value.
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable=false)
    private String title;

    @Size(min = 2, max = 500, message = "Description must be between 2 and 500 characters")
    @Column(length=500)
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
