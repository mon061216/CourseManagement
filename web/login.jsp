<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; height: 100vh; display: flex; align-items: center; justify-content: center; }
        .login-card { width: 100%; max-width: 400px; padding: 20px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); background: #fff; }
    </style>
</head>
<body>
    <div class="login-card">
        <h3 class="text-center mb-4">Đăng Nhập</h3>
        
        <c:if test="${not empty MSG_LOGIN}">
            <div class="alert alert-danger p-2 small text-center">${MSG_LOGIN}</div>
        </c:if>
        <c:if test="${not empty MSG_LOGOUT}">
            <div class="alert alert-success p-2 small text-center">${MSG_LOGOUT}</div>
        </c:if>

        <form action="MainController" method="POST">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" placeholder="Nhập tên đăng nhập" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required />
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="gridCheck">
                <label class="form-check-label" for="gridCheck">Remember me</label>
            </div>
            <button type="submit" name="action" value="Login" class="btn btn-primary w-100">Login</button>
        </form>
        <div class="text-center mt-3">
            <a href="Register.jsp" class="text-decoration-none small">Chưa có tài khoản? Đăng ký ngay</a>
        </div>
    </div>
</body>
</html>