<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
  <meta charset="UTF-8">
  <title>Patient Details</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- LOCAL Bootstrap 5.3 CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
  <!-- LOCAL Bootstrap-Icons CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/icons/bootstrap-icons.css">

  <style>
    :root{--clr-primary:#6f42c1;--clr-danger:#e55353;--clr-success:#1cc88a;--clr-light:#f8f9fa}
    body{background-color:var(--clr-light);}
    .profile-card{border-radius:1rem;background:#fff;box-shadow:0 .125rem .25rem rgba(0,0,0,.075)}
    .section-title{font-size:1.1rem;font-weight:600;color:var(--clr-primary);border-bottom:2px solid var(--clr-primary);padding-bottom:.5rem;margin-bottom:1rem}
    .info-group{display:flex;justify-content:space-between;align-items:center;padding:.5rem 0;border-bottom:1px solid #e9ecef}
    .info-label{font-weight:600;color:#5f6f81}
    .info-value{color:#212529}
    .table th{background-color:var(--clr-light);color:#333}
  </style>
</head>

<body>
<!-- =====  TOP BAR  ===== -->
<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <a class="navbar-brand d-flex align-items-center" href="#">
      <i class="bi bi-person-heart text-primary me-2"></i>
      <span class="fw-bold">Patient Details</span>
    </a>
    <div class="d-flex gap-2">
      <a href="javascript:history.back()" class="btn btn-sm btn-outline-secondary"><i class="bi bi-arrow-left"></i> Back</a>
    </div>
  </div>
</nav>

<!-- =====  MAIN  ===== -->
<main class="container py-4">
  <div class="profile-card p-4">
    <!-- Avatar + Name -->
    <div class="text-center mb-4">
      <h4 class="mb-0">${patient.name}</h4>
      <span class="text-muted">${patient.age} yrs, ${patient.gender}</span>
    </div>

    <!-- Personal Information -->
    <div class="section-title">Personal Information</div>
    <div class="row g-3">
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-person me-2"></i>Full Name</span>
          <span class="info-value">${patient.name}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-calendar-event me-2"></i>Age</span>
          <span class="info-value">${patient.age}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-gender-ambiguous me-2"></i>Gender</span>
          <span class="info-value">${patient.gender}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-telephone me-2"></i>Contact</span>
          <span class="info-value">${patient.contact}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-briefcase me-2"></i>Occupation</span>
          <span class="info-value">${patient.occupation}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-heart me-2"></i>Marital Status</span>
          <span class="info-value">${patient.maritalStatus}</span>
        </div>
      </div>
      <div class="col-12">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-geo-alt me-2"></i>Residence</span>
          <span class="info-value">${patient.residence}</span>
        </div>
      </div>
    </div>

    <!-- Emergency Contact -->
    <div class="section-title mt-4">Emergency Contact</div>
    <div class="row g-3">
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-person-plus me-2"></i>Contact Name</span>
          <span class="info-value">${patient.emmergenceName}</span>
        </div>
      </div>
      <div class="col-md-6">
        <div class="info-group">
          <span class="info-label"><i class="bi bi-telephone me-2"></i>Contact Number</span>
          <span class="info-value">${patient.emmergencyContact}</span>
        </div>
      </div>
    </div>

    <!-- ===========================================================
         1.  VISIT  HISTORY
         =========================================================== -->
    <div class="section-title mt-4">Visit History</div>
    <div class="table-responsive">
      <table class="table table-hover align-middle mb-0">
        <thead class="table-light">
          <tr>
            <th>#</th>
            <th>Date</th>
            <th>Time</th>
            <th>Doctor</th>
            <th>Reason</th>
<!--             <th>Status</th> -->
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="v" items="${visits}" varStatus="loop">
               <tr>
               <td>${loop.index + 1}</td>
              <td>${v.visitDate}</td>
              <td>${v.visitTime}</td>
              <td>Dr ${v.attendingStaff.firstName} ${v.attendingStaff.lastName}</td>
              <td>${v.consultationType.name}</td>
<%--               <td><span class="badge bg-secondary">${v.status}</span></td> --%>
              <td><a href="${pageContext.request.contextPath}/visits/${v.id}" class="btn btn-sm btn-outline-primary">View</a></td>
            </tr>
          </c:forEach>
          <c:if test="${empty visits}">
            <tr><td colspan="7" class="text-center text-muted">No visits recorded yet.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>

    <!-- ===========================================================
         2.  FACTURE (INVOICE) HISTORY
         =========================================================== -->
    <div class="section-title mt-4">Invoice History</div>
    <div class="table-responsive">
      <table class="table table-hover align-middle mb-0">
        <thead class="table-light">
          <tr>
            <th>#</th>
            <th>Date</th>
            <th>Total</th>
            <th>Disc</th>
            <th>Net</th>
            <th>Paid</th>
            <th>Balance</th>
            <th>Status</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="f" items="${factures}" varStatus="loop">
               <tr>
                  <td>${loop.index + 1}</td>
              <td>${f.createdDate}</td>
              <td><fmt:formatNumber value="${f.totalAmount}" type="currency" currencyCode="XAF"/></td>
              <td>${f.discount}%</td>
              <td><fmt:formatNumber value="${f.netAmount}"  type="currency" currencyCode="XAF"/></td>
              <td><fmt:formatNumber value="${f.amountPaid}" type="currency" currencyCode="XAF"/></td>
              <td><fmt:formatNumber value="${f.balance}"    type="currency" currencyCode="XAF"/></td>
              <td>
                  <span class="badge ${f.fullyPaid ? 'bg-success' : 'bg-warning text-dark'}">${f.fullyPaid ? 'PAID' : 'PENDING'}</span>
              </td>
              <td>
                <a href="${pageContext.request.contextPath}/factures/receipt/${f.id}" target="_blank" class="btn btn-sm btn-dark" title="Print receipt">
                  <i class="bi bi-printer"></i>
                </a>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty factures}">
            <tr><td colspan="9" class="text-center text-muted">No invoices found.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>

    <!-- Previous Appointments (kept as-is) -->
    <div class="section-title mt-4">Previous Appointments</div>
    <div class="table-responsive">
      <table class="table table-hover align-middle mb-0">
        <thead class="table-light">
          <tr>
           <th></i>#</th>
            <th><i class="bi bi-calendar me-2"></i>Date</th>
            <th><i class="bi bi-check-circle me-2"></i>Reason</th>
            <th><i class="bi bi-person-badge me-2"></i>Doctor</th>
            <th><i class="bi bi-check-circle me-2"></i>Status</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="a" items="${appointments}" varStatus="loop">
               <tr>
                  <td>${loop.index + 1}</td>
              <td>${a.appointmentDate}</td>
              <td>${a.reason}</td>
              <td>Dr. ${a.doctor.firstName} ${a.doctor.lastName}</td>
              <td>${a.status}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <c:if test="${empty appointments}">
        <div class="text-center py-4 text-muted">No appointments recorded yet.</div>
      </c:if>
    </div>

  </div><!-- /profile-card -->
</main>

<!-- LOCAL Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>