<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<h2>Consultations Report - ${month}/${year}</h2>

<h3>Summary by Doctor</h3>
<table class="table table-bordered table-hover shadow-sm" style="text-align: center;">
        <thead class="table-dark">
    <tr>
    	 <th>Number</th>
        <th>Doctor</th>
        <th>Number of Consultations</th>
    </tr>
    </thead>
    <c:forEach var="entry" items="${doctorCounts}" varStatus="loop">
             <tr>
             <td>${loop.index + 1}</td>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<h3>Detailed List</h3>
<table class="table table-bordered table-hover shadow-sm" style="text-align: center;">
        <thead class="table-dark">
        <tr>
     	<th>Number</th>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Amount</th>
        <th>Date</th>
        </tr>
    </thead>
    <c:forEach var="consultation" items="${consultations}"  varStatus="loop">
             <tr>
             <td>${loop.index + 1}</td>
            <td>${consultation.patient.name}</td>
            <td>${consultation.doctor.firstName}</td>
            <td>${consultation.amountPaid}</td>
            <td>${consultation.consultationDate}</td>
        </tr>
    </c:forEach>
</table>
</html>

