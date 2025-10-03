<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Today’s Sales</title>

    <!-- Bootstrap 5 CSS (remove if you already load it globally) -->
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"> -->

    <style>
        :root{--bs-primary:#0d6efd;--bs-success:#198754;--bs-danger:#dc3545;--bs-warning:#ffc107;}
        body{background-color:#f8f9fa;font-size:.9rem}
        .sale-card{border:none;border-radius:.75rem;transition:transform .2s}
        .sale-card:hover{transform:translateY(-2px)}
        .badge-large{font-size:.8rem;padding:.35em .65em}
        .sticky-footer{position:sticky;bottom:0;background-color:#fff;border-top:2px solid var(--bs-success);}
        @media (max-width: 767.98px){
            .mobile-only{display:block!important}
            .desktop-only{display:none!important}
        }
        @media (min-width: 768px){
            .mobile-only{display:none!important}
            .desktop-only{display:block!important}
        }
    </style>
</head>
<body>
<main class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2 class="h4 mb-0 text-primary">Today’s Sales</h2>
        <span class="badge bg-primary fs-6">${sales.size()} sales</span>
    </div>

    <!-- ======  Mobile cards  ====== -->
    <div class="row g-3 mobile-only">
        <c:forEach var="sale" items="${sales}">
            <div class="col-12">
                <div class="card sale-card shadow-sm p-3">
                    <div class="d-flex justify-content-between align-items-start mb-2">
                        <div>
                            <div class="text-muted small">Facture</div>
                            <div class="fw-bold">#${sale.factureId}</div>
                        </div>
                        <span class="badge badge-large
                            ${sale.paymentStatus eq 'PAID' ? 'bg-success' :
                              sale.paymentStatus eq 'PARTIAL' ? 'bg-warning text-dark' : 'bg-danger'}">
                            ${sale.paymentStatus}
                        </span>
                    </div>
                    <div class="row small">
                        <div class="col-6 mb-1"><span class="text-muted">Patient:</span><br>${sale.patientName}</div>
                        <div class="col-6 mb-1"><span class="text-muted">Reason:</span><br>${sale.reasonName != null ? sale.reasonName : 'N/A'}</div>
                        <div class="col-6"><span class="text-muted">Net:</span><br><b>${sale.netAmount} XAF</b></div>
                        <div class="col-6"><span class="text-muted">Paid:</span><br><b>${sale.paidAmount} XAF</b></div>
                        <div class="col-6"><span class="text-muted">Discount:</span><br>${sale.discount}%</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- ======  Desktop table  ====== -->
    <div class="table-responsive desktop-only">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Facture ID</th>
                    <th scope="col">Patient / Customer</th>
                    <th scope="col">Visit Reason</th>
                    <th scope="col" class="text-end">Net Amount (XAF)</th>
                    <th scope="col" class="text-end">Paid (XAF)</th>
                    <th scope="col" class="text-center">Discount (%)</th>
                    <th scope="col" class="text-center">Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sale" items="${sales}" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index + 1}</th>
                        <td>${sale.factureId}</td>
                        <td>${sale.patientName}</td>
                        <td>${sale.reasonName != null ? sale.reasonName : 'N/A'}</td>
                        <td class="text-end">${sale.netAmount}</td>
                        <td class="text-end">${sale.paidAmount}</td>
                        <td class="text-center">${sale.discount}</td>
                        <td class="text-center">
                            <span class="badge
                                ${sale.paymentStatus eq 'PAID' ? 'bg-success' :
                                  sale.paymentStatus eq 'PARTIAL' ? 'bg-warning text-dark' : 'bg-danger'}">
                                ${sale.paymentStatus}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- ======  Empty state  ====== -->
    <c:if test="${empty sales}">
        <div class="text-center py-5">
<!--             <img src="https://cdn-icons-png.flaticon.com/512/5911/5911466.png" alt="No sales" width="160"> -->
            <h5 class="text-muted mt-3">No sales recorded for today yet.</h5>
        </div>
    </c:if>

    <!-- ======  Sticky total bar  ====== -->
    <c:if test="${not empty sales}">
        <div class="sticky-footer p-3 mt-4 rounded-top">
            <div class="d-flex justify-content-between align-items-center">
                <span class="fw-bold text-success">TOTAL REVENUE</span>
                <span class="fw-bold fs-5">${totalRevenue} XAF</span>
            </div>
        </div>
    </c:if>
</main>

<!-- Bootstrap bundle (popper included) – remove if already loaded globally -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script> -->
</body>
</html>