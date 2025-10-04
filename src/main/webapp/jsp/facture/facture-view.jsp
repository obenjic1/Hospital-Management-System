<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
  <meta charset="UTF-8">
</head>
<style>
    .modal-body { background-color: #f8f9fa; }
    .card-body  { padding: 0.75rem; }
    .table-bordered th { background-color: #e9ecef; }
    @media print {
        .no-print, .modal-header, .modal-footer { display:none !important; }
        .modal-body { background: #fff !important; }
    }
</style>

<div class="modal-header bg-light no-print">
    <div class="d-flex align-items-center gap-3 w-100">
        <%-- STATUS BADGE --%>
        <h5 class="mb-0">
            <span class="badge ${facture.fullyPaid ? 'bg-success' : 'bg-warning'} fs-6">
                ${facture.status}
            </span>
        </h5>

        <%-- INVOICE NUMBER --%>
        <div class="fw-bold text-primary me-auto">Invoice #${facture.referenceNumber}</div>

        <%-- QUICK ACTIONS --%>
        <button class="btn btn-sm btn-outline-secondary" onclick="printView('${facture.referenceNumber}')" title="Print">
            <i class="bi bi-printer"></i>
        </button>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
</div>

<div class="modal-body" id="factureContent">
    <%-- ===== 1.  INVOICE SUMMARY ===== --%>
    <h6 class="text-primary mb-2">Invoice Summary</h6>
    <div class="row my-3 text-center">
       <div class="row my-3 text-center">
    <div class="col">
        <div class="card border-primary">
            <div class="card-body py-2">
                <div class="fw-bold">Total</div>
                <div class="fs-5"><fmt:formatNumber value="${facture.totalAmount}" type="currency" currencyCode="XAF"/></div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card border-info">
            <div class="card-body py-2">
                <div class="fw-bold">Discount</div>
                <div class="fs-5">${facture.discount}%</div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card border-success">
            <div class="card-body py-2">
                <div class="fw-bold">Net</div>
                <div class="fs-5"><fmt:formatNumber value="${facture.netAmount}" type="currency" currencyCode="XAF"/></div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card border-warning">
            <div class="card-body py-2">
                <div class="fw-bold">Paid</div>
                <div class="fs-5"><fmt:formatNumber value="${facture.amountPaid}" type="currency" currencyCode="XAF"/></div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card border-danger">
            <div class="card-body py-2">
                <div class="fw-bold">Balance</div>
                <div class="fs-5"><fmt:formatNumber value="${facture.balance}" type="currency" currencyCode="XAF"/></div>
            </div>
        </div>
    </div>
</div>
    </div>

<c:if test="${empty facture.visit}">
  <h6 class="text-primary mb-2">Pharmacy Bills</h6>
    <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered">
            <thead class="table-light"><tr><th>#</th><th>Medicine name</th><th>Qty</th><th>unit Price</th><th class="text-end">SubTotal</th></tr></thead>
            <tbody>
                <c:forEach var="sub" items="${facture.items}" varStatus="vs">
                    <tr>
                        <td>${sub.id}</td>
                        <td>${sub.description}</td>
                        <td>${sub.quantity}</td>
                         <td>${sub.unitPrice}</td>
                        <td class="text-end"><fmt:formatNumber value="${sub.subTotal}" type="currency" currencyCode="XAF"/></td>
                    </tr>
                </c:forEach>
              
            </tbody>
        </table>
    </div>
 </c:if>
 <c:if test="${not empty facture.visit}">
  <h6 class="text-primary mb-2">Billed Services</h6>
    <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered">
            <thead class="table-light"><tr><th>#</th><th>Service</th><th class="text-end">Price</th></tr></thead>
            <tbody>
                <c:forEach var="sub" items="${facture.visit.subtypes}" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${sub.name}</td>
                        <td class="text-end"><fmt:formatNumber value="${sub.price}" type="currency" currencyCode="XAF"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty facture.visit.subtypes}">
                                    <tr><td colspan="6" class="text-center text-muted">No Billed Services recorded</td></tr>
                
                </c:if>
            </tbody>
        </table>
    </div>
 </c:if>
 
    

    <%-- ===== 3.  PAYMENT HISTORY ===== --%>
    <h6 class="text-primary mb-2">Payment History</h6>
    <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered">
            <thead class="table-light">
                <tr><th>#</th><th>Date</th><th>Method</th><th>Amount</th><th>Ref</th><th class="text-center">Receipt</th></tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${facture.payments}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.paymentDate}</td>
                        <td>${p.method}</td>
                        <td><fmt:formatNumber value="${p.amountPaid}" type="currency" currencyCode="XAF"/></td>
                        <td>${p.reference}</td>
                        
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/payments/receipt/${p.id}" target="_blank"
                               class="btn btn-sm btn-dark"><i class="bi bi-printer"></i></a>

                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty facture.payments}">
                    <tr><td colspan="6" class="text-center text-muted">No payments recorded</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <%-- ===== 4.  PATIENT & VISIT INFO ===== --%>
    <h6 class="text-primary mb-2">Patient & Visit Details</h6>
    <div class="row g-2 mb-2">
      <c:if test="${empty facture.visit}">
              <div class="col-md-3"><label class="form-label mb-0">Patient</label><div class="fw-bold">${facture.customerName}</div></div>
      
      </c:if>
      <c:if test="${not empty facture.visit}">
              <div class="col-md-3"><label class="form-label mb-0">Patient</label><div class="fw-bold">${facture.visit.patient.name}</div></div>
      
      </c:if>
      
        <div class="col-md-2"><label class="form-label mb-0">Age / Sex</label><div class="fw-bold">${facture.visit.patient.age} / ${facture.visit.patient.gender}</div></div>
        <div class="col-md-2"><label class="form-label mb-0">Visit Date</label><div class="fw-bold">${facture.visit.visitDate}</div></div>
        <div class="col-md-2"><label class="form-label mb-0">Doctor</label><div class="fw-bold">${facture.visit.attendingStaff.firstName} ${facture.visit.attendingStaff.lastName}</div></div>
        <div class="col-md-3"><label class="form-label mb-0">Invoice Date</label><div class="fw-bold">${facture.createdDate}</div></div>
    </div>

</div>

<div class="modal-footer bg-light no-print">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    <button type="button" class="btn btn-outline-primary" onclick="printView('${facture.referenceNumber}')">
        <i class="bi bi-printer"></i> Print
    </button>
</div>
<script src="assets/js/hospital/html2pdf.bundle.min.js"></script>
<script src="assets/js/hospital/visit.js"></script>
