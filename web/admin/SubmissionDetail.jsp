<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Submission Detail</title>
    </head>

    <body>

        <h2>Submission Detail</h2>

        <p><b>Submission ID:</b> ${SUBMISSION.submissionID}</p>

        <p><b>Assignment ID:</b> ${SUBMISSION.assignmentID}</p>

        <p><b>Student:</b> ${SUBMISSION.studentID}</p>

        <p><b>Submission Date:</b> ${SUBMISSION.submissionDate}</p>

        <p><b>Status:</b>
    <c:if test="${SUBMISSION.submissionState}">
        Submitted
    </c:if>
    <c:if test="${!SUBMISSION.submissionState}">
        Not Submitted
    </c:if>
</p>

<!--<p><b>File:</b></p>

<a href="${SUBMISSION.submissionFile}" target="_blank">
    Download Submission
</a>-->

<br><br>

<form action="MainController" method="POST">
    <input type="hidden" name="studentID" value="${STUDENT_ID}">
    <input type="hidden" name="classID" value="${CLASS_ID}">
    <button name="action" value="ViewScore">
        Back to Scores
    </button>
</form>

</body>
</html>