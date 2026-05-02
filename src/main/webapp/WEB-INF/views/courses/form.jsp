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
    <form:form method="post" action="${actionUrl}" modelAttribute="course" class="form">
      <label for="code">Course Code</label>
      <form:input path="code" id="code" placeholder="CS101" />
      <form:errors path="code" cssClass="error" />

      <label for="title">Title</label>
      <form:input path="title" id="title" placeholder="Introduction to Computer Science" />
      <form:errors path="title" cssClass="error" />

      <label for="credits">Credits</label>
      <form:input path="credits" id="credits" type="number" min="1" max="6" />
      <form:errors path="credits" cssClass="error" />

      <div class="actions">
        <button class="button" type="submit">Save Course</button>
        <a class="button secondary" href="<c:url value='/courses' />">Cancel</a>
      </div>
    </form:form>
  </section>
</main>
</body>
</html>
