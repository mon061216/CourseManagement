<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-4">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">Classes</li>
            <li class="breadcrumb-item active" aria-current="page">Class ${CLASS_ID}</li>
        </ol>
    </nav>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Students in Class <span class="badge bg-secondary">${CLASS_ID}</span></h2>
    </div>

    <c:if test="${empty STUDENT_LIST}">
        <div class="alert alert-warning">No students enrolled in this class yet.</div>
    </c:if>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <table class="table table-hover mb-0">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th class="text-center">Attendance</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="s" items="${STUDENT_LIST}">
                    <tr>
                        <td><strong>${s.userID}</strong></td>
                        <td>${s.fullname}</td>
                        <td><code class="text-muted">${s.username}</code></td>
                        <td>${s.mail}</td>
                        <td>${s.phoneNumber}</td>
                        <td class="text-center">
                           
                        </td>
                        <td class="text-center">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="studentID" value="${s.userID}">
                                <input type="hidden" name="classID" value="${CLASS_ID}">
                                <button name="action" value="ViewScore" class="btn btn-outline-primary btn-sm">
                                    View Score
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>