<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <style>
            :root {
                --primary: #4a90e2;
                --success: #2ecc71;
                --danger: #e74c3c;
                --bg-color: #f5f7fb;
                --text-color: #333;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: var(--bg-color);
                color: var(--text-color);
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
            }

            .profile-container {
                background: #fff;
                width: 100%;
                max-width: 600px;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0 8px 24px rgba(149, 157, 165, 0.2);
            }

            .profile-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-bottom: 2px solid #eaedf2;
                padding-bottom: 15px;
                margin-bottom: 25px;
            }

            .profile-header h2 {
                margin: 0;
                color: #2c3e50;
            }

            .badge {
                background-color: #e8f0fe;
                color: var(--primary);
                padding: 6px 12px;
                border-radius: 20px;
                font-size: 0.85rem;
                font-weight: bold;
            }

            .form-group {
                margin-bottom: 18px;
            }

            label {
                display: block;
                font-weight: 600;
                margin-bottom: 8px;
                font-size: 0.9rem;
                color: #6c757d;
            }

            input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ced4da;
                border-radius: 8px;
                box-sizing: border-box;
                font-size: 1rem;
                transition: border-color 0.3s ease;
            }

            input:focus {
                outline: none;
                border-color: var(--primary);
            }

            input:disabled {
                background-color: #f8f9fa;
                border: 1px solid transparent;
                color: #212529;
                cursor: not-allowed;
                padding-left: 0; /* Aesthetic choice for view mode */
            }

            .btn-group {
                margin-top: 30px;
                display: flex;
                gap: 12px;
            }

            .btn {
                padding: 12px 24px;
                border: none;
                border-radius: 8px;
                font-size: 1rem;
                font-weight: bold;
                cursor: pointer;
                transition: all 0.3s ease;
                flex: 1;
            }

            .btn-edit {
                background-color: var(--primary);
                color: white;
            }
            .btn-edit:hover {
                background-color: #357abd;
            }

            .btn-save {
                background-color: var(--success);
                color: white;
                display: none;
            }
            .btn-save:hover {
                background-color: #27ae60;
            }

            .btn-cancel {
                background-color: #95a5a6;
                color: white;
                display: none;
            }
            .btn-cancel:hover {
                background-color: #7f8c8d;
            }
        </style>
    </head>
    <body>

        <div class="profile-container">
            <div class="profile-header">
                <h2>My Profile</h2>
                <span class="badge">ID: ${user.userID}</span>
            </div>

            <form id="profileForm" action="MainController" method="POST">

                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullname" value="${user.fullname}"  required>
                </div>

                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="mail" value="${user.mail}"  required>
                </div>

                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="tel" name="phoneNumber" value="${user.phoneNumber}" >
                </div>

                <div class="form-group">
                    <label>Date of Birth</label>
                    <input type="date" name="dob" value="${user.dob}" >
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <input type="text" name="address" value="${user.address}" >
                </div>
                <input type="hidden" name="userID" value="${user.userID}">
                <input type="hidden" name="roleID" value="${user.roleID}">
                <input type="hidden" name="userState" value="${user.userState}">
                <input type="hidden" name="username" value="${user.username}">
                <div class="btn-group">
                    <button type="button" class="btn btn-edit" id="editBtn" onclick="toggleEditMode(true)">Edit Profile</button>
                    <button type="submit" class="btn btn-save" id="saveBtn" name="action" value="UpdateUser">Save Changes</button>
                    <button type="button" class="btn btn-cancel" id="cancelBtn" onclick="toggleEditMode(false)">Cancel</button>
                </div>
            </form>
        </div>

        <script>
            function toggleEditMode(isEditing) {
                const inputs = document.querySelectorAll('#profileForm input');
                const editBtn = document.getElementById('editBtn');
                const saveBtn = document.getElementById('saveBtn');
                const cancelBtn = document.getElementById('cancelBtn');

                inputs.forEach(input => {
                    input.disabled = !isEditing;
                });

                if (isEditing) {
                    editBtn.style.display = 'none';
                    saveBtn.style.display = 'inline-block';
                    cancelBtn.style.display = 'inline-block';
                } else {
                    // If cancelled, reload the original data to discard unsaved edits
                    window.location.reload();
                }
            }
        </script>

    </body>
</html>