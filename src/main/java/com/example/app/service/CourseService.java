package com.example.app.service;

import com.example.app.domain.Course;
import com.example.app.repository.CourseRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  @Transactional(readOnly = true)
  public List<Course> findAllCourses() {
    return courseRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Course findCourse(Long id) {
    return courseRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));
  }

  public Course updateCourse(Long id, Course updatedCourse) {
    Course course = findCourse(id);
    course.setTitle(updatedCourse.getTitle());
    course.setCode(updatedCourse.getCode());
    course.setCredits(updatedCourse.getCredits());
    return courseRepository.save(course);
  }
}
