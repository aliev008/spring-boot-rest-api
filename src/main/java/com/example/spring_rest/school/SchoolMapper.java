package com.example.spring_rest.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School dtoToSchool(SchoolDto schoolDto) {

        School school = new School();
        school.setName(schoolDto.name());

        return school;
    }

    public SchoolResponseDto schoolToDto(School school) {
        return new SchoolResponseDto(school.getName());
    }
}
