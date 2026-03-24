<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-header bg-dark text-white text-center py-3">
                    <h2 class="h4 mb-0">Submission Detail</h2>
                </div>
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between">
                            <strong>Submission ID:</strong> <span>${SUBMISSION.submissionID}</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <strong>Assignment ID:</strong> <span>${SUBMISSION.assignmentID}</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <strong>Student ID:</strong> <span>${SUBMISSION.studentID}</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <strong>Submission Date:</strong> <span>${SUBMISSION.submissionDate}</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <strong>Status:</strong>
                            <c:choose>
                                <c:when test="${SUBMISSION.submissionState}">
                                    <span class="badge bg-success">Submitted</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-danger">Not Submitted</span>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>

                    <div class="mt-4 d-grid">
                        <form action="MainController" method="POST">
                            <input type="hidden" name="studentID" value="${STUDENT_ID}">
                            <input type="hidden" name="classID" value="${CLASS_ID}">
                            <button name="action" value="ViewScore" class="btn btn-secondary w-100">
                                Back to Scores
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>