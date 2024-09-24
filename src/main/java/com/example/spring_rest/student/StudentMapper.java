package com.example.spring_rest.student;

import com.example.spring_rest.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto dto) {

        if (dto == null) {
            throw new NullPointerException("Student DTO should not be null");
        }

        Student student = new Student();
        School school = new School();

        school.setId(dto.schoolId());

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setAge(dto.age());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentDtoResponse(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
