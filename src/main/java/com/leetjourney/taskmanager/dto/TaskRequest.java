package com.leetjourney.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/* 
* DTO (Data Transfer Object) is used to transfer data between the client and the server.
* We should use DTOs to transfer data between the client and the server instead of using the entity class directly.
* This is because the entity class is used to map the data to the database and the DTO class is used to transfer the data between the client and the server.
* We might not want to expose all the fields of the entity class to the client. It's better to keep the sensitive data private and only expose the necessary data to the client.
* ------------------------------------------------------------------------------------------------------------------------
* Record is a new feature in Java 16. It's a way to create an immutable class with a constructor, getters, and equals methods.
* It's a way to hold data and transfer it between the client and the server and not modify the data.
*/

public record TaskRequest(
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    String title,

    @Size(min = 2, max = 500, message = "Description must be between 2 and 500 characters")
    String description,
    
    Boolean completed,

    Long categoryId
) {}
