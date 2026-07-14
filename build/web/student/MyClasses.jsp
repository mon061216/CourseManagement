<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Enrolled Classes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body class="animated-bg">

    <nav class="navbar navbar-expand-lg navbar-dark navbar-glass sticky-top mb-4">
        <div class="container">
            <span class="navbar-brand fw-bold">My Learning Space</span>
            <div class="d-flex align-items-center gap-3">
                <a href="MainController?action=ShowCourse" class="btn btn-outline-light btn-sm rounded-pill px-3">Browse Courses</a>
                <form action="MainController" method="post" class="m-0">
                    <button class="btn btn-outline-secondary btn-sm rounded-pill px-3 text-light" type="submit" name="action" value="StudentDashboard">
                        &larr; Dashboard
                    </button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold text-dark mb-0">My Enrolled Classes</h2>
            <form action="MainController" method="post" class="m-0">
                <button class="btn btn-premium shadow-sm rounded-pill px-4" type="submit" name="action" value="StudentSchedule">
                    View Weekly Schedule
                </button>
            </form>
        </div>

        <div class="row">
            <c:choose>
                <c:when test="${not empty CLASS_LIST}">
                    <c:forEach var="c" items="${CLASS_LIST}">
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="glass-card h-100 d-flex flex-column" style="margin:0;">
                                <div class="mb-3 border-bottom border-secondary-subtle pb-3">
                                    <span class="badge bg-success bg-opacity-75 text-white mb-2 rounded-pill px-3 py-2">Enrolled</span>
                                    <h4 class="fw-bold text-dark mb-0">${c.classID}</h4>
                                </div>
                                <div class="mb-2">
                                    <small class="text-muted d-block fw-bold">Course Reference</small>
                                    <span class="fw-bold text-primary">${c.courseID}</span>
                                </div>
                                <div class="mb-4">
                                    <small class="text-muted d-block fw-bold">Assigned Teacher</small>
                                    <span class="fw-medium">${c.teacherID}</span>
                                </div>
                                
                                <div class="mt-auto">
                                    <form action="MainController" method="post" class="m-0">
                                        <input type="hidden" value="${c.classID}" name="classID">
                                        <button name="action" value="Assignment" class="btn btn-outline-primary rounded-pill w-100 shadow-sm">
                                            View Assignments
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="col-12 text-center py-5">
                        <div class="alert alert-warning d-inline-block px-5 border-0 shadow-sm rounded-3">
                            You haven't enrolled in any classes yet.
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>