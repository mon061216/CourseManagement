<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard - Students</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">Admin Panel</li>
                    <li class="breadcrumb-item text-muted">Classes</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Class ${CLASS_ID}</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold text-dark mb-0">Students in Class <span class="badge bg-primary bg-opacity-75 rounded-pill px-3">${CLASS_ID}</span></h2>
                <form action="MainController" method="POST" class="m-0">
                    <button type="submit" name="action" value="Logout" class="btn btn-outline-secondary btn-sm shadow-sm rounded-pill px-3">Logout</button>
                </form>
            </div>

            <c:if test="${empty STUDENT_LIST}">
                <div class="alert alert-warning border-0 shadow-sm rounded-3">No students enrolled in this class yet.</div>
            </c:if>

            <div class="glass-panel p-4 mb-4">
                <div class="table-responsive">
                    <table class="table table-glass text-center mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th class="text-center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${STUDENT_LIST}">
                                <tr>
                                    <td><strong>${s.userID}</strong></td>
                                    <td>${s.fullname}</td>
                                    <td><code class="text-muted">${s.username}</code></td>
                                    <td>${s.mail}</td>
                                    <td>${s.phoneNumber}</td>
                                    <td class="text-center">
                                        <form action="MainController" method="POST" class="m-0">
                                            <input type="hidden" name="studentID" value="${s.userID}">
                                            <input type="hidden" name="classID" value="${CLASS_ID}">
                                            <button name="action" value="ViewScore" class="btn btn-premium btn-sm rounded-pill shadow-sm px-3">
                                                View Score
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <form action="MainController" method="POST" class="m-0">
                <input type="hidden" name="courseID" value="${COURSE_ID}">
                <button name="action" value="ShowClass" class="btn btn-secondary shadow-sm rounded-pill px-4">
                    &larr; Back to Classes
                </button>
            </form>
        </div>

    </body>
</html>