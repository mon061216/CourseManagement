<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Scores</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>
    <body class="animated-bg">
        <div class="container mt-5 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item text-muted">Class ${CLASS_ID}</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">My Scores</li>
                </ol>
            </nav>

            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <div class="glass-panel p-5 h-100">
                        <div class="mb-4">
                            <h4 class="fw-bold text-dark mb-1">My Grade Report</h4>
                            <p class="text-muted small fw-bold mb-0">Class: <span class="text-primary">${CLASS_ID}</span> | Student: ${STUDENT_ID}</p>
                        </div>
                        
                        <c:if test="${empty GRADE_LIST}">
                            <div class="alert alert-info border-0 shadow-sm rounded-3">You don't have any graded assignments yet.</div>
                        </c:if>
                        
                        <c:if test="${not empty GRADE_LIST}">
                            <div class="table-responsive">
                                <table class="table table-glass text-center mb-0">
                                    <thead>
                                        <tr>
                                            <th>Submission ID</th>
                                            <th>Teacher</th>
                                            <th>Grade Date</th>
                                            <th>Score</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="g" items="${GRADE_LIST}">
                                            <tr>
                                                <td class="fw-bold text-dark">#${g.submissionID}</td>
                                                <td>${g.teacherID}</td>
                                                <td class="text-muted small">${g.gradeDate}</td>
                                                <td>
                                                    <span class="badge bg-success bg-opacity-75 rounded-pill px-4 py-2" style="font-size: 1rem;">${g.gradeScore}</span>
                                                </td>
                                                <td>
                                                    <form action="MainController" method="POST" class="m-0">
                                                        <input type="hidden" name="studentID" value="${STUDENT_ID}">
                                                        <input type="hidden" name="classID" value="${CLASS_ID}">
                                                        <input type="hidden" name="submissionID" value="${g.submissionID}">
                                                        <button name="action" value="ViewSubmissionOfAStudent" class="btn btn-outline-primary btn-sm rounded-pill px-3 shadow-sm">
                                                            View Details
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>

                        <div class="mt-5 pt-3 border-top border-secondary-subtle">
                            <form action="MainController" method="post">
                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                <button class="btn btn-secondary shadow-sm rounded-pill px-5" type="submit" name="action" value="Assignment">
                                    &larr; Back to Assignments
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
