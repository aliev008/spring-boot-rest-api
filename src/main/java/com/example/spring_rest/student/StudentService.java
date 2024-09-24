package com.example.spring_rest.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        return studentMapper.toStudentDtoResponse(studentRepository.save(student));
    }

    public List<StudentResponseDto> findAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDtoResponse).toList();
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    public StudentResponseDto findStudentById(Integer id) {
        return studentRepository.findById(id).map(studentMapper::toStudentDtoResponse).orElse(studentMapper.toStudentDtoResponse(new Student()));
    }

    public List<StudentResponseDto> findStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name).stream().map(studentMapper::toStudentDtoResponse).toList();
    }
}
