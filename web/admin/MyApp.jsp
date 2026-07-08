<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard - Course Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <nav class="navbar navbar-expand-lg navbar-dark navbar-glass sticky-top mb-4">
            <div class="container">
                <span class="navbar-brand fw-bold">Admin Dashboard</span>
                <div class="d-flex align-items-center gap-3">
                    <span class="text-white-50 small">Welcome, Admin</span>
                    <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                        <button type="submit" name="action" value="Logout" class="btn btn-outline-light btn-sm rounded-pill px-3">Logout</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container mb-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h3 class="fw-bold text-dark mb-0">Course Management</h3>
                <div class="d-flex gap-2">
                    <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                        <button type="submit" name="action" value="ShowCourse" class="btn btn-light btn-sm shadow-sm rounded-pill px-3">Refresh List</button>
                        <button name="action" value="Profile" class="btn btn-info btn-sm text-white shadow-sm rounded-pill px-3">
                            <input type="hidden" name="userID" value="${user.userID}">
                            My Profile
                        </button>
                    </form>

                    <a href="${pageContext.request.contextPath}/admin/CreateCourse.jsp" class="btn btn-premium btn-sm d-flex align-items-center shadow-sm rounded-pill px-3">
                        + New Course
                    </a>

                    <a href="${pageContext.request.contextPath}/MainController?action=editUser" class="btn btn-secondary btn-sm shadow-sm rounded-pill px-3">Edit User</a>
                </div>
            </div>

            <c:if test="${not empty MSG}">
                <div class="alert alert-warning border-0 shadow-sm rounded-3">${MSG}</div>
            </c:if>

            <div class="glass-panel p-4 mb-5">
                <div class="table-responsive">
                    <table class="table table-glass text-center mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Title</th>
                                <th>Dept</th>
                                <th>Materials</th>
                                <th style="width: 200px;">Description</th>
                                <th colspan="3">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty CourseList}">
                                <tr><td colspan="9" class="text-muted fst-italic py-4">No courses found.</td></tr>
                            </c:if>
                            <c:forEach var="c" items="${CourseList}">
                                <tr>
                                    <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                                        <td><input type="text" name="courseID" value="${c.courseID}" class="form-control form-control-sm text-center" readonly style="background: rgba(0,0,0,0.05)!important; border:none!important;"></td>
                                        <td><input type="text" name="courseCode" value="${c.courseCode}" class="form-control form-control-sm text-center"></td>
                                        <td><input type="text" name="courseTitle" value="${c.courseTitle}" class="form-control form-control-sm text-center"></td>
                                        <td><input type="text" name="department" value="${c.department}" class="form-control form-control-sm text-center"></td>
                                        <td><input type="text" name="courseMaterials" value="${c.materials}" class="form-control form-control-sm text-center"></td>
                                        <td><input type="text" name="courseDescription" value="${c.description}" class="form-control form-control-sm text-center"></td>
                                        <input type="hidden" name="courseState" value="${c.courseState}">
                                        <td>
                                            <button type="submit" name="action" value="UpdateCourse" class="btn btn-primary btn-sm rounded-pill px-3 shadow-sm">Update</button>
                                        </td>
                                    </form>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/MainController" onsubmit="return confirm('Are you sure you want to delete this course?');" method="POST" class="m-0">
                                            <input type="hidden" name="courseID" value="${c.courseID}" />
                                            <button type="submit" name="action" value="DeleteCourse" class="btn btn-danger btn-sm rounded-pill px-3 shadow-sm">Delete</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                                            <input type="hidden" name="courseID" value="${c.courseID}" />
                                            <button type="submit" name="action" value="ShowClass" class="btn btn-warning btn-sm text-white rounded-pill px-3 shadow-sm">Classes</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>