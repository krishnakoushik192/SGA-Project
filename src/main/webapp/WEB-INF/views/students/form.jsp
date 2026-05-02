<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>${formTitle}</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="container narrow">
  <nav class="nav">
    <a href="<c:url value='/students' />">Students</a>
    <a href="<c:url value='/courses' />">Courses</a>
  </nav>

  <section class="card">
    <h1>${formTitle}</h1>
    <c:url var="actionUrl" value="${formAction}" />
    <form:form method="post" action="${actionUrl}" modelAttribute="student" class="form">
      <label for="name">Name</label>
      <form:input path="name" id="name" />
      <form:errors path="name" cssClass="error" />

      <label for="email">Email</label>
      <form:input path="email" id="email" type="email" />
      <form:errors path="email" cssClass="error" />

      <label for="enrollmentDate">Enrollment Date</label>
      <form:input path="enrollmentDate" id="enrollmentDate" type="date" />
      <form:errors path="enrollmentDate" cssClass="error" />

      <label for="course">Course</label>
      <select id="course" name="course.id" required>
        <option value="">Select a course</option>
        <c:forEach var="course" items="${courses}">
          <option value="${course.id}" ${student.course.id == course.id ? 'selected' : ''}>
            ${course.code} - ${course.title}
          </option>
        </c:forEach>
      </select>
      <form:errors path="course" cssClass="error" />

      <div class="actions">
        <button class="button" type="submit">Save Student</button>
        <a class="button secondary" href="<c:url value='/students' />">Cancel</a>
      </div>
    </form:form>
  </section>
</main>
</body>
</html>
