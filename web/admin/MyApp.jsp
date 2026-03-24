<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <nav class="navbar navbar-dark bg-dark mb-4">
            <div class="container">
                <span class="navbar-brand">Admin Dashboard</span>
                <form action="MainController" method="POST" class="d-inline">
                    <input type="submit" name="action" value="Logout" class="btn btn-outline-light btn-sm">
                </form>
            </div>
        </nav>

        <div class="container">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3>Course Management</h3>
                <div>
                    <form action="MainController" method="POST" class="d-inline">
                        <button type="submit" name="action" value="ShowCourse" class="btn btn-info btn-sm">Refresh List</button>
                        <button name="action" value="Profile">
                            <input type="hidden" name="userID" value="${user.userID}">
                            My Profile
                        </button>
                    </form>

                    <a href="admin/CreateCourse.jsp">
                        <button type="submit" class="btn btn-success btn-sm">+ New Course</button>
                    </a>

                    <a href="${pageContext.request.contextPath}/MainController?action=editUser" class="btn btn-secondary btn-sm">Edit User</a>
                </div>
            </div>

            <c:if test="${not empty MSG}">
                <div class="alert alert-warning">${MSG}</div>
            </c:if>

            <div class="card shadow-sm">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0 text-center">
                        <thead class="table-dark">
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
                                <tr><td colspan="9" class="text-muted italic">No courses found.</td></tr>
                            </c:if>
                            <c:forEach var="c" items="${CourseList}">
                                <tr>
                            <form action="MainController" method="POST">
                                <td><input type="text" name="courseID" value="${c.courseID}" class="form-control form-control-sm" readonly></td>
                                <td><input type="text" name="courseCode" value="${c.courseCode}" class="form-control form-control-sm"></td>
                                <td><input type="text" name="courseTitle" value="${c.courseTitle}" class="form-control form-control-sm"></td>
                                <td><input type="text" name="department" value="${c.department}" class="form-control form-control-sm"></td>
                                <td><input type="text" name="courseMaterials" value="${c.materials}" class="form-control form-control-sm"></td>
                                <td><input type="text" name="courseDescription" value="${c.description}" class="form-control form-control-sm"></td>
                                <input type="hidden" name="courseState" value="${c.courseState}">
                                <td>
                                    <button type="submit" name="action" value="UpdateCourse" class="btn btn-primary btn-sm">Update</button>
                                </td>
                            </form>
                            <td>
                                <form action="MainController" onsubmit="return confirm('Delete?');" method="POST">
                                    <input type="hidden" name="courseID" value="${c.courseID}" />
                                    <button type="submit" name="action" value="DeleteCourse" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>
                            <td>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="courseID" value="${c.courseID}" />
                                    <button type="submit" name="action" value="ShowClass" class="btn btn-warning btn-sm text-white">Classes</button>
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