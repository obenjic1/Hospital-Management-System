<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <title>Patient History</title>
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
    body{background:#f4f7fe;font-family:"Segoe UI",Arial,sans-serif;}
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
      <i class="bi bi-clock-history text-primary me-2"></i>Patient History
    </span>
    <div class="d-flex align-items-center small text-muted">
      <i class="bi bi-clock me-1"></i><span id="digital-clock"></span>
    </div>
  </div>
</nav>

<!-- =====  MAIN  ===== -->
<main id="add-machine" class="mt-4">
  <div class="table-card">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h5 class="mb-0 fw-bold">Patient Tracking Log</h5>
      <span class="badge bg-secondary">Total : ${patients.tracking.size()}</span>
    </div>

    <c:choose>
      <c:when test="${empty patients.tracking}">
        <div class="text-center py-5">
          <div class="text-muted fs-1"><i class="bi bi-inbox"></i></div>
          <p class="fw-bold mt-3">No history recorded yet</p>
          <p class="text-muted">Tracking events will appear here automatically</p>
        </div>
      </c:when>
      <c:otherwise>
        <div class="table-responsive">
          <table class="table table-hover align-middle" id="historyTable" style="width:100%">
            <thead>
              <tr>
                <th style="width:120px"><i class="bi bi-calendar me-2"></i><fmt:message key="date"/></th>
                <th style="width:150px"><i class="bi bi-person me-2"></i><fmt:message key="user"/></th>
                <th><i class="bi bi-info-circle me-2"></i><fmt:message key="operation"/></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="p" items="${patients.tracking}" varStatus="loop">
                <tr>
                  <td>
                    <span class="badge badge-date">
<%--                       <fmt:formatDate value="${p.creationDate}" pattern="dd-MM-yyyy"/> --%>
                    </span>
<%--                    <br><small class="text-muted"><fmt:formatDate value="${p.creationDate}" pattern="HH:mm"/></small> --%>
                  </td>
                  <td>
                    <span class="badge badge-user">${p.performedBy}</span>
                  </td>
                  <td>${p.description}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:otherwise>
    </c:choose>
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
  $(function () {
    $('#historyTable').DataTable({
      paging: true,
      searching: true,
      lengthChange: false,
      info: true,
      autoWidth: false,
      order: [[0, 'desc']], // newest first
      language: {
        search: '<i class="bi bi-search"></i>',
        searchPlaceholder: 'Search history...'
      }
    });
  });

  /* =====  LIVE CLOCK  ===== */
  function updateClock() {
    const now = new Date();
    document.getElementById('digital-clock').textContent =
      now.toLocaleTimeString('en-GB');
  }
  setInterval(updateClock, 1000);
  updateClock();
</script>
</body>
</html>