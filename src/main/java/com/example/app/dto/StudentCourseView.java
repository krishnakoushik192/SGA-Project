package com.example.app.dto;

import java.time.LocalDate;

public record StudentCourseView(
  Long studentId,
  String studentName,
  String studentEmail,
  LocalDate enrollmentDate,
  Long courseId,
  String courseTitle,
  String courseCode,
  Integer courseCredits
) {
}
