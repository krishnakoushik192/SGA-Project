package com.example.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Course title is required")
  @Column(nullable = false)
  private String title;

  @NotBlank(message = "Course code is required")
  @Column(nullable = false, unique = true, length = 20)
  private String code;

  @NotNull(message = "Credits are required")
  @Min(value = 1, message = "Credits must be at least 1")
  @Max(value = 6, message = "Credits cannot exceed 6")
  @Column(nullable = false)
  private Integer credits;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = false)
  private List<Student> students = new ArrayList<>();

  public Course() {
  }

  public Course(String title, String code, Integer credits) {
    this.title = title;
    this.code = code;
    this.credits = credits;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getCredits() {
    return credits;
  }

  public void setCredits(Integer credits) {
    this.credits = credits;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
