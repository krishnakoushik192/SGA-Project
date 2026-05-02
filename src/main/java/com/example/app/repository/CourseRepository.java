package com.example.app.repository;

import com.example.app.domain.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByCode(String code);
}
