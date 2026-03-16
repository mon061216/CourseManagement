<%-- 
    Document   : Assignments
    Created on : Mar 5, 2026, 10:51:46 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h2>Class ${CLASS_ID}</h2>
         <h2>Welcome ${STUDENT_ID}</h2>

    <c:if test="${empty GRADE_LIST}">
        <p>No SCORES.</p>
    </c:if>

    <table border="1" cellpadding="5">
        <tr>
            <th>TEACHER</th>
            <th>SUBMISSION</th>
            <th>SCORE</th>
            <th>DATE</th>
            <th>ACTION</th>
        </tr>

        <c:forEach var="s" items="${GRADE_LIST}">
            <tr>
                <td>${s.teacherID}</td>
                <td>${s.submissionID}</td>
                <td>${s.gradeScore}</td>
                <td>${s.gradeDate}</td>
            <form action="MainController" method="POST">
                <input type="hidden" name="studentID" value="${STUDENT_ID}">
                <input type="hidden" name="classID" value="${CLASS_ID}">
                <input type="hidden" name="submissionID" value="${s.submissionID}">
                <button name="action" value="ViewSubmission" >View Details Submission</button>
            </form>
            </tr>
        </c:forEach>
    </table>

    <br>
    </body>
</html>
