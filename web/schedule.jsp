<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Schedule</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light py-4">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Weekly Timetable</h2>
            <a href="MainController?action=MyClasses" class="btn btn-secondary">Back to My Classes</a>
        </div>

        <div class="table-responsive shadow-sm">
            <table class="table table-bordered bg-white text-center align-middle">
                <thead class="table-primary">
                    <tr>
                        <th style="width: 10%">Slot</th>
                        <th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th><th>Sun</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="slot" begin="1" end="5">
                        <tr>
                            <td class="fw-bold table-light small">Slot ${slot}</td>
                            <c:forEach var="day" items="${['Mon','Tue','Wed','Thu','Fri','Sat','Sun']}">
                                <td style="min-height: 80px; width: 12%;">
                                    <c:forEach var="c" items="${CLASS_LIST}">
                                        <c:if test="${fn:contains(c.schedule, day.concat('-').concat(slot))}">
                                            <div class="p-1 mb-1 bg-success-subtle border border-success rounded small">
                                                ${c.classID}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>