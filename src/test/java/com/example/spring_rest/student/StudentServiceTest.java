package com.example.spring_rest.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // Which service we want to test

    @InjectMocks
    private StudentService studentService;

    // Declaring dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student() {
        // Given
        StudentDto studentDto = new StudentDto("firstName", "lastName", "email", 0, 1);

        Student student = new Student("firstName", "lastName", "email", 0);

        Student savedStudent = new Student("firstName", "lastName", "email", 0);
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentDtoResponse(savedStudent)).thenReturn(new StudentResponseDto("firstName", "lastName", "email"));

        // When
        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);

        // Then
        assertEquals(studentDto.firstName(), studentResponseDto.firstName());
        assertEquals(studentDto.lastName(), studentResponseDto.lastName());
        assertEquals(studentDto.email(), studentResponseDto.email());

        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentDtoResponse(savedStudent);
    }

    @Test
    public void should_successfully_find_all_students() {
        // Given
        Student student1 = new Student("firstName1", "lastName1", "email1", 1);
        Student student2 = new Student("firstName2", "lastName2", "email2", 2);
        Student student3 = new Student("firstName3", "lastName3", "email3", 3);

        StudentResponseDto studentResponseDto1 = new StudentResponseDto("firstName1", "lastName1", "email1");
        StudentResponseDto studentResponseDto2 = new StudentResponseDto("firstName2", "lastName2", "email2");
        StudentResponseDto studentResponseDto3 = new StudentResponseDto("firstName3", "lastName3", "email3");

        List<Student> students = new ArrayList<>();
        List<StudentResponseDto> studentsResponseDTO = new ArrayList<>();

        students.add(student1);
        students.add(student2);
        students.add(student3);

        studentsResponseDTO.add(studentResponseDto1);
        studentsResponseDTO.add(studentResponseDto2);
        studentsResponseDTO.add(studentResponseDto3);

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentDtoResponse(student1)).thenReturn(studentResponseDto1);
        when(studentMapper.toStudentDtoResponse(student2)).thenReturn(studentResponseDto2);
        when(studentMapper.toStudentDtoResponse(student3)).thenReturn(studentResponseDto3);

        // When
        List<StudentResponseDto> studentsResponseDTOTest = studentService.findAllStudents();

        // Then
        assertEquals(students.size(), studentsResponseDTOTest.size());
        assertEquals(studentsResponseDTO, studentsResponseDTOTest);
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).toStudentDtoResponse(student1);
    }

    @Test
    public void should_successfully_find_student_by_id() {
        // Given
        Integer studentId = 1;

        Student student1 = new Student("firstName1", "lastName1", "email1", 1);

        StudentDto studentDto = new StudentDto("firstName", "lastName", "email", 0, 1);

        student1.setId(studentId);

        StudentResponseDto studentResponseDto1 = new StudentResponseDto("firstName1", "lastName1", "email1");

        // Mock the calls
        when(studentRepository.findById(1)).thenReturn(Optional.of(student1));
        when(studentMapper.toStudentDtoResponse(any(Student.class))).thenReturn(studentResponseDto1);

        // When
        StudentResponseDto studentResponseDTO = studentService.findStudentById(1);

        // Then
        assertEquals(studentResponseDTO.firstName(), studentResponseDto1.firstName());
        assertEquals(studentResponseDTO.lastName(), studentResponseDto1.lastName());
        assertEquals(studentResponseDTO.email(), studentResponseDto1.email());

        verify(studentRepository, times(1)).findById(1);
        verify(studentMapper, times(1)).toStudentDtoResponse(student1);
    }

    @Test
    public void should_successfully_find_students_by_name() {
        // Given

        Student student1 = new Student("firstName1", "lastName1", "email1", 1);

        StudentResponseDto studentDto1 = new StudentResponseDto("firstName1", "lastName1", "email1");

        StudentResponseDto studentResponseDto1 = new StudentResponseDto("firstName1", "lastName1", "email1");

        List<Student> students = new ArrayList<>();
        List<StudentResponseDto> studentsResponseDTO = new ArrayList<>();

        students.add(student1);
        studentsResponseDTO.add(studentDto1);

        // Mock the calls
        when(studentRepository.findAllByFirstNameContaining(student1.getFirstName())).thenReturn(students);
        when(studentMapper.toStudentDtoResponse(any(Student.class))).thenReturn(studentResponseDto1);

        // When
        List<StudentResponseDto> studentsResponseDTO_test = studentService.findStudentsByName(student1.getFirstName());

        // Then
        assertEquals(studentsResponseDTO.size(), students.size());
        assertEquals(studentsResponseDTO, studentsResponseDTO_test);

        verify(studentRepository, times(1)).findAllByFirstNameContaining(student1.getFirstName());
        verify(studentMapper, times(1)).toStudentDtoResponse(any(Student.class));
    }

}