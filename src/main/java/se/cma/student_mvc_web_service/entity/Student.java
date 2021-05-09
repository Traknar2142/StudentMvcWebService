package se.cma.student_mvc_web_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_STUDENT")
public class Student {
    @Id
    @SequenceGenerator(name="id_generator",sequenceName="id_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

   @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "group_name")
    private String group;
}
