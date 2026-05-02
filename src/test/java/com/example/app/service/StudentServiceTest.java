package com.example.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.app.domain.Course;
import com.example.app.domain.Student;
import com.example.app.dto.StudentCourseView;
import com.example.app.repository.CourseRepository;
import com.example.app.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private CourseRepository courseRepository;

  @InjectMocks
  private StudentService studentService;

  @Test
  void createStudentAssignsCourseAndSaves() {
    Course course = new Course("Data Structures", "CS230", 4);
    Student student = new Student("Student One", "one@example.com", LocalDate.of(2024, 8, 1), null);
    when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
    when(studentRepository.save(student)).thenReturn(student);

    Student result = studentService.createStudent(student, 1L);

    assertThat(result.getCourse()).isSameAs(course);
    verify(studentRepository).save(student);
  }

  @Test
  void updateStudentChangesExistingStudent() {
    Course course = new Course("Operating Systems", "CS301", 4);
    Student existing = new Student("Old", "old@example.com", LocalDate.of(2024, 7, 1), course);
    Student update = new Student("New", "new@example.com", LocalDate.of(2024, 9, 1), null);
    when(studentRepository.findById(5L)).thenReturn(Optional.of(existing));
    when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
    when(studentRepository.save(existing)).thenReturn(existing);

    Student result = studentService.updateStudent(5L, update, 2L);

    assertThat(result.getName()).isEqualTo("New");
    assertThat(result.getEmail()).isEqualTo("new@example.com");
    assertThat(result.getEnrollmentDate()).isEqualTo(LocalDate.of(2024, 9, 1));
    assertThat(result.getCourse()).isSameAs(course);
  }

  @Test
  void findStudentCourseDetailsDelegatesToRepository() {
    StudentCourseView row = new StudentCourseView(
      1L,
      "Student",
      "student@example.com",
      LocalDate.of(2024, 8, 1),
      2L,
      "Cyber Security",
      "CS410",
      3
    );
    when(studentRepository.findStudentCourseDetails()).thenReturn(List.of(row));

    List<StudentCourseView> results = studentService.findStudentCourseDetails();

    assertThat(results).containsExactly(row);
  }
}
