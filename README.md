# Student-Course CRUD Spring Boot Application

## Run

```bash
.\mvnw.cmd spring-boot:run
```

Open the web application at:

- `http://localhost:8080/students`
- `http://localhost:8080/courses`

## Test

```bash
.\mvnw.cmd test
```

## Features

- JPA entities for `Student` and `Course`
- One course can have many students; each student belongs to one course
- Create, read, and update operations through JSP pages
- Startup seed data with 10 courses and 10 students
- Custom repository query using an inner join between students and courses
- Validation and duplicate-value handling for student email and course code
- Repository and service tests using JUnit, Spring Data JPA tests, and Mockito

## Important Routes

- `GET /students`: list students and inner-join student-course results
- `GET /students/new`: student creation form
- `POST /students`: create student
- `GET /students/{id}/edit`: student update form
- `POST /students/{id}`: update student
- `GET /courses`: list courses
- `GET /courses/new`: course creation form
- `POST /courses`: create course
- `GET /courses/{id}/edit`: course update form
- `POST /courses/{id}`: update course

## Structure

- `pom.xml`: Maven build and dependency management
- `src/main/java/com/example/app/Application.java`: application entry point
- `src/main/java/com/example/app/health/HealthController.java`: health and root endpoints
- `src/main/resources/application.yml`: app configuration and actuator setup
- `src/main/java/com/example/app/domain`: JPA entity classes
- `src/main/java/com/example/app/repository`: Spring Data repositories
- `src/main/java/com/example/app/service`: business logic services
- `src/main/java/com/example/app/controller`: Spring MVC controllers
- `src/main/webapp/WEB-INF/views`: JSP views
- `src/main/resources/static/css/style.css`: page styling
- `docs/report.md`: report draft for PDF submission
- `docs/screenshot-checklist.md`: screenshot checklist for the report
