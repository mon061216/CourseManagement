<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Assignments</title>
    </head>
    <body>

        <h2>Assignments - Class ${classID}</h2>

        <h3>Create Assignment</h3>

        <form action="MainController" method="post">

            <input type="hidden" name="classID" value="${classID}">

            Title:
            <input type="text" name="assignmentTitle" required><br><br>

            Description:
            <input type="text" name="assignmentDescription"><br><br>

            Note:
            <input type="text" name="assignmentNote"><br><br>

            Due Date:
            <input type="datetime-local" name="dueDate" required><br><br>

            <button type="submit" name="action" value="SaveAssignment">
                Create
            </button>

        </form>

        <hr>

        <h3>Assignment List</h3>

        <c:if test="${empty ASSIGNMENT_LIST}">
            No assignments found.
        </c:if>

        <c:forEach var="a" items="${ASSIGNMENT_LIST}">
            <div>
                <form action="MainController" method="post">

                    <input type="hidden" name="assignmentID" value="${a.assignmentID}">
                    <input type="hidden" name="classID" value="${classID}">

                    Title:
                    <input type="text" name="assignmentTitle"
                           value="${a.assignmentTitle}"><br><br>

                    Description:
                    <input type="text" name="assignmentDescription"
                           value="${a.assignmentDescription}"><br><br>

                    Note:
                    <input type="text" name="assignmentNote"
                           value="${a.assignmentNote}"><br><br>

                    Due Date:
                    <input type="datetime-local" name="dueDate" value="${a.dueDate}"><br><br>

                    <button type="submit" name="action" value="UpdateAssignment">
                        Update
                    </button>
                    <button type="submit" name="action" value="ViewSubmissions">
                        View Submissions
                    </button>
                </form>

                <hr>
            </div>
        </c:forEach>

        <br>
        <form action="MainController" method="post">
            <button type="submit" name="action" value="BackToMyClasses">
                Back
            </button>
        </form>

    </body>
</html>