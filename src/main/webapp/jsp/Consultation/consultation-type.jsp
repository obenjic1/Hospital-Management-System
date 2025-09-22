<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Manage Consultation Types</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

    <h2>🩺 Manage Consultation Types & Subtypes</h2>
    <hr/>

    <!-- Flash messages (if needed) -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <div class="row">
        <!-- Add Type -->
        <div class="col-md-6">
            <h4>Add New Consultation Type</h4>
            <form method="post" >
                <div class="my-3">
                    <label class="form-label">Type Name:</label>
                    <input type="text" id="name" name="name" class="form-control" required />
                </div>
                <button type="button"  onclick="saveConsultationType()" class="btn btn-outline-primary">Add Type</button>
            </form>
        </div>

        <!-- Add Subtype -->
        <div class="col-md-6">
            <h4>Add New Subtype</h4>
            <form method="post" action="/admin/consultation-types/add-subtype">
                <div class="my-3">
                    <label class="form-label">Subtype Name:</label>
                    <input type="text" id="subName" name="name" class="form-control" required />
                </div>
                <div class="my-3">
                    <label class="form-label">Price:</label>
                    <input type="number"id="price" name="price" class="form-control" step="0.01" required />
                </div>
                <div class="my-3">
                    <label class="form-label">Type:</label>
                    <select name="typeId"  id="typeId" class="form-select" required>
                        <option value="">-- Select Type --</option>
                        <c:forEach var="type" items="${types}">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="button" onclick="saveConsultationSubtype()" class="btn btn-success">Add Subtype</button>
            </form>
        </div>
    </div>  

    <hr/>

    <!-- Display Types and Subtypes -->
    <h4>Existing Consultation Types & Subtypes</h4>
    <c:forEach var="type" items="${types}">
        <div class="card mt-3">
            <div class="card-header bg-light">
                <strong>${type.name}</strong>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty type.subtypes}">
                        <table class="table table-bordered table-sm">
                            <thead>
                                <tr>
                                    <th>Subtype Name</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="sub" items="${type.subtypes}">
                                    <tr>
                                        <td>${sub.name}</td>
                                        <td>${sub.price}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>No subtypes added for this type.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>

</body>
    <script src="assets/js/hospital/consultation.js"></script>

</html>
