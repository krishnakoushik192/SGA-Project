<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Students</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="container">
  <nav class="nav">
    <a href="<c:url value='/students' />">Students</a>
    <a href="<c:url value='/courses' />">Courses</a>
  </nav>

  <section class="hero">
    <div>
      <p class="eyebrow">Student-Course Management</p>
      <h1>Students</h1>
      <p>Create, read, and update student records with course assignments.</p>
    </div>
    <a class="button" href="<c:url value='/students/new' />">Add Student</a>
  </section>

  <c:if test="${not empty successMessage}">
    <div class="alert success">${successMessage}</div>
  </c:if>

  <section class="card">
    <h2>All Students</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Enrollment Date</th>
        <th>Course</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="student" items="${students}">
        <tr>
          <td>${student.id}</td>
          <td>${student.name}</td>
          <td>${student.email}</td>
          <td>${student.enrollmentDate}</td>
          <td>${student.course.title}</td>
          <td><a class="link" href="<c:url value='/students/${student.id}/edit' />">Edit</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </section>

  <section class="card">
    <h2>Inner Join Result: Students With Courses</h2>
    <table>
      <thead>
      <tr>
        <th>Student</th>
        <th>Email</th>
        <th>Course Code</th>
        <th>Course Title</th>
        <th>Credits</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="row" items="${studentCourseDetails}">
        <tr>
          <td>${row.studentName()}</td>
          <td>${row.studentEmail()}</td>
          <td>${row.courseCode()}</td>
          <td>${row.courseTitle()}</td>
          <td>${row.courseCredits()}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </section>
</main>
</body>
</html>
