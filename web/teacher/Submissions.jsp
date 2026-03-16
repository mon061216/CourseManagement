<%-- 
    Document   : Submissions
    Created on : Mar 4, 2026, 2:27:57 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submissions</title>
    </head>
    <body>
        <h2>Submissions - Assignment ${assignmentID}</h2>

    <c:forEach var="s" items="${SUBMISSION_LIST}">
        <div>

            Submission ID: ${s.submissionID} <br>
            Student ID: ${s.studentID} <br>
            Date: ${s.submissionDate} <br>
            File/Text: ${s.submissionFile} <br><br>

            <!-- GRADE FORM -->
            <form action="MainController" method="post">

                <input type="hidden" name="submissionID"
                       value="${s.submissionID}">
                <input type="hidden" name="assignmentID"
                       value="${s.assignmentID}">
                <input type="hidden" name="classID"
                       value="${classID}">

                Score:
                <input type="number" step="0.1" name="gradeScore" min="0" max="10" required>

                <button type="submit" name="action" value="GradeSubmission">
                    Save Grade
                </button>

            </form>

            <hr>
        </div>
    </c:forEach>
</body>
</html>
