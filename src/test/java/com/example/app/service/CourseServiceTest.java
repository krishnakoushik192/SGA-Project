package com.example.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.app.domain.Course;
import com.example.app.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

  @Mock
  private CourseRepository courseRepository;

  @InjectMocks
  private CourseService courseService;

  @Test
  void createCourseSavesCourse() {
    Course course = new Course("Web Development", "CS220", 3);
    when(courseRepository.save(course)).thenReturn(course);

    Course result = courseService.createCourse(course);

    assertThat(result).isSameAs(course);
    verify(courseRepository).save(course);
  }

  @Test
  void findAllCoursesReturnsRepositoryRows() {
    when(courseRepository.findAll()).thenReturn(List.of(new Course("Cloud Computing", "CS420", 3)));

    List<Course> courses = courseService.findAllCourses();

    assertThat(courses).hasSize(1);
    assertThat(courses.get(0).getCode()).isEqualTo("CS420");
  }

  @Test
  void updateCourseChangesExistingCourse() {
    Course existing = new Course("Old Title", "CS100", 2);
    Course update = new Course("New Title", "CS101", 4);
    when(courseRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(courseRepository.save(existing)).thenReturn(existing);

    Course result = courseService.updateCourse(1L, update);

    assertThat(result.getTitle()).isEqualTo("New Title");
    assertThat(result.getCode()).isEqualTo("CS101");
    assertThat(result.getCredits()).isEqualTo(4);
  }
}
