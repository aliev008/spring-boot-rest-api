package com.example.spring_rest.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void should_convert_dto_to_student() {
        StudentDto studentDto = new StudentDto("firstName", "lastName", "email", 21, 1);
        Student student = mapper.toStudent(studentDto);

        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());
        assertEquals(studentDto.age(), student.getAge());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_nullPointerException_when_studentDTO_is_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("Student DTO should not be null", exception.getMessage());
    }

    @Test
    public void should_convert_from_student_to_student_response_dto() {

        // Given
        Student student = new Student("firstName", "lastName", "email", 21);

        // When
        StudentResponseDto studentDto = mapper.toStudentDtoResponse(student);

        // Then
        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());
    }
}