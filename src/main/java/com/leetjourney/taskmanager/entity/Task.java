package com.leetjourney.taskmanager.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods.
@NoArgsConstructor // Lombok annotation to generate a no-args constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields.
public class Task {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDateTime createdAt;
}
