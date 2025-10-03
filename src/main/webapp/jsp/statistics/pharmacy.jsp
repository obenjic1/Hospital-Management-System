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
          <button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPage('pharmacy/pharmacy/details?date=${rev.date}"  class="btn btn-sm btn-outline-primary">View Details</button>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
