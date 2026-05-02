package com.example.app.config;

import com.example.app.domain.Course;
import com.example.app.domain.Student;
import com.example.app.repository.CourseRepository;
import com.example.app.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final CourseRepository courseRepository;
  private final StudentRepository studentRepository;

  public DataInitializer(CourseRepository courseRepository, StudentRepository studentRepository) {
    this.courseRepository = courseRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public void run(String... args) {
    if (courseRepository.count() > 0 || studentRepository.count() > 0) {
      return;
    }

    List<Course> courses = courseRepository.saveAll(List.of(
      new Course("Introduction to Programming", "CS101", 4),
      new Course("Database Systems", "CS201", 4),
      new Course("Web Development", "CS220", 3),
      new Course("Data Structures", "CS230", 4),
      new Course("Operating Systems", "CS301", 4),
      new Course("Computer Networks", "CS310", 3),
      new Course("Software Engineering", "CS320", 3),
      new Course("Artificial Intelligence", "CS401", 4),
      new Course("Cyber Security", "CS410", 3),
      new Course("Cloud Computing", "CS420", 3)
    ));

    studentRepository.saveAll(List.of(
      new Student("Aarav Sharma", "aarav.sharma@example.com", LocalDate.of(2024, 7, 1), courses.get(0)),
      new Student("Diya Patel", "diya.patel@example.com", LocalDate.of(2024, 7, 2), courses.get(1)),
      new Student("Rohan Mehta", "rohan.mehta@example.com", LocalDate.of(2024, 7, 3), courses.get(2)),
      new Student("Isha Nair", "isha.nair@example.com", LocalDate.of(2024, 7, 4), courses.get(3)),
      new Student("Kabir Rao", "kabir.rao@example.com", LocalDate.of(2024, 7, 5), courses.get(4)),
      new Student("Anika Singh", "anika.singh@example.com", LocalDate.of(2024, 7, 6), courses.get(5)),
      new Student("Vihaan Gupta", "vihaan.gupta@example.com", LocalDate.of(2024, 7, 7), courses.get(6)),
      new Student("Meera Joshi", "meera.joshi@example.com", LocalDate.of(2024, 7, 8), courses.get(7)),
      new Student("Arjun Verma", "arjun.verma@example.com", LocalDate.of(2024, 7, 9), courses.get(8)),
      new Student("Sara Khan", "sara.khan@example.com", LocalDate.of(2024, 7, 10), courses.get(9))
    ));
  }
}
