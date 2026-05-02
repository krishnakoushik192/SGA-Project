# Screenshot Checklist

Use this checklist while preparing the final PDF report.

## Application Screens

- `http://localhost:8080/students`: student list showing 10 seeded students.
- `http://localhost:8080/students`: inner join table showing student and course details.
- `http://localhost:8080/students/new`: add student form.
- Student form after successful create operation.
- `http://localhost:8080/students/{id}/edit`: update student form.
- Student list after successful update operation.
- `http://localhost:8080/courses`: course list showing 10 seeded courses.
- `http://localhost:8080/courses/new`: add course form.
- Course form after successful create operation.
- `http://localhost:8080/courses/{id}/edit`: update course form.
- Course list after successful update operation.

## Validation Screens

- Duplicate email validation message on the student form.
- Duplicate course code validation message on the course form.

## Code Screenshots for Report

- `Student.java` and `Course.java` showing entity annotations.
- `StudentRepository.java` showing the inner join query.
- `StudentService.java` or `CourseService.java` showing service logic.
- `StudentController.java` or `CourseController.java` showing controller methods.
- A JSP form page showing JSTL/EL or Spring form binding.
- Test output showing `mvn test` passing.
