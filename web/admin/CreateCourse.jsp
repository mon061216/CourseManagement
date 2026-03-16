<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Course</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4 text-primary">Create New Course</h2>

        <c:if test="${not empty MSG}">
            <div class="alert alert-danger text-center" role="alert">
                ${MSG}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/MainController" method="POST">
            
            <div class="mb-3">
                <label for="courseID" class="form-label fw-bold">Course ID</label>
                <input type="text" class="form-control" id="courseID" name="courseID" placeholder="Enter Course ID" required>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="courseCode" class="form-label fw-bold">Course Code</label>
                    <input type="text" class="form-control" id="courseCode" name="courseCode" placeholder="Ex: CS101" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="department" class="form-label fw-bold">Department</label>
                    <input type="text" class="form-control" id="department" name="department" placeholder="Ex: IT" required>
                </div>
            </div>

            <div class="mb-3">
                <label for="courseTitle" class="form-label fw-bold">Course Title</label>
                <input type="text" class="form-control" id="courseTitle" name="courseTitle" placeholder="Enter Full Course Name" required>
            </div>

            <div class="mb-3">
                <label for="courseMaterials" class="form-label fw-bold">Materials</label>
                <input type="text" class="form-control" id="courseMaterials" name="courseMaterials" placeholder="Link or material list">
            </div>

            <div class="mb-4">
                <label for="courseDescription" class="form-label fw-bold">Description</label>
                <textarea class="form-control" id="courseDescription" name="courseDescription" rows="4" placeholder="Briefly describe the course content..."></textarea>
            </div>

            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                <button type="submit" name="action" value="CreateCourse" class="btn btn-success px-5">
                    Create Course
                </button>
                <a href="${pageContext.request.contextPath}/MainController?action=ShowCourse" class="btn btn-outline-secondary px-5">
                    Cancel
                </a>
            </div>

        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>