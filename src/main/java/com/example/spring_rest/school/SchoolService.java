package com.example.spring_rest.school;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolResponseDto addSchool(SchoolDto schoolDto) {
        var school = schoolMapper.dtoToSchool(schoolDto);
        return schoolMapper.schoolToDto(schoolRepository.save(school));
    }

    public List<SchoolResponseDto> getSchools() {
        return schoolRepository
                .findAll()
                .stream()
                .map(schoolMapper::schoolToDto)
                .toList();
    }
}
