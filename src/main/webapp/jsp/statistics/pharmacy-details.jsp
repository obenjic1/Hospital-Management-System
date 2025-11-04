<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"   %>

<div class="card shadow-sm">
  <div class="card-header d-flex justify-content-between align-items-center">
    <h4 class="mb-0">Pharmacy daily sales   ${sales[0].saleDate}</h4>
    <a href="javascript:window.print()" class="btn btn-outline-secondary btn-sm">Print</a>
  </div>

  <div class="card-body">
    <c:choose>
      <%-- No sales today --%>
      <c:when test="${empty sales}">
        <div class="alert alert-info">No sales recorded for this date.</div>
      </c:when>

      <%-- Sales exist --%>
      <c:otherwise>
        <%-- Grand totals --%>
        <c:set var="gross" value="${0}"/>
        <c:set var="paid"  value="${0}"/>
        <c:forEach var="s" items="${sales}">
          <c:set var="gross" value="${gross + s.totalAmount}"/>
          <c:set var="paid"  value="${paid  + s.amountPaid}"/>
        </c:forEach>

        <div class="row mb-2">
          <div class="col-md-2">
            <div class="border rounded p-2 text-center">
              <div class="text-muted small">Gross</div>
              <div class="h5"><fmt:formatNumber value="${gross}" type="currency" currencySymbol="CFA"/></div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="border rounded p-2 text-center">
              <div class="text-muted small">Paid</div>
              <div class="h5"><fmt:formatNumber value="${paid}" type="currency" currencySymbol="CFA "/></div>
            </div>
          </div>
        </div>

        <%-- Sale cards --%>
        <c:forEach var="sale" items="${sales}" varStatus="vs">
          <div class="border rounded mb-2">
            <div class="p-2 bg-light d-flex justify-content-between">
              <div>
                <span class="badge bg-primary">#${vs.index + 1}</span>
                <span class="ms-2">${sale.customerName}</span>
<%--                 <small class="text-muted">(${sale.customerContact})</small> --%>
              </div>
              <div>
                <span class="text-muted small">Cashier:</span> ${sale.pharmacist.fullName}
              </div>
            </div>

            <%-- Items table --%>
            <table class="table table-sm mb-0">
              <thead>
                <tr>
                  <th>Medicine</th>
                  <th class="text-end">Qty</th>
                  <th class="text-end">Unit Price</th>
                  <th class="text-end">Sub-total</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="item" items="${sale.items}">
                  <tr>
                    <td>${item.medicine.name}</td>
                    <td class="text-end">${item.quantity}</td>
                    <td class="text-end"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="CFA "/></td>
                    <td class="text-end"><fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="CFA "/></td>
                  </tr>
                </c:forEach>
              </tbody>
              <tfoot class="table-light">
                <tr>
                  <th colspan="3" class="text-end">Total</th>
                  <th class="text-end"><fmt:formatNumber value="${sale.totalAmount}" type="currency" currencySymbol="CFA "/></th>
                </tr>
                <c:if test="${sale.discount > 0}">
                  <tr>
                    <td colspan="3" class="text-end">Discount</td>
                    <td class="text-end">-<fmt:formatNumber value="${sale.discount}" type="percent"/></td>
                  </tr>
                  <tr>
                    <th colspan="3" class="text-end">Net</th>
                    <th class="text-end"><fmt:formatNumber value="${sale.netAmount}" type="currency" currencySymbol="CFA "/></th>
                  </tr>
                </c:if>
                <tr>
                  <td colspan="3" class="text-end">Paid</td>
                  <td class="text-end"><fmt:formatNumber value="${sale.facture.amountPaid}" type="currency" currencySymbol="CFA "/></td>
                </tr>
                <c:if test="${sale.balance > 0}">
                  <tr>
                    <td colspan="3" class="text-end">Balance</td>
                    <td class="text-end text-danger"><fmt:formatNumber value="${sale.balance}" type="currency" currencySymbol="CFA "/></td>
                  </tr>
                </c:if>
              </tfoot>
            </table>
          </div>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </div>
</div>