<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Class Enrollment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Select a Class to Enroll</h2>
            <a href="MainController?action=ShowCourse" class="btn btn-outline-secondary">Back to Courses</a>
        </div>

        <c:if test="${not empty MSG or not empty ERROR}">
            <div class="alert ${not empty ERROR ? 'alert-danger' : 'alert-success'}">
                ${MSG}${ERROR}
            </div>
        </c:if>

        <div class="row">
            <c:forEach var="c" items="${ClassList}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm border-0">
                        <div class="card-header bg-white fw-bold text-primary">${c.className}</div>
                        <div class="card-body">
                            <p class="mb-1 small text-muted">Class ID: ${c.classID}</p>
                            <p class="mb-1"><strong>Teacher ID:</strong> ${c.teacherID}</p>
                            <p class="mb-1"><strong>Term:</strong> ${c.academicTerm}</p>
                            <p class="mb-1"><strong>Dates:</strong> <span class="small">${c.classStartDate} to ${c.classEndDate}</span></p>
                            <hr>
                            <div class="d-flex justify-content-between align-items-center">
                                <span class="badge bg-info text-dark">Seats: ${c.classCapacity}</span>
                                <form action="MainController" method="post">
                                    <input type="hidden" name="classID" value="${c.classID}" />
                                    <button type="submit" name="action" value="EnrolClass" class="btn btn-success">Enroll Now</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>