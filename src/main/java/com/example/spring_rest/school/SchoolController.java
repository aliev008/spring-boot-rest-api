package com.example.spring_rest.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolRepository schoolRepository, SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolResponseDto addSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        return schoolService.addSchool(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getSchools(
    ) {
        return schoolService.getSchools();
    }
}
