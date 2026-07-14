<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>Assignments</title>

            <!-- Bootstrap -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

            <!-- CSS -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        </head>

        <body>

            <div class="container mt-5">

                <div class="glass-card">

                    <h2 class="mb-4">
                        Assignments - Class ${classID}
                    </h2>

                    <form action="MainController" method="post">

                        <input type="hidden" name="classID" value="${classID}">

                        <div class="mb-3">
                            <label class="form-label">Title</label>

                            <input class="form-control" type="text" name="assignmentTitle" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Description</label>

                            <input class="form-control" type="text" name="assignmentDescription">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Note</label>

                            <input class="form-control" type="text" name="assignmentNote">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Due Date</label>

                            <input class="form-control" type="datetime-local" name="dueDate">
                        </div>

                        <button class="btn-premium" type="submit" name="action" value="SaveAssignment">

                            Create Assignment

                        </button>

                    </form>

                </div>

                <br>

                <div class="glass-card">

                    <h3 class="mb-4">
                        Assignment List
                    </h3>

                    <c:if test="${empty ASSIGNMENT_LIST}">
                        <p>No assignments found.</p>
                    </c:if>

                    <table class="table table-glass">

                        <thead>

                            <tr>

                                <th>Title</th>
                                <th>Description</th>
                                <th>Note</th>
                                <th>Due Date</th>
                                <th>Action</th>

                            </tr>

                        </thead>

                        <tbody>

                            <c:forEach var="a" items="${ASSIGNMENT_LIST}">

                                <tr>

                                    <form action="MainController" method="post">

                                        <input type="hidden" name="assignmentID" value="${a.assignmentID}">

                                        <input type="hidden" name="classID" value="${classID}">

                                        <td>

                                            <input class="form-control" name="assignmentTitle"
                                                value="${a.assignmentTitle}">

                                        </td>

                                        <td>

                                            <input class="form-control" name="assignmentDescription"
                                                value="${a.assignmentDescription}">

                                        </td>

                                        <td>

                                            <input class="form-control" name="assignmentNote"
                                                value="${a.assignmentNote}">

                                        </td>

                                        <td>

                                            <input class="form-control" type="datetime-local" name="dueDate"
                                                value="${a.dueDate}">

                                        </td>

                                        <td>

                                            <button class="btn-premium" type="submit" name="action"
                                                value="UpdateAssignment">

                                                Update

                                            </button>

                                            <br><br>

                                            <button class="btn btn-secondary" type="submit" name="action"
                                                value="ViewSubmissions">

                                                View

                                            </button>

                                        </td>

                                    </form>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                    <form action="MainController" method="post">

                        <button class="btn-premium" type="submit" name="action" value="BackToMyClasses">

                            Back

                        </button>

                    </form>

                </div>

            </div>

        </body>

        </html>