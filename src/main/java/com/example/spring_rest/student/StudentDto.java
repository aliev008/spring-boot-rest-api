package com.example.spring_rest.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "Firstname must not be empty")
        String firstName,
        @NotEmpty(message = "Lastname must not be empty")
        String lastName,
        String email,
        int age,
        Integer schoolId
) {
}
