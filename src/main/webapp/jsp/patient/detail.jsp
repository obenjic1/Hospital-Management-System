<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
  <meta charset="UTF-8">
  <title>Patient Details</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap 5.3 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

  <!-- =====  CUSTOM CSS  ===== -->
  <style>
    :root{
      --clr-primary:#6f42c1;
      --clr-danger:#e55353;
      --clr-success:#1cc88a;
      --clr-light:#f8f9fa;
    }
    body{background-color:var(--clr-light);}
    .profile-card{
      border-radius:1rem;
      background:#fff;
      box-shadow:0 .125rem .25rem rgba(0,0,0,.075);
    }
    .section-title{
      font-size:1.1rem;
      font-weight:600;
      color:var(--clr-primary);
      border-bottom:2px solid var(--clr-primary);
      padding-bottom:.5rem;
      margin-bottom:1rem;
    }
    .info-group{display:flex;justify-content:space-between;align-items:center;padding:.5rem 0;border-bottom:1px solid #e9ecee;}
    .info-label{font-weight:600;color:#5f6f81;}
    .info-value{color:#212529;}
    .table th{background-color:var(--clr-light);color:#333;}
    @media (max-width: 576px){
      .info-group{flex-direction:column;align-items:start;}
    }
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
      <!-- optional actions -->
      <a href="javascript:history.back()" class="btn btn-sm btn-outline-secondary">
        <i class="bi bi-arrow-left"></i> Back
      </a>
      <!--
      <a href="${pageContext.request.contextPath}/patients/${patient.id}/history/pdf"
         class="btn btn-sm btn-outline-primary" target="_blank">
        <i class="bi bi-file-earmark-pdf"></i> PDF
      </a>
      -->
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

    <!-- Previous Appointments -->
    <div class="section-title mt-4">Previous Appointments</div>
    <div class="table-responsive">
      <table class="table table-hover align-middle mb-0">
        <thead class="table-light">
          <tr>
            <th><i class="bi bi-calendar me-2"></i>Date</th>
            <th><i class="bi bi-person-badge me-2"></i>Doctor</th>
            <th><i class="bi bi-check-circle me-2"></i>Status</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="a" items="${appointments}">
            <tr>
              <td><fmt:formatDate value="${a.appointmentDate}" pattern="dd-MM-yyyy"/></td>
              <td>Dr. ${a.doctor.firstName} ${a.doctor.lastName}</td>
              <td>
                <span class="badge
                  ${a.status=='Completed' ? 'bg-success' :
                a.status=='Cancelled' ? 'bg-danger' : 'bg-warning'}">
                  ${a.status}
                </span>
              </td>
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

<!-- =====  SCRIPTS  ===== -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


