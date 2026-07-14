<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Assignments</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>
    <body class="animated-bg">
        <div class="container mt-4 mb-5">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item text-muted">My Classes</li>
                    <li class="breadcrumb-item active fw-bold text-dark" aria-current="page">Class ${classID} - Assignments</li>
                </ol>
            </nav>

            <div class="row gap-4 justify-content-center">
                <!-- Create Assignment Form -->
                <div class="col-lg-4">
                    <div class="glass-panel p-4 h-100">
                        <h4 class="fw-bold text-primary mb-4">Create Assignment</h4>
                        <form action="MainController" method="post">
                            <input type="hidden" name="classID" value="${classID}">
                            
                            <div class="mb-3">
                                <label class="form-label text-muted small fw-bold">Title</label>
                                <input class="form-control" type="text" name="assignmentTitle" required>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label text-muted small fw-bold">Description</label>
                                <textarea class="form-control" name="assignmentDescription" rows="2"></textarea>
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label text-muted small fw-bold">Note</label>
                                <input class="form-control" type="text" name="assignmentNote">
                            </div>
                            
                            <div class="mb-4">
                                <label class="form-label text-muted small fw-bold">Due Date</label>
                                <input class="form-control" type="datetime-local" name="dueDate" required>
                            </div>
                            
                            <button class="btn btn-premium w-100" type="submit" name="action" value="SaveAssignment">
                                Create Assignment
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Assignment List -->
                <div class="col-lg-7">
                    <div class="glass-panel p-4 h-100">
                        <h4 class="fw-bold text-dark mb-4">Assignment List</h4>
                        
                        <c:if test="${empty ASSIGNMENT_LIST}">
                            <div class="alert alert-warning border-0 shadow-sm rounded-3">No assignments found for this class.</div>
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
                                            <th class="text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="a" items="${ASSIGNMENT_LIST}">
                                            <tr>
                                                <form action="MainController" method="post">
                                                    <input type="hidden" name="assignmentID" value="${a.assignmentID}">
                                                    <input type="hidden" name="classID" value="${classID}">
                                                    
                                                    <td>
                                                        <input class="form-control form-control-sm text-center" name="assignmentTitle" value="${a.assignmentTitle}">
                                                    </td>
                                                    <td>
                                                        <input class="form-control form-control-sm text-center" name="assignmentDescription" value="${a.assignmentDescription}">
                                                    </td>
                                                    <td>
                                                        <input class="form-control form-control-sm text-center" name="assignmentNote" value="${a.assignmentNote}">
                                                    </td>
                                                    <td>
                                                        <input class="form-control form-control-sm text-center" type="datetime-local" name="dueDate" value="${a.dueDate}">
                                                    </td>
                                                    <td class="d-flex flex-column gap-2 justify-content-center">
                                                        <button class="btn btn-outline-success btn-sm rounded-pill" type="submit" name="action" value="UpdateAssignment">
                                                            Save
                                                        </button>
                                                        <button class="btn btn-premium btn-sm rounded-pill" type="submit" name="action" value="ViewSubmissions">
                                                            Submissions
                                                        </button>
                                                    </td>
                                                </form>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>

                        <div class="mt-4 pt-3 border-top border-secondary-subtle">
                            <form action="MainController" method="post">
                                <button class="btn btn-secondary btn-sm rounded-pill px-4 shadow-sm" type="submit" name="action" value="BackToMyClasses">
                                    &larr; Back to Classes
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>