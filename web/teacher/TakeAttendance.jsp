<%-- 
    Document   : TakeAttendance
    Created on : Mar 6, 2026, 10:33:34 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Take Attendance</h2>

        Class: ${CLASS_ID}
        <br>
        Slot: ${SLOT_ID}

        <form action="MainController" method="POST">

            <input type="hidden" name="slotID" value="${SLOT_ID}">
            <input type="hidden" name="classID" value="${CLASS_ID}">

            <table border="1">

                <tr>
                    <th>Student ID</th>
                    <th>Name</th>
                    <th>Present</th>
                </tr>

                <c:forEach var="s" items="${STUDENT_LIST}">

                    <tr>

                        <td>${s.userID}</td>
                        <td>${s.fullname}</td>

                        <td>

                            <input type="checkbox" name="present" value="${s.userID}">

                        </td>

                    </tr>

                </c:forEach>

            </table>

            <br>

            <button name="action" value="SaveAttendance">
                Save Attendance
            </button>

        </form>
    </body>
</html>
