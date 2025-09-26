<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <title>Edit Staff Record</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- =====  LOCAL BOOTSTRAP 5  ===== -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

  <!-- =====  CUSTOM THEME  ===== -->
  <style>
    :root{
      --clr-primary:#6f42c1;
      --radius:.75rem;
    }
    body{background:#f4f7fe;font-family:"Segoe UI",Arial,sans-serif;}
    .card-form{
      background:#fff;
      border-radius:var(--radius);
      box-shadow:0 .25rem .75rem rgba(0,0,0,.08);
      padding:2rem;
    }
    .section-title{
      font-size:1.1rem;font-weight:600;color:var(--clr-primary);
      border-bottom:2px solid var(--clr-primary);padding-bottom:.5rem;margin-bottom:1.5rem;
    }
    .input-group-text{background:var(--clr-primary);color:#fff;border:0;}
    .btn-save{width:200px;}
  </style>
</head>

<body class="container-fluid p-4">
<!-- =====  TOP BAR  ===== -->
<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1 d-flex align-items-center">
      <i class="bi bi-pencil-square text-primary me-2"></i>Edit Staff Record
    </span>
    <div class="d-flex align-items-center small text-muted">
      <i class="bi bi-clock me-1"></i><span id="digital-clock"></span>
    </div>
  </div>
</nav>

<!-- =====  MAIN  ===== -->
<main id="add-user" class="mt-4">
  <div class="card-form">
    <h5 class="section-title">Personal Details</h5>

    <form id="staffForm" class="row g-3 needs-validation" novalidate>
      <!-- Row 1 -->
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="first.name"/> <span class="text-danger">*</span></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-person"></i></span>
          <input type="text" id="firstName" name="firstName" class="form-control" value="${staff.firstName}" placeholder="First name" required>
        </div>
      </div>
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="last.name"/></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-person"></i></span>
          <input type="text" id="lastName" name="lastName" class="form-control" value="${staff.lastName}" placeholder="Last name">
        </div>
      </div>

      <!-- Row 2 -->
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="email"/></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-envelope"></i></span>
          <input type="email" id="email" name="email" class="form-control" value="${staff.email}" placeholder="name@example.com">
        </div>
        <span id="emailMsg" class="small text-danger"></span>
      </div>
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="phone"/> <span class="text-danger">*</span></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-telephone"></i></span>
          <input type="text" id="mobile" name="mobile" class="form-control" value="${staff.phone}" placeholder="Mobile" required>
        </div>
      </div>

      <!-- Row 3 -->
      <div class="col-md-6">
        <label class="form-label">Gender</label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-gender-ambiguous"></i></span>
          <select id="gender" name="gender" class="form-select">
            <option value="male" ${staff.gender eq 'male' ? 'selected' : ''}>Male</option>
            <option value="female" ${staff.gender eq 'female' ? 'selected' : ''}>Female</option>
          </select>
        </div>
      </div>
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="address"/> <span class="text-danger">*</span></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
          <input type="text" id="address" name="address" class="form-control" value="${staff.address}" placeholder="Address" required>
        </div>
      </div>

      <!-- Row 4 -->
      <div class="col-md-6">
        <label class="form-label"><fmt:message key="list.departement"/></label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-building"></i></span>
          <select id="department2" name="department" class="form-select" onchange="getDepartmentUpdate()">
            <c:forEach items="${departments}" var="departement">
              <option value="${departement.id}" data-dept="${departement.name}"
                <c:if test="${departement.id eq staff.department.id}">selected</c:if>>${departement.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="col-md-6" id="percentageDiv2" style="display:none">
        <label class="form-label">Consultation Percentage</label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-percent"></i></span>
          <input type="number" id="percentage2" name="percentage" class="form-control" value="${staff.percentage}" placeholder="0-100">
        </div>
      </div>

      <!-- Row 5 -->
      <div class="col-md-6">
        <label class="form-label">Speciality</label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-briefcase"></i></span>
          <input type="text" id="speciality" name="speciality" class="form-control" value="${staff.speciality}" placeholder="e.g. Cardiology">
        </div>
      </div>
      <div class="col-md-6">
        <label class="form-label">Salary (CFA)</label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-currency-dollar"></i></span>
          <input type="number" id="salary" name="salary" class="form-control" value="${staff.salary}" placeholder="0" min="0" step="0.01">
        </div>
      </div>

      <!-- Row 6 -->
      <div class="col-md-6">
        <label class="form-label">Status</label>
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-toggle-on"></i></span>
          <select id="status" name="status" class="form-select">
            <option value="true" ${staff.active ? 'selected' : ''}>Active</option>
            <option value="false" ${!staffs.active ? 'selected' : ''}>Inactive</option>
          </select>
        </div>
      </div>

      <!-- Submit -->
      <div class="col-12 text-center">
        <button type="button" id="createBtn" style="float:right" onclick="updateStaff(${staff.id})" class="btn btn-outline-primary btn-save">
          <i class="bi bi-check2-circle me-2"></i>Save Changes
        </button>
      </div>
    </form>
  </div><!-- /card-form -->
</main>

<!-- =====  SCRIPTS  ===== -->
<script src="assets/vendor/jquery-3.5.1.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/hospital/staff.js"></script>

<script>
  /* =====  LIVE CLOCK  ===== */
  function updateClock(){
    const now=new Date();
    document.getElementById('digital-clock').textContent=now.toLocaleTimeString('en-GB');
  }
  setInterval(updateClock,1000);
  updateClock();
</script>
</body>
</html>