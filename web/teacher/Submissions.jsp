<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Submissions</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item text-muted">Class ${classID}</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Assignment ${assignmentID} Submissions</li>
                </ol>
            </nav>

            <h2 class="fw-bold text-dark mb-4">Submissions - Assignment <span class="text-primary">${assignmentID}</span></h2>

            <c:if test="${empty SUBMISSION_LIST}">
                <div class="alert alert-warning border-0 shadow-sm rounded-3">No submissions yet.</div>
            </c:if>

            <div class="row">
                <c:forEach var="s" items="${SUBMISSION_LIST}">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="glass-card h-100 d-flex flex-column" style="margin:0;">
                            <h5 class="fw-bold text-dark mb-2">Student ID: <span class="text-primary">${s.studentID}</span></h5>
                            <p class="text-muted small mb-1">Submission ID: ${s.submissionID}</p>
                            <p class="text-muted small mb-3">Date: ${s.submissionDate}</p>
                            
                            <div class="p-3 mb-4 rounded bg-white bg-opacity-50 flex-grow-1">
                                <span class="fw-bold small">File/Text:</span>
                                <p class="mb-0 mt-1 small">${s.submissionFile}</p>
                            </div>

                            <!-- GRADE FORM -->
                            <form action="MainController" method="post" class="mt-auto">
                                <input type="hidden" name="submissionID" value="${s.submissionID}">
                                <input type="hidden" name="assignmentID" value="${s.assignmentID}">
                                <input type="hidden" name="classID" value="${classID}">
                                
                                <label class="form-label text-muted small fw-bold">Score (0-10):</label>
                                <div class="input-group mb-3">
                                    <input type="number" step="0.1" name="gradeScore" class="form-control" min="0" max="10" required>
                                    <button class="btn btn-premium" type="submit" name="action" value="GradeSubmission">
                                        Save Grade
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <form action="MainController" method="post" class="mt-2">
                <input type="hidden" name="classID" value="${classID}">
                <button class="btn btn-secondary btn-sm shadow-sm rounded-pill px-4" type="submit" name="action" value="Assignment">
                    &larr; Back to Assignments
                </button>
            </form>
        </div>
    </body>
</html>
