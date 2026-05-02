package com.example.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Student name is required")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  @Column(nullable = false, unique = true)
  private String email;

  @NotNull(message = "Enrollment date is required")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(nullable = false)
  private LocalDate enrollmentDate;

  @NotNull(message = "Course is required")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public Student() {
  }

  public Student(String name, String email, LocalDate enrollmentDate, Course course) {
    this.name = name;
    this.email = email;
    this.enrollmentDate = enrollmentDate;
    this.course = course;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(LocalDate enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
