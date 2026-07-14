<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Class Schedule</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Class ${CLASS_ID} - Schedule</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold text-dark mb-0">Schedule of Class <span class="text-primary">${CLASS_ID}</span></h2>
                <form action="MainController" method="post" class="m-0">
                    <button class="btn btn-secondary btn-sm shadow-sm rounded-pill px-3" type="submit" name="action" value="BackToMyClasses">
                        &larr; Back to Classes
                    </button>
                </form>
            </div>

            <c:if test="${empty SLOT_LIST}">
                <div class="alert alert-warning border-0 shadow-sm rounded-3">No schedule found for this class.</div>
            </c:if>

            <c:if test="${not empty SLOT_LIST}">
                <div class="glass-panel p-4">
                    <div class="table-responsive">
                        <table class="table table-glass text-center mb-0">
                            <thead>
                                <tr>
                                    <th>Session Date</th>
                                    <th>Start Time</th>
                                    <th>End Time</th>
                                    <th>Room</th>
                                    <th class="text-center">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${SLOT_LIST}">
                                    <tr>
                                        <td><strong><fmt:formatDate value="${s.sessionDate}" pattern="dd-MM-yyyy"/></strong></td>
                                        <td>${s.startTime}</td>
                                        <td>${s.endTime}</td>
                                        <td><span class="badge bg-info text-dark bg-opacity-75">${s.roomCode}</span></td>
                                        <td class="text-center">
                                            <form action="MainController" method="POST" class="m-0">
                                                <input type="hidden" name="slotID" value="${s.slotID}">
                                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                                <button name="action" value="TakeAttendance" class="btn btn-premium btn-sm rounded-pill px-3 shadow-sm">
                                                    Take Attendance
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
