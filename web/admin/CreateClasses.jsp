<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create Class</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg py-5">
        <div class="container glass-panel p-5 mt-5" style="max-width: 700px;">
            <h3 class="mb-4 text-center fw-bold text-dark">Create New Class</h3>

            <c:if test="${not empty MSG}">
                <div class="alert alert-danger border-0 shadow-sm rounded-3">${MSG}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/MainController" method="POST">
                <input type="hidden" name="courseID" value="${param.courseID}">
                <div class="row g-4">
                    <div class="col-12 border-bottom border-secondary-subtle pb-3 mb-2 text-muted fw-bold">
                        Course Reference ID: <span class="text-primary">${param.courseID}</span>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">Class ID</label>
                        <input type="text" name="classID" class="form-control" placeholder="EX: CLASS001" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">Class Name</label>
                        <input type="text" name="className" class="form-control" placeholder="Enter class name" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">Capacity</label>
                        <input type="number" name="classCapacity" class="form-control" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">Academic Term</label>
                        <input type="text" name="academicTerm" class="form-control" placeholder="EX: Spring 2026" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">Start Date</label>
                        <input type="date" name="classStartDate" class="form-control" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold text-dark small">End Date</label>
                        <input type="date" name="classEndDate" class="form-control" required>
                    </div>

                    <div class="col-12">
                        <label class="form-label fw-bold text-dark small">Teacher ID</label>
                        <input type="text" name="teacherID" class="form-control" placeholder="Enter teacher ID" required>
                    </div>

                    <div class="col-12 mt-5 d-flex gap-3 justify-content-center">
                        <button type="submit" name="action" value="CreateClasses" class="btn btn-premium shadow-sm rounded-pill px-5">Create Class</button>
                        <a href="Classes.jsp" class="btn btn-secondary shadow-sm rounded-pill px-5">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>