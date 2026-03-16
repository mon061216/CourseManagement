<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Class</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light py-5">
    <div class="container shadow p-4 bg-white rounded-3" style="max-width: 700px;">
        <h2 class="mb-4 text-center text-primary">Create New Class</h2>

        <c:if test="${not empty MSG}">
            <div class="alert alert-danger">${MSG}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/MainController" method="POST">
            <input type="hidden" name="courseID" value="${param.courseID}">
            <div class="row g-3">
                <div class="col-12 border-bottom pb-2 mb-2 text-muted">
                    <strong>Course Reference ID:</strong> ${param.courseID}
                </div>
                
                <div class="col-md-6">
                    <label class="form-label fw-bold">Class ID</label>
                    <input type="text" name="classID" class="form-control" placeholder="EX: CLASS001" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Class Name</label>
                    <input type="text" name="className" class="form-control" placeholder="Enter class name" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Capacity</label>
                    <input type="number" name="classCapacity" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Academic Term</label>
                    <input type="text" name="academicTerm" class="form-control" placeholder="EX: Spring 2026" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Start Date</label>
                    <input type="date" name="classStartDate" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">End Date</label>
                    <input type="date" name="classEndDate" class="form-control" required>
                </div>

                <div class="col-12">
                    <label class="form-label fw-bold">Teacher ID</label>
                    <input type="text" name="teacherID" class="form-control" placeholder="Enter teacher ID" required>
                </div>

                <div class="col-12 mt-4 d-flex gap-2 justify-content-center">
                    <button type="submit" name="action" value="CreateClasses" class="btn btn-success px-5">Create Class</button>
                    <a href="Classes.jsp" class="btn btn-outline-secondary px-5">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>