<%-- 
    Document   : Students
    Created on : Mar 4, 2026, 3:44:37 PM
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
        <h2>Students in Class ${CLASS_ID}</h2>

        <c:if test="${empty STUDENT_LIST}">
            <p>No students enrolled.</p>
        </c:if>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Attendance</th>
                <th>Action</th>
            </tr>

            <c:forEach var="s" items="${STUDENT_LIST}">
                <tr>
                    <td>${s.userID}</td>
                    <td>${s.fullname}</td>
                    <td>${s.username}</td>
                    <td>${s.mail}</td>
                    <td>${s.phoneNumber}</td>
                    <td>
                        ${ATTEND_MAP[s.userID]} %
                    </td>
                    <td>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="studentID" value="${s.userID}">
                            <input type="hidden" name="classID" value="${CLASS_ID}">
                            <button name="action" value="ViewScore" >View Score</button>
                        </form>

                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>
        <!--    <a href="teacher/MyApp.jsp">⬅ Back to Classes</a>-->
    </body>
</html>
