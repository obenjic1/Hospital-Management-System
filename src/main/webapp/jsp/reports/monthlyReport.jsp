
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<h3>Monthly Report ${month } - ${year}  </h3>

<table class="table table-bordered table-hover shadow-sm">
    <thead class="table-dark">
        <tr>
       		 <th>No</th>
            <th>Date</th>
            <th>Total Sales (Pharmacy + Services)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entry" items="${dailyReport}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
        <tr class="table-primary">
            <td><strong></strong></td>
            <td><strong>Total</strong></td>
            <td><strong>${totalRevenue}</strong></td>
        </tr>
    </tbody>
</table>
		