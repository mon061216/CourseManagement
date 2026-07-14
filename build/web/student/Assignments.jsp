<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Assignments</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>
    <body class="animated-bg">
        <div class="container mt-5 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Class ${classID} - Assignments</li>
                </ol>
            </nav>

            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <div class="glass-panel p-5 h-100">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h4 class="fw-bold text-dark mb-0">Assignments List</h4>
                            <form action="MainController" method="post" class="m-0">
                                <input type="hidden" name="classID" value="${classID}">
                                <input type="hidden" name="studentID" value="${user.userID}">
                                <button class="btn btn-premium btn-sm rounded-pill px-4 shadow-sm" type="submit" name="action" value="ViewScore">
                                    View My Scores
                                </button>
                            </form>
                        </div>
                        
                        <c:if test="${empty ASSIGNMENT_LIST}">
                            <div class="alert alert-info border-0 shadow-sm rounded-3">No assignments have been posted for this class yet.</div>
                        </c:if>
                        
                        <c:if test="${not empty ASSIGNMENT_LIST}">
                            <div class="table-responsive">
                                <table class="table table-glass text-center mb-0">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Description</th>
                                            <th>Note</th>
                                            <th>Due Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="a" items="${ASSIGNMENT_LIST}">
                                            <tr>
                                                <td class="fw-bold text-primary">${a.assignmentTitle}</td>
                                                <td>${a.assignmentDescription}</td>
                                                <td class="text-muted small">${a.assignmentNote}</td>
                                                <td>
                                                    <span class="badge bg-warning text-dark bg-opacity-75 rounded-pill px-3 py-2">${a.dueDate}</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>

                        <div class="mt-5 pt-3 border-top border-secondary-subtle">
                            <form action="MainController" method="post">
                                <button class="btn btn-secondary shadow-sm rounded-pill px-5" type="submit" name="action" value="MyClasses">
                                    &larr; Back to Dashboard
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
