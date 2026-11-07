package com.leetjourney.taskmanager.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetjourney.taskmanager.entity.Task;




@RestController // Spring Boot annotation to mark the class as a REST controller and it combines @Controller and @ResponseBody.
@RequestMapping("/api/${api.version}/tasks")
public class TaskController {

    // Temporary in-memory list of tasks
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }
    

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(nextId++);
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask (@PathVariable Long id, @RequestBody Task updatedTask) {

        //? Way 1: Stream API
        Task existingTask = tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);

        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle() != null ? updatedTask.getTitle() : existingTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription() != null ? updatedTask.getDescription() : existingTask.getDescription());
            existingTask.setCompleted(updatedTask.getCompleted() != null ? updatedTask.getCompleted() : existingTask.getCompleted());
            tasks.set(tasks.indexOf(existingTask), existingTask);
            return existingTask;
        }
        
        return null;

        //? Way 2: For-Each Loop
        // for (Task task : tasks) {
        //     if (task.getId().equals(id)) {
        //         updatedTask.setId(id);
        //         updatedTask.setCreatedAt(task.getCreatedAt());
        //         tasks.set(tasks.indexOf(task), updatedTask);
        //         return updatedTask
        //     }
        // }
        // return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
