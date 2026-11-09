package com.leetjourney.taskmanager.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//? Without @Entity, this class is a POJO (Plain Old Java Object) and it's not a JPA entity.

@Entity // JPA annotation to mark the class as a JPA entity and it's used to map the class to a database table.
@Table(name = "tasks") // JPA annotation to map the class to a database table.
@Data // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods.
@Builder // Lombok annotation to generate a builder for the class. A builder is a way to create an object with a builder pattern.
@NoArgsConstructor // Lombok annotation to generate a no-args constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields.
public class Task {

    @Id // JPA annotation to mark the field as a primary key.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // JPA annotation to generate a primary key value.
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(length=500)
    private String description;

    @Column(nullable = false)
    private Boolean completed;

    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
