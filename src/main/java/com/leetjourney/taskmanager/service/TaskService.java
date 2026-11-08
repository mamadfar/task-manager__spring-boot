package com.leetjourney.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leetjourney.taskmanager.dto.TaskRequest;
import com.leetjourney.taskmanager.dto.TaskResponse;
import com.leetjourney.taskmanager.entity.Task;
import com.leetjourney.taskmanager.exception.TaskNotFoundException;
import com.leetjourney.taskmanager.mapper.TaskMapper;
import com.leetjourney.taskmanager.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service // Services provides business logic.
@Transactional // Transactions are used to ensure data consistency and integrity. Either all or nothing.
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(task);
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        //? Instead of this:
        // Task entityTask = Task.builder()
        //     .title(taskRequest.title())
        //     .description(taskRequest.description())
        //     .completed(taskRequest.completed())
        //     .build();
        //? We do mapping like this:
        Task entityTask = taskMapper.toEntity(taskRequest);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTaskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        taskMapper.updateEntityFromRequest(task, updatedTaskRequest);
            return taskMapper.toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
            taskRepository.delete(task);
    }

    public List<TaskResponse> getTasksByCompletionStatus(boolean status) {
        final List<Task> completedTasks = taskRepository.findByCompleted(status);
        return completedTasks.stream().map(taskMapper::toResponse).toList();
    }

    public Page<TaskResponse> getTasksByCompletionStatus(boolean status, Pageable pageable) {
        final Page<Task> completedTasks = taskRepository.findByCompleted(status, pageable);
        return completedTasks.map(taskMapper::toResponse);
    }

    public List<TaskResponse> searchTasksByTitle(String title) {
        final List<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(title);
        return tasks.stream().map(taskMapper::toResponse).toList();
    }
    
}
