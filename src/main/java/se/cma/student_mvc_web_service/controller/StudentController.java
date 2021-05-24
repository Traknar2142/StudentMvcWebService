package se.cma.student_mvc_web_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@Validated @PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> addStudent(@Validated @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @PutMapping(value ="update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> updateStudent(@Validated @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(studentDto));
    }

    @PutMapping(value ="update_name", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> updateStudentName(@Validated @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudentName(studentDto));
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@Validated @PathVariable Long id) {
        studentService.deleteById(id);
    }
}
