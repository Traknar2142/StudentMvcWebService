package se.cma.student_mvc_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.cma.student_mvc_web_service.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
