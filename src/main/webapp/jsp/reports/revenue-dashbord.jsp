<h3>Monthly Report </h3>

<table class="table table-bordered table-hover shadow-sm text-align-center">
    <thead class="table-dark">
        <tr>
            <th>Date</th>
            <th>Total Sales (Pharmacy + Services)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entry" items="${dailyReport}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
        <tr class="table-primary">
            <td><strong>Total</strong></td>
            <td><strong>${totalRevenue}</strong></td>
        </tr>
    </tbody>
</table>
