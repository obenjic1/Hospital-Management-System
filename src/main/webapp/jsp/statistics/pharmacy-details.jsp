<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Pharmacy Daily Revenue</h3>
<table class="table table-bordered">
  <thead>
    <tr>
      <th>Date</th>
      <th>Total Revenue (CDF)</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="rev" items="${dailyRevenue}">
      <tr>
        <td>${rev.date}</td>
        <td>${rev.total}</td>
        <td>
          <a href="pharmacy/details?date=${rev.date}" class="btn btn-sm btn-primary">View Details</a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
