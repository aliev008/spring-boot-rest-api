package com.example.spring_rest.student;

public record StudentResponseDto(
        String firstName,
        String lastName,
        String email
) {
}
