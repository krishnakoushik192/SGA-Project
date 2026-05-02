<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Courses</title>
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
      <p class="eyebrow">Course Catalog</p>
      <h1>Courses</h1>
      <p>Maintain course records and assign students to courses.</p>
    </div>
    <a class="button" href="<c:url value='/courses/new' />">Add Course</a>
  </section>

  <c:if test="${not empty successMessage}">
    <div class="alert success">${successMessage}</div>
  </c:if>

  <section class="card">
    <h2>All Courses</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Title</th>
        <th>Credits</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="course" items="${courses}">
        <tr>
          <td>${course.id}</td>
          <td>${course.code}</td>
          <td>${course.title}</td>
          <td>${course.credits}</td>
          <td><a class="link" href="<c:url value='/courses/${course.id}/edit' />">Edit</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </section>
</main>
</body>
</html>
