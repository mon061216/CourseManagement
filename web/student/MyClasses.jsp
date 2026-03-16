<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Enrolled Classes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; }
        .class-card {
            transition: transform 0.2s;
            border: none;
            border-radius: 12px;
        }
        .class-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1) !important;
        }
    </style>
</head>
<body>

    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">My Learning Space</span>
            <a href="MainController?action=ShowCourse" class="btn btn-outline-light btn-sm">Browse Courses</a>
        </div>
    </nav>

    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold text-secondary">My Enrolled Classes</h2>
            <a href="schedule.jsp" class="btn btn-primary shadow-sm">
                <i class="bi bi-calendar3"></i> View Weekly Schedule
            </a>
        </div>

        <div class="row">
            <c:choose>
                <c:when test="${not empty CLASS_LIST}">
                    <c:forEach var="c" items="${CLASS_LIST}">
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card h-100 class-card shadow-sm">
                                <div class="card-header bg-white border-0 pt-4">
                                    <span class="badge bg-success-subtle text-success mb-2">Enrolled</span>
                                    <h5 class="card-title fw-bold text-dark">${c.classID}</h5>
                                </div>
                                <div class="card-body">
                                    <div class="mb-2">
                                        <small class="text-muted d-block">Course Reference</small>
                                        <span class="fw-medium">${c.courseID}</span>
                                    </div>
                                    <div class="mb-3">
                                        <small class="text-muted d-block">Assigned Teacher</small>
                                        <span class="fw-medium text-primary">${c.teacherID}</span>
                                    </div>
                                </div>
                                <div class="card-footer bg-white border-0 pb-4">
                                    <div class="d-grid">
                                        <form action="MainController" method="post">
                                            <input type="hidden" value = "${c.classID}" name="classID">
                                            <button name="action" value="Assignment" class="btn btn-outline-primary">
                                                View Assignments
                                            </button>
                                        </form>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="col-12 text-center py-5">
                        <div class="alert alert-warning d-inline-block px-5">
                            You haven't enrolled in any classes yet.
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>