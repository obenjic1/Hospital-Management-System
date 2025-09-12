<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Doctor Revenue Summary</title>

  
</head>
<body class="container mt-2">

<h2 class="mb-4">Doctor Revenue Summary</h2>

<div class="row row-cols-1 row-cols-md-3 g-4 mb-2">

    <!-- Total Revenue Card -->
    <div class="col">
        <div class="card border-dark h-100 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">💰 Total Revenue</h5>
                <p class="card-text fs-4 text-dark fw-bold">
                    $<fmt:formatNumber value="${summary.totalRevenue}" type="number" maxFractionDigits="2"/>
                </p>
            </div>
        </div>
    </div>

    <!-- Doctor Payout Card -->
    <div class="col">
        <div class="card border-danger h-100 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">🧑‍⚕️ Doctor Payout</h5>
                <p class="card-text fs-4 text-danger fw-bold">
                    $<fmt:formatNumber value="${summary.totalDoctorPayout}" type="number" maxFractionDigits="2"/>
                </p>
            </div>
        </div>
    </div>

    <!-- Profit Card -->
    <div class="col">
        <div class="card border-success h-100 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">📈 Profit</h5>
                <p class="card-text fs-4 text-success fw-bold">
                    $<fmt:formatNumber value="${summary.profit}" type="number" maxFractionDigits="2"/>
                </p>
            </div>
        </div>
    </div>

</div>

<!-- Month and Year selection form -->
<form class="row g-3 mb-1" method="get" action="/doctor-revenue">
<%
    List<String> months = Arrays.asList(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    );
    request.setAttribute("months", months);
%>
<div class="col-4">
<label for="month" class="form-label">Month</label>
<select class="form-select" id="month" name="month" required>
    <c:forEach var="m" begin="1" end="12" varStatus="status">
        <option value="${m}" <c:if test="${m == defaultMonth}">selected</c:if>>
            ${months[status.index]}
        </option>
    </c:forEach>
</select>
</div>
    <div class="col-4">
        <label for="year" class="form-label">Year</label>
        <select class="form-select" id="year" name="year" required>
            <c:forEach var="y" begin="${defaultYear - 5}" end="${defaultYear + 5}">
                <option value="${y}" <c:if test="${y == defaultYear}">selected</c:if>>
                    ${y}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="col-3 ">
        <button type="submit" class="btn btn-outline-primary">View Report</button>
    </div>

</form>

<c:choose>
    <c:when test="${empty summary.doctorStats}">
        <div class="alert alert-warning" role="alert">
            No revenue data available for the selected month and year.
        </div>
    </c:when>

    <c:otherwise>

        <!-- Summary section -->
        

        <!-- Revenue Table -->
        <table class="table table-bordered table-striped">
            <thead class="table-light">
                <tr>
               		 <th>#</th>	
                    <th>Doctor Name</th>
                    <th>Consultation Count</th>
                    <th>Total Revenue</th>
                    <th>Doctor Percentage (%)</th>
                    <th>Amount to Pay</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="doctor" items="${summary.doctorStats}"  varStatus="loop">
               <tr>
                  <td>${loop.index + 1}</td>
                        <td>${doctor.doctorName}</td>
                        <td>${doctor.consultationCount}</td>
                        <td><fmt:formatNumber value="${doctor.totalRevenue}" type="number" maxFractionDigits="2"/> CFA</td>
                        <td>${doctor.doctorPercentage}</td>
                        <td class="text-danger"><fmt:formatNumber value="${doctor.amountToPay}" type="number" maxFractionDigits="2"/> CFA</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>

</body>
</html>
