<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Score</title>
    </head>

    <body>

        <h2>Class ${CLASS_ID}</h2>
        <h3>Student: ${STUDENT_ID}</h3>

        <c:if test="${empty GRADE_LIST}">
            <p>No scores available.</p>
        </c:if>

        <table border="1" cellpadding="5">

            <tr>
                <th>Teacher</th>
                <th>Submission</th>
                <th>Score</th>
                <th>Grade Date</th>
                <th>Action</th>
            </tr>

            <c:forEach var="g" items="${GRADE_LIST}">

                <tr>

                    <td>${g.teacherID}</td>

                    <td>${g.submissionID}</td>

                    <td>${g.gradeScore}</td>

                    <td>${g.gradeDate}</td>

                    <td>

                        <form action="MainController" method="POST">

                            <input type="hidden" name="studentID" value="${STUDENT_ID}">
                            <input type="hidden" name="classID" value="${CLASS_ID}">
                            <input type="hidden" name="submissionID" value="${g.submissionID}">

                            <button name="action" value="ViewSubmissionOfAStudent">
                                View Submission
                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>

        </table>

        <br>

        <form action="MainController" method="POST">
            <input type="hidden" name="classID" value="${CLASS_ID}">
            <button name="action" value="ViewStudents">Back to Students</button>
        </form>

    </body>
</html>