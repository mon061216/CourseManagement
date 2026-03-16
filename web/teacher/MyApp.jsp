<%-- 
    Document   : MyApp
    Created on : Feb 27, 2026, 8:45:04 PM
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
        <h2>My Classes</h2>

        <c:forEach var="c" items="${CLASS_LIST}">
            <div>

                Class ID: ${c.classID} <br>
                Class Name: ${c.className} <br>
                Course ID: ${c.courseID} <br>
                Capacity: ${c.classCapacity} <br>
                Start: ${c.classStartDate} <br>
                End: ${c.classEndDate} <br>
                Term: ${c.academicTerm} <br>
                Active: ${c.classState} <br>

                <form action="MainController" method="post">
                    <input type="hidden" name="classID" value="${c.classID}">
                    <button type="submit" name="action" value="ViewStudents">View Students</button>
                    <button name="action" value="ViewSchedule">
                        View Schedule
                    </button>
                    <button type="submit" name="action" value="Assignment"> Assignments</button>
                </form>
             
                <hr>
            </div>
        </c:forEach>
    </body>
</html>
