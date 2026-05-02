package com.example.app.repository;

import com.example.app.domain.Student;
import com.example.app.dto.StudentCourseView;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

  @Override
  @EntityGraph(attributePaths = "course")
  List<Student> findAll();

  Optional<Student> findByEmail(String email);

  @Query("""
    select new com.example.app.dto.StudentCourseView(
      s.id,
      s.name,
      s.email,
      s.enrollmentDate,
      c.id,
      c.title,
      c.code,
      c.credits
    )
    from Student s
    inner join s.course c
    order by s.name
    """)
  List<StudentCourseView> findStudentCourseDetails();
}
