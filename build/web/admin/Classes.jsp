<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Class Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-dark bg-dark mb-4">
            <div class="container">
                <span class="navbar-brand">Admin Panel - Classes</span>
                <form action="${pageContext.request.contextPath}/MainController" method="POST" class="d-inline">
                    <input type="submit" name="action" value="Logout" class="btn btn-outline-light btn-sm">
                </form>
            </div>
        </nav>

        <div class="container-fluid px-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3>Class List</h3>
                <a href="admin/CreateClasses.jsp?courseID=${courseID}" class="btn btn-success">+ Add New Class</a>
            </div>

            <c:if test="${not empty MSG}">
                <div class="alert alert-info alert-dismissible fade show" role="alert">
                    ${MSG}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <div class="card shadow-sm mt-3">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0 text-center">
                        <thead class="table-secondary text-uppercase small">
                            <tr>
                                <th>Class ID</th>
                                <th>Class Name</th>
                                <th>Capacity</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Term</th>
                                <th>Teacher</th>
                                <th colspan="3">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty ClassList}">
                                <c:forEach var="c" items="${ClassList}">
                                    <tr>
                                <form action="MainController" method="POST">
                                    <td><input type="text" name="classID" value="${c.classID}" class="form-control form-control-sm text-center" readonly></td>
                                    <td><input type="text" name="className" value="${c.className}" class="form-control form-control-sm"></td>
                                    <td><input type="number" name="classCapacity" value="${c.classCapacity}" class="form-control form-control-sm mx-auto" style="max-width: 80px;"></td>
                                    <td><input type="date" name="classStartDate" value="${c.classStartDate}" class="form-control form-control-sm"></td>
                                    <td><input type="date" name="classEndDate" value="${c.classEndDate}" class="form-control form-control-sm"></td>
                                    <td><input type="text" name="academicTerm" value="${c.academicTerm}" class="form-control form-control-sm"></td>
                                    <td><input type="text" name="teacherID" value="${c.teacherID}" class="form-control form-control-sm text-center"></td>

                                    <input type="hidden" name="courseID" value="${c.courseID}">
                                    <input type="hidden" name="classState" value="${c.classState}">

                                    <td>
                                        <button type="submit" name="action" value="UpdateClasses" class="btn btn-primary btn-sm">Update</button>
                                    </td>
                                </form>
                                <td>
                                    <form action="MainController" onsubmit="return confirm('Are you sure you want to delete this class?');" method="POST">
                                        <input type="hidden" name="classID" value="${c.classID}" />
                                        <input type="hidden" name="courseID" value="${c.courseID}" />
                                        <input type="hidden" name="teacherID" value="${c.teacherID}" />
                                        <button type="submit" name="action" value="DeleteClasses" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="classID" value="${c.classID}" />
                                        <button type="submit" name="action" value="ViewStudents" class="btn btn-danger btn-sm">View Students</button>
                                    </form>
                                </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty ClassList}">
                            <tr><td colspan="9" class="text-muted py-4">No classes available.</td></tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/MainController?action=ShowCourse" class="text-decoration-none">← Back to Course List</a>
            </div>
        </div>
    </body>
</html>