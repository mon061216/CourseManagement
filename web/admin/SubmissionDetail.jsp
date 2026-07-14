<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard - Submission Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-5 mb-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="glass-panel p-5">
                        <div class="text-center mb-4 border-bottom border-secondary-subtle pb-4">
                            <h2 class="fw-bold text-dark mb-0">Submission Detail</h2>
                            <p class="text-muted small fw-bold mt-2 mb-0">ID: <span class="text-primary">#${SUBMISSION.submissionID}</span></p>
                        </div>
                        
                        <div class="mb-5">
                            <div class="d-flex justify-content-between align-items-center border-bottom border-secondary-subtle py-3">
                                <strong class="text-dark small">Assignment ID:</strong> 
                                <span class="fw-bold">${SUBMISSION.assignmentID}</span>
                            </div>
                            <div class="d-flex justify-content-between align-items-center border-bottom border-secondary-subtle py-3">
                                <strong class="text-dark small">Student ID:</strong> 
                                <span class="fw-bold text-primary">${SUBMISSION.studentID}</span>
                            </div>
                            <div class="d-flex justify-content-between align-items-center border-bottom border-secondary-subtle py-3">
                                <strong class="text-dark small">Submission Date:</strong> 
                                <span class="fw-bold text-muted">${SUBMISSION.submissionDate}</span>
                            </div>
                            <div class="d-flex justify-content-between align-items-center py-3 border-bottom border-secondary-subtle">
                                <strong class="text-dark small">Status:</strong>
                                <c:choose>
                                    <c:when test="${SUBMISSION.submissionState}">
                                        <span class="badge bg-success bg-opacity-75 rounded-pill px-3 py-2">Submitted</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger bg-opacity-75 rounded-pill px-3 py-2">Not Submitted</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <div class="d-flex justify-content-center">
                            <form action="MainController" method="POST" class="m-0">
                                <input type="hidden" name="studentID" value="${STUDENT_ID}">
                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                <button name="action" value="ViewScore" class="btn btn-secondary shadow-sm rounded-pill px-5">
                                    &larr; Back to Scores
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>