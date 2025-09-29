<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="modal-header bg-light">
    <h5 class="modal-title">Payment - Invoice #${facture.id}</h5>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
</div>

<div class="modal-body">
    <!-- =========  1.  INVOICE HEADER  ========= -->
    <h6 class="text-primary mb-2">Invoice Summary</h6>
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

    <!-- =========  2.  BILLED SUB-SERVICES  ========= -->
    <h6 class="text-primary mb-2">Billed Services</h6>
    <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered">
            <thead class="table-light">
                <tr>
                    <th>#</th>
                    <th>Service</th>
                    <th class="text-end">Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sub" items="${facture.visit.subtypes}" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${sub.name}</td>
                        <td class="text-end"><fmt:formatNumber value="${sub.price}" type="currency" currencyCode="XAF"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty facture.visit.subtypes}">
                    <tr><td colspan="3" class="text-center text-muted">No detailed services</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <!-- =========  3.  PREVIOUS PAYMENTS  (already done) ========= -->
    <h6 class="text-primary mb-2">Previous Payments</h6>
    <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered">
            <thead class="table-light">
                <tr>
                    <th>#</th>
                    <th>Date</th>
                    <th>Method</th>
                    <th>Amount</th>
                    <th>Reference</th>
                    <th class="text-center">Receipt</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${facture.payments}">
                    <tr>
                        <td>${p.id}</td>
                        <td><fmt:formatDate value="${p.paymentDate}" pattern="dd-MM-yyyy HH:mm"/></td>
                        <td>${p.method}</td>
                        <td><fmt:formatNumber value="${p.amountPaid}" type="currency" currencyCode="XAF"/></td>
                        <td>${p.reference}</td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/payments/receipt/${p.id}" target="_blank" class="btn btn-sm btn-outline-dark">
                                <i class="bi bi-printer"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty facture.payments}">
                    <tr><td colspan="6" class="text-center text-muted">No payments yet</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <!-- =========  4.  NEW PAYMENT FORM  (already done) ========= -->
    <h6 class="text-primary mb-2">Record New Payment</h6>
    <form id="paymentForm" class="row g-3">
        <input type="hidden" name="factureId" value="${facture.id}">

        <div class="col-md-4">
            <label class="form-label">Amount <span class="text-danger">*</span></label>
            <input type="number" class="form-control" name="amount" id="amount" step="0.01"
                   min="0.01" max="${facture.balance}" required
                   value="${facture.balance}">
        </div>

        <div class="col-md-4">
            <label class="form-label">Method <span class="text-danger">*</span></label>
            <select class="form-select" name="method" id="method" required onchange="toggleReference()">
                <option value="">-- Choose --</option>
                <option value="CASH">Cash</option>
                <option value="MOBILE_MONEY">Mobile Money</option>
                <option value="CARD">Card</option>
                <option value="INSURANCE">Insurance</option>
                <option value="CHEQUE">Cheque</option>
            </select>
        </div>

        <div class="col-md-4">
            <label class="form-label">Reference</label>
            <input type="text" class="form-control" name="reference" id="reference"
                   placeholder="Transaction / Cheque / Auth number">
        </div>

        <div class="col-12 d-flex justify-content-end gap-2">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-success">
                <i class="bi bi-cash-coin"></i> Record Payment
            </button>
        </div>
    </form>
</div>


<style>
    .modal-body { background-color: #f8f9fa; }
    .card-body { padding: 0.75rem; }
    .table-bordered th { background-color: #e9ecef; }
</style>