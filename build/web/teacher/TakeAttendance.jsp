<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Take Attendance</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item text-muted">Class ${CLASS_ID}</li>
                    <li class="breadcrumb-item text-muted">Schedule</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Take Attendance</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Take Attendance</h2>
                    <p class="text-muted small fw-bold mb-0">Class: <span class="text-primary">${CLASS_ID}</span> | Slot: <span class="text-info">${SLOT_ID}</span></p>
                </div>
                <form action="MainController" method="post" class="m-0">
                    <input type="hidden" name="classID" value="${CLASS_ID}">
                    <button class="btn btn-secondary btn-sm shadow-sm rounded-pill px-3" type="submit" name="action" value="ViewSchedule">
                        &larr; Back to Schedule
                    </button>
                </form>
            </div>

            <form action="MainController" method="POST">
                <input type="hidden" name="slotID" value="${SLOT_ID}">
                <input type="hidden" name="classID" value="${CLASS_ID}">

                <div class="glass-panel p-4 mb-4">
                    <div class="table-responsive">
                        <table class="table table-glass text-center mb-0">
                            <thead>
                                <tr>
                                    <th>Student ID</th>
                                    <th>Full Name</th>
                                    <th class="text-center">Present</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${STUDENT_LIST}">
                                    <tr>
                                        <td><strong>${s.userID}</strong></td>
                                        <td>${s.fullname}</td>
                                        <td class="text-center">
                                            <div class="form-check d-flex justify-content-center">
                                                <input class="form-check-input border-secondary" type="checkbox" name="present" value="${s.userID}" style="transform: scale(1.3);">
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="d-flex justify-content-end">
                    <button class="btn btn-premium rounded-pill px-5 shadow-sm" name="action" value="SaveAttendance">
                        Save Attendance
                    </button>
                </div>
            </form>
        </div>
    </body>
</html>
