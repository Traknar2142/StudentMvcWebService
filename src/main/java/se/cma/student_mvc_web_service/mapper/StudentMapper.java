package se.cma.student_mvc_web_service.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import se.cma.student_mvc_web_service.dto.StudentDto;
import se.cma.student_mvc_web_service.entity.Student;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentMapper {
    private final ModelMapper modelMapper;

    public Student toEntity(StudentDto dto){
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Student.class);
    }

    public StudentDto toDto(Student entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity,StudentDto.class);
    }

    public List<StudentDto> toDtoList(List<Student> listEntity){
        return listEntity
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
