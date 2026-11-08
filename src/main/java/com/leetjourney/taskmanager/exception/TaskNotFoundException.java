package com.leetjourney.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException { // RuntimeException means that the exception is not checked at compile time and happens when the program is running.
    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
