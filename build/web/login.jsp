<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Course Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body class="animated-bg auth-flex">
    <div class="glass-card" style="width: 100%; max-width: 420px; margin: 0 20px;">
        <h3 class="text-center mb-4 fw-bold text-dark">Welcome Back</h3>
        
        <c:if test="${not empty MSG_LOGIN}">
            <div class="alert alert-danger p-2 small text-center rounded-3 border-0 shadow-sm">${MSG_LOGIN}</div>
        </c:if>
        <c:if test="${not empty MSG_LOGOUT}">
            <div class="alert alert-success p-2 small text-center rounded-3 border-0 shadow-sm">${MSG_LOGOUT}</div>
        </c:if>

        <form action="MainController" method="POST">
            <div class="mb-3">
                <label class="form-label text-muted small fw-bold">Username</label>
                <input type="text" name="username" class="form-control" placeholder="Enter your username" required />
            </div>
            <div class="mb-4">
                <label class="form-label text-muted small fw-bold">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter your password" required />
            </div>
            <div class="mb-4 form-check">
                <input type="checkbox" class="form-check-input border-secondary" id="gridCheck">
                <label class="form-check-label text-muted small" for="gridCheck">Remember me</label>
            </div>
            <button type="submit" name="action" value="Login" class="btn btn-premium w-100 py-2">Sign In</button>
        </form>
        
        <div class="text-center mt-4 pt-3 border-top border-secondary-subtle">
            <span class="text-muted small">Don't have an account? </span>
            <a href="Register.jsp" class="text-decoration-none fw-bold" style="color: var(--primary)">Register here</a>
        </div>
    </div>
</body>
</html>