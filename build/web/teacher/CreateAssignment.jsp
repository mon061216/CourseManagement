<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Assignment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg auth-flex">
        <div class="glass-card" style="width: 100%; max-width: 600px; margin: 40px 20px;">
            <h3 class="text-center mb-4 fw-bold text-dark">Thêm Bài Tập Mới</h3>
            
            <form action="MainController" method="POST">
                <input type="hidden" name="action" value="create">

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="assignmentID" class="form-label text-muted small fw-bold">Mã bài tập (Assignment ID):</label>
                        <input type="text" class="form-control" id="assignmentID" name="assignmentID" placeholder="Ví dụ: ASGN009" required>
                    </div>
                    <div class="col-md-6">
                        <label for="classID" class="form-label text-muted small fw-bold">Mã lớp (Class ID):</label>
                        <input type="text" class="form-control" id="classID" name="classID" placeholder="Ví dụ: CL001" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="assignmentTitle" class="form-label text-muted small fw-bold">Tiêu đề bài tập:</label>
                    <input type="text" class="form-control" id="assignmentTitle" name="assignmentTitle" required>
                </div>

                <div class="mb-3">
                    <label for="assignmentDescription" class="form-label text-muted small fw-bold">Mô tả bài tập:</label>
                    <textarea class="form-control" id="assignmentDescription" name="assignmentDescription" rows="3"></textarea>
                </div>

                <div class="mb-3">
                    <label for="assignmentNote" class="form-label text-muted small fw-bold">Ghi chú (Note):</label>
                    <input type="text" class="form-control" id="assignmentNote" name="assignmentNote">
                </div>

                <div class="mb-4">
                    <label for="dueDate" class="form-label text-muted small fw-bold">Hạn nộp (Due Date):</label>
                    <input type="datetime-local" class="form-control" id="dueDate" name="dueDate" required>
                </div>

                <div class="d-flex justify-content-end gap-2 mt-4 pt-3 border-top border-secondary-subtle">
                    <a href="Assignments.jsp" class="btn btn-secondary rounded-pill px-4 shadow-sm">Hủy</a>
                    <button type="submit" class="btn btn-premium rounded-pill px-4 shadow-sm">Lưu bài tập</button>
                </div>
            </form>
        </div>
    </body>
</html>