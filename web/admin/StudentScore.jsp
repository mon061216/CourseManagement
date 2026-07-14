<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard - Student Scores</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">Admin Panel</li>
                    <li class="breadcrumb-item text-muted">Classes</li>
                    <li class="breadcrumb-item text-muted">Class ${CLASS_ID}</li>
                    <li class="breadcrumb-item text-muted">Students</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Student ${STUDENT_ID}</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Scores for Student <span class="text-primary">${STUDENT_ID}</span></h2>
                    <p class="text-muted small fw-bold mb-0">Class: ${CLASS_ID}</p>
                </div>
                <form action="MainController" method="POST" class="m-0">
                    <input type="hidden" name="classID" value="${CLASS_ID}">
                    <button name="action" value="ViewStudents" class="btn btn-secondary shadow-sm rounded-pill px-4">
                        &larr; Back to Students
                    </button>
                </form>
            </div>

            <c:if test="${empty GRADE_LIST}">
                <div class="alert alert-info border-0 shadow-sm rounded-3">No scores available for this student.</div>
            </c:if>

            <c:if test="${not empty GRADE_LIST}">
                <div class="glass-panel p-4 mb-4">
                    <div class="table-responsive">
                        <table class="table table-glass text-center mb-0">
                            <thead>
                                <tr>
                                    <th>Teacher</th>
                                    <th>Submission ID</th>
                                    <th>Score</th>
                                    <th>Grade Date</th>
                                    <th class="text-center">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="g" items="${GRADE_LIST}">
                                    <tr>
                                        <td>${g.teacherID}</td>
                                        <td>#${g.submissionID}</td>
                                        <td><span class="badge bg-success bg-opacity-75 rounded-pill px-3">${g.gradeScore}</span></td>
                                        <td>${g.gradeDate}</td>
                                        <td class="text-center">
                                            <form action="MainController" method="POST" class="m-0">
                                                <input type="hidden" name="studentID" value="${STUDENT_ID}">
                                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                                <input type="hidden" name="submissionID" value="${g.submissionID}">
                                                <button name="action" value="ViewSubmissionOfAStudent" class="btn btn-premium btn-sm shadow-sm rounded-pill px-3">
                                                    View Submission
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