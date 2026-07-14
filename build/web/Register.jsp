<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account - Course Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body class="animated-bg auth-flex">
    <div class="glass-card" style="width: 100%; max-width: 500px; margin: 40px 20px;">
        <h3 class="text-center mb-4 fw-bold text-dark">Register New Account</h3>
        
        <c:if test="${not empty MSG}">
            <div class="alert alert-danger p-2 small text-center rounded-3 border-0 shadow-sm">${MSG}</div>
        </c:if>

        <form action="MainController" method="POST">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label text-muted small fw-bold">Username</label>
                    <input type="text" name="username" class="form-control" placeholder="Choose username" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label text-muted small fw-bold">Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Create password" required>
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label text-muted small fw-bold">Full Name</label>
                <input type="text" name="fullName" class="form-control" placeholder="Enter your full name" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label text-muted small fw-bold">Email</label>
                <input type="email" name="mail" class="form-control" placeholder="Enter email address">
            </div>
            
            <div class="mb-4">
                <label class="form-label text-muted small fw-bold">Phone Number</label>
                <input type="text" name="phoneNumber" class="form-control" placeholder="Enter phone number" required>
            </div>
            
            <button type="submit" name="action" value="Register" class="btn btn-premium w-100 py-2">Create Account</button>
        </form>
        
        <div class="text-center mt-4 pt-3 border-top border-secondary-subtle">
            <span class="text-muted small">Already have an account? </span>
            <a href="login.jsp" class="text-decoration-none fw-bold" style="color: var(--primary)">Sign In here</a>
        </div>
    </div>
</body>
</html>