package se.cma.student_mvc_web_service.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.cma.student_mvc_web_service.dto.StudentDto;
import se.cma.student_mvc_web_service.entity.Student;
import se.cma.student_mvc_web_service.exceptions.StudentNotFoundException;
import se.cma.student_mvc_web_service.mapper.StudentMapper;
import se.cma.student_mvc_web_service.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

    private StudentDto studentDto;

    private Student student;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Before
    public void setUp() {
        studentDto = StudentDto.builder()
                .firstName("FirstName")
                .middleName("middleName")
                .lastName("lastName")
                .birthdayDate(LocalDate.of(1991, 12, 3))
                .group("group-1")
                .build();

        student = Student.builder()
                .firstName("FirstName")
                .middleName("middleName")
                .lastName("lastName")
                .birthdayDate(LocalDate.of(1991, 12, 3))
                .group("group-1")
                .build();
    }

    @Test
    public void findAll_ShouldReturnListOfStudentDto() {
        List<Student> students = Arrays.asList(student);
        List<StudentDto> studentsDto = Arrays.asList(studentDto);

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toDtoList(students)).thenReturn(studentsDto);

        List<StudentDto> actualList = studentService.findAll();

        assertThat(actualList).isEqualTo(studentsDto);
    }

    @Test
    public void findById_ShouldReturnStudentDto() {
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDto);
        when(studentMapper.toEntity(any(StudentDto.class))).thenReturn(student);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(student));

        StudentDto actualStudentDto = studentService.findById(1L);

        assertThat(actualStudentDto).isNotNull();
        assertThat(actualStudentDto).isEqualTo(studentDto);
    }

    @Test(expected = StudentNotFoundException.class)
    public void findById_ShouldThrowStudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenThrow(new StudentNotFoundException("Student not found"));

        studentService.findById(1L);
    }

    @Test
    public void createStudent_ShouldReturnTheSameStudentDtoAsTheInput() {
        when(studentMapper.toEntity(any(StudentDto.class))).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDto);

        StudentDto actualStudentDto = studentService.createStudent(studentDto);

        assertThat(actualStudentDto).isEqualTo(studentDto);
    }

    @Test
    public void updateStudent_ShouldReturnStudentDto() {
        when(studentMapper.toEntity(any(StudentDto.class))).thenReturn(student);
        when(studentRepository.findById(any())).thenReturn(Optional.ofNullable(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDto);

        StudentDto actualStudentDto = studentService.updateStudent(studentDto);
        assertThat(actualStudentDto).isEqualTo(studentDto);
    }

    @Test(expected = StudentNotFoundException.class)
    public void updateStudent_ShouldThrowStudentNotFoundException() {
        when(studentMapper.toEntity(any(StudentDto.class))).thenReturn(student);
        when(studentRepository.findById(anyLong())).thenThrow(new StudentNotFoundException("Student not found"));

        studentService.updateStudent(studentDto);
    }

    @Test(expected = StudentNotFoundException.class)
    public void deleteById_ShouldThrowStudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenThrow(new StudentNotFoundException("Student not found"));

        studentService.deleteById(1L);
    }
}
