<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Invoice List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"> --%>
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/icons/bootstrap-icons.css"> --%>
  <style>
    :root{--clr-primary:#6f42c1;--clr-light:#f8f9fa}
    body{background-color:var(--clr-light)}
    .card{border-radius:1rem;box-shadow:0 .125rem .25rem rgba(0,0,0,.075)}
    .badge{cursor:default}
  </style>
</head>
<body>

<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h5"><i class="bi bi-receipt text-primary me-2"></i>Invoice List</span>
    <div class="d-flex gap-2">
<%--       <a href="${pageContext.request.contextPath}/factures/new" class="btn btn-sm btn-outline-primary"><i class="bi bi-plus-circle"></i> New Invoice</a> --%>
      <a href="javascript:history.back()" class="btn btn-sm btn-outline-secondary"><i class="bi bi-arrow-left"></i> Back</a>
    </div>
  </div>
</nav>

<!-- =====  SEARCH CARD (unchanged) ===== -->
<main class="container py-1">
 <!-- =====  SEARCH BAR (JS driven)  ===== -->
<div class="card my-2">
  <div class="card-header d-flex justify-content-between align-items-center">
    <span class="fw-semibold">Filters</span>
    <button class="btn btn-sm btn-outline-secondary" type="button" data-bs-toggle="collapse" data-bs-target="#searchCard">
      <i class="bi bi-funnel"></i>
    </button>
  </div>
  <div class="collapse show" id="searchCard">
    <div class="card-body">
      <div class="row g-3">
        <div class="col-md-4">
          <label class="form-label">Patient name</label>
          <input type="text" id="nameFilter" class="form-control" placeholder="Type patient name">
        </div>
        <div class="col-md-3">
          <label class="form-label">From date</label>
          <input type="date" id="fromFilter" class="form-control">
        </div>
        <div class="col-md-3">
          <label class="form-label">To date</label>
          <input type="date" id="toFilter" class="form-control">
        </div>
        <div class="col-md-2 d-flex align-items-end">
          <button type="button" class="btn btn-outline-primary w-100" onclick="applyFilter()">
            <i class="bi bi-search"></i> Search
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- =====  QUICK STATUS BUTTONS  ===== -->
<div class="btn-group btn-group-sm my-2" role="group">
  <button class="btn btn-outline-secondary active" value="" onclick="setStatus('')">All</button>
<!--   <button class="btn btn-outline-secondary" value="PAID" onclick="setStatus('PAID')">Paid only</button> -->
  <button class="btn btn-outline-secondary" value="PENDING" onclick="setStatus('PENDING')">Pending only</button>
</div>
  <!-- =========  RESULT TABLE  ========= -->
  <!-- =====  RESULT TABLE  ===== -->
<div class="card shadow">
  <div class="card-header d-flex justify-content-between align-items-center">
    <span class="fw-semibold">Invoices</span>
    <span class="badge bg-secondary" id="countBadge">${factures.size()} record(s)</span>
  </div>
  <div class="card-body p-0">
    <div class="table-responsive">
      <table class="table table-hover align-middle mb-0">
        <thead class="table-light">
          <tr>
            <th>#</th> <th>Receipt #</th><th>Patient</th><th>Total</th><th>Disc</th>
            <th>Net</th><th>Paid</th><th>Balance</th>
           <th>Status</th><th>Date</th><th class="text-center">Actions</th>
          </tr>
        </thead>
        <tbody id="tableBody">
          <%-- initial load --%>
          <c:forEach var="f" items="${factures}" varStatus="loop">
               <tr>
              <td>${loop.index + 1}</td>
               <td>${not empty f.referenceNumber ? f.referenceNumber : '—'}</td>
               
                
              <td>${ not empty f.visit.patient.name ? f.visit.patient.name : f.customerName}</td>
              
              
              <td><fmt:formatNumber value="${f.totalAmount}" type="currency" currencyCode="XAF"/></td>
              <td>${f.discount}%</td>
              <td><fmt:formatNumber value="${f.netAmount}"  type="currency" currencyCode="XAF"/></td>
              <td><fmt:formatNumber value="${f.amountPaid}" type="currency" currencyCode="XAF"/></td>
              <td><fmt:formatNumber value="${f.balance}"    type="currency" currencyCode="XAF"/></td>
               <td>${f.createdDate}</td>
              <td><span class="badge ${f.fullyPaid ? 'bg-success' : 'bg-warning text-dark'}">${f.fullyPaid ? 'PAID' : 'PENDING'}</span></td>
              <td class="text-nowrap text-center">
              
              <c:if test="${!f.fullyPaid}">
             		 <a href="#" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('factures/${f.id}/payments/new')"  class="btn btn-sm btn-success" title="Add Payment"> <i class="bi bi-cash-coin"></i></a>
			</c:if>

                <a href="#" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('factures/${f.id}/view')"  class="btn btn-sm btn-info" title="View"><i class="bi bi-eye"></i></a>
                <a href="${pageContext.request.contextPath}/factures/receipt/${f.id}" target="_blank" class="btn btn-sm btn-dark" title="Print receipt"><i class="bi bi-printer"></i></a>
<%--                 <button class="btn btn-sm btn-danger" onclick="confirmDelete(${f.id})" title="Delete"><i class="bi bi-trash"></i></button> --%>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty factures}">
            <tr><td colspan="11" class="text-center text-muted">No invoices found.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</div>
</main>

<!-- LOCAL Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/hospital/visit.js"></script>

<script>
  function confirmDelete(id){
    if(confirm('Delete invoice #' + id + ' ?\nThis action cannot be undone.')){
      window.location = '${pageContext.request.contextPath}/factures/delete/' + id;
    }
  }
</script>
</body>
</html>