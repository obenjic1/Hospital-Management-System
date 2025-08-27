<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3 col-lg-7">
        <h2>Hospital Staff</h2>
    <button onclick="loadPageModalForm('staff/new')" data-toggle="tooltip" data-placement="top" title="create new user" type="button" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" class="btn btn-primary" >+ Add Staff </button>
        
    </div>
<!-- href="/staff/new"  -->
    <table class="table table-bordered table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Speciality</th>
            <th>Salary</th>
            
            <th>Phone</th>
            <th>Email</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${staffList}">
            <tr>
                <td>${s.id}</td>
                <td>${s.firstName} ${s.lastName}</td>
                <td>${s.department.name}</td>
                <td>${s.speciality}</td>
                <td>${s.salary}</td>
                
                <td>${s.phone}</td>
                <td>${s.email}</td>
                <td>
                    <span class="badge ${s.active ? 'bg-success' : 'bg-danger'}">
                        ${s.active ? 'Active' : 'Inactive'}
                    </span>
                </td>
                <td>
                    <a href="/staff/edit/${s.id}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="/staff/delete/${s.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
