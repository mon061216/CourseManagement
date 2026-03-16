<%-- 
    Document   : Schedule
    Created on : Mar 6, 2026, 10:29:38 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Schedule of Class ${CLASS_ID}</h2>

        <c:if test="${empty SLOT_LIST}">
            No schedule.
        </c:if>

        <table border="1">

            <tr>
                <th>Session</th>
                <th>Start</th>
                <th>End</th>
                <th>Room</th>
                <th>Action</th>
            </tr>

            <c:forEach var="s" items="${SLOT_LIST}">

                <tr>
                    <td>
                        <fmt:formatDate value="${s.sessionDate}" pattern="dd-MM-yyyy"/>
                    </td>
                    <td>${s.startTime}</td>
                    <td>${s.endTime}</td>
                    <td>${s.roomCode}</td>

                    <td>

                        <form action="MainController" method="POST">

                            <input type="hidden" name="slotID" value="${s.slotID}">
                            <input type="hidden" name="classID" value="${CLASS_ID}">

                            <button name="action" value="TakeAttendance">
                                Take Attendance
                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>

        </table>
    </body>
</html>
