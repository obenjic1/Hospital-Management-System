<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Hospital Sales Dashboard</title>
<script src="assets/vendor/jquery-3.5.1.min.js"></script>


<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">

    <h2 class="mb-4 text-center">📊 Sales & Pharmacy Dashboard</h2>

    <!-- Summary Cards -->
    <div class="row g-4">
        <div class="col-md-3">
            <div class="card shadow border-0 text-center">
                <div class="card-body">
                    <h6>💰 Today Revenue</h6>
                    <h4 class="text-success">
                        <c:out value="${todayRevenue}" /> CFA
                    </h4>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow border-0 text-center">
                <div class="card-body">
                    <h6>📦 Today Items</h6>
                    <h4 class="text-primary">
                        <c:out value="${todayItems}" />
                    </h4>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow border-0 text-center">
                <div class="card-body">
                    <h6>📅 This Week Items</h6>
                    <h4 class="text-info">
                        <c:out value="${weekItems}" />
                    </h4>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow border-0 text-center">
                <div class="card-body">
                    <h6>🗓 This Month Items</h6>
                    <h4 class="text-warning">
                        <c:out value="${monthItems}" />
                    </h4>
                </div>
            </div>
        </div>
    </div>

    <!-- Low Stock Medicines -->
    <div class="mt-5">
        <h4 class="text-danger">⚠️ Low Stock Medicines</h4>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Medicine</th>
                <th>Quantity</th>
                <th>Threshold</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="med" items="${lowStock}">
                <tr>
                    <td><c:out value="${med.name}" /></td>
                    <td><c:out value="${med.quantity}" /></td>
                    <td><c:out value="${med.threshold}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Expired Medicines -->
    <div class="mt-5">
        <h4 class="text-danger">❌ Expired Medicines</h4>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Medicine</th>
                <th>Expiration Date</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="med" items="${expired}">
                <tr class="table-danger">
                    <td><c:out value="${med.name}" /></td>
                    <td><c:out value="${med.expirationDate}" /></td>
                    <td><c:out value="${med.quantity}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Sales History -->
    <div class="mt-5">
        <h4 class="text-primary">📑 Sales History</h4>

        <!-- Filter Form -->
        <form action="dashboard" method="get" class="row g-3 mb-3">
            <div class="col-md-2">
                <label class="form-label">From Date</label>
                <input type="date" class="form-control" name="startDate" value="${param.startDate}">
            </div>
            <div class="col-md-2">
                <label class="form-label">To Date</label>
                <input type="date" class="form-control" name="endDate" value="${param.endDate}">
            </div>
            <div class="col-md-1">
                <label class="form-label">Pharmacist</label>
                <select class="form-select" name="pharmacistId">
                    <option value="">All</option>
                    <c:forEach var="user" items="${pharmacists}">
                        <option value="${user.id}" ${param.pharmacistId == user.id ? 'selected' : ''}>
                            ${user.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-1 d-flex align-items-end">
                <button type="submit" class="btn btn-success w-100">Filter</button>
            </div>
        </form>

        <!-- Table -->
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Receipt #</th>
                <th>Customer</th>
                <th>Pharmacist</th>
                <th>Payment</th>
                <th>Total (CFA)</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sale" items="${salesHistory}">
                <tr>
                    <td>${sale.receiptNumber}</td>
                    <td>${sale.customerName}</td>
                    <td>${sale.pharmacist.name}</td>
                    <td>${sale.paymentMethod}</td>
                    <td class="text-success fw-bold">${sale.total}</td>
                    <td>${sale.saleDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
