package se.cma.student_mvc_web_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @Null
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("middleName")
    private String middleName;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("birthdayDate")
    private LocalDate birthdayDate;

    @NotNull
    @JsonProperty("groupName")
    private String group;
}