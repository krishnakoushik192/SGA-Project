package com.example.app.service;

import com.example.app.domain.Course;
import com.example.app.domain.Student;
import com.example.app.dto.StudentCourseView;
import com.example.app.repository.CourseRepository;
import com.example.app.repository.StudentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;

  public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  public Student createStudent(Student student, Long courseId) {
    student.setCourse(findCourse(courseId));
    return studentRepository.save(student);
  }

  @Transactional(readOnly = true)
  public List<Student> findAllStudents() {
    return studentRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<StudentCourseView> findStudentCourseDetails() {
    return studentRepository.findStudentCourseDetails();
  }

  @Transactional(readOnly = true)
  public Student findStudent(Long id) {
    return studentRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));
  }

  public Student updateStudent(Long id, Student updatedStudent, Long courseId) {
    Student student = findStudent(id);
    student.setName(updatedStudent.getName());
    student.setEmail(updatedStudent.getEmail());
    student.setEnrollmentDate(updatedStudent.getEnrollmentDate());
    student.setCourse(findCourse(courseId));
    return studentRepository.save(student);
  }

  private Course findCourse(Long courseId) {
    return courseRepository.findById(courseId)
      .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));
  }
}
