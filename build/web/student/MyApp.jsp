<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Student Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <nav class="navbar navbar-expand-lg navbar-dark navbar-glass sticky-top mb-4">
            <div class="container">
                <span class="navbar-brand fw-bold">Student Portal</span>
                <div class="d-flex align-items-center gap-3">
                    <span class="text-white-50 small">Welcome, Student</span>
                    <form action="MainController" method="post" class="m-0 d-flex gap-2">
                        <button type="submit" name="action" value="MyClasses" class="btn btn-outline-light btn-sm rounded-pill px-3">My Classes</button>
                        <a href="MainController?action=ShowCourse" class="btn btn-outline-light btn-sm rounded-pill px-3">Browse Courses</a>
                        <button type="submit" name="action" value="Profile" class="btn btn-outline-info btn-sm rounded-pill px-3">My Profile</button>
                        <button type="submit" name="action" value="Logout" class="btn btn-outline-danger btn-sm rounded-pill px-3">Logout</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container mb-5">
            <h2 class="fw-bold text-dark mb-4">Available Courses</h2>

            <c:if test="${not empty MSG}">
                <div class="alert alert-info border-0 shadow-sm rounded-3">${MSG}</div>
            </c:if>

            <div class="glass-panel p-4">
                <div class="table-responsive">
                    <table class="table table-glass text-center mb-0">
                        <thead>
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
                                            <td><span class="badge bg-secondary bg-opacity-75 rounded-pill">${c.courseCode}</span></td>
                                            <td class="fw-bold text-primary">${c.courseTitle}</td>
                                            <td>${c.department}</td>
                                            <td><small>${c.materials}</small></td>
                                            <td><small class="text-muted">${c.description}</small></td>
                                            <td class="text-center">
                                                <form action="MainController" method="POST" class="m-0">
                                                    <input type="hidden" name="courseID" value="${c.courseID}" />
                                                    <button type="submit" name="action" value="ShowClass" class="btn btn-premium shadow-sm btn-sm rounded-pill px-3">View Classes</button>
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