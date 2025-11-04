<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <title>Medicine </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- =====  LOCAL BOOTSTRAP 5  ===== -->
  <link href="assets/css/billing/job.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/DataTables/datatables.css" rel="stylesheet">

  <!-- =====  CUSTOM THEME  ===== -->
  <style>
    :root{
      --clr-primary:#6f42c1;
      --clr-light:#f8f9fa;
      --radius:.75rem;
    }
    body{
      background:#f4f7fe;
      font-family:"Segoe UI",Arial,sans-serif;
    }
    .table-card{
      background:#fff;
      border-radius:var(--radius);
      box-shadow:0 .25rem .75rem rgba(0,0,0,.08);
      padding:1.5rem;
    }
    .table thead th{
      border-top:none;
      background:var(--clr-primary);
      color:#fff;
      font-weight:600;
    }
    .badge-date{background:var(--clr-primary);color:#fff;font-size:.75rem;}
    .badge-user{background:#6c757d;color:#fff;font-size:.75rem;}
  </style>
</head>

<body class="container-fluid p-4">
<!-- =====  TOP BAR  ===== -->
<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1 d-flex align-items-center">
      <i class="bi bi-clock-history text-primary me-2"></i> Medicines
    </span>
    <div class="d-flex align-items-center small text-muted">
      <i class="bi bi-clock me-1"></i><span id="digital-clock"></span>
    </div>
  </div>
</nav>

<!-- =====  MAIN  ===== -->
<main id="add-machine" class="mt-4">
  <div class="table-card">
     <table class="table table-striped table-hover align-middle" id="medicineTable">
                    <thead class="table-dark">
                        <tr>
                            <th>No</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Purchase Price (CFA)</th>
                            <th>Units / Packet</th>
                            <th>Total Qty</th>
                            <th>Expired Date</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${medicines}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${m.code}</td>
                                <td class="">${m.name}</td>
                                <td><fmt:formatNumber value="${m.purchasePrice}" type="currency" currencySymbol="CFA"/></td>
                                <td>${m.unitsPerPacket}</td>
                                <td>${m.quantity}</td>
                                 <td>${m.expirationDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
     
     
  </div><!-- /table-card -->
</main>

<!-- =====  SCRIPTS  ===== -->
<script src="assets/vendor/jquery-3.5.1.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/DataTables/datatables.js"></script>
<script src="assets/js/main.js"></script>
<script src="assets/js/billing/machine.js"></script>

<script>
  /* =====  INIT DATATABLE  ===== */
 
</script>
</body>
</html>