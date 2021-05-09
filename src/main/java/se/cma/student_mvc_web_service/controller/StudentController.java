package se.cma.student_mvc_web_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.cma.student_mvc_web_service.dto.StudentDto;
import se.cma.student_mvc_web_service.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentDto getStudentById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto addStudent(@RequestBody StudentDto studentDto){
        return  studentService.createStudent(studentDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
    }
}
