package se.cma.student_mvc_web_service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import se.cma.student_mvc_web_service.dto.StudentDto;
import se.cma.student_mvc_web_service.entity.Student;
import se.cma.student_mvc_web_service.exceptions.StudentNotFoundException;
import se.cma.student_mvc_web_service.mapper.StudentMapper;
import se.cma.student_mvc_web_service.repository.StudentRepository;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        List<Student> studentsEntities = studentRepository.findAll();
        return studentMapper.toDtoList(studentsEntities);
    }

    @Override
    public StudentDto findById(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found")));
    }

    @Override
    public StudentDto createStudent(StudentDto student) {
        Student studentEntity = studentRepository.save(studentMapper.toEntity(student));
        return studentMapper.toDto(studentEntity);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student studentEntity = studentMapper.toEntity(studentDto);
        studentRepository.findById(studentEntity.getId()).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return studentMapper.toDto(studentRepository.save(studentEntity));
    }

    @Override
    public StudentDto updateStudentName(StudentDto user) {
        Student studentEntity = studentRepository.findById(user.getId()).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        studentEntity.builder()
                .firstName(user.getFirstName())
                .build();
        return studentMapper.toDto(studentRepository.save(studentEntity));
    }

    @Override
    public void deleteById(Long id) {
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        studentRepository.delete(studentEntity);
    }
}
