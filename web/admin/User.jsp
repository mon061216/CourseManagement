<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 text-white">
            <div class="container">
                <span class="navbar-brand">User Administration</span>
                <a href="${pageContext.request.contextPath}/MainController?action=ShowCourse" class="btn btn-outline-light btn-sm">Home</a>
            </div>
        </nav>

        <div class="container-fluid px-4">
            <c:if test="${not empty MSG}">
                <div class="alert alert-info shadow-sm">${MSG}</div>
            </c:if>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2 class="h4">System User Records</h2>
                <form action="${pageContext.request.contextPath}/MainController" method="POST">
                    <button type="submit" name="action" value="ShowUser" class="btn btn-primary">Refresh User List</button>
                </form>
            </div>

            <div class="card shadow-sm">
                <div class="table-responsive">
                    <table class="table table-striped align-middle mb-0">
                        <thead class="table-dark small">
                            <tr>
                                <th class="text-center">#</th>
                                <th>User ID</th>
                                <th>Role</th>
                                <th>Username</th>
                                <th>Full Name</th>
                                <th>Date of Birth</th>
                                <th>Address</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th class="text-center">Status</th>
                                <th colspan="2" class="text-center">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty userList}">
                                <tr>
                                    <td colspan="11" class="text-center py-5 text-muted">
                                        No users found in the system database.
                                    </td>
                                </tr>
                            </c:if>

                            <c:forEach var="u" items="${userList}" varStatus="status">
                                <tr>
                            <form action="MainController" method="POST">
                                <td class="text-center text-muted small">
                                    ${status.count}
                                </td>

                                <td>
                                    <input type="text" name="userID" 
                                           value="${u.userID}" 
                                           class="form-control form-control-sm text-center" 
                                           readonly>
                                </td>

                                <td>
                                    <input type="text" name="roleID" 
                                           value="${u.roleID}" 
                                           class="form-control form-control-sm">
                                </td>

                                <td>
                                    <input type="text" name="username" 
                                           value="${u.username}" 
                                           class="form-control form-control-sm">
                                </td>

                                <td>
                                    <input type="text" name="fullname" 
                                           value="${u.fullname}" 
                                           class="form-control form-control-sm">
                                </td>

                                <td>
                                    <input type="date" name="dob" 
                                           value="<fmt:formatDate value='${u.dob}' pattern='yyyy-MM-dd'/>" 
                                           class="form-control form-control-sm">
                                </td>
                                <td>
                                    <input type="text" name="address"
                                           value="${u.address}"
                                           class="form-control form-control-sm">
                                </td>
                                <td>
                                    <input type="email" name="mail" 
                                           value="${u.mail}" 
                                           class="form-control form-control-sm">
                                </td>

                                <td>
                                    <input type="text" name="phoneNumber" 
                                           value="${u.phoneNumber}" 
                                           class="form-control form-control-sm">
                                </td>

                                <td class="text-center">
                                    <select name="userState" class="form-select form-select-sm">
                                        <option value="true" ${u.userState ? "selected" : ""}>Active</option>
                                        <option value="false" ${!u.userState ? "selected" : ""}>Inactive</option>
                                    </select>
                                </td>

                                <td>
                                    <button type="submit" 
                                            name="action" 
                                            value="UpdateUser" 
                                            class="btn btn-primary btn-sm">
                                        Update
                                    </button>
                                </td>
                            </form>

                            <td>
                                <form action="MainController" method="POST"
                                      onsubmit="return confirm('Are you sure you want to delete this user?');">
                                    <input type="hidden" name="userID" value="${u.userID}">
                                    <button type="submit" 
                                            name="action" 
                                            value="DeleteUser" 
                                            class="btn btn-danger btn-sm">
                                        Delete
                                    </button>
                                </form>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer bg-white text-muted small">
                    Total Records Found: <strong>${empty userList ? 0 : userList.size()}</strong>
                </div>
            </div>
        </div>
    </body>
</html>