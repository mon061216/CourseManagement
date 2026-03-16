<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Student Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-dark bg-primary mb-4">
            <div class="container">
                <span class="navbar-brand mb-0 h1">Student Portal</span>
                <div class="d-flex gap-2">
                    <form action="MainController" method="post" class="m-0">
                        <button type="submit" name="action" value="MyClasses" class="btn btn-light btn-sm">My Classes</button>
                    </form>
                    <div class="container">
                        <a href="MainController?action=ShowCourse" class="btn btn-outline-light btn-sm">Browse Courses</a>
                    </div>
                    <a href="admin/User.jsp" class="btn btn-outline-light btn-sm">Edit Profile</a>
                    <form action="MainController" method="POST" class="m-0">
                        <button name="action" value="Profile">
                            My Profile
                        </button>
                        <button type="submit" name="action" value="Logout" class="btn btn-danger btn-sm">Logout</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container">
            <h2 class="mb-4">Available Courses</h2>

            <c:if test="${not empty MSG}">
                <div class="alert alert-info">${MSG}</div>
            </c:if>

            <div class="card shadow-sm">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                            <tr>
                                <th>Code</th>
                                <th>Title</th>
                                <th>Department</th>
                                <th>Materials</th>
                                <th>Description</th>
                                <th class="text-center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty CourseList}">
                                    <tr><td colspan="6" class="text-center py-4">No courses found.</td></tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="c" items="${CourseList}">
                                        <tr>
                                            <td><span class="badge bg-secondary">${c.courseCode}</span></td>
                                            <td class="fw-bold">${c.courseTitle}</td>
                                            <td>${c.department}</td>
                                            <td><small>${c.materials}</small></td>
                                            <td><small class="text-muted">${c.description}</small></td>
                                            <td class="text-center">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="courseID" value="${c.courseID}" />
                                                    <button type="submit" name="action" value="ShowClass" class="btn btn-primary btn-sm">View Classes</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>