<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Course</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body class="animated-bg py-5">

<div class="container">
    <div class="glass-panel mx-auto p-5 mt-5" style="max-width: 650px;">
        <h3 class="text-center mb-4 fw-bold text-dark">Create New Course</h3>

        <c:if test="${not empty MSG}">
            <div class="alert alert-danger border-0 shadow-sm rounded-3 text-center" role="alert">
                ${MSG}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/MainController" method="POST">
            
            <div class="mb-4">
                <label for="courseID" class="form-label fw-bold text-dark small">Course ID</label>
                <input type="text" class="form-control" id="courseID" name="courseID" placeholder="Enter Course ID" required>
            </div>

            <div class="row g-4 mb-4">
                <div class="col-md-6">
                    <label for="courseCode" class="form-label fw-bold text-dark small">Course Code</label>
                    <input type="text" class="form-control" id="courseCode" name="courseCode" placeholder="Ex: CS101" required>
                </div>
                <div class="col-md-6">
                    <label for="department" class="form-label fw-bold text-dark small">Department</label>
                    <input type="text" class="form-control" id="department" name="department" placeholder="Ex: IT" required>
                </div>
            </div>

            <div class="mb-4">
                <label for="courseTitle" class="form-label fw-bold text-dark small">Course Title</label>
                <input type="text" class="form-control" id="courseTitle" name="courseTitle" placeholder="Enter Full Course Name" required>
            </div>

            <div class="mb-4">
                <label for="courseMaterials" class="form-label fw-bold text-dark small">Materials</label>
                <input type="text" class="form-control" id="courseMaterials" name="courseMaterials" placeholder="Link or material list">
            </div>

            <div class="mb-5">
                <label for="courseDescription" class="form-label fw-bold text-dark small">Description</label>
                <textarea class="form-control" id="courseDescription" name="courseDescription" rows="4" placeholder="Briefly describe the course content..."></textarea>
            </div>

            <div class="d-flex justify-content-center gap-3">
                <button type="submit" name="action" value="CreateCourse" class="btn btn-premium shadow-sm rounded-pill px-5">
                    Create Course
                </button>
                <a href="${pageContext.request.contextPath}/MainController?action=ShowCourse" class="btn btn-secondary shadow-sm rounded-pill px-5">
                    Cancel
                </a>
            </div>

        </form>
    </div>
</div>

</body>
</html>