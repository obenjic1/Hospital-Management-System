<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Facture Item Sales Report</title>
    <style>
        body { font-family: Inter, sans-serif; background: #f8f9fc; color: #333; }
        h2 { margin: 1rem 0; }
        table { width: 100%; border-collapse: collapse; background: #fff; border-radius: 8px; overflow: hidden; }
        th, td { padding: 10px; border-bottom: 1px solid #eee; }
        th { background: #f4f4f4; text-transform: uppercase; font-size: 12px; color: #666; }
        tr:hover { background: #f9f9f9; }
        .paid { color: green; font-weight: bold; }
        .pending { color: red; font-weight: bold; }
        .total-row { background: #f4f4f4; font-weight: bold; }
        form { margin-bottom: 1rem; }
    </style>
</head>
<body>

<h2>Sales Report for  : ${date}</h2>

<form>
<div class="row">
<div class="col-md-1"><label>Select date:</label></div>
<div class="col-md-4"> 
    <input type="date" name="date" id="date"  value="${date}" class="form-control" width="50%"></div>
<div class="col-md-4"> <button type="button" onclick="searchStats()" class="btn btn-outline-primary">Search</button></div>




</div>
   
   
</form>

<table>
    <thead>
        <tr>
            <th>#</th>
            <th>Facture ID</th>
            <th>Customer</th>
            <th>Reason</th>
            <th>Item</th>
            <th>Price</th>
            <th>Qty</th>
            <th>Total</th>
<!--             <th>Paid</th> -->
<!--             <th>Discount</th> -->
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="s" items="${sales}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${s.factureRef}</td>
                <td>${s.customer}</td>
                <td>${s.reason}</td>
                <td>${s.name}</td>
                <td><fmt:formatNumber value="${s.price}" type="number"/></td>
                <td>${s.quantity}</td>
                <td><fmt:formatNumber value="${s.total}" type="number"/></td>
<%--                 <td><fmt:formatNumber value="${s.amountPaid}" type="number"/></td> --%>
<%--                 <td><fmt:formatNumber value="${s.discount}" type="number" /></td> --%>
                <td>
                    <c:choose>
                        <c:when test="${s.status eq 'PAID'}"><span class="paid">PAID</span></c:when>
                        <c:otherwise><span class="pending">PENDING</span></c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>

        <tr class="total-row">
            <td colspan="7">TOTAL REVENUE</td>
            <td colspan="4"><fmt:formatNumber value="${totalRevenue}" type="number"/> XAF</td>
        </tr>
    </tbody>
</table>
<script src="assets/js/hospital/visit.js"></script>

</body>
</html>
