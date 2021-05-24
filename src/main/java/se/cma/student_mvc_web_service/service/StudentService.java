package se.cma.student_mvc_web_service.service;

import se.cma.student_mvc_web_service.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAll();

    StudentDto findById(Long id);

    StudentDto createStudent(StudentDto user);

    StudentDto updateStudent(StudentDto user);

    StudentDto updateStudentName(StudentDto user);

    void deleteById(Long id);
}
