<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Class Enrollment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body class="animated-bg">
    <div class="container py-5 mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold text-dark">Select a Class to Enroll</h2>
            <a href="MainController?action=ShowCourse" class="btn btn-secondary rounded-pill px-4 shadow-sm">&larr; Back to Courses</a>
        </div>

        <c:if test="${not empty MSG or not empty ERROR}">
            <div class="alert ${not empty ERROR ? 'alert-danger' : 'alert-success'} border-0 shadow-sm rounded-3">
                ${MSG}${ERROR}
            </div>
        </c:if>

        <div class="row">
            <c:forEach var="c" items="${ClassList}">
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="glass-card h-100 d-flex flex-column" style="margin:0;">
                        <h4 class="fw-bold text-primary mb-3">${c.className}</h4>
                        <p class="mb-1 text-dark small fw-bold">Class ID: <span class="fw-normal text-muted">${c.classID}</span></p>
                        <p class="mb-1 text-dark small fw-bold">Teacher ID: <span class="fw-normal text-muted">${c.teacherID}</span></p>
                        <p class="mb-1 text-dark small fw-bold">Term: <span class="fw-normal text-muted">${c.academicTerm}</span></p>
                        <p class="mb-3 text-dark small fw-bold">Dates: <span class="fw-normal text-muted">${c.classStartDate} to ${c.classEndDate}</span></p>
                        
                        <div class="mt-auto pt-3 border-top border-secondary-subtle d-flex justify-content-between align-items-center">
                            <span class="badge bg-info bg-opacity-75 text-dark rounded-pill px-3 py-2">Seats: ${c.classCapacity}</span>
                            <form action="MainController" method="post" class="m-0">
                                <input type="hidden" name="classID" value="${c.classID}" />
                                <input type="hidden" name="courseID" value="${c.courseID}" />
                                <button type="submit" name="action" value="EnrolClass" class="btn btn-premium btn-sm rounded-pill px-4 shadow-sm">Enroll Now</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>