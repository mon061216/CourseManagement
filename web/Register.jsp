<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light py-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card border-0 shadow-lg">
                    <div class="card-body p-5">
                        <h2 class="text-center mb-4">Register New Account</h2>
                        
                        <c:if test="${not empty MSG}">
                            <div class="alert alert-danger small">${MSG}</div>
                        </c:if>

                        <form action="MainController" method="POST">
                            
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label small fw-bold">Username</label>
                                    <input type="text" name="username" class="form-control" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label small fw-bold">Password</label>
                                    <input type="password" name="password" class="form-control" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small fw-bold">Full Name</label>
                                <input type="text" name="fullName" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small fw-bold">Email</label>
                                <input type="email" name="mail" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label class="form-label small fw-bold">Phone Number</label>
                                <input type="text" name="phoneNumber" class="form-control" required>
                            </div>
                            <div class="mb-3 text-center">
                                <button type="submit" name="action" value="Register" class="btn btn-primary w-100">Create Account</button>
                                <a href="login.jsp" class="btn btn-link btn-sm mt-2 text-decoration-none">Already have an account? Login</a>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>