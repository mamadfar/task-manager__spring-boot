package com.leetjourney.taskmanager.mapper;

import org.springframework.stereotype.Component;

import com.leetjourney.taskmanager.dto.TaskRequest;
import com.leetjourney.taskmanager.dto.TaskResponse;
import com.leetjourney.taskmanager.entity.Task;

/* 
* Mapper is a class that is used to map the data between the DTO and the entity.
* In this case, instead of map the data manually, like title, description, completed, we can use a mapper to map them one by one from TaskRequest to the Task entity.
*/

@Component // Spring Boot annotation to mark the class as a Spring component and it's used to inject the dependency automatically.
public class TaskMapper {
    
    public Task toEntity(TaskRequest request) {
        return Task.builder()
            .title(request.title())
            .description(request.description())
            .completed(request.completed() != null ? request.completed() : false)
            .build();
    }

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
            .id(task.getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .completed(task.getCompleted())
            .createdAt(task.getCreatedAt())
            .build();
    }

    public void updateEntityFromRequest(Task task, TaskRequest request) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(request.completed() != null ? request.completed() : false);
    }
}
