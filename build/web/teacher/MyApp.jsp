<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Teacher Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">

        <nav class="navbar navbar-expand-lg navbar-dark navbar-glass sticky-top mb-4">
            <div class="container">
                <span class="navbar-brand fw-bold">Teacher Dashboard</span>
                <div class="d-flex align-items-center gap-3">
                    <span class="text-white-50 small">Welcome, Teacher</span>
                    <form action="${pageContext.request.contextPath}/MainController" method="POST" class="m-0">
                        <button name="action" value="Profile" class="btn btn-outline-info btn-sm rounded-pill px-3 me-2">Profile</button>
                        <button type="submit" name="action" value="Logout" class="btn btn-outline-light btn-sm rounded-pill px-3">Logout</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container">
            <h3 class="fw-bold mb-4 text-dark">My Classes</h3>

            <div class="row">
                <c:forEach var="c" items="${CLASS_LIST}">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="glass-card h-100 d-flex flex-column" style="margin:0;">
                            <h4 class="fw-bold text-primary mb-1">${c.className}</h4>
                            <p class="text-muted small fw-bold mb-3">ID: ${c.classID} | Course: ${c.courseID}</p>
                            
                            <ul class="list-unstyled small mb-4 flex-grow-1">
                                <li class="mb-2"><strong>Capacity:</strong> ${c.classCapacity}</li>
                                <li class="mb-2"><strong>Term:</strong> ${c.academicTerm}</li>
                                <li class="mb-2"><strong>Duration:</strong> ${c.classStartDate} to ${c.classEndDate}</li>
                                <li><strong>Status:</strong> 
                                    <c:choose>
                                        <c:when test="${c.classState}">
                                            <span class="badge bg-success bg-opacity-75">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary bg-opacity-75">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </ul>

                            <form action="${pageContext.request.contextPath}/MainController" method="post" class="d-flex flex-column gap-2 mt-auto">
                                <input type="hidden" name="classID" value="${c.classID}">
                                <button type="submit" name="action" value="ViewStudents" class="btn btn-premium btn-sm shadow-sm w-100">
                                    View Students & Scores
                                </button>
                                <button type="submit" name="action" value="ViewSchedule" class="btn btn-info text-white btn-sm shadow-sm w-100">
                                    Schedule & Attendance
                                </button>
                                <button type="submit" name="action" value="Assignment" class="btn btn-warning text-white btn-sm shadow-sm w-100">
                                    Assignments & Grading
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
