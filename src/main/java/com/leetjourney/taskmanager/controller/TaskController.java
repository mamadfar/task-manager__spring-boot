package com.leetjourney.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leetjourney.taskmanager.dto.TaskRequest;
import com.leetjourney.taskmanager.dto.TaskResponse;
import com.leetjourney.taskmanager.entity.Task;
import com.leetjourney.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController // Spring Boot annotation to mark the class as a REST controller and it combines @Controller and @ResponseBody.
@RequestMapping("/api/${api.version}/tasks")
public class TaskController {

    private final TaskService taskService;

    //? Constructor Injection
    // @Autowired // Spring Boot annotation to inject the dependency automatically. Not needed because Spring Boot will automatically inject the dependency.
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // @GetMapping
    // public List<Task> getAllTasks() {
    //     return taskService.getAllTasks();
    // }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("ASC")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Task> taskPage = taskService.getAllTasks(pageable);
        
        List<TaskResponse> tasks = taskPage.getContent()
            .stream()
            .map(task -> new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted(),
                task.getCreatedAt()
            ))
            .toList();

            Map<String, Object> response = new HashMap<>();
            response.put("tasks", tasks);
            response.put("currentPage", taskPage.getNumber());
            response.put("totalItems", taskPage.getTotalElements());
            response.put("totalPages", taskPage.getTotalPages());
            response.put("hasNext", taskPage.hasNext());
            response.put("hasPrevious", taskPage.hasPrevious());

            return new ResponseEntity<>(response, HttpStatus.OK);
    }
        

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse savedTask = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest updatedTaskRequest) {
        return taskService.updateTask(id, updatedTaskRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/completed/{status}")
    public List<TaskResponse> getTasksByCompletions(@PathVariable boolean status) {
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/search")
    public List<TaskResponse> searchTasksByTitle(@RequestParam String title) {
        return taskService.searchTasksByTitle(title);
    }

}
