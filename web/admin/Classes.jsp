<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Class Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <nav class="navbar navbar-expand-lg navbar-dark navbar-glass sticky-top mb-4">
            <div class="container">
                <span class="navbar-brand fw-bold">Admin Panel - Classes</span>
                <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                    <button type="submit" name="action" value="Logout" class="btn btn-outline-light btn-sm rounded-pill px-3">Logout</button>
                </form>
            </div>
        </nav>

        <div class="container-fluid px-4 mb-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h3 class="fw-bold text-dark mb-0">Class List</h3>
                <div class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/MainController?action=ShowCourse" class="btn btn-secondary btn-sm rounded-pill shadow-sm px-3">&larr; Back to Course List</a>
                    <a href="admin/CreateClasses.jsp?courseID=${courseID}" class="btn btn-premium btn-sm rounded-pill shadow-sm px-3">+ Add New Class</a>
                </div>
            </div>

            <c:if test="${not empty MSG}">
                <div class="alert alert-info border-0 shadow-sm rounded-3">
                    ${MSG}
                </div>
            </c:if>

            <div class="glass-panel p-4">
                <div class="table-responsive">
                    <table class="table table-glass text-center mb-0">
                        <thead>
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
                                        <form action="MainController" method="POST" class="m-0">
                                            <td><input type="text" name="classID" value="${c.classID}" class="form-control form-control-sm text-center" readonly style="background: rgba(0,0,0,0.05)!important; border:none!important;"></td>
                                            <td><input type="text" name="className" value="${c.className}" class="form-control form-control-sm text-center"></td>
                                            <td><input type="number" name="classCapacity" value="${c.classCapacity}" class="form-control form-control-sm text-center mx-auto" style="max-width: 80px;"></td>
                                            <td><input type="date" name="classStartDate" value="${c.classStartDate}" class="form-control form-control-sm text-center"></td>
                                            <td><input type="date" name="classEndDate" value="${c.classEndDate}" class="form-control form-control-sm text-center"></td>
                                            <td><input type="text" name="academicTerm" value="${c.academicTerm}" class="form-control form-control-sm text-center"></td>
                                            <td><input type="text" name="teacherID" value="${c.teacherID}" class="form-control form-control-sm text-center"></td>

                                            <input type="hidden" name="courseID" value="${c.courseID}">
                                            <input type="hidden" name="classState" value="${c.classState}">

                                            <td>
                                                <button type="submit" name="action" value="UpdateClasses" class="btn btn-primary shadow-sm btn-sm rounded-pill px-3">Update</button>
                                            </td>
                                        </form>
                                        <td>
                                            <form action="MainController" onsubmit="return confirm('Are you sure you want to delete this class?');" method="POST" class="m-0">
                                                <input type="hidden" name="classID" value="${c.classID}" />
                                                <input type="hidden" name="courseID" value="${c.courseID}" />
                                                <input type="hidden" name="teacherID" value="${c.teacherID}" />
                                                <button type="submit" name="action" value="DeleteClasses" class="btn btn-danger shadow-sm btn-sm rounded-pill px-3">Delete</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST" class="m-0">
                                                <input type="hidden" name="classID" value="${c.classID}" />
                                                <input type="hidden" name="courseID" value="${c.courseID}" />
                                                <button type="submit" name="action" value="ViewStudents" class="btn btn-warning text-white shadow-sm btn-sm rounded-pill px-3">Students</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty ClassList}">
                                <tr><td colspan="10" class="text-muted py-4">No classes available.</td></tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>tml>