<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    </head>
    <body class="animated-bg auth-flex">

        <div class="glass-card" style="width: 100%; max-width: 600px; margin: 40px 20px;">
            <div class="d-flex justify-content-between align-items-center mb-4 border-bottom border-secondary-subtle pb-3">
                <h3 class="fw-bold text-dark mb-0">My Profile</h3>
                <span class="badge bg-primary bg-opacity-75 rounded-pill px-3 py-2">ID: ${user.userID}</span>
            </div>

            <form id="profileForm" action="MainController" method="POST">
                
                <div class="mb-3">
                    <label class="form-label text-muted small fw-bold">Full Name</label>
                    <input type="text" class="form-control" name="fullname" value="${user.fullname}" disabled required>
                </div>

                <div class="mb-3">
                    <label class="form-label text-muted small fw-bold">Email Address</label>
                    <input type="email" class="form-control" name="mail" value="${user.mail}" disabled required>
                </div>

                <div class="mb-3">
                    <label class="form-label text-muted small fw-bold">Phone Number</label>
                    <input type="tel" class="form-control" name="phoneNumber" value="${user.phoneNumber}" disabled>
                </div>

                <div class="mb-3">
                    <label class="form-label text-muted small fw-bold">Date of Birth</label>
                    <input type="date" class="form-control" name="dob" value="${user.dob}" disabled>
                </div>

                <div class="mb-4">
                    <label class="form-label text-muted small fw-bold">Address</label>
                    <input type="text" class="form-control" name="address" value="${user.address}" disabled>
                </div>

                <input type="hidden" name="userID" value="${user.userID}">
                <input type="hidden" name="roleID" value="${user.roleID}">
                <input type="hidden" name="userState" value="${user.userState}">
                <input type="hidden" name="username" value="${user.username}">

                <div class="d-flex flex-column flex-sm-row gap-2 mt-4 pt-3 border-top border-secondary-subtle">
                    <a href="javascript:history.back()" class="btn btn-secondary shadow-sm px-4 rounded-pill" id="backBtn">
                        &larr; Back
                    </a>
                    <button type="button" class="btn btn-premium shadow-sm px-4 rounded-pill ms-sm-auto" id="editBtn" onclick="toggleEditMode(true)">
                        Edit Profile
                    </button>
                    <button type="button" class="btn btn-secondary shadow-sm px-4 rounded-pill d-none ms-sm-auto" id="cancelBtn" onclick="toggleEditMode(false)">
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-success shadow-sm px-4 rounded-pill d-none" id="saveBtn" name="action" value="UpdateUser">
                        Save Changes
                    </button>
                </div>
            </form>
        </div>

        <script>
            function toggleEditMode(isEditing) {
                const inputs = document.querySelectorAll('#profileForm input[type="text"], #profileForm input[type="email"], #profileForm input[type="tel"], #profileForm input[type="date"]');
                const editBtn = document.getElementById('editBtn');
                const saveBtn = document.getElementById('saveBtn');
                const cancelBtn = document.getElementById('cancelBtn');
                const backBtn = document.getElementById('backBtn');

                inputs.forEach(input => {
                    input.disabled = !isEditing;
                });

                if (isEditing) {
                    editBtn.classList.add('d-none');
                    backBtn.classList.add('d-none');
                    saveBtn.classList.remove('d-none');
                    cancelBtn.classList.remove('d-none');
                } else {
                    window.location.reload();
                }
            }
        </script>
    </body>
</html>