package com.example.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.app.domain.Course;
import com.example.app.domain.Student;
import com.example.app.dto.StudentCourseView;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Test
  void findStudentCourseDetailsReturnsInnerJoinRows() {
    Course course = courseRepository.save(new Course("Database Systems", "CS201", 4));
    studentRepository.save(new Student(
      "Test Student",
      "test.student@example.com",
      LocalDate.of(2024, 8, 1),
      course
    ));

    List<StudentCourseView> results = studentRepository.findStudentCourseDetails();

    assertThat(results).hasSize(1);
    assertThat(results.get(0).studentName()).isEqualTo("Test Student");
    assertThat(results.get(0).courseCode()).isEqualTo("CS201");
  }
}
