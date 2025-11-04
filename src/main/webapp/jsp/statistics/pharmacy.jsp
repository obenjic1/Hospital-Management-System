<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Pharmacy Daily Revenue</h3>

<c:if test=""></c:if>
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
          <div class="stat-icon bg-gradient-exp drugs" title="view expired drugs"     data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('pharmacy/pharmacy/details?date=${rev.date}')">
          <button class="btn btn-sm btn-outline-primary" title="view sales Details"><i class="bi bi-exclamation-triangle">View Details</i></button></div>
          
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
