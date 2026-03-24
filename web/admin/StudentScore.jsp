<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-4">
    <div class="card border-0 shadow-sm mb-4 bg-light">
        <div class="card-body">
            <h2 class="h4">Class: <span class="text-primary">${CLASS_ID}</span></h2>
            <h3 class="h5 text-muted">Student: <strong>${STUDENT_ID}</strong></h3>
        </div>
    </div>

    <c:if test="${empty GRADE_LIST}">
        <div class="alert alert-info">No scores available for this student.</div>
    </c:if>

    <div class="table-responsive shadow-sm rounded">
        <table class="table table-bordered align-middle">
            <thead class="table-primary">
                <tr>
                    <th>Teacher</th>
                    <th>Submission</th>
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
                        <td><span class="fw-bold text-success">${g.gradeScore}</span></td>
                        <td>${g.gradeDate}</td>
                        <td class="text-center">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="studentID" value="${STUDENT_ID}">
                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                <input type="hidden" name="submissionID" value="${g.submissionID}">
                                <button name="action" value="ViewSubmissionOfAStudent" class="btn btn-info btn-sm text-white">
                                    View Submission
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <form action="MainController" method="POST" class="mt-3">
        <input type="hidden" name="classID" value="${CLASS_ID}">
        <button name="action" value="ViewStudents" class="btn btn-secondary">
            &larr; Back to Students
        </button>
    </form>
</div>